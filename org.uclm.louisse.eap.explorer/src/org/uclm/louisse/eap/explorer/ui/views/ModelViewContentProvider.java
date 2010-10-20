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

import java.util.ArrayList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.sparx.Attribute;
import org.sparx.Collection;
import org.sparx.Connector;
import org.sparx.Diagram;
import org.sparx.DiagramObject;
import org.sparx.Element;
import org.sparx.Method;
import org.sparx.Package;
import org.sparx.Repository;
import org.uclm.louisse.eap.explorer.model.ModelRoot;

public class ModelViewContentProvider implements ITreeContentProvider
{
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object[] getChildren(Object parentElement) {
		
		if(parentElement instanceof ModelRoot) {
			ModelRoot modelRoot = (ModelRoot) parentElement;
			return new Object[]{modelRoot.getRepository()};
		}
		if(parentElement instanceof Repository) {
			return collToArray(((Repository)parentElement).GetModels());
		}
		if(parentElement instanceof Diagram) {
			Diagram diagram = (Diagram) parentElement;
			ArrayList list = new ArrayList();
			for(DiagramObject object : diagram.GetDiagramObjects()) {
				list.add(object);
			}
			return list.toArray();
		}
		if(parentElement instanceof Package) {
			Package package1 = (Package) parentElement;
			ArrayList list = new ArrayList();
			for(Package package2 : package1.GetPackages()) {
				list.add(package2);
			}
			for(Diagram diagram : package1.GetDiagrams()) {
				if(isChildOf(package1.GetPackageID(), diagram.GetParentID(), diagram.GetPackageID())) {
					list.add(diagram);
				}
			}
			for(Element element : package1.GetElements()) {
				if(isChildOf(package1.GetPackageID(), element.GetParentID(), element.GetPackageID())) {
					list.add(element);
				}
			}
			for(Connector connector : package1.GetConnectors()) {
				list.add(connector);
			}
			return list.toArray();
		}
		if(parentElement instanceof Element) {
			Element parent = (Element) parentElement;
			ArrayList list = new ArrayList();
			for(Diagram diagram : parent.GetDiagrams()) {
				if(parent.GetElementID() == diagram.GetParentID()) {
					list.add(diagram);
				}
			}
			for(Element element : parent.GetElements()) {
				if(parent.GetElementID() == element.GetParentID()) {
					list.add(element);
				}
			}
			for(Method method : parent.GetMethods()) {
				list.add(method);
			}
			for(Attribute attribute : parent.GetAttributes()) {
				list.add(attribute);
			}
			for(Connector connector : parent.GetConnectors()) {
				list.add(connector);
			}
			return list.toArray();
		}
		
		return new Object[]{};
	}

	private boolean isChildOf(int parentID, int childParentID, int childPackageID) {
		return (parentID == childParentID) || ((childParentID == 0) && (parentID == childPackageID));
	}
	
	@Override
	public void dispose() {
	}
	
	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}
	
	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object[] collToArray(Collection collection) {
		ArrayList al = new ArrayList();
		for(Object element : collection) {
			al.add(element);
		}
		return al.toArray();
	}
}
