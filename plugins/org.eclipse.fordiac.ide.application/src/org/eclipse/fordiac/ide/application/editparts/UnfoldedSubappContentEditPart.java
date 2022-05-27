/*******************************************************************************
 * Copyright (c) 2020, 2021 Primetals Technologies Germany GmbH,
 * 							Primetals Technologies Austria GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Bianca Wiesmayr, Alois Zoitl - initial implementation and/or documentation
 *   Alexander Lumplecker, Bianca Wiesmayr, Alois Zoitl - Bug: 568569
 *   Alois Zoitl - extracted most code into common base class for group
 *                 infrastructure
 *******************************************************************************/

package org.eclipse.fordiac.ide.application.editparts;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.fordiac.ide.application.policies.FBAddToSubAppLayoutEditPolicy;
import org.eclipse.fordiac.ide.model.commands.create.AbstractCreateFBNetworkElementCommand;
import org.eclipse.fordiac.ide.model.typelibrary.TypeEntry;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;

public class UnfoldedSubappContentEditPart extends AbstractContainerContentEditPart {


	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		// Add policy to handle drag&drop of fbs
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new FBAddToSubAppLayoutEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new AbstractCreateInstanceDirectEditPolicy() {
			@Override
			protected Command getElementCreateCommand(final TypeEntry value, final Point refPoint) {
				return AbstractCreateFBNetworkElementCommand.createCreateCommand(value, getModel().getSubappContent(),
						refPoint.x, refPoint.y);
			}
		});
	}

	@Override
	public UnfoldedSubappContentNetwork getModel() {
		return (UnfoldedSubappContentNetwork) super.getModel();
	}

	@Override
	protected EObject getContainerElement() {
		return getModel().getSubapp();
	}

}
