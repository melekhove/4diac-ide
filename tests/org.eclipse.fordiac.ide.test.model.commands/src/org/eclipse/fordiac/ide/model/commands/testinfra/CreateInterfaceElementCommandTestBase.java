/*******************************************************************************
 * Copyright (c) 2020 Johannes Kepler University
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Ernst Blecha
 *     - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.model.commands.testinfra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.fordiac.ide.model.commands.change.ChangeInterfaceOrderCommand;
import org.eclipse.fordiac.ide.model.commands.change.UpdateFBTypeCommand;
import org.eclipse.fordiac.ide.model.commands.create.FBCreateCommandTest;
import org.eclipse.fordiac.ide.model.libraryElement.IInterfaceElement;
import org.eclipse.fordiac.ide.model.libraryElement.InterfaceList;
import org.eclipse.gef.commands.Command;
import org.junit.jupiter.params.provider.Arguments;

//see org.eclipse.fordiac.ide.util.ColorHelperTest.java for information on implementing tests

public abstract class CreateInterfaceElementCommandTestBase extends FBNetworkTestBase {

	protected static final String EVENT_TYPE = "Event"; //$NON-NLS-1$

	protected static State initializeState() {
		final State state = new State();
		return FBCreateCommandTest.executeCommand(state);
	}

	private static void verifyInitialState(final State state, final State oldState, final TestFunction t) {
		FBCreateCommandTest.verifyState(state, oldState, tester.get()); // skip further tests if FB
		// creation failed
	}

	protected static Collection<Arguments> createCommands(final List<ExecutionDescription<?>> executionDescriptions) {
		final Collection<Arguments> commands = new ArrayList<>();

		commands.addAll(describeCommand("Start from default values", // //$NON-NLS-1$
				CreateInterfaceElementCommandTestBase::initializeState, //
				(StateVerifier<State>) CreateInterfaceElementCommandTestBase::verifyInitialState, //
				executionDescriptions //
				));

		return commands;
	}

	protected static InterfaceList getTypeInterfaceList(final State s) {
		return s.getFunctionblock().getType().getInterfaceList();
	}

	protected static InterfaceList getInstanceInterfaceList(final State s) {
		return s.getFbNetwork().getNetworkElements().get(0).getInterface();
	}

	private static State executeNOP(final State state) {
		state.setCommand(new Command() {
			// NOP
		});
		return commandExecution(state);
	}

	private static <V extends IInterfaceElement> State executeReorder(final State state, final Function<State, EList<V>> translator,
			final int index, final boolean direction) {
		final EList<V> list = translator.apply(state);
		state.setCommand(new ChangeInterfaceOrderCommand(list.get(index), direction));
		return commandExecution(state);
	}

	private static <V extends IInterfaceElement> State executeReorder(final State state, final Function<State, EList<V>> translator,
			final int index, final int newPosition) {
		final EList<V> list = translator.apply(state);
		state.setCommand(new ChangeInterfaceOrderCommand(list.get(index), newPosition));
		return commandExecution(state);
	}

	private static <V extends IInterfaceElement> void verifyOrder(final State state, final TestFunction t,
			final Function<State, EList<V>> translator, final String name1, final String name2, final String name3) {
		final EList<V> list = translator.apply(state);
		t.test(list.size(), 3);
		t.test(list.get(0).getName(), name1);
		t.test(list.get(1).getName(), name2);
		t.test(list.get(2).getName(), name3);
	}

	protected static <V extends IInterfaceElement> Collection<ExecutionDescription<State>> createReordering(
			final Function<State, EList<V>> translator, final String element1, final String element2, final String element3) {
		return List.of( //
				new ExecutionDescription<>("validate order", //$NON-NLS-1$
						CreateInterfaceElementCommandTestBase::executeNOP, //
						(final State s, final State o, final TestFunction t) -> verifyOrder(s, t, translator, element1,
								element2, element3)), //
				new ExecutionDescription<>("move second element to third place", //$NON-NLS-1$
						(final State s) -> executeReorder(s, translator, 1, false), //
						(final State s, final State o, final TestFunction t) -> verifyOrder(s, t, translator, element1,
								element3, element2)), //
				new ExecutionDescription<>("move second element to first place", //$NON-NLS-1$
						(final State s) -> executeReorder(s, translator, 1, true), //
						(final State s, final State o, final TestFunction t) -> verifyOrder(s, t, translator, element3,
								element1, element2)), //
				new ExecutionDescription<>("move first element past lower bound", //$NON-NLS-1$
						(final State s) -> executeReorder(s, translator, 0, true), //
						(final State s, final State o, final TestFunction t) -> verifyOrder(s, t, translator, element3,
								element1, element2)), //
				new ExecutionDescription<>("move third element past upper bound", //$NON-NLS-1$
						(final State s) -> executeReorder(s, translator, 2, false), //
						(final State s, final State o, final TestFunction t) -> verifyOrder(s, t, translator, element3,
								element1, element2)), //
				new ExecutionDescription<>("move first element to third place", //$NON-NLS-1$
						(final State s) -> executeReorder(s, translator, 0, 2), //
						(final State s, final State o, final TestFunction t) -> verifyOrder(s, t, translator, element1,
								element2, element3)) //
				);
	}

	private static State executeUpdate(final State state) {
		state.setCommand(
				new UpdateFBTypeCommand(state.getFbNetwork().getNetworkElements().get(0), state.getFunctionblock()));
		return commandExecution(state);
	}

	protected static Collection<ExecutionDescription<State>> createUpdateAndValidate(final StateVerifier<State> v) {
		return List.of( //
				new ExecutionDescription<>("validate missing entries before update FB", // //$NON-NLS-1$
						CreateInterfaceElementCommandTestBase::executeNOP, //
						(final State s, final State oldState, final TestFunction t) -> {
							final InterfaceList interfacelist = getInstanceInterfaceList(s);
							t.test(interfacelist.getInputVars().isEmpty());
							t.test(interfacelist.getOutputVars().isEmpty());
							t.test(interfacelist.getEventInputs().isEmpty());
							t.test(interfacelist.getEventOutputs().isEmpty());
						}), //
				new ExecutionDescription<>("update FB", // //$NON-NLS-1$
						CreateInterfaceElementCommandTestBase::executeUpdate, //
						v) //
				);
	}

}
