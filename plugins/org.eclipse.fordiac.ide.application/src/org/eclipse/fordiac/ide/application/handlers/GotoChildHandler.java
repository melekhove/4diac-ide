/*******************************************************************************
 * Copyright (c) 2021 Primetals Technologies Austria GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Fabio Gandolfi - implemented Handler for Goto Child command/shortcut
 *******************************************************************************/
package org.eclipse.fordiac.ide.application.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Status;
import org.eclipse.fordiac.ide.model.libraryElement.CFBInstance;
import org.eclipse.fordiac.ide.model.libraryElement.IInterfaceElement;
import org.eclipse.fordiac.ide.model.libraryElement.SubApp;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISources;
import org.eclipse.ui.handlers.HandlerUtil;

public class GotoChildHandler extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final StructuredSelection selection = (StructuredSelection) HandlerUtil.getCurrentSelection(event);

		final EditPart editPart = getValidEditPart(selection);
		if (editPart != null) {
			editPart.performRequest(new Request(RequestConstants.REQ_OPEN));
			return Status.OK_STATUS;
		}
		return Status.CANCEL_STATUS;
	}

	@Override
	public void setEnabled(final Object evaluationContext) {
		final ISelection selection = (ISelection) HandlerUtil.getVariable(evaluationContext,
				ISources.ACTIVE_CURRENT_SELECTION_NAME);
		final IEditorPart editor = (IEditorPart) HandlerUtil.getVariable(evaluationContext,
				ISources.ACTIVE_EDITOR_NAME);
		final EditPart validEditPart = getValidEditPart(selection);
		setBaseEnabled(editor != null && validEditPart != null);
	}

	public static EditPart getValidEditPart(final ISelection selection) {
		if (selection instanceof IStructuredSelection && !selection.isEmpty()) {
			final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof EditPart
					&& ((EditPart) structuredSelection.getFirstElement()).getAdapter(EditPart.class) != null) {
				final EditPart editPart = ((EditPart) structuredSelection.getFirstElement()).getAdapter(EditPart.class);
				final Object model = editPart.getModel();
				if (isSubAppOrCFBInstance(model)) {
					return editPart;
				}
				if (model instanceof IInterfaceElement
						&& isSubAppOrCFBInstance(((IInterfaceElement) model).getFBNetworkElement())) {
					return editPart;
				}
			}
		}
		return null;
	}

	private static boolean isSubAppOrCFBInstance(final Object model) {
		return model instanceof SubApp || model instanceof CFBInstance;
	}
}
