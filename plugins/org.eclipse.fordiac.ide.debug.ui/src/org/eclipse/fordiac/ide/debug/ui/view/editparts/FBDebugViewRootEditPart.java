/*******************************************************************************
 * Copyright (c) 2022 Primetals Technologies Austria GmbH
 *               2023 Martin Erich Jobst
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Alois Zoitl - initial API and implementation and/or initial documentation
 *   Martin Jobst - refactor evaluator API
 *******************************************************************************/
package org.eclipse.fordiac.ide.debug.ui.view.editparts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ShortestPathConnectionRouter;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.fordiac.ide.debug.EvaluatorDebugTarget;
import org.eclipse.fordiac.ide.debug.EvaluatorDebugThread;
import org.eclipse.fordiac.ide.debug.EvaluatorDebugVariable;
import org.eclipse.fordiac.ide.debug.EvaluatorProcess;
import org.eclipse.fordiac.ide.gef.editparts.AbstractDiagramEditPart;
import org.eclipse.fordiac.ide.gef.policies.EmptyXYLayoutEditPolicy;
import org.eclipse.fordiac.ide.gef.policies.ModifiedNonResizeableEditPolicy;
import org.eclipse.fordiac.ide.gef.preferences.DiagramPreferences;
import org.eclipse.fordiac.ide.model.eval.Evaluator;
import org.eclipse.fordiac.ide.model.eval.EvaluatorMonitor;
import org.eclipse.fordiac.ide.model.eval.EvaluatorThreadPoolExecutor;
import org.eclipse.fordiac.ide.model.eval.fb.FBEvaluator;
import org.eclipse.fordiac.ide.model.eval.fb.FBEvaluatorCountingEventQueue;
import org.eclipse.fordiac.ide.model.eval.value.Value;
import org.eclipse.fordiac.ide.model.eval.variable.Variable;
import org.eclipse.fordiac.ide.model.libraryElement.Event;
import org.eclipse.fordiac.ide.model.libraryElement.FBType;
import org.eclipse.fordiac.ide.model.libraryElement.IInterfaceElement;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.swt.widgets.Display;

