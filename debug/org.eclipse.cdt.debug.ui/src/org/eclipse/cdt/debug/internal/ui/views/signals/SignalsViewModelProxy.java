/*******************************************************************************
 * Copyright (c) 2004, 2011 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     QNX Software Systems - Initial API and implementation
 *     Wind River Systems - flexible hierarchy Signals view (bug 338908)
 *******************************************************************************/
package org.eclipse.cdt.debug.internal.ui.views.signals; 

import org.eclipse.debug.internal.ui.viewers.update.DebugEventHandler;
import org.eclipse.debug.internal.ui.viewers.update.EventHandlerModelProxy;
 
/**
 * Default update for Signals view.
 */
public class SignalsViewModelProxy extends EventHandlerModelProxy {

	private Object fTarget;

	/** 
	 * Constructor for SignalesViewModelProxy. 
	 */
	public SignalsViewModelProxy( Object target ) {
		super();
		fTarget = target;
	}

	@Override
	protected DebugEventHandler[] createEventHandlers() {
		return new DebugEventHandler[] { new FlexibleSignalsViewEventHandler( this, fTarget ) };
	}

	@Override
	public synchronized void dispose() {
		super.dispose();
		fTarget = null;
	}
}
