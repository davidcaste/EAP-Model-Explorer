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
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.sparx.Collection;
import org.uclm.louisse.eap.explorer.model.ClassMethod;

class ObjectDetailsContentProvider implements ITreeContentProvider
{
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object[] getElements(Object inputElement) {
		Object input = inputElement;
		Class c = inputElement.getClass();
		if(inputElement instanceof ClassMethod) {	// element is instance of ClassMethod
			ClassMethod cMethod = (ClassMethod) inputElement;
			if(cMethod.getMethod().getReturnType().equals(org.sparx.Collection.class)) {
				ArrayList list = new ArrayList();
				Collection collection = (Collection) cMethod.getOutput();
				for(Object item : collection) {
					list.add(item);
				}
				return list.toArray();
			}
			if(!returnIsPrimitive(cMethod)) {	// method return isn't primitive, return its members
				input = cMethod.getOutput();
				c = cMethod.getOutput().getClass();
			} else {	// return is primitive, return nothing
				return new Object[] {};
			}
		}
		// return the members of the input
		ArrayList<ClassMethod> list = new ArrayList<ClassMethod>();
		for(Method method : c.getDeclaredMethods()) {
			if(method.getName().startsWith("Get") &&
					!method.getName().startsWith("GetSelected") &&
					(method.getParameterTypes().length == 0) &&
					Modifier.isPublic(method.getModifiers())) {
				list.add(new ClassMethod(input, method));
			}
		}
		// Does the class expose the (weird) method MiscData?
		try {
			Method method = c.getMethod("MiscData", new Class[]{Integer.TYPE});
			boolean foo = true;
			for(int i=0; foo; i++) {
				ClassMethod cMethod = new ClassMethod(input, method, new Object[]{i});
				if(cMethod.getOutput() != null) {
					list.add(cMethod);
				} else {
					foo = false;
				}
			}
		} catch (NoSuchMethodException e) {
			// Mmm, the method doesn't exist in that clase, so do nothing
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		Collections.sort(list);
		return list.toArray();
	}
	
	@SuppressWarnings("rawtypes")
	private boolean returnIsPrimitive(ClassMethod cMethod) {
		Class cReturnType = cMethod.getMethod().getReturnType();
		if(cReturnType.equals(Boolean.class) ||
				cReturnType.equals(Integer.class) ||
				cReturnType.equals(String.class)) {
			return true;
		}
		if(cMethod.getOutput() == null) {
			return true;
		}
		return false;
	}
	
	@Override
	public void dispose() {}
	
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

	@Override
	public Object[] getChildren(Object parentElement) {
		return getElements(parentElement);
	}

	@Override
	public Object getParent(Object element) {
		//TODO
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return getElements(element).length > 0;
	}
}