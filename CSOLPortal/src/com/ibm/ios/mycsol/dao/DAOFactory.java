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
 * DESCRIPTION: DAOFactory.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.dao;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class represents a DAO factory to provide DAO object.
 * @version 1.00 07-29-2015
 */
public final class DAOFactory {

	/** Logger instance */
	public static final Logger logger = LogManager.getLogger("TaskLogger");
	
	/** The singleton DAO factory map  */
	private static final HashMap<String, DAO> daoFactory = new HashMap<String, DAO>();

	/**
	 * Static method to provide the singleton DAO instance.
	 * One type of SQL will only have one instance.
	  * @param sqlName
	 * @return
	 */
	public static DAO getDAO (String sqlName) {
		if (daoFactory.containsKey(sqlName)) {
			logger.info("DAO " + sqlName + " instance already exists, return that one.");
			return daoFactory.get(sqlName);
		}
		else {
			DAO dao = new DAO(sqlName);
			daoFactory.put(sqlName, dao);
			logger.info("DAO " + sqlName + " instance does not exist, create new one.");
			return dao;
		}
	}
}
