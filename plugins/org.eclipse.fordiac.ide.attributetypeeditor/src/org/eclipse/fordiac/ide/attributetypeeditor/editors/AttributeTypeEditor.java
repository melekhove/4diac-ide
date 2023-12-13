/*******************************************************************************
 * Copyright (c) 2020, 2023 Johannes Kepler University, Linz,
 *                          Primetals Technologies Austria GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Daniel Lindhuber, Bianca Wiesmayr
 *     - initial API and implementation and/or initial documentation
 *   Muttenthaler Benjamin
 *     - fixed reload of view if file on file system did change
 *     - use new saveType method of AbstractTypeExporter
 *     - replaced DataTypeListener by AdapterImpl
 *     - keep a copy of the datatype object in the view, otherwise the content of the file is changed even the save button was not pressed
 *   Lukas Wais
 *     - enabled Save As
 *   Sebastian Hollersbacher
 *     - Changed from DataTypeEditor to AttributeTypeEditor
 *******************************************************************************/

package org.eclipse.fordiac.ide.attributetypeeditor.editors;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.fordiac.ide.attributetypeeditor.Messages;
import org.eclipse.fordiac.ide.datatypeeditor.widgets.StructEditingComposite;
import org.eclipse.fordiac.ide.gef.annotation.FordiacMarkerGraphicalAnnotationModel;
import org.eclipse.fordiac.ide.gef.annotation.GraphicalAnnotationModel;
import org.eclipse.fordiac.ide.model.data.StructuredType;
import org.eclipse.fordiac.ide.model.libraryElement.INamedElement;
import org.eclipse.fordiac.ide.model.libraryElement.LibraryElementPackage;
import org.eclipse.fordiac.ide.model.typelibrary.AttributeTypeEntry;
import org.eclipse.fordiac.ide.model.typelibrary.TypeLibraryManager;
import org.eclipse.fordiac.ide.systemmanagement.changelistener.IEditorFileChangeListener;
import org.eclipse.fordiac.ide.ui.FordiacLogHelper;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackEvent;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.RedoAction;
import org.eclipse.gef.ui.actions.UndoAction;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class AttributeTypeEditor extends EditorPart implements CommandStackEventListener,
		ITabbedPropertySheetPageContributor, ISelectionListener, IEditorFileChangeListener {

	private final CommandStack commandStack = new CommandStack();
	private GraphicalAnnotationModel annotationModel;
	private StructEditingComposite structComposite;
	private Composite errorComposite;
	private boolean importFailed;
	private boolean outsideWorkspace;

	private ActionRegistry actionRegistry;
	private final List<String> selectionActions = new ArrayList<>();
	private final List<String> stackActions = new ArrayList<>();
	private final List<String> propertyActions = new ArrayList<>();

	private AttributeTypeEntry attributeTypeEntry;

	private final Adapter adapter = new AdapterImpl() {
		@Override
		public void notifyChanged(final Notification notification) {
			super.notifyChanged(notification);
			final Object feature = notification.getFeature();
			if ((null != feature)
					&& (LibraryElementPackage.LIBRARY_ELEMENT__NAME == notification.getFeatureID(feature.getClass()))) {
				Display.getDefault().asyncExec(() -> {
					if (null != attributeTypeEntry) {
						// input should be set before the partname
						setInput(new FileEditorInput(attributeTypeEntry.getFile()));
						setPartName(attributeTypeEntry.getFile().getName());
					}
				});
			}
		}
	};

	@Override
	public void stackChanged(final CommandStackEvent event) {
		updateActions(stackActions);
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	@Override
	public String getContributorId() {
		return "org.eclipse.fordiac.ide.attributetypeeditor.editors.AttributeTypeEditor"; //$NON-NLS-1$
	}

	@Override
	public void dispose() {
		// get these values here before calling super dispose
		final boolean dirty = isDirty();

		getCommandStack().removeCommandStackEventListener(this);
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
		getActionRegistry().dispose();
		removeListenerFromAttributeDeclaration();
		if (annotationModel != null) {
			annotationModel.dispose();
		}
		super.dispose();
		if (dirty && attributeTypeEntry != null) {
			// purge editable type from type entry after super.dispose() so that no
			// notifiers will be called
			attributeTypeEntry.setTypeEditable(null);
		}
	}

	@Override
	protected void firePropertyChange(final int property) {
		super.firePropertyChange(property);
		updateActions(propertyActions);
	}

	@Override
	public void doSave(final IProgressMonitor monitor) {
		removeListenerFromAttributeDeclaration();
		loadAllOpenEditors();
		doSaveInternal(monitor, null);
	}

	private void doSaveInternal(final IProgressMonitor monitor, final Set<INamedElement> set) {
		commandStack.markSaveLocation();
		final WorkspaceModifyOperation operation = new WorkspaceModifyOperation(
				attributeTypeEntry.getFile().getParent()) {

			@Override
			protected void execute(final IProgressMonitor monitor)
					throws CoreException, InvocationTargetException, InterruptedException {
				attributeTypeEntry.save(monitor);
			}
		};
		try {
			operation.run(monitor);
		} catch (final InvocationTargetException e) {
			FordiacLogHelper.logError(e.getMessage(), e);
		} catch (final InterruptedException e) {
			FordiacLogHelper.logError(e.getMessage(), e);
			Thread.currentThread().interrupt();
		}
		addListenerToAttributeDeclaration();
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	@Override
	public void doSaveAs() {
		// nothing here yet
	}

	private static void loadAllOpenEditors() {
		final IEditorReference[] openEditors = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getEditorReferences();
		for (final IEditorReference iEditorReference : openEditors) {
			iEditorReference.getEditor(true);
		}
	}

	@Override
	public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
		importType(input);
		setInput(input);
		setSite(site);
		addListenerToAttributeDeclaration();
		site.getWorkbenchWindow().getSelectionService().addSelectionListener(this);
		getCommandStack().addCommandStackEventListener(this);
		initializeActionRegistry();
		setActionHandlers(site);
		if (input instanceof final IFileEditorInput fileEditorInput) {
			annotationModel = new FordiacMarkerGraphicalAnnotationModel(fileEditorInput.getFile());
		}
	}

	@Override
	public String getTitleToolTip() {
		final String tooltip = (attributeTypeEntry != null) ? attributeTypeEntry.getFullTypeName() + "\n" : ""; //$NON-NLS-1$ //$NON-NLS-2$
		return tooltip + super.getTitleToolTip();
	}

	private void addListenerToAttributeDeclaration() {
		if (attributeTypeEntry != null && attributeTypeEntry.getTypeEditable() != null) {
			attributeTypeEntry.getTypeEditable().eAdapters().add(adapter);
		}
	}

	private void removeListenerFromAttributeDeclaration() {
		if (attributeTypeEntry != null && attributeTypeEntry.getTypeEditable() != null
				&& attributeTypeEntry.getTypeEditable().eAdapters().contains(adapter)) {
			attributeTypeEntry.getTypeEditable().eAdapters().remove(adapter);
		}
	}

	private void importType(final IEditorInput input) throws PartInitException {
		if (input instanceof final FileEditorInput fileEditorInput) {
			final IFile file = fileEditorInput.getFile();
			try {
				importFailed = importTypeBasedOnFile(file);
			} catch (final Exception e) {
				throw new PartInitException(e.getMessage(), e);
			}
		} else if (input instanceof FileStoreEditorInput) {
			// is called when files are opened via File -> Open File
			importFailed = true;
			outsideWorkspace = true;
		}
	}

	private boolean importTypeBasedOnFile(final IFile file) throws CoreException {
		file.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
		// exist anymore!
		if (file.exists()) {
			attributeTypeEntry = (AttributeTypeEntry) TypeLibraryManager.INSTANCE.getTypeEntryForFile(file);
			setPartName(attributeTypeEntry.getFile().getName());
			return attributeTypeEntry.getTypeEditable() == null;
		}
		return true; // import failed
	}

	private void setActionHandlers(final IEditorSite site) {
		final ActionRegistry registry = getActionRegistry();
		final IActionBars bars = site.getActionBars();
		String id = ActionFactory.UNDO.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		id = ActionFactory.REDO.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		id = ActionFactory.DELETE.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		bars.updateActionBars();
	}

	@Override
	public boolean isDirty() {
		return getCommandStack().isDirty();
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(final Composite parent) {
		if (attributeTypeEntry != null
				&& attributeTypeEntry.getTypeEditable().getType() instanceof final StructuredType structType
				&& !importFailed) {
			structComposite = new StructEditingComposite(parent, commandStack, structType, annotationModel);
			getSite().setSelectionProvider(structComposite);
			structComposite.setTitel(Messages.StructViewingComposite_Headline);
		} else if (importFailed) {
			createErrorComposite(parent, Messages.ErrorCompositeMessage);
			if (outsideWorkspace) {
				MessageDialog.openError(getSite().getShell().getShell(),
						Messages.MessageDialogTitle_OutsideWorkspaceError,
						Messages.MessageDialogContent_OutsideWorkspaceError);
			}
		}
	}

	private void createErrorComposite(final Composite parent, final String errorText) {
		errorComposite = new Composite(parent, SWT.NONE);
		errorComposite.setLayout(new GridLayout(1, false));
		final Label label = new Label(errorComposite, SWT.CENTER);
		label.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.HEADER_FONT));
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		label.setText(errorText);
	}

	@Override
	public void setFocus() {
		if (null == structComposite) {
			errorComposite.setFocus();
		} else {
			structComposite.setFocus();
		}
	}

	public CommandStack getCommandStack() {
		return commandStack;
	}

	@Override
	public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
		if (this.equals(getSite().getPage().getActiveEditor())) {
			updateActions(selectionActions);
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
	}

	private void createActions() {
		final ActionRegistry registry = getActionRegistry();
		IAction action;

		action = new UndoAction(this);
		registry.registerAction(action);
		getStackActions().add(action.getId());

		action = new RedoAction(this);
		registry.registerAction(action);
		getStackActions().add(action.getId());
	}

	@Override
	public <T> T getAdapter(final Class<T> key) {
		if (key == org.eclipse.ui.views.properties.IPropertySheetPage.class) {
			return key.cast(new TabbedPropertySheetPage(this));
		}
		if (key == CommandStack.class) {
			return key.cast(getCommandStack());
		}
		if (key == ActionRegistry.class) {
			return key.cast(getActionRegistry());
		}
		if (key == GraphicalAnnotationModel.class) {
			return key.cast(annotationModel);
		}

		return super.getAdapter(key);
	}

	private List<String> getStackActions() {
		return stackActions;
	}

	private void initializeActionRegistry() {
		createActions();
		updateActions(propertyActions);
		updateActions(stackActions);
	}

	private void updateActions(final List<String> actionIds) {
		final ActionRegistry registry = getActionRegistry();
		actionIds.forEach(id -> {
			final IAction action = registry.getAction(id);
			if (action instanceof final UpdateAction updateAction) {
				updateAction.update();
			}
		});
	}

	private ActionRegistry getActionRegistry() {
		if (null == actionRegistry) {
			actionRegistry = new ActionRegistry();
		}
		return actionRegistry;
	}

	@Override
	public void reloadFile() {
		try {
			removeListenerFromAttributeDeclaration();
			attributeTypeEntry.setTypeEditable(null);
			importType(getEditorInput());
			if (attributeTypeEntry.getTypeEditable() != null
					&& attributeTypeEntry.getTypeEditable().getType() instanceof final StructuredType structType) {
				structComposite.setStructType(structType);
			}
			commandStack.flush();
			addListenerToAttributeDeclaration();
		} catch (final PartInitException e) {
			FordiacLogHelper
					.logError("Error during refreshing struct table after file change detection: " + e.toString(), e); //$NON-NLS-1$
		}

	}

	@Override
	public IFile getFile() {
		Assert.isNotNull(((FileEditorInput) getEditorInput()).getFile());
		return ((FileEditorInput) getEditorInput()).getFile();
	}

	@Override
	public void updateEditorInput(final FileEditorInput newInput) {
		setInput(newInput);
		firePropertyChange(IWorkbenchPart.PROP_TITLE);
	}
}
