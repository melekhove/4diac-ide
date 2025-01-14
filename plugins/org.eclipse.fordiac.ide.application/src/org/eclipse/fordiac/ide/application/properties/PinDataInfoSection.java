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
package org.eclipse.fordiac.ide.application.properties;

import org.eclipse.fordiac.ide.model.libraryElement.VarDeclaration;
import org.eclipse.fordiac.ide.model.ui.nat.DataTypeSelectionTreeContentProvider;
import org.eclipse.fordiac.ide.model.ui.widgets.DataTypeSelectionContentProvider;
import org.eclipse.fordiac.ide.model.ui.widgets.ITypeSelectionContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

/** Here only because it's relevant for the filtering of the PinInfos */

public class PinDataInfoSection extends PinEventInfoSection {

	@Override
	protected VarDeclaration getType() {
		return (VarDeclaration) super.getType();
	}

	@Override
	protected ITypeSelectionContentProvider getTypeSelectionContentProvider() {
		return DataTypeSelectionContentProvider.INSTANCE;
	}

	@Override
	protected ITreeContentProvider getTypeSelectionTreeContentProvider() {
		return DataTypeSelectionTreeContentProvider.INSTANCE;
	}
}
