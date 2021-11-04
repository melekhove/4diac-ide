/*******************************************************************************
 * Copyright (c) 2012, 2013, 2016 Profactor GmbH, TU Wien ACIN, fortiss GmbH
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Gerhard Ebenhofer, Alois Zoitl
 *    - initial implementation
 *******************************************************************************/
package org.eclipse.fordiac.ide.fbtypeeditor.fbtester.policies;

import org.eclipse.fordiac.ide.fbtypeeditor.fbtester.editparts.TestEditPart;
import org.eclipse.fordiac.ide.gef.editparts.AbstractViewEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

/** EditPolicy for editable data fields */
public class SetTestValueEditPolicy extends DirectEditPolicy {

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		if (getHost() instanceof TestEditPart) {
			final TestEditPart testEditPart = (TestEditPart) getHost();
			testEditPart.getModel().setValue((String) request.getCellEditor().getValue());
			testEditPart.refresh();

			// return a dummy command needed to fulfill requirements of direct edit of
			// interface value for testing.
			// canExecute is set to false so that it is not put onto the command stack and
			// makes the editor dirty.
			return new Command() {
				@Override
				public boolean canExecute() {
					return false;
				}
			};
		}
		return null;
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		final String value = (String) request.getCellEditor().getValue();
		if (getHost() instanceof AbstractViewEditPart) {
			final AbstractViewEditPart viewEditPart = (AbstractViewEditPart) getHost();
			viewEditPart.getNameLabel().setText(value);
		}
	}
}
