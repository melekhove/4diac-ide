/*******************************************************************************
 * Copyright (c) 2012 - 2016 Profactor GbmH, fortiss GmbH
 * 				 2019 Johannes Kepler University Linz
 * 				 2022 Primetals Technologies Austria GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Gerhard Ebenhofer, Alois Zoitl
 *     - initial API and implementation and/or initial documentation
 *   Alois Zoitl - added removing the watch listener on dispose
 *   Fabio Gandolfi - added selection handling & context menu
 *******************************************************************************/
package org.eclipse.fordiac.ide.monitoring.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.fordiac.ide.deployment.monitoringbase.IMonitoringListener;
import org.eclipse.fordiac.ide.deployment.monitoringbase.MonitoringBaseElement;
import org.eclipse.fordiac.ide.deployment.monitoringbase.PortElement;
import org.eclipse.fordiac.ide.model.data.EventType;
import org.eclipse.fordiac.ide.model.libraryElement.FB;
import org.eclipse.fordiac.ide.model.libraryElement.FBNetwork;
import org.eclipse.fordiac.ide.model.libraryElement.FBNetworkElement;
import org.eclipse.fordiac.ide.model.libraryElement.Group;
import org.eclipse.fordiac.ide.model.libraryElement.SubApp;
import org.eclipse.fordiac.ide.model.libraryElement.VarDeclaration;
import org.eclipse.fordiac.ide.model.monitoring.MonitoringElement;
import org.eclipse.fordiac.ide.monitoring.Messages;
import org.eclipse.fordiac.ide.monitoring.MonitoringManager;
import org.eclipse.fordiac.ide.monitoring.handlers.ClearForceHandler;
import org.eclipse.fordiac.ide.monitoring.handlers.ForceHandler;
import org.eclipse.fordiac.ide.monitoring.provider.WatchesCommentLabelProvider;
import org.eclipse.fordiac.ide.monitoring.provider.WatchesContentProvider;
import org.eclipse.fordiac.ide.monitoring.provider.WatchesNameLabelProvider;
import org.eclipse.fordiac.ide.monitoring.provider.WatchesTypeLabelProvider;
import org.eclipse.fordiac.ide.monitoring.provider.WatchesValueEditingSupport;
import org.eclipse.fordiac.ide.monitoring.provider.WatchesValueLabelProvider;
import org.eclipse.fordiac.ide.ui.imageprovider.FordiacImage;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.ViewPart;

public class WatchesView extends ViewPart implements ISelectionListener {

	private Composite root;
	private FilteredTree filteredTree;
	private final WatchesContentProvider provider = new WatchesContentProvider();
	private boolean visible = false;
	private boolean selectionActive = false;
	private Action toggleSelection;

	private final IMonitoringListener listener = new IMonitoringListener() {

		@Override
		public void notifyTriggerEvent(final PortElement port) {
			// currently nothing to do
		}

		@Override
		public void notifyRemovePort(final PortElement port) {
			filteredTree.getViewer().refresh();
		}

		@Override
		public void notifyAddPort(final PortElement port) {
			if (!filteredTree.isDisposed()) {
				filteredTree.getViewer().refresh();
			}
		}

		@Override
		public void notifyWatchesChanged() {
			provider.update();
			if (!filteredTree.isDisposed()) {
				filteredTree.getViewer().refresh();
			}
		}
	};

	@Override
	public void createPartControl(final Composite parent) {

		root = new Composite(parent, SWT.NONE);
		root.setLayout(new GridLayout());
		final PatternFilter patternFilter = new PatternFilter();

		filteredTree = new FilteredTree(root, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION,
				patternFilter, true,
				true);

		final GridData treeGridData = new GridData();
		treeGridData.grabExcessHorizontalSpace = true;
		treeGridData.grabExcessVerticalSpace = true;
		treeGridData.horizontalAlignment = SWT.FILL;
		treeGridData.verticalAlignment = SWT.FILL;

		filteredTree.setLayoutData(treeGridData);

		createNameColumn();
		createValueColumn();
		createTypeColumn();
		createCommentColumn();

		filteredTree.getViewer().getTree().setHeaderVisible(true);
		filteredTree.getViewer().getTree().setLinesVisible(true);

		filteredTree.getViewer().setContentProvider(provider);

		filteredTree.getViewer().setInput(new Object());

		final Menu contextMenu = createContextMenu();
		filteredTree.getViewer().getControl().setMenu(contextMenu);

		contributeToActionBars();

		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
		getSite().getPage().addPartListener(new IPartListener2() {
			@Override
			public void partHidden(final IWorkbenchPartReference ref) {
				final IWorkbenchPart part = ref.getPart(false);
				if (part instanceof WatchesView) {
					visible = false;
				}
			}

			@Override
			public void partVisible(final IWorkbenchPartReference ref) {
				final IWorkbenchPart part = ref.getPart(false);
				if (part instanceof WatchesView) {
					visible = true;
				}
			}
		});

		addWatchesAdapters();
	}

