/*******************************************************************************
 * Copyright (c) 2013 Google, Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	   Sergey Prigogin (Google) - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.internal.ui.preferences;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.PixelConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

import org.eclipse.cdt.core.parser.util.ArrayUtil;
import org.eclipse.cdt.utils.ui.controls.TabFolderLayout;

import org.eclipse.cdt.internal.ui.dialogs.IStatusChangeListener;

/**
 * The preference block for configuring styles of names.
 */
public class TabConfigurationBlock extends OptionsConfigurationBlock {
	private final OptionsConfigurationBlock[] fTabs;
	private final String[] fTabLabels;
	private IStatus fStatus = Status.OK_STATUS;

	public TabConfigurationBlock(IStatusChangeListener context, IProject project,
			OptionsConfigurationBlock[] tabs, String[] tabLabels,
			IWorkbenchPreferenceContainer container) {
		super(context, project, getAllKeys(tabs), container);
		if (tabLabels.length != tabs.length) {
			throw new IllegalArgumentException("Number of labels doesn't match the number of tabs"); //$NON-NLS-1$
		}
		fTabs = tabs;
		fTabLabels = tabLabels;

		IStatusChangeListener statusMerger = new IStatusChangeListener() {
			@Override
			public void statusChanged(IStatus status) {
				if (status.getSeverity() > fStatus.getSeverity())
					fStatus = status;
			}
		};

		for (OptionsConfigurationBlock tab : tabs) {
			tab.fContext = statusMerger;
		}
	}

	private static Key[] getAllKeys(OptionsConfigurationBlock[] tabs) {
		ArrayList<Key> keys = new ArrayList<Key>();
		for (OptionsConfigurationBlock tab : tabs) {
			ArrayUtil.addAll(keys, tab.fAllKeys);
		}
		return keys.toArray(new Key[keys.size()]);
	}

	@Override
	protected Control createContents(Composite parent) {
		PixelConverter pixelConverter =  new PixelConverter(parent);
		final TabFolder folder = new TabFolder(parent, SWT.NONE);
		folder.setLayout(new TabFolderLayout());
		folder.setLayoutData(new GridData(GridData.FILL_BOTH));

		for (int i = 0; i < fTabs.length; i++) {
			TabItem item = new TabItem(folder, SWT.NONE);
			item.setText(fTabLabels[i]);
			Composite composite = new Composite(folder, SWT.NONE);
			FillLayout layout = new FillLayout();
			layout.marginHeight = pixelConverter.convertHorizontalDLUsToPixels(4);
			layout.marginWidth = pixelConverter.convertVerticalDLUsToPixels(4);
			composite.setLayout(layout);
			item.setControl(composite);
			item.setData(fTabs[i]);
			fTabs[i].createContents(composite);
		}
		Dialog.applyDialogFont(folder);
		return folder;		
	}

	@Override
	protected void validateSettings(Key changedKey, String oldValue, String newValue) {
		fStatus = Status.OK_STATUS;
		for (OptionsConfigurationBlock tab : fTabs) {
			tab.validateSettings(changedKey, oldValue, newValue);
			if (fStatus.getSeverity() >= IStatus.ERROR)
				break;
		}
		fContext.statusChanged(fStatus);
	}
}
