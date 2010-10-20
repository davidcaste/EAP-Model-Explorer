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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class ObjectDetailsView extends ViewPart
{
	public static final String ID = "org.uclm.louisse.eap.explorer.ui.views.ObjectDetailsView";

	private TreeViewer viewer;
	
	ISelectionListener listener = new ISelectionListener() {
		@Override
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			if(!(selection instanceof IStructuredSelection)) return;
			IStructuredSelection ss = (IStructuredSelection) selection;
			if(ss.size() == 1) {
				viewer.setInput(ss.getFirstElement());
				updateTables();
			}
		}
	};
	
	@Override
	public void createPartControl(Composite parent) {
		getSite().getPage().addSelectionListener(listener);
		viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.getTree().setLinesVisible(true);
		viewer.getTree().setHeaderVisible(true);
		TreeColumn propertyColumn = new TreeColumn(viewer.getTree(), SWT.LEFT);
		propertyColumn.setText("Property");
		TreeColumn valueColumn = new TreeColumn(viewer.getTree(), SWT.LEFT);
		valueColumn.setText("Value");
		
		viewer.setContentProvider(new ObjectDetailsContentProvider());
		viewer.setLabelProvider(new ObjectDetailsLabelProvider());
	}

	private void updateTables() {
		Tree tree = viewer.getTree();
		for(TreeColumn column : tree.getColumns()) {
			column.pack();
		}
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(listener);
	}
	
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
