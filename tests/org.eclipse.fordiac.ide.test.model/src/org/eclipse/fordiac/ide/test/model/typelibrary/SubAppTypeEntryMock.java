/*********************************************************************************
 * Copyright (c) 2022 Primetals Technologies Austria GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Alois Zoitl - initial API and implementation and/or initial documentation
 ********************************************************************************/
package org.eclipse.fordiac.ide.test.model.typelibrary;

import java.util.Collections;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.impl.BasicNotifierImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.fordiac.ide.model.helpers.PackageNameHelper;
import org.eclipse.fordiac.ide.model.libraryElement.LibraryElement;
import org.eclipse.fordiac.ide.model.libraryElement.LibraryElementPackage;
import org.eclipse.fordiac.ide.model.libraryElement.SubAppType;
import org.eclipse.fordiac.ide.model.typelibrary.SubAppTypeEntry;
import org.eclipse.fordiac.ide.model.typelibrary.TypeEntry;
import org.eclipse.fordiac.ide.model.typelibrary.TypeLibrary;

public final class SubAppTypeEntryMock extends BasicNotifierImpl implements SubAppTypeEntry {

	private SubAppType subAppType;
	private TypeLibrary typelib;
	private IFile file;

	public SubAppTypeEntryMock(final SubAppType subAppType, final TypeLibrary typelib, final IFile file) {
		this.subAppType = subAppType;
		this.typelib = typelib;
		this.file = file;
	}

	public SubAppTypeEntryMock(final SubAppTypeEntry typeEntry) {
		this(typeEntry.getType(), typeEntry.getTypeLibrary(), typeEntry.getFile());
	}

	@Override
	public IFile getFile() {
		return file;
	}

	@Override
	public void setFile(final IFile value) {
		file = value;
	}

	@Override
	public long getLastModificationTimestamp() {
		return 0;
	}

	@Override
	public void setLastModificationTimestamp(final long value) {
		// currently not needed in mock
	}

	@Override
	public void setType(final LibraryElement value) {
		subAppType = (SubAppType) value;
	}

	/**
	 * @deprecated see {@link TypeEntry#setTypeEditable(LibraryElement)}
	 */
	@Override
	@Deprecated(since = "3.0.0", forRemoval = true)
	public void setTypeEditable(final LibraryElement value) {
		// currently not needed in mock
	}

	@Override
	public TypeLibrary getTypeLibrary() {
		return typelib;
	}

	@Override
	public void setTypeLibrary(final TypeLibrary typeLib) {
		this.typelib = typeLib;
	}

	@Override
	public SubAppType getType() {
		return subAppType;
	}

	/**
	 * @deprecated see {@link TypeEntry#getTypeEditable()}
	 */
	@Override
	@Deprecated(since = "3.0.0", forRemoval = true)
	public SubAppType getTypeEditable() {
		// currently not needed in mock
		return null;
	}

	@Override
	public SubAppType copyType() {
		// currently not needed in mock
		return null;
	}

	@Override
	public String getTypeName() {
		return getType().getName();
	}

	@Override
	public String getFullTypeName() {
		return PackageNameHelper.getFullTypeName(getType());
	}

	@Override
	public void save(final LibraryElement toSave, final IProgressMonitor monitor) {
		// currently not needed in mock
	}

	@Override
	public void refresh() {
		// currently not needed in mock
	}

	@Override
	public Set<TypeEntry> getDependencies() {
		return Collections.emptySet();
	}

	@Override
	public String getComment() {
		return subAppType.getComment();
	}

	@Override
	public EClass getTypeEClass() {
		return LibraryElementPackage.Literals.SUB_APP_TYPE;
	}
}