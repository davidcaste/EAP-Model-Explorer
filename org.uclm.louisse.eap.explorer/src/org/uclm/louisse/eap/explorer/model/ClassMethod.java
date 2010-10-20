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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClassMethod implements Comparable<ClassMethod>
{
	private Object object;
	private Method method;
	private Object[] args;
	private Object output;
	
	public ClassMethod(Object object, Method method) {
		this(object, method, new Object[]{});
	}
	
	public ClassMethod(Object object, Method method, Object[] args) {
		this.object = object;
		this.method = method;
		this.args = args;
	}
	
	public Object getObject() {
		return object;
	}
	
	public Method getMethod() {
		return method;
	}
	
	public String getName() {
		String name = method.getName();
		if(method.getName().startsWith("Get")) {
			name = name.substring(3, name.length());
		} else if(method.getName().equals("MiscData")) {
			StringBuffer sb = new StringBuffer();
			sb.append(name);
			sb.append('[');
			sb.append(args[0]);
			sb.append(']');
			name = sb.toString();
		}
		return name;
	}
	
	public Object getOutput() {
		if(output == null) {
			try {
				output = invokeMethod();
			} catch (InvocationTargetException e) {
				output = null;
			}
		}
		return output;
	}
	
	private Object invokeMethod() throws InvocationTargetException {
		Object result = null;
		try {
			result = method.invoke(object, args);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.err.printf("IllegalAccessException '%s' (%s): %s\n",
					method.getName(),
					Modifier.toString(method.getModifiers()), e.getMessage());
			//e.printStackTrace();
		} 
		return result;
	}

	@Override
	public int compareTo(ClassMethod o) {
		return getName().compareTo(o.getName());
	}
}