	private void createNameColumn() {
		final TreeViewerColumn nameColumn = new TreeViewerColumn(filteredTree.getViewer(), SWT.None);
		nameColumn.getColumn().setText(Messages.MonitoringWatchesView_WatchedElement);
		nameColumn.getColumn().setWidth(340);
		nameColumn.setLabelProvider(new WatchesNameLabelProvider());
	}

	private void createValueColumn() {
		final TreeViewerColumn valueColumn = new TreeViewerColumn(filteredTree.getViewer(), SWT.None);
		valueColumn.getColumn().setText(Messages.MonitoringWatchesView_Value);
		valueColumn.getColumn().setWidth(100);
		valueColumn.setLabelProvider(new WatchesValueLabelProvider());
		valueColumn.setEditingSupport(
				new WatchesValueEditingSupport(valueColumn.getViewer(), filteredTree.getViewer().getTree()));
	}

	private void createTypeColumn() {
		final TreeViewerColumn typeColumn = new TreeViewerColumn(filteredTree.getViewer(), SWT.None);
		typeColumn.getColumn().setText(Messages.MonitoringWatchesView_Type);
		typeColumn.getColumn().setWidth(100);
		typeColumn.setLabelProvider(new WatchesTypeLabelProvider());
	}

	private void createCommentColumn() {
		final TreeViewerColumn commentColumn = new TreeViewerColumn(filteredTree.getViewer(), SWT.None);
		commentColumn.getColumn().setText(Messages.MonitoringWatchesView_Comment);
		commentColumn.getColumn().setWidth(340);
		commentColumn.setLabelProvider(new WatchesCommentLabelProvider());
	}


	private void addWatchesAdapters() {
		MonitoringManager.getInstance().addMonitoringListener(listener);
	}

	/** Contribute to action bars. */
	private void contributeToActionBars() {
		final IActionBars bars = getViewSite().getActionBars();
		fillLocalToolBar(bars.getToolBarManager());
	}

