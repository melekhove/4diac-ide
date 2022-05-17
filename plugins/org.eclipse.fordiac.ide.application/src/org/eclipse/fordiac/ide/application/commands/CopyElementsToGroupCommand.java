/*******************************************************************************
 * Copyright (c) 2022 Primetals Technologies Austria GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Dunja Životin - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.application.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.fordiac.ide.model.commands.change.AddElementsToGroup;
import org.eclipse.fordiac.ide.model.libraryElement.FBNetworkElement;
import org.eclipse.fordiac.ide.model.libraryElement.Group;
import org.eclipse.gef.commands.Command;

public class CopyElementsToGroupCommand extends Command {

	private final Group targetGroup;
	private final PasteCommand pasteCommand;
	private AddElementsToGroup addElements;
	private final List<FBNetworkElement> elementsToAdd = new ArrayList<>();
	private final Point offset;

	public CopyElementsToGroupCommand(final Group targetGroup, final PasteCommand pasteCommand, final Point offset) {
		this.targetGroup = targetGroup;
		this.pasteCommand = pasteCommand;
		this.offset = offset;
	}

	@Override
	public void execute() {
		pasteCommand.execute();
		elementsToAdd.addAll(pasteCommand.getCopiedFBs());
		addElements = new AddElementsToGroup(targetGroup, elementsToAdd, offset);
		addElements.execute();
	}

	@Override
	public void undo() {
		addElements.undo();
		pasteCommand.undo();
	}

	@Override
	public void redo() {
		pasteCommand.redo();
		addElements.redo();
	}

}