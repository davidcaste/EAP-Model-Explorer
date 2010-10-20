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

import java.util.Iterator;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.sparx.Attribute;
import org.sparx.Connector;
import org.sparx.Diagram;
import org.sparx.DiagramObject;
import org.sparx.Element;
import org.sparx.Method;
import org.sparx.Package;
import org.sparx.Parameter;
import org.sparx.Repository;
import org.uclm.louisse.eap.explorer.EapExplorerPlugin;

public class ModelViewLabelProvider extends LabelProvider implements ILabelProvider
{
	EapExplorerPlugin plugin = EapExplorerPlugin.getDefault();
	
	@SuppressWarnings("rawtypes")
	@Override
	public String getText(Object element) {
		if(element instanceof Repository) {
			return ((Repository)element).GetConnectionString();
		}
		if(element instanceof Package) {
			Package package1 = (Package) element;
			StringBuffer sb = new StringBuffer();
			if((package1.GetName() != null) && (package1.GetName().length() > 0)) {
				sb.append(package1.GetName());
				sb.append(" (");
			} else {
				sb.append('(');
			}
			sb.append(package1.GetPackageID());
			sb.append(')');
			return sb.toString();
		}
		if(element instanceof Diagram) {
			Diagram diagram = (Diagram) element;
			StringBuffer sb = new StringBuffer();
			if((diagram.GetName() != null) && (diagram.GetName().length() > 0)) {
				sb.append(diagram.GetName());
				sb.append(" (");
			} else {
				sb.append('(');
			}
			sb.append(diagram.GetDiagramID());
			sb.append(')');
			return sb.toString();
		}
		if(element instanceof DiagramObject) {
			DiagramObject dObject = (DiagramObject) element;
			StringBuffer sb = new StringBuffer();
			sb.append("ElementID: ");
			sb.append(dObject.GetElementID());
			return sb.toString();
		}
		if(element instanceof Element) {
			Element element1 = (Element) element;
			StringBuffer sb = new StringBuffer();
			if((element1.GetName() != null) && (element1.GetName().length() > 0)) {
				sb.append(element1.GetName());
				sb.append(" (");
			} else {
				sb.append('(');
			}
			sb.append(element1.GetElementID());
			sb.append(')');
			return sb.toString();
		}
		if(element instanceof Method) {
			Method method = (Method) element;
			StringBuffer sb = new StringBuffer();
			sb.append(method.GetName());
			sb.append('(');
			Iterator iter = method.GetParameters().iterator();
			while(iter.hasNext()) {
				Parameter param = (Parameter) iter.next();
				sb.append(param.GetName());
				sb.append(" : ");
				sb.append(param.GetType());
				if(iter.hasNext()) {
					sb.append(", ");
				}
			}
			sb.append(") : ");
			sb.append(method.GetReturnType());
			return sb.toString();
		}
		if(element instanceof Attribute) {
			Attribute attribute = (Attribute) element;
			StringBuffer sb = new StringBuffer();
			sb.append(attribute.GetName());
			sb.append(" : ");
			sb.append(attribute.GetType());
			return sb.toString();
		}
		if(element instanceof Connector) {
			Connector connector = (Connector) element;
			StringBuffer sb = new StringBuffer();
			if((connector.GetName() != null) && (connector.GetName().length() > 0)) {
				sb.append(connector.GetName());
				sb.append(" (");
			} else {
				sb.append('(');
			}
			sb.append(connector.GetConnectorID());
			sb.append(") ");
			sb.append(connector.GetClientID());
			sb.append(" -> ");
			sb.append(connector.GetSupplierID());
			return sb.toString();
		}
		return element.toString();
	}
	
	@Override
	public Image getImage(Object element) {
		ISharedImages sImages = PlatformUI.getWorkbench().getSharedImages();
		if(element == null) {
			return null;
		}
		if(element instanceof Boolean) {
			return plugin.getImage(EapExplorerPlugin.IMG_BOOLEAN_VALUE);
		}
		if(element instanceof Integer) {
			return plugin.getImage(EapExplorerPlugin.IMG_INTEGRAL_VALUE);
		}
		if(element instanceof String) {
			return plugin.getImage(EapExplorerPlugin.IMG_TEXT_VALUE);
		}
		if(element instanceof Repository) {
			return plugin.getImage(EapExplorerPlugin.IMG_MODEL);
		}
		if(element instanceof Package) {
			return plugin.getImage(EapExplorerPlugin.IMG_PACKAGE);
		}
		if(element instanceof Diagram) {
			Diagram diagram = (Diagram)	element;
			if(diagram.GetType().equals("Logical")) {
				return plugin.getImage(EapExplorerPlugin.IMG_CLASS_DIAGRAM);
			} else if(diagram.GetType().equals("Sequence")) {
				return plugin.getImage(EapExplorerPlugin.IMG_SEQUENCE_DIAGRAM);
			}
			return sImages.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
		if(element instanceof DiagramObject) {
			return plugin.getImage(EapExplorerPlugin.IMG_DIAGRAM_OBJECT);
		}
		if(element instanceof Element) {
			Element element1 = (Element) element;
			if(element1.GetType().equals("Component")) {
				return plugin.getImage(EapExplorerPlugin.IMG_COMPONENT);
			} else if(element1.GetType().equals("Class")) {
				return plugin.getImage(EapExplorerPlugin.IMG_CLASS);
			}  else if(element1.GetType().equals("Interface")) {
				return plugin.getImage(EapExplorerPlugin.IMG_INTERFACE);
			} else if(element1.GetType().equals("Object")) {
				return plugin.getImage(EapExplorerPlugin.IMG_INSTANCE_SPECIFICATION);
			} else if(element1.GetType().equals("Collaboration")) {
				return plugin.getImage(EapExplorerPlugin.IMG_COLLABORATION);
			} else if(element1.GetType().equals("Interaction")) {
				return plugin.getImage(EapExplorerPlugin.IMG_INTERACTION);
			} else if(element1.GetType().equals("Sequence")) {
				return plugin.getImage(EapExplorerPlugin.IMG_LIFELINE);
			} else if(element1.GetType().equals("InteractionFragment")) {
				return plugin.getImage(EapExplorerPlugin.IMG_COMBINEDFRAGMENT);
			}
			return sImages.getImage(ISharedImages.IMG_OBJ_FILE);
		}
		if(element instanceof Method) {
			Method method = (Method) element;
			if(method.GetVisibility().equalsIgnoreCase("Public")) {
				return plugin.getImage(EapExplorerPlugin.IMG_PUBLIC);
			} else if(method.GetVisibility().equalsIgnoreCase("Private")) {
				return plugin.getImage(EapExplorerPlugin.IMG_PRIVATE);
			} else {
				return plugin.getImage(EapExplorerPlugin.IMG_PROTECTED);
			}
		}
		if(element instanceof Attribute) {
			Attribute attribute = (Attribute) element;
			if(attribute.GetVisibility().equalsIgnoreCase("Public")) {
				return plugin.getImage(EapExplorerPlugin.IMG_PUBLIC);
			} else if(attribute.GetVisibility().equalsIgnoreCase("Private")) {
				return plugin.getImage(EapExplorerPlugin.IMG_PRIVATE);
			} else {
				return plugin.getImage(EapExplorerPlugin.IMG_PROTECTED);
			}
		}
		if(element instanceof Connector) {
			return plugin.getImage(EapExplorerPlugin.IMG_CONTROLFLOW);
		}
		return plugin.getImage(EapExplorerPlugin.IMG_GENERIC_VALUE);
	}
}
