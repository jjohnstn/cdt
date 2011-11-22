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
package org.eclipse.cdt.rdt.proxy;

import org.eclipse.cdt.internal.core.remoteproxy.IRemoteCommandLauncher;
import org.eclipse.cdt.internal.core.remoteproxy.IRemoteFileProxy;
import org.eclipse.cdt.internal.core.remoteproxy.IRemoteProxyManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.rdt.internal.rdt.proxy.RDTCommandLauncher;
import org.eclipse.rdt.internal.rdt.proxy.RDTFileProxy;


public class RDTProxyManager implements IRemoteProxyManager {

	@Override
	public IRemoteFileProxy getFileProxy(IProject project) throws CoreException {
		return new RDTFileProxy(project);
	}

	@Override
	public IRemoteCommandLauncher getLauncher(IProject project)
			throws CoreException {
		return new RDTCommandLauncher(project);
	}

	@Override
	public String getOS(IProject project) throws CoreException {
//		// TODO Auto-generated method stub
//		URI uri = project.getLocationURI();
//		IRemoteServices services = PTPRemoteCorePlugin.getDefault().getRemoteServices(uri);
//		IRemoteConnection connection = services.getConnectionManager().getConnection(uri);
		return "linux"; //FIXME: why doesn't getProperty("os.name") work?
	}

}