	/** Fill local tool bar.
	 *
	 * @param manager the manager */
	private void fillLocalToolBar(final IToolBarManager manager) {
		toggleSelection = new Action(Messages.MonitoringManagerUtils_SelectionFilteringActive,
				IAction.AS_RADIO_BUTTON) {
			@Override
			public void run() {
				selectionActive = !selectionActive;
				toggleSelection.setChecked(selectionActive);
				if (!selectionActive) {
					update();
				} else {
					selectionChanged(getSite().getPart(), getSite().getPage().getActiveEditor().getEditorSite()
							.getSelectionProvider().getSelection());
				}
			}
		};
		toggleSelection.setImageDescriptor(
				PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
		toggleSelection.setToolTipText(Messages.MonitoringManagerUtils_SelectionFilteringActive);
		manager.add(toggleSelection);
	}

	@Override
	public void dispose() {
		MonitoringManager.getInstance().removeMonitoringListener(listener);
		super.dispose();
	}

	@Override
	public void setFocus() {
		// currently nothing to do
	}

	@Override
	public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {

		if (visible && (null != root) && (!root.isDisposed()) && selectionActive
				&& selection instanceof StructuredSelection) {

			final Collection<MonitoringBaseElement> activeElements = new ArrayList<>();

			if (((IStructuredSelection) selection).size() > 0
					&& ((IStructuredSelection) selection).getFirstElement() instanceof EditPart) {

				for (final Object selectionObject : ((IStructuredSelection) selection).toList()) {
					if (selectionObject instanceof EditPart) {

						final Object singleSelection = ((EditPart) selectionObject).getModel();
						if (singleSelection instanceof SubApp) {
							activeElements.addAll(getSubAppMonitoringBaseElements(singleSelection));
						} else if (singleSelection instanceof Group) {
							activeElements.addAll(getGroupMonitoringBaseElements(singleSelection));
						} else if (singleSelection instanceof FB) {
							activeElements.addAll(getFBMonitoringBaseElements(singleSelection));
						} else if (singleSelection instanceof FBNetwork) {
							activeElements.addAll(getFBNetworkMonitoringBaseElements(singleSelection));
						} else {
							// Nothing to do with connections and others...
						}
					}
				}
			}
			if (!activeElements.isEmpty()) {
				updateWithList(activeElements);
			}
		}
	}

	public void update() {
		provider.update();
		if (!filteredTree.isDisposed()) {
			filteredTree.getViewer().refresh();
		}
	}

	public void updateWithList(final Collection<MonitoringBaseElement> activeElements) {
		provider.updateWithList(activeElements);
		if (!filteredTree.isDisposed()) {
			filteredTree.getViewer().refresh();
		}
	}

	public static Collection<MonitoringBaseElement> getGroupMonitoringBaseElements(final Object group) {
		return findMonitoringBaseElement(((Group) group).getGroupElements());
	}

	public static Collection<MonitoringBaseElement> getSubAppMonitoringBaseElements(final Object subApp) {
		final Collection<MonitoringBaseElement> activeElements = new ArrayList<>();
		if (((SubApp) subApp).isUnfolded()) {
			activeElements.addAll(findMonitoringBaseElement(((SubApp) subApp).getSubAppNetwork().getNetworkElements()));
		}
		activeElements.addAll(findMonitoringBaseElement(((FBNetworkElement) subApp)));
		return activeElements;
	}

	public static Collection<MonitoringBaseElement> getFBMonitoringBaseElements(final Object fb) {
		return findMonitoringBaseElement(((FBNetworkElement) fb));
	}

	public static Collection<MonitoringBaseElement> getFBNetworkMonitoringBaseElements(final Object fbNetwork) {
		final Collection<MonitoringBaseElement> activeElements = new ArrayList<>();
		for (final FBNetworkElement model : ((FBNetwork) fbNetwork).getNetworkElements()) {
			if (model instanceof SubApp) {
				activeElements.addAll(getSubAppMonitoringBaseElements(model));
			} else {
				activeElements.addAll(findMonitoringBaseElement(model));
			}
		}
		return activeElements;
	}

	public static Collection<MonitoringBaseElement> findMonitoringBaseElement(final List<FBNetworkElement> models) {
		final Collection<MonitoringBaseElement> activeElements = new ArrayList<>();
		for (final FBNetworkElement model : models) {
			activeElements.addAll(findMonitoringBaseElement(model));
		}
		return activeElements;
	}

	public static Collection<MonitoringBaseElement> findMonitoringBaseElement(final FBNetworkElement model) {
		final Collection<MonitoringBaseElement> elements = MonitoringManager.getInstance().getAllElementsToMonitor();
		final Collection<MonitoringBaseElement> activeElements = new ArrayList<>();
		for (final MonitoringBaseElement monitoringBaseElement : elements) {
			if (model.equals(monitoringBaseElement.getPort().getFb())) {
				activeElements.add(monitoringBaseElement);
			}
		}
		return activeElements;
	}

	private Menu createContextMenu() {
		final Menu contextMenu = new Menu(filteredTree.getViewer().getControl());
		final MenuItem forceMenuItem = createForceMenuItem(filteredTree.getViewer(), contextMenu);
		final MenuItem clearForceMenuItem = createClearForceMenuItem(filteredTree.getViewer(), contextMenu);
		final MenuItem triggerMenuItem = createTriggerMenuItem(filteredTree.getViewer(), contextMenu);
		contextMenu.addMenuListener(new MenuListener() {
			@Override
			public void menuShown(final MenuEvent e) {
				if (filteredTree.getViewer().getSelection() instanceof StructuredSelection) {
					final MonitoringManager manager = MonitoringManager.getInstance();
					final StructuredSelection selection = (StructuredSelection) filteredTree.getViewer().getSelection();
					if (selection.getFirstElement() instanceof WatchValueTreeNode) {
						final MonitoringBaseElement element = manager
								.getMonitoringElement(((WatchValueTreeNode) selection.getFirstElement())
										.getMonitoringBaseElement().getPort().getInterfaceElement());
						forceMenuItem.setEnabled(
								!(((WatchValueTreeNode) selection.getFirstElement()).getMonitoringBaseElement()
										.getPort().getInterfaceElement().getType() instanceof EventType));
						clearForceMenuItem.setEnabled(
								!(((WatchValueTreeNode) selection.getFirstElement()).getMonitoringBaseElement()
										.getPort().getInterfaceElement().getType() instanceof EventType)
								&& ((MonitoringElement) element).isForce());
						triggerMenuItem.setEnabled(
								((WatchValueTreeNode) selection.getFirstElement()).getMonitoringBaseElement().getPort()
								.getInterfaceElement().getType() instanceof EventType);
					}
				} else {
					contextMenu.setVisible(false);
				}
			}

			@Override
			public void menuHidden(final MenuEvent e) {
				// currently nothing to be done here
			}

		});
		return contextMenu;
	}

	private static MenuItem createForceMenuItem(final TreeViewer viewer, final Menu menu) {
		final MenuItem forceMenuItem = new MenuItem(menu, SWT.NONE);
		forceMenuItem.addListener(SWT.Selection, event -> {
			if (viewer.getSelection() instanceof StructuredSelection) {
				final StructuredSelection sel = (StructuredSelection) viewer.getSelection();
				if (sel.getFirstElement() instanceof WatchValueTreeNode) {
					final WatchValueTreeNode treeNode = (WatchValueTreeNode) sel.getFirstElement();
					if (treeNode.getMonitoringBaseElement().getPort().getInterfaceElement() instanceof VarDeclaration) {
						final VarDeclaration variable = (VarDeclaration) treeNode.getMonitoringBaseElement().getPort()
								.getInterfaceElement();
						ForceHandler.showDialogAndProcess(variable);
						viewer.refresh();
					}
				}
			}
		});
		forceMenuItem.setText(Messages.MonitoringWatchesView_Force);
		forceMenuItem.setImage(FordiacImage.ICON_FORCE_VALUE.getImage());
		return forceMenuItem;
	}

	private static MenuItem createClearForceMenuItem(final TreeViewer viewer, final Menu menu) {
		final MenuItem clearForceMenuItem = new MenuItem(menu, SWT.NONE);
		clearForceMenuItem.addListener(SWT.Selection, event -> {
			if (viewer.getSelection() instanceof StructuredSelection) {
				final StructuredSelection sel = (StructuredSelection) viewer.getSelection();
				if (sel.getFirstElement() instanceof WatchValueTreeNode) {
					final WatchValueTreeNode treeNode = (WatchValueTreeNode) sel.getFirstElement();
					if (treeNode.getMonitoringBaseElement().getPort().getInterfaceElement() instanceof VarDeclaration) {
						final VarDeclaration variable = (VarDeclaration) treeNode.getMonitoringBaseElement().getPort().getInterfaceElement();
						ClearForceHandler.processHandler(variable);
						viewer.refresh();
					}
				}
			}
		});
		clearForceMenuItem.setText(Messages.MonitoringWatchesView_ClearForce);
		clearForceMenuItem.setImage(FordiacImage.ICON_CLEAR_FORCE.getImage());
		return clearForceMenuItem;
	}

	private static MenuItem createTriggerMenuItem(final TreeViewer viewer, final Menu menu) {
		final MenuItem triggerMenuItem = new MenuItem(menu, SWT.NONE);
		triggerMenuItem.addListener(SWT.Selection, event -> {
			if (viewer.getSelection() instanceof StructuredSelection) {
				final StructuredSelection sel = (StructuredSelection) viewer.getSelection();
				if (sel.getFirstElement() instanceof WatchValueTreeNode) {
					MonitoringManager.getInstance().triggerEvent(((WatchValueTreeNode) sel.getFirstElement())
							.getMonitoringBaseElement().getPort().getInterfaceElement());
				}
			}
		});
		triggerMenuItem.setText(Messages.MonitoringWatchesView_TriggerEvent);
		triggerMenuItem.setImage(FordiacImage.ICON_TRIGGER_EVENT.getImage());
		return triggerMenuItem;
	}
}
