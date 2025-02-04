/*******************************************************************************
 * Copyright (c)2021 Primetals Technologies Germany GmbH
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Alois Zoitl - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.application.utilities;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.fordiac.ide.model.libraryElement.FBNetwork;
import org.eclipse.fordiac.ide.model.libraryElement.Resource;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

public class IsMapableFBNetwork extends PropertyTester {

	@Override
	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
		final IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
		final FBNetwork fbnetwork = activeEditor.getAdapter(FBNetwork.class);
		return ((fbnetwork != null) && !(fbnetwork.isSubApplicationNetwork() || fbnetwork.isCFBTypeNetwork()
				|| fbnetwork.eContainer() instanceof Resource));
	}

}
