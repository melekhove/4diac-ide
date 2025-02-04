/*******************************************************************************
 * Copyright (c) 2023 Martin Erich Jobst
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Martin Jobst - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.structuredtextfunctioneditor.ui.resource;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.fordiac.ide.structuredtextfunctioneditor.ui.internal.StructuredtextfunctioneditorActivator;
import org.eclipse.fordiac.ide.systemmanagement.SystemManager;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.ui.resource.IResourceSetInitializer;

import com.google.inject.Injector;

public class STFunctionResourceSetInitializer implements IResourceSetInitializer {

	@Override
	public void initialize(final ResourceSet resourceSet, final IProject project) {
		if (has4diacProjectNature(project)) {
			final IResourceFactory resourceFactory = getInjector().getInstance(IResourceFactory.class);
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("fct", resourceFactory); //$NON-NLS-1$
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("FCT", resourceFactory); //$NON-NLS-1$
		}
	}

	public static boolean has4diacProjectNature(final IProject project) {
		try {
			return project != null && project.isOpen() && project.hasNature(SystemManager.FORDIAC_PROJECT_NATURE_ID);
		} catch (final CoreException e) {
			return false;
		}
	}

	public static Injector getInjector() {
		final StructuredtextfunctioneditorActivator activator = StructuredtextfunctioneditorActivator.getInstance();
		if (activator == null) {
			throw new IllegalStateException("The bundle has not been started!"); //$NON-NLS-1$
		}
		final Injector injector = activator.getInjector(
				StructuredtextfunctioneditorActivator.ORG_ECLIPSE_FORDIAC_IDE_STRUCTUREDTEXTFUNCTIONEDITOR_STFUNCTION);
		if (injector == null) {
			throw new IllegalStateException("The bundle was not initialized properly!"); //$NON-NLS-1$
		}
		return injector;
	}
}
