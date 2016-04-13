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
 * DESCRIPTION: BeanBase.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is the base class for bean objects.
 * Provide several common fields and methods.
 * 
 * @version 1.00 07-29-2015
 */
public class BeanBase {
	
	/** Logger instance */
	public static final Logger logger = LogManager.getLogger("TaskLogger");
	
	/** The field list of member fields */
	protected Field[] fields;
	
	/** The member variables list of member fields */
	private HashSet<String> memberVariables;
	
	/** This map is used to store the value whose key is not in the member fields list */
	protected HashMap<String, Object> extendsVariables;

	/**
	 * Public constructor with no parameter.
	 */
	public BeanBase() {
		// Initialize member fields list in constructor.
		this.fields = this.getClass().getDeclaredFields();
		this.memberVariables = new HashSet<String>();
		for (Field f : this.fields) {
			this.memberVariables.add(f.getName());
		}
		// Initialize extends variables map in constructor.
		this.extendsVariables = new HashMap<String, Object>();
	}

	/**
	 * Public method for other class to get bean member fields list.
	 * @return
	 */
	public Field[] getColumnFields () {
		return this.fields;
	}

	/**
	 * Common method for bean objects to set value to member fields.
	 * @param key
	 * @param value
	 */
	public void setValue(String key, Object value) {
		try {
			if (this.memberVariables.contains(key)) {
				Method m = this.getClass().getDeclaredMethod("set" + key, value.getClass());
				Object[] args = {value};
				m.invoke(this, args);
			}
			else {
				this.extendsVariables.put(key, value);
			}
		} catch (IllegalArgumentException e) {
			logger.error("", e);
		} catch (IllegalAccessException e) {
			logger.error("", e);
		} catch (NoSuchMethodException e) {
			logger.warn("");
		} catch (InvocationTargetException e) {
			logger.error("", e);
		} catch (SecurityException e) {
			logger.error("", e);
		}
	}

	/**
	 * Common method for bean objects to get value from member fields.
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		Object value = "";
		try {
			if (this.memberVariables.contains(key)) {
				Method m = this.getClass().getDeclaredMethod("get" + key);
				value = m.invoke(this);
			}
			else {
				value = this.extendsVariables.get(key);
				if (value == null) {
					value = "";
				}
			}
		} catch (SecurityException e) {
			logger.error("", e);
		} catch (IllegalArgumentException e) {
			logger.error("", e);
		} catch (IllegalAccessException e) {
			logger.error("", e);
		} catch (NoSuchMethodException e) {
			logger.error("", e);
		} catch (InvocationTargetException e) {
			logger.error("", e);
		}
		return value.toString();
	}

	/**
	 * Override toString() of Object.
	 * @return
	 */
	public String toString() {
		String ret = "[";
		try {
			for (int i = 0; i < this.fields.length; i++) {
				Field f = this.fields[i];
				ret += f.getName() + ":" + this.getValue(f.getName()) + ",";
			}
			ret = ret.substring(0, ret.length() - 1) + "]";
		} catch (IllegalArgumentException e) {
			logger.error("", e);
		}
		return ret;
	}
	
	/**
	 * Override equals() of Object.
	 * @param e
	 * @return
	 */
	public boolean equals(BeanBase e) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
