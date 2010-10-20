/*******************************************************************************
 * Copyright (c) 2010 David Castellanos Serrano and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     David Castellanos Serrano - initial API and implementation
 *******************************************************************************/
package org.uclm.louisse.eap.explorer.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.uclm.louisse.eap.explorer.EapExplorerPlugin;

public class OpenHandler extends AbstractHandler implements IHandler
{
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
		
		//File standard dialog
		FileDialog fileDialog = new FileDialog(shell);
		// Set the text
		fileDialog.setText("Select EAP Model File");
		// Set filter on .txt files
		fileDialog.setFilterExtensions(new String[] {"*.eap"});
		//Open Dialog and save result of selection
		String selected = fileDialog.open();
		if(selected != null) {
			EapExplorerPlugin.getDefault().getModelRoot().setFilepath(selected);
		}
		
		return null;
	}
}
