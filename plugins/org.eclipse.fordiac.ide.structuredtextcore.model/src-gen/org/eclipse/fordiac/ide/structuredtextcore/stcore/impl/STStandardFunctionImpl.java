/**
 * *******************************************************************************
 * Copyright (c) 2022 Primetals Technologies GmbH,
 *               2022 Martin Erich Jobst
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Martin Jobst, Martin Melik Merkumians
 *      - initial API and implementation and/or initial documentation
 * *******************************************************************************
 */
package org.eclipse.fordiac.ide.structuredtextcore.stcore.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.fordiac.ide.model.data.DataType;

import org.eclipse.fordiac.ide.model.libraryElement.INamedElement;
import org.eclipse.fordiac.ide.structuredtextcore.stcore.STCorePackage;
import org.eclipse.fordiac.ide.structuredtextcore.stcore.STStandardFunction;
import org.eclipse.fordiac.ide.structuredtextcore.stcore.STVarDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ST Standard Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.stcore.impl.STStandardFunctionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.stcore.impl.STStandardFunctionImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.stcore.impl.STStandardFunctionImpl#getSignature <em>Signature</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.stcore.impl.STStandardFunctionImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.stcore.impl.STStandardFunctionImpl#getInputParameters <em>Input Parameters</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.stcore.impl.STStandardFunctionImpl#getOutputParameters <em>Output Parameters</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.stcore.impl.STStandardFunctionImpl#getInOutParameters <em>In Out Parameters</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.stcore.impl.STStandardFunctionImpl#getOnlySupportedBy <em>Only Supported By</em>}</li>
 * </ul>
 *
 * @generated
 */
public class STStandardFunctionImpl extends MinimalEObjectImpl.Container implements STStandardFunction {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMENT_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected String comment = COMMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getSignature() <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignature()
	 * @generated
	 * @ordered
	 */
	protected static final String SIGNATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSignature() <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignature()
	 * @generated
	 * @ordered
	 */
	protected String signature = SIGNATURE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected DataType returnType;

