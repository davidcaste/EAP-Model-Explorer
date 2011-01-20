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
package org.uclm.louisse.eap.explorer;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.uclm.louisse.eap.explorer.model.ModelRoot;

/**
 * The activator class controls the plug-in life cycle
 */
public class EapExplorerPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.uclm.louisse.eap.explorer"; //$NON-NLS-1$

	// The shared instance
	private static EapExplorerPlugin plugin;

	private ModelRoot modelRoot = new ModelRoot();
	
	public static final String IMG_DIAGRAM_OBJECT = "diagram_object";
	public static final String IMG_PUBLIC = "Public";
	public static final String IMG_PROTECTED = "Protected";
	public static final String IMG_PRIVATE = "Private";
	public static final String IMG_BOOLEAN_VALUE = "BooleanValue";
	public static final String IMG_GENERIC_VALUE = "GenericValue";
	public static final String IMG_INTEGRAL_VALUE = "IntegralValue";
	public static final String IMG_REAL_VALUE = "RealValue";
	public static final String IMG_TEXT_VALUE = "TextValue";
	public static final String IMG_CLASS_DIAGRAM = "Diagram_Class";
	public static final String IMG_SEQUENCE_DIAGRAM = "Diagram_Sequence";
	public static final String IMG_PACKAGE = "Package";
	public static final String IMG_MODEL = "Model";
	public static final String IMG_CLASS = "Class";
	public static final String IMG_INTERFACE = "Interface";
	public static final String IMG_INSTANCE_SPECIFICATION = "InstanceSpecification";
	public static final String IMG_COMPONENT = "Component";
	public static final String IMG_COLLABORATION = "Collaboration";
	public static final String IMG_INTERACTION = "Interaction";
	public static final String IMG_INTERACTION_USE = "InteractionUse";
	public static final String IMG_LIFELINE = "Lifeline";
	public static final String IMG_CONTROLFLOW = "ControlFlow";
	public static final String IMG_COMBINEDFRAGMENT = "CombinedFragment";
	public static final String IMG_USECASE = "UseCase";
	public static final String IMG_COMMENT = "Comment";
	public static final String IMG_DATATYPE = "DataType";
	public static final String IMG_ENUMERATION = "Enumeration";
	public static final String IMG_ENUMERATIONLITERAL = "EnumerationLiteral";
	
	/**
	 * The constructor
	 */
	public EapExplorerPlugin() {
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry registry) {
		registerImage(registry, IMG_DIAGRAM_OBJECT, "diagram_object.gif");
		registerImage(registry, IMG_PUBLIC, "public_co.gif");
		registerImage(registry, IMG_PROTECTED, "protected_co.gif");
		registerImage(registry, IMG_PRIVATE, "private_co.gif");
		registerImage(registry, IMG_BOOLEAN_VALUE, "full/obj16/BooleanValue.gif");
		registerImage(registry, IMG_GENERIC_VALUE, "full/obj16/GenericValue.gif");
		registerImage(registry, IMG_INTEGRAL_VALUE, "full/obj16/IntegralValue.gif");
		registerImage(registry, IMG_REAL_VALUE, "full/obj16/RealValue.gif");
		registerImage(registry, IMG_TEXT_VALUE, "full/obj16/TextValue.gif");
		registerImage(registry, IMG_CLASS_DIAGRAM, "full/obj16/Diagram_Class.gif");
		registerImage(registry, IMG_SEQUENCE_DIAGRAM, "full/obj16/Diagram_Sequence.gif");
		registerImage(registry, IMG_PACKAGE, "full/obj16/Package.gif");
		registerImage(registry, IMG_MODEL, "full/obj16/Model.gif");
		registerImage(registry, IMG_COMPONENT, "full/obj16/Component.gif");
		registerImage(registry, IMG_CLASS, "full/obj16/Class.gif");
		registerImage(registry, IMG_INTERFACE, "full/obj16/Interface.gif");
		registerImage(registry, IMG_INSTANCE_SPECIFICATION, "full/obj16/InstanceSpecification.gif");
		registerImage(registry, IMG_COLLABORATION, "full/obj16/Collaboration.gif");
		registerImage(registry, IMG_INTERACTION, "full/obj16/Interaction.gif");
		registerImage(registry, IMG_INTERACTION_USE, "full/obj16/InteractionUse.gif");
		registerImage(registry, IMG_LIFELINE, "full/obj16/Lifeline.gif");
		registerImage(registry, IMG_CONTROLFLOW, "full/obj16/ControlFlow.gif");
		registerImage(registry, IMG_COMBINEDFRAGMENT, "full/obj16/CombinedFragment.gif");
		registerImage(registry, IMG_USECASE, "full/obj16/UseCase.gif");
		registerImage(registry, IMG_COMMENT, "full/obj16/Comment.gif");
		registerImage(registry, IMG_DATATYPE, "full/obj16/DataType.gif");
		registerImage(registry, IMG_ENUMERATION, "full/obj16/Enumeration.gif");
		registerImage(registry, IMG_ENUMERATIONLITERAL, "full/obj16/EnumerationLiteral.gif");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static EapExplorerPlugin getDefault() {
		return plugin;
	}

	public ModelRoot getModelRoot() {
		return modelRoot;
	}
	
	public void exitEA() {
		modelRoot.closeRepository();
	}
	
	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public Image getImage(String key) {
		return getImageRegistry().get(key);
	}
	
	private void registerImage(ImageRegistry registry, String key, String filename) {
		try {
			IPath path = new Path("icons/" + filename);
			URL url = FileLocator.find(getBundle(), path, null);
			if(url != null) {
				ImageDescriptor desc = ImageDescriptor.createFromURL(url);
				registry.put(key, desc);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
