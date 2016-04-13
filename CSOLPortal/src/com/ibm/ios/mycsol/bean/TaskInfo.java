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
 * DESCRIPTION: TaskInfo.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.bean;

/**
 * This class represents a bean object : BeanBase.
 * The member field must be the same as DB table which has the same name.
 * 
 * @version 1.00 07-29-2015
 */
public class TaskInfo extends BeanBase {
	private String ID;
	private String FINISH_NUM;
	private String NOTIFICATION_COUNT;
	private String START_TIME;
	private String FINISH_TIME;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFINISH_NUM() {
		return FINISH_NUM;
	}
	public void setFINISH_NUM(String fINISH_NUM) {
		FINISH_NUM = fINISH_NUM;
	}
	public String getNOTIFICATION_COUNT() {
		return NOTIFICATION_COUNT;
	}
	public void setNOTIFICATION_COUNT(String nOTIFICATION_COUNT) {
		NOTIFICATION_COUNT = nOTIFICATION_COUNT;
	}
	public String getSTART_TIME() {
		return START_TIME;
	}
	public void setSTART_TIME(String sTART_TIME) {
		START_TIME = sTART_TIME;
	}
	public String getFINISH_TIME() {
		return FINISH_TIME;
	}
	public void setFINISH_TIME(String fINISH_TIME) {
		FINISH_TIME = fINISH_TIME;
	}

}
