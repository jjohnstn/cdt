/*******************************************************************************
 * Copyright (c) 2011 Red Hat Inc..
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Incorporated - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.internal.core.remoteproxy;

import java.io.OutputStream;

import org.eclipse.cdt.core.CommandLauncher;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

public class LocalLauncher implements IRemoteCommandLauncher {

	private CommandLauncher launcher;
	
	public LocalLauncher() {
		launcher = new CommandLauncher();
	}
	
	@Override
	public Process execute(String commandPath, String[] args, String[] env,
			String changeToDirectory, IProgressMonitor monitor)
			throws CoreException {
		launcher.showCommand(true);
		Process p = launcher.execute(new Path(commandPath), args, env, new Path(changeToDirectory), monitor);
		return p;
	}

	@Override
	public Process execute(String commandPath, String[] args, String[] env,
			String changeToDirectory, boolean usePTY, IProgressMonitor monitor)
					throws CoreException {
		launcher.showCommand(true);
		Process p = launcher.execute(new Path(commandPath), args, env, new Path(changeToDirectory), usePTY, monitor);
		return p;
	}
	
	@Override
	public int waitAndRead(OutputStream output, OutputStream err,
			IProgressMonitor monitor) {
		return launcher.waitAndRead(output, err, monitor);
	}
	
	@Override
	public String getErrorMessage() {
		return launcher.getErrorMessage();
	}

	@Override
	public String getCommandLine() {
		return launcher.getCommandLine();
	}

}
