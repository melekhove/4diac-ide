/*
 * generated by Xtext 2.29.0
 */
package org.eclipse.fordiac.ide.globalconstantseditor.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.fordiac.ide.structuredtextcore.validation.STCoreValidator;

public abstract class AbstractGlobalConstantsValidator extends STCoreValidator {
	
	@Override
	protected List<EPackage> getEPackages() {
		List<EPackage> result = new ArrayList<EPackage>(super.getEPackages());
		result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/fordiac/ide/structuredtextcore/STCore"));
		result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/fordiac/ide/globalconstantseditor/GlobalConstants"));
		return result;
	}
}