public class FBDebugViewRootEditPart extends AbstractDiagramEditPart
		implements EvaluatorMonitor, IDebugEventSetListener {

	private static final long MIN_UPDATE_INTERVAL = 100; // the minimal update interval between two full refresh of
	// the shown values

	private final Map<String, InterfaceValueEntity> interfaceValues = new HashMap<>();
	private final Map<Event, EventValueEntity> eventValues = new HashMap<>();
	private long lastUpdate;

	@Override
	protected ConnectionRouter createConnectionRouter(final IFigure figure) {
		return new ShortestPathConnectionRouter(figure);
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new EmptyXYLayoutEditPolicy() {
			@Override
			protected EditPolicy createChildEditPolicy(final EditPart child) {
				if (child instanceof AbstractDebugInterfaceValueEditPart) {
					// we only want to provide selection feedback for debug values
					return new ModifiedNonResizeableEditPolicy(DiagramPreferences.CORNER_DIM_HALF, new Insets(1));
				}
				return null;
			}
		});
	}

	@Override
	public EvaluatorProcess getModel() {
		return (EvaluatorProcess) super.getModel();
	}

	@Override
	public void activate() {
		super.activate();
		getModel().getExecutor().addMonitor(this);
		DebugPlugin.getDefault().addDebugEventListener(this);
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void deactivate() {
		super.deactivate();
		getModel().getExecutor().removeMonitor(this);
		DebugPlugin.getDefault().removeDebugEventListener(this);
	}

	public FBEvaluator<?> getFBEvaluator() {
		return (FBEvaluator<?>) getModel().getEvaluator();
	}

	private FBType getFBType() {
		return getFBEvaluator().getType();
	}

	@Override
	protected List<?> getModelChildren() {
		final ArrayList<Object> children = new ArrayList<>();
		children.add(getFBType());
		children.addAll(getEventValues());
		children.addAll(getInterfaceValues());
		return children;
	}

	private Collection<EventValueEntity> getEventValues() {
		if (eventValues.isEmpty()) {
			fillEventValues();
		}
		return eventValues.values();
	}

	private void fillEventValues() {
		final var eventQueue = getFBEvaluator().getEventQueue();
		if (eventQueue instanceof final FBEvaluatorCountingEventQueue countingEventQueue) {
			getFBType().getInterfaceList().getEventInputs().forEach(ev -> addEventEntry(countingEventQueue, ev));
			getFBType().getInterfaceList().getEventOutputs().forEach(ev -> addEventEntry(countingEventQueue, ev));
		}
	}

	private EventValueEntity addEventEntry(final FBEvaluatorCountingEventQueue queue, final Event ev) {
		return eventValues.put(ev, new EventValueEntity(ev, queue.getCount(ev)));
	}

	private Collection<InterfaceValueEntity> getInterfaceValues() {
		if (interfaceValues.isEmpty()) {
			fillInterfaceValues();
		}
		return interfaceValues.values();
	}

	private void fillInterfaceValues() {
		final EvaluatorDebugTarget debugTarget = (EvaluatorDebugTarget) getModel().getAdapter(IDebugTarget.class);
		getFBEvaluator().getContext().getMembers().entrySet().forEach(entry -> {
			final IInterfaceElement interfaceElement = getFBType().getInterfaceList()
					.getInterfaceElement(entry.getKey());
			if (interfaceElement != null) {
				interfaceValues.put(entry.getKey(),
						new InterfaceValueEntity(interfaceElement, entry.getValue(), debugTarget));
			}
		});
	}

	@Override
	public void info(final String message) {
		// Nothing to do here
	}

	@Override
	public void warn(final String message) {
		// Nothing to do here
	}

	@Override
	public void error(final String message) {
		// Nothing to do here
	}

	@Override
	public void error(final String message, final Throwable t) {
		// Nothing to do here
	}

	@Override
	public void update(final Collection<? extends Variable<?>> variables, final Evaluator evaluator) {
		if (evaluator == getFBEvaluator()) {
			// our evaluator sent an update
			updateValues(variables);
		}
	}

	@Override
	public void terminated(final EvaluatorThreadPoolExecutor executor) {
		updateAllValues();
	}

	private void updateValues(final Collection<? extends Variable<?>> variables) {
		final Map<Object, Object> editPartRegistry = getViewer().getEditPartRegistry();
		if (shouldUpdate()) {
			Display.getDefault().asyncExec(() -> {
				variables
						.forEach(variable -> updateVariable(editPartRegistry, variable.getName(), variable.getValue()));
				updateAllEvents(editPartRegistry);
			});
		}
	}

	private boolean shouldUpdate() {
		final long currentTime = System.currentTimeMillis();
		final long delta = currentTime - lastUpdate;
		if (delta > MIN_UPDATE_INTERVAL) {
			lastUpdate = currentTime;
			return true;
		}
		return false;
	}

	private void updateVariable(final Map<Object, Object> editPartRegistry, final String variableName,
			final Value value) {
		final InterfaceValueEntity interfaceValueEntity = interfaceValues.get(variableName);
		if (interfaceValueEntity != null) {
			final Object ep = editPartRegistry.get(interfaceValueEntity);
			if (ep instanceof InterfaceValueEditPart) {
				((InterfaceValueEditPart) ep).setValue(value);
				refreshVisuals();
			}
		}
	}

	@Override
	public void handleDebugEvents(final DebugEvent[] events) {
		for (final DebugEvent ev : events) {
			switch (ev.getKind()) {
			case DebugEvent.SUSPEND:
				if (isCorrectSource(ev.getSource())) {
					updateAllValues();
				}
				break;
			case DebugEvent.CHANGE:
				if (ev.getSource() instanceof EvaluatorDebugVariable) {
					final Map<Object, Object> editPartRegistry = getViewer().getEditPartRegistry();
					Display.getDefault().asyncExec(() -> {
						final EvaluatorDebugVariable evaluatorDebugVariable = (EvaluatorDebugVariable) ev.getSource();
						updateVariable(editPartRegistry, evaluatorDebugVariable.getName(),
								((EvaluatorDebugVariable) ev.getSource()).getValue().getInternalValue());
					});
				}
				break;
			default:
				break;
			}
		}
	}

	private void updateAllValues() {
		final Map<Object, Object> editPartRegistry = getViewer().getEditPartRegistry();
		Display.getDefault().asyncExec(() -> {
			interfaceValues.entrySet().forEach(entry -> updateVariable(editPartRegistry, entry.getKey(),
					entry.getValue().getVariable().getValue()));
			updateAllEvents(editPartRegistry);
		});
	}

	private void updateAllEvents(final Map<Object, Object> editPartRegistry) {
		eventValues.entrySet().forEach(entry -> {
			final Object ep = editPartRegistry.get(entry.getValue());
			if (ep instanceof EventValueEditPart) {
				((EventValueEditPart) ep).update();
			}
		});
	}

	private boolean isCorrectSource(final Object source) {
		return ((source instanceof EvaluatorDebugThread)
				&& ((EvaluatorDebugThread) source).getDebugTarget().getProcess() == getModel());
	}
}
