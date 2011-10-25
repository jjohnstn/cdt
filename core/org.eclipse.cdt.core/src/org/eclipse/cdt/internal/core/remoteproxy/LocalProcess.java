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

import java.io.InputStream;
import java.io.OutputStream;

public class LocalProcess implements IProcess {

	private Process process;
	
	public LocalProcess(Process process) {
		this.process = process;
	}
	@Override
	public OutputStream getOutputStream() {
		return process.getOutputStream();
	}

	@Override
	public InputStream getInputStream() {
		return process.getInputStream();
	}

	@Override
	public InputStream getErrorStream() {
		return process.getErrorStream();
	}
	
	@Override
	public int exitValue() {
		return process.exitValue();
	}

}
