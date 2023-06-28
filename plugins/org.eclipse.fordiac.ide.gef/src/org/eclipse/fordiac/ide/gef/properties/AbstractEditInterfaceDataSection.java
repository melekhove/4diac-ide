/*******************************************************************************
 * Copyright (c) 2017 fortiss GmbH
 * 				 2019 - 2020 Johannes Kepler University Linz
 * 				 2020 Primetals Technologies Germany GmbH
 *               2023 Martin Erich Jobst
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Monika Wenger
 *     - initial API and implementation and/or initial documentation
 *   Bianca Wiesmayr - create command now has enhanced guess, added columns
 *   Daniel Lindhuber - added addEntry method & type search field
 *   Martin Jobst - add initial value cell editor support
 *******************************************************************************/
package org.eclipse.fordiac.ide.gef.properties;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.fordiac.ide.gef.nat.FordiacInterfaceListProvider;
import org.eclipse.fordiac.ide.gef.nat.InitialValueEditorConfiguration;
import org.eclipse.fordiac.ide.gef.nat.TypeDeclarationEditorConfiguration;
import org.eclipse.fordiac.ide.gef.nat.VarDeclarationColumnAccessor;
import org.eclipse.fordiac.ide.gef.nat.VarDeclarationColumnProvider;
import org.eclipse.fordiac.ide.gef.nat.VarDeclarationListProvider;
import org.eclipse.fordiac.ide.model.data.DataType;
import org.eclipse.fordiac.ide.model.datatype.helper.IecTypes;
import org.eclipse.fordiac.ide.model.libraryElement.ErrorMarkerDataType;
import org.eclipse.fordiac.ide.model.libraryElement.IInterfaceElement;
import org.eclipse.fordiac.ide.model.libraryElement.InterfaceList;
import org.eclipse.fordiac.ide.model.libraryElement.VarDeclaration;
import org.eclipse.fordiac.ide.model.ui.editors.DataTypeDropdown;
import org.eclipse.fordiac.ide.model.ui.widgets.DataTypeSelectionButton;
import org.eclipse.fordiac.ide.ui.widget.I4diacNatTableUtil;
import org.eclipse.fordiac.ide.ui.widget.NatTableWidgetFactory;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.LabelStack;
import org.eclipse.swt.widgets.Group;

public abstract class AbstractEditInterfaceDataSection extends AbstractEditInterfaceSection<VarDeclaration> {

	protected static final String INITIAL_VALUE = "initialvalue"; //$NON-NLS-1$
	protected static final int INITIALVALUE_WIDTH = 100;
	private DataTypeDropdown typeDropdown;

	@Override
	protected CellEditor createTypeCellEditor(final TableViewer viewer) {
		typeDropdown = new DataTypeDropdown(() -> getDataTypeLib().getDataTypesSorted(), viewer);
		return typeDropdown;
	}

	@Override
	protected String[] fillTypeCombo() {
		final List<String> list = new ArrayList<>();
		for (final DataType dataType : getDataTypeLib().getDataTypesSorted()) {
			list.add(dataType.getName());
		}
		return list.toArray(new String[0]);
	}

	protected static DataType getLastUsedDataType(final InterfaceList interfaceList, final boolean isInput,
			final IInterfaceElement interfaceElement) {
		if (null != interfaceElement) {
			return interfaceElement.getType();
		}
		final EList<VarDeclaration> dataList = getDataList(interfaceList, isInput);
		if (!dataList.isEmpty()) {
			return dataList.get(dataList.size() - 1).getType();
		}
		return IecTypes.ElementaryTypes.BOOL; // bool is default
	}

	@Override
	protected int getInsertingIndex(final IInterfaceElement interfaceElement, final boolean isInput) {
		if (null != interfaceElement) {
			final InterfaceList interfaceList = (InterfaceList) interfaceElement.eContainer();
			return getInsertingIndex(interfaceElement, getDataList(interfaceList, isInput));
		}
		return -1;
	}

