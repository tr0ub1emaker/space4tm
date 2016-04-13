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
 * DESCRIPTION: Timer.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a simple timer that tracks and format time.
 * 
 * @version 1.00 07-29-2015
 */
public final class Timer {

	/** DateFormat Object for yyyy-MM-dd */
	private static final DateFormat dfForDate = new SimpleDateFormat("yyyy-MM-dd");
	
	/** DateFormat Object for yyyy-MM-dd hh:mm:ss.SSSSSS */
	private static final DateFormat dfForTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS");

	/**
	 * Get the current date in format yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentDate () {
		return dfForDate.format(System.currentTimeMillis());
	}
	
	/**
	 * Get the current time in format yyyy-MM-dd hh:mm:ss.SSSSSS
	 * @return
	 */
	public static String getCurrentTime () {
		return dfForTime.format(System.currentTimeMillis());
	}
	
	/**
	 * Format date with yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String formatDate (Date date) {
		return dfForDate.format(date);
	}
	
	/**
	 * Format time with yyyy-MM-dd hh:mm:ss.SSSSSS
	 * @param date
	 * @return
	 */
	public static String formatTime (Date date) {
		return dfForTime.format(date);
	}
}
