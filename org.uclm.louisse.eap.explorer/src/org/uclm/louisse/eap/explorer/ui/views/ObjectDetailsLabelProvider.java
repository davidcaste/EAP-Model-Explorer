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

import java.lang.reflect.Method;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.sparx.Collection;
import org.uclm.louisse.eap.explorer.model.ClassMethod;

class ObjectDetailsLabelProvider extends LabelProvider implements ITableLabelProvider
{
	ModelViewLabelProvider mLabelProvider = new ModelViewLabelProvider();
	
	@SuppressWarnings("rawtypes")
	@Override
	public String getColumnText(Object element, int columnIndex) {
		String label = "";
		if(element instanceof ClassMethod) {
			ClassMethod cMethod = (ClassMethod) element;
			switch(columnIndex) {
			case 0:
				label = cMethod.getName();
				break;
			case 1:
				Object return1 = cMethod.getOutput();
				if(return1 != null) {
					if(return1 instanceof Collection) {
						StringBuffer sb = new StringBuffer();
						sb.append("Collection (");
						sb.append(((Collection)return1).GetCount());
						sb.append(')');
						label = sb.toString();
					} else {
						label = return1.toString();
					}
				}
				break;
			}
		} else {
			switch(columnIndex) {
			case 0:
				try {
					Method method = element.getClass().getMethod("GetName");
					label = (String) method.invoke(element, new Object[]{});
				} catch (Exception e) {
					label = element.getClass().getSimpleName();
					e.printStackTrace();
				}
				break;
			case 1:
				label = element.toString();
				break;
			}
		}
		return label;
	}
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		switch(columnIndex) {
		case 1:
			if(element instanceof ClassMethod) {
				return mLabelProvider.getImage(((ClassMethod)element).getOutput());
			}
			break;
		}
		return null;
	}
}