	private static EList<VarDeclaration> getDataList(final InterfaceList interfaceList, final boolean isInput) {
		return isInput ? interfaceList.getInputVars() : interfaceList.getOutputVars();
	}

	@Override
	public void addEntry(final Object entry, final boolean isInput, final int index, final CompoundCommand cmd) {
		if (entry instanceof VarDeclaration) {
			final IInterfaceElement entry2 = (IInterfaceElement) entry;
			cmd.add(newInsertCommand(entry2, isInput, index));
		}
	}

	@Override
	protected void configureLabels(final ListDataProvider<VarDeclaration> provider, final LabelStack configLabels,
			final int columnPosition, final int rowPosition) {
		final VarDeclaration rowItem = provider.getRowObject(rowPosition);
		switch (columnPosition) {
		case I4diacNatTableUtil.TYPE:
			if (rowItem.getType() instanceof ErrorMarkerDataType
					|| (rowItem.isArray() && rowItem.getArraySize().hasError())) {
				configLabels.addLabelOnTop(NatTableWidgetFactory.ERROR_CELL);
			}
			if (isEditable()) {
				configLabels.addLabel(TypeDeclarationEditorConfiguration.TYPE_DECLARATION_CELL);
			}
			break;
		case I4diacNatTableUtil.INITIAL_VALUE:
			if (isEditable()) {
				if (rowItem.getValue() != null && rowItem.getValue().hasError()) {
					configLabels.addLabelOnTop(NatTableWidgetFactory.ERROR_CELL);
				}
				configLabels.addLabel(InitialValueEditorConfiguration.INITIAL_VALUE_CELL);
			}
			break;
		case I4diacNatTableUtil.NAME:
		case I4diacNatTableUtil.COMMENT:
			configLabels.addLabelOnTop(NatTableWidgetFactory.LEFT_ALIGNMENT);
			break;
		default:
			break;
		}
	}

	@Override
	public void removeEntry(final Object entry, final CompoundCommand cmd) {
		if (entry instanceof VarDeclaration) {
			cmd.add(newDeleteCommand((IInterfaceElement) entry));
		}
	}

	@Override
	public void setupOutputTable(final Group outputsGroup) {
		outputProvider = new VarDeclarationListProvider(new VarDeclarationColumnAccessor(this));
		final DataLayer outputDataLayer = setupDataLayer(outputProvider);
		outputTable = NatTableWidgetFactory.createRowNatTable(outputsGroup, outputDataLayer,
				new VarDeclarationColumnProvider(), getSectionEditableRule(), new DataTypeSelectionButton(typeSelection),
				this, false);
		outputTable.addConfiguration(new InitialValueEditorConfiguration(outputProvider));
		outputTable.addConfiguration(new TypeDeclarationEditorConfiguration(outputProvider));
		outputTable.configure();
	}

	@Override
	public void setupInputTable(final Group inputsGroup) {
		inputProvider = new VarDeclarationListProvider(new VarDeclarationColumnAccessor(this));
		final DataLayer inputDataLayer = setupDataLayer(inputProvider);
		inputTable = NatTableWidgetFactory.createRowNatTable(inputsGroup, inputDataLayer,
				new VarDeclarationColumnProvider(), getSectionEditableRule(), new DataTypeSelectionButton(typeSelection),
				this, true);
		inputTable.addConfiguration(new InitialValueEditorConfiguration(inputProvider));
		inputTable.addConfiguration(new TypeDeclarationEditorConfiguration(inputProvider));
		inputTable.configure();
	}

	@Override
	protected void setInputInit() {
		((VarDeclarationListProvider) outputProvider).setTypeLib(getDataTypeLib());
		((VarDeclarationListProvider) inputProvider).setTypeLib(getDataTypeLib());
	}

	@Override
	public void setTableInput(final InterfaceList il) {
		((FordiacInterfaceListProvider<VarDeclaration>) inputProvider).setInput(il.getInputVars());
		((FordiacInterfaceListProvider<VarDeclaration>) outputProvider).setInput(il.getOutputVars());
	}
}
