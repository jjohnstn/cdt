/*******************************************************************************
 * Copyright (c) 2017 Red Hat Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core;

import java.util.List;

import org.eclipse.cdt.core.settings.model.ICLanguageSettingEntry;
import org.eclipse.core.resources.IProject;

/**
 * @since 6.3
 */
public interface ICommandLauncherFactory {
	
	/**
	 * Get a Command Launcher for a project (optional)
	 * @param project - optional parameter to help determine appropriate launcher
	 * @return ICommandLauncher or null if not appropriate for project
	 */
	public ICommandLauncher getCommandLauncher(IProject project);

	/**
	 * Register language setting entries for a project
	 * @param project - IProject used in obtaining language setting entries
	 * @param entries - List of language setting entries
	 */
	public void registerLanguageSettingEntries(IProject project, List<? extends ICLanguageSettingEntry> entries);

}
