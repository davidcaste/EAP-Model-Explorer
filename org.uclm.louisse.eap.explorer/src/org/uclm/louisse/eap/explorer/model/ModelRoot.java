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
package org.uclm.louisse.eap.explorer.model;

import java.io.File;

import org.sparx.Repository;
import org.uclm.louisse.eap.explorer.ui.views.ModelView;

public class ModelRoot
{
	private Repository r;
	private ModelView listener;
	
	public void setFilepath(String filePath) {
		if(r != null) {
			closeRepository();
		}
		File eapFile = new File(filePath);
		r = new Repository();
		r.OpenFile(eapFile.getAbsolutePath());
		
		listener.configureViewer();
	}

	public void addListener(ModelView modelView) {
		listener = modelView;
	}

	public Repository getRepository() {
		return r;
	}
	
	public void closeRepository() {
		r.CloseFile();
		r.Exit();
	}
}