	/**
	 * The cached value of the '{@link #getInputParameters() <em>Input Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<INamedElement> inputParameters;

	/**
	 * The cached value of the '{@link #getOutputParameters() <em>Output Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<INamedElement> outputParameters;

	/**
	 * The cached value of the '{@link #getInOutParameters() <em>In Out Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInOutParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<INamedElement> inOutParameters;

	/**
	 * The cached value of the '{@link #getOnlySupportedBy() <em>Only Supported By</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnlySupportedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<String> onlySupportedBy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected STStandardFunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return STCorePackage.Literals.ST_STANDARD_FUNCTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, STCorePackage.ST_STANDARD_FUNCTION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getComment() {
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setComment(String newComment) {
		String oldComment = comment;
		comment = newComment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, STCorePackage.ST_STANDARD_FUNCTION__COMMENT, oldComment, comment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSignature() {
		return signature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSignature(String newSignature) {
		String oldSignature = signature;
		signature = newSignature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, STCorePackage.ST_STANDARD_FUNCTION__SIGNATURE, oldSignature, signature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DataType getReturnType() {
		if (returnType != null && returnType.eIsProxy()) {
			InternalEObject oldReturnType = (InternalEObject)returnType;
			returnType = (DataType)eResolveProxy(oldReturnType);
			if (returnType != oldReturnType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, STCorePackage.ST_STANDARD_FUNCTION__RETURN_TYPE, oldReturnType, returnType));
			}
		}
		return returnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType basicGetReturnType() {
		return returnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReturnType(DataType newReturnType) {
		DataType oldReturnType = returnType;
		returnType = newReturnType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, STCorePackage.ST_STANDARD_FUNCTION__RETURN_TYPE, oldReturnType, returnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<INamedElement> getInputParameters() {
		if (inputParameters == null) {
			inputParameters = new EObjectContainmentEList<INamedElement>(INamedElement.class, this, STCorePackage.ST_STANDARD_FUNCTION__INPUT_PARAMETERS);
		}
		return inputParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<INamedElement> getOutputParameters() {
		if (outputParameters == null) {
			outputParameters = new EObjectContainmentEList<INamedElement>(INamedElement.class, this, STCorePackage.ST_STANDARD_FUNCTION__OUTPUT_PARAMETERS);
		}
		return outputParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<INamedElement> getInOutParameters() {
		if (inOutParameters == null) {
			inOutParameters = new EObjectContainmentEList<INamedElement>(INamedElement.class, this, STCorePackage.ST_STANDARD_FUNCTION__IN_OUT_PARAMETERS);
		}
		return inOutParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getOnlySupportedBy() {
		if (onlySupportedBy == null) {
			onlySupportedBy = new EDataTypeUniqueEList<String>(String.class, this, STCorePackage.ST_STANDARD_FUNCTION__ONLY_SUPPORTED_BY);
		}
		return onlySupportedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case STCorePackage.ST_STANDARD_FUNCTION__INPUT_PARAMETERS:
				return ((InternalEList<?>)getInputParameters()).basicRemove(otherEnd, msgs);
			case STCorePackage.ST_STANDARD_FUNCTION__OUTPUT_PARAMETERS:
				return ((InternalEList<?>)getOutputParameters()).basicRemove(otherEnd, msgs);
			case STCorePackage.ST_STANDARD_FUNCTION__IN_OUT_PARAMETERS:
				return ((InternalEList<?>)getInOutParameters()).basicRemove(otherEnd, msgs);
			default:
				return super.eInverseRemove(otherEnd, featureID, msgs);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case STCorePackage.ST_STANDARD_FUNCTION__NAME:
				return getName();
			case STCorePackage.ST_STANDARD_FUNCTION__COMMENT:
				return getComment();
			case STCorePackage.ST_STANDARD_FUNCTION__SIGNATURE:
				return getSignature();
			case STCorePackage.ST_STANDARD_FUNCTION__RETURN_TYPE:
				if (resolve) return getReturnType();
				return basicGetReturnType();
			case STCorePackage.ST_STANDARD_FUNCTION__INPUT_PARAMETERS:
				return getInputParameters();
			case STCorePackage.ST_STANDARD_FUNCTION__OUTPUT_PARAMETERS:
				return getOutputParameters();
			case STCorePackage.ST_STANDARD_FUNCTION__IN_OUT_PARAMETERS:
				return getInOutParameters();
			case STCorePackage.ST_STANDARD_FUNCTION__ONLY_SUPPORTED_BY:
				return getOnlySupportedBy();
			default:
				return super.eGet(featureID, resolve, coreType);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case STCorePackage.ST_STANDARD_FUNCTION__NAME:
				setName((String)newValue);
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__COMMENT:
				setComment((String)newValue);
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__SIGNATURE:
				setSignature((String)newValue);
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__RETURN_TYPE:
				setReturnType((DataType)newValue);
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__INPUT_PARAMETERS:
				getInputParameters().clear();
				getInputParameters().addAll((Collection<? extends INamedElement>)newValue);
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__OUTPUT_PARAMETERS:
				getOutputParameters().clear();
				getOutputParameters().addAll((Collection<? extends INamedElement>)newValue);
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__IN_OUT_PARAMETERS:
				getInOutParameters().clear();
				getInOutParameters().addAll((Collection<? extends INamedElement>)newValue);
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__ONLY_SUPPORTED_BY:
				getOnlySupportedBy().clear();
				getOnlySupportedBy().addAll((Collection<? extends String>)newValue);
				return;
			default:
				super.eSet(featureID, newValue);
				return;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case STCorePackage.ST_STANDARD_FUNCTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__COMMENT:
				setComment(COMMENT_EDEFAULT);
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__SIGNATURE:
				setSignature(SIGNATURE_EDEFAULT);
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__RETURN_TYPE:
				setReturnType((DataType)null);
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__INPUT_PARAMETERS:
				getInputParameters().clear();
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__OUTPUT_PARAMETERS:
				getOutputParameters().clear();
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__IN_OUT_PARAMETERS:
				getInOutParameters().clear();
				return;
			case STCorePackage.ST_STANDARD_FUNCTION__ONLY_SUPPORTED_BY:
				getOnlySupportedBy().clear();
				return;
			default:
				super.eUnset(featureID);
				return;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case STCorePackage.ST_STANDARD_FUNCTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case STCorePackage.ST_STANDARD_FUNCTION__COMMENT:
				return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
			case STCorePackage.ST_STANDARD_FUNCTION__SIGNATURE:
				return SIGNATURE_EDEFAULT == null ? signature != null : !SIGNATURE_EDEFAULT.equals(signature);
			case STCorePackage.ST_STANDARD_FUNCTION__RETURN_TYPE:
				return returnType != null;
			case STCorePackage.ST_STANDARD_FUNCTION__INPUT_PARAMETERS:
				return inputParameters != null && !inputParameters.isEmpty();
			case STCorePackage.ST_STANDARD_FUNCTION__OUTPUT_PARAMETERS:
				return outputParameters != null && !outputParameters.isEmpty();
			case STCorePackage.ST_STANDARD_FUNCTION__IN_OUT_PARAMETERS:
				return inOutParameters != null && !inOutParameters.isEmpty();
			case STCorePackage.ST_STANDARD_FUNCTION__ONLY_SUPPORTED_BY:
				return onlySupportedBy != null && !onlySupportedBy.isEmpty();
			default:
				return super.eIsSet(featureID);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", comment: "); //$NON-NLS-1$
		result.append(comment);
		result.append(", signature: "); //$NON-NLS-1$
		result.append(signature);
		result.append(", onlySupportedBy: "); //$NON-NLS-1$
		result.append(onlySupportedBy);
		result.append(')');
		return result.toString();
	}

} //STStandardFunctionImpl
