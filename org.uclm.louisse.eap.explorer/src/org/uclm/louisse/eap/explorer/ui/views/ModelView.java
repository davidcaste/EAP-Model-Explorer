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
package org.uclm.louisse.eap.explorer.ui.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.uclm.louisse.eap.explorer.EapExplorerPlugin;
import org.uclm.louisse.eap.explorer.model.ModelRoot;

public class ModelView extends ViewPart
{
	public static final String ID = "org.uclm.louisse.eap.explorer.ui.views.ModelView";

	private TreeViewer viewer;	
	private ModelRoot modelRoot;


	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		modelRoot = EapExplorerPlugin.getDefault().getModelRoot();
		modelRoot.addListener(this);
	}
	
	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
		getSite().setSelectionProvider(viewer);
		viewer.setContentProvider(new ModelViewContentProvider());
		viewer.setLabelProvider(new ModelViewLabelProvider());
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public void configureViewer() {
		viewer.setInput(modelRoot);
	}
}