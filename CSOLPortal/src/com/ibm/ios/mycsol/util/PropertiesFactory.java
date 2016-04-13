/*
 *============================================================================
 *
 * IBM Confidential
 *
 * (C) Copyright IBM Corp. 2015.
 *
 * The source code for this program is not published or otherwise divested of
 * its trade secrets, irrespective of what has been deposited with the U.S.
 * Copyright office.
 *
 * ===========================================================================
 * Module Information:
 *
 * DESCRIPTION: Properties.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.util;

import java.util.HashMap;
import java.util.PropertyResourceBundle;

/**
 * This class represents a central repository to store application configuration
 * properties and constants.  This class provides convenient methods for
 * loading .properties resource bundles, and querying the keys from anywhere
 * in an application.  This class implements the Singleton design pattern.
 *
 * @version 1.00 07-29-2015
 */
public class PropertiesFactory {

	/** This is the static initializer for the singleton. */
	private static final PropertiesFactory PROPERTIES_FACTORY = new PropertiesFactory();

	/** This is the main data structure used to store all properties. */
	private HashMap<String, Object> propertiesStore = new HashMap<String, Object>();

	/**
	 * This is the private constructor for the PropertiesFactory which makes it a singleton.
	 */
	private PropertiesFactory() {
	}

	/**
	 * This method returns the Singleton instance for the PropertiesFactory.
	 * @return
	 */
	public static PropertiesFactory getInstance() {
		// Return the PropertiesFactory instance.
		return PROPERTIES_FACTORY;
	}

	/**
	* This method will load all the keys from a given properties file.
	 * @param propertiesFileName
	 * @throws Exception
	 */
	public static void loadProperties(String propertiesFileName) {

		// Obtain the PropertyResourceBundle from the given class name.
		PropertyResourceBundle configBundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle(propertiesFileName);

		// Loop through all the keys in the Properties file and load the Properties files
		// specified by the key values into the HashMap.
		for (String bundleKey : configBundle.keySet()) {

			PROPERTIES_FACTORY.propertiesStore.put(propertiesFileName + "." + bundleKey,
					configBundle.getObject(bundleKey));
		}
	}
	
	/**
	* This method returns a property from the PropertiesFactory as an Object.
	* @param  propertyKey
	* @return 
	* @throws Exception
	*/
	public static Object getProperties(String propertyKey) {
		// Get the property for the specified key as an Object.
		Object value = PROPERTIES_FACTORY.propertiesStore.get(propertyKey);
		if (value == null) {
			value = "";
		}
		// Return the result.
		return value;
	}

	/**
	* This method returns a property from the PropertiesFactory as a String.
	* @param  propertyKey
	* @return 
	* @throws Exception
	*/
	public static String getStringProperties (String propertyKey) {
		// Get the property for the specified key as a String and return.
		return (String)getProperties(propertyKey);
	}

	/**
	* This method returns a property from the PropertiesFactory as an int.
	* @param  propertyKey
	* @return 
	* @throws Exception
	*/
	public static int getIntProperties (String propertyKey) {
		// Get the property for the specified key as an int and return.
		return Integer.parseInt(getStringProperties(propertyKey));
	}

	/**
	* This method returns a property from the PropertiesFactory as a boolean.
	* @param  propertyKey
	* @return 
	* @throws Exception
	*/
	public static boolean getBooleanProperties (String propertyKey) {
		// Get the property for the specified key as a boolean and return.
		return getStringProperties(propertyKey).equalsIgnoreCase("TRUE");
	}
}
