/********************************************************************************
 * Copyright (c) 2014 fortiss GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Waldemar Eisenmenger
 *    - initial API and implementation and/or initial documentation
 ********************************************************************************/
package org.eclipse.fordiac.ide.model.typelibrary.impl;

import org.eclipse.core.resources.IFile;
import org.eclipse.fordiac.ide.model.typelibrary.AdapterTypeEntry;
import org.eclipse.fordiac.ide.model.typelibrary.ITypeEntryCreator;
import org.eclipse.fordiac.ide.model.typelibrary.TypeLibraryTags;

public class CreateAdapterTypeEntry implements ITypeEntryCreator {

	@Override
	public boolean canHandle(final IFile file) {
		return (TypeLibraryTags.ADAPTER_TYPE_FILE_ENDING.equalsIgnoreCase(file.getFileExtension()));
	}

	@Override
	public AdapterTypeEntry createTypeEntry() {
		return new AdapterTypeEntryImpl();
	}

}
