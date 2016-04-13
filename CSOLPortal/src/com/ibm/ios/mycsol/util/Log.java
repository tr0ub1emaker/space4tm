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
 * DESCRIPTION: Log.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is log tool used to trace and write application information.
 * 
 * @version 1.00 07-29-2015
 */
public class Log {
	/** Logger instance */
	public static final Logger logger = LogManager.getLogger("TaskLogger");
	
//
//	/**
//	 * This method is for outputting debug level logs.
//	 * @param message
//	 */
//	public static void debug(Object message) {
//		logger.debug(message);
//	}
//	
//	/**
//	 * This method is for outputting info level logs.
//	 * @param message
//	 */
//	public static void info (Object message) {
//		logger.info(message);
//	}
//	
//	/**
//	 * This method is for outputting warn level logs.
//	 * @param message
//	 */
//	public static void warn (Object message) {
//		logger.warn(message);
//	}
//	
//	/**
//	 * This method is for outputting error level logs.
//	 * @param message
//	 */
//	public static void error (Object message) {
//		logger.error(message);
//	}
//	
//	/**
//	 * This method is for outputting fatal level logs.
//	 * @param message
//	 */
//	public static void fatal (Object message) {
//		logger.fatal(message);
//	}
//	
//	/**
//	 * This method is for outputting debug level logs and exception information.
//	 * @param message
//	 * @param e
//	 */
//	public static void debug (Object message, Throwable e) {
//		logger.debug(message, e);
//	}
//	
//	/**
//	 * This method is for outputting info level logs and exception information.
//	 * @param message
//	 * @param e
//	 */
//	public static void info (Object message, Throwable e) {
//		logger.info(message, e);
//	}
//	
//	/**
//	 * This method is for outputting warn level logs and exception information.
//	 * @param message
//	 * @param e
//	 */
//	public static void warn (Object message, Throwable e) {
//		logger.warn(message, e);
//	}
//	
//	/**
//	 * This method is for outputting error level logs and exception information.
//	 * @param message
//	 * @param e
//	 */
//	public static void error (Object message, Throwable e) {
//		logger.error(message, e);
//	}
//	
//	/**
//	 * This method is for outputting fatal level logs and exception information.
//	 * @param message
//	 * @param e
//	 */
//	public static void fatal (Object message, Throwable e) {
//		logger.fatal(message, e);
//	}
}
