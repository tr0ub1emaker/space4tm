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
 * DESCRIPTION: Constants.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.util;

/**
 * This class defines CSOL constants.
 *
 * @version 1.00 07-29-2015
 */
public class Constants {
	
	/** Task manager properties file name */
	public static final String TASK_PROPERTIES_FILE = "TaskManager";
	
	/** Properties key of initialDelayTime in task manager properties file */
	public static final String INITIALDELAYTIME = "TaskManager.initialDelayTime";
	
	/** Properties key of delayTime in task manager properties file */
	public static final String DELAYTIME = "TaskManager.delayTime";
	
	/** Properties key of NotificationType01 in task manager properties file */
	public static final String NOTIFICATIONTYPE01 = "TaskManager.NotificationType01";
	
	/** Properties key of NotificationType02 in task manager properties file */
	public static final String NOTIFICATIONTYPE02 = "TaskManager.NotificationType02";
	
	/** Properties key of NotificationType03 in task manager properties file */
	public static final String NOTIFICATIONTYPE03 = "TaskManager.NotificationType03";
	
	/** Properties key of NotificationType04 in task manager properties file */
	public static final String NOTIFICATIONTYPE04 = "TaskManager.NotificationType04";
	
	/** Properties key of NotificationType05 in task manager properties file */
	public static final String NOTIFICATIONTYPE05 = "TaskManager.NotificationType05";
	
	/** Properties key of NotificationType06 in task manager properties file */
	public static final String NOTIFICATIONTYPE06 = "TaskManager.NotificationType06";
	
	/** Properties key of NotificationType07 in task manager properties file */
	public static final String NOTIFICATIONTYPE07 = "TaskManager.NotificationType07";
	
	/** Properties key of NotificationType08 in task manager properties file */
	public static final String NOTIFICATIONTYPE08 = "TaskManager.NotificationType08";
	
	/** Properties key of NotificationType09 in task manager properties file */
	public static final String NOTIFICATIONTYPE09 = "TaskManager.NotificationType09";
	
	/** Properties key of dbDriver in task manager properties file */
	public static final String DB_DRIVER = "TaskManager.dbDriver";
	
	/** Properties key of dbURL in task manager properties file */
	public static final String DB_URL = "TaskManager.dbURL";
	
	/** Properties key of dbDriver in task manager properties file */
	public static final String DB_USERNAME = "TaskManager.dbUserName";
	
	/** Properties key of dbPassword in task manager properties file */
	public static final String DB_PASSWORD = "TaskManager.dbPassword";
	
	/** Properties key of dbWaitIfBusy in task manager properties file */
	public static final String DB_WAIT_IF_BUSY = "TaskManager.dbWaitlfBusy";
	
	/** Properties key of dbTransactionIsolationLevel in task manager properties file */
	public static final String DB_TRANS_ISOLATE_LEVEL = "TaskManager.dbTransactionIsolationLevel";
	
	/** Properties key of dbConnectionValidationSQL in task manager properties file */
	public static final String DB_CONN_VALID_SQL = "TaskManager.dbConnectionValidationSQL";
	
	/** Properties key of smtpHost in task manager properties file */
	public static final String SMTP_HOST = "TaskManager.smtpHost";
	
	/** Properties key of smtpUserName in task manager properties file */
	public static final String SMTP_USERNAME = "TaskManager.smtpUserName";
	
	/** Properties key of smtpPassword in task manager properties file */
	public static final String SMTP_PASSWORD = "TaskManager.smtpPassword";
	
	/** Properties key of alertEmailFrom in task manager properties file */
	public static final String ALERT_EMAIL_FROM = "TaskManager.alertEmailFrom";
	
	/** Properties key of alertEmailDisplayFrom in task manager properties file */
	public static final String ALERT_EMAIL_DISPLAY_FROM = "TaskManager.alertEmailDisplayFrom";
	
	/** Properties key of alertEmailTo in task manager properties file */
	public static final String ALERT_EMAIL_TO = "TaskManager.alertEmailTo";
	
	/** Properties key of alertEmailDisplayTo in task manager properties file */
	public static final String ALERT_EMAIL_DISPLAY_TO = "TaskManager.alertEmailDisplayTo";
	
	/** Properties key of alertEmailCc in task manager properties file */
	public static final String ALERT_EMAIL_CC = "TaskManager.alertEmailCc";
	
	/** Properties key of alertEmailDisplayCc in task manager properties file */
	public static final String ALERT_EMAIL_DISPLAY_CC = "TaskManager.alertEmailDisplayCc";
	
	/** Properties key of alertEmailBcc in task manager properties file */
	public static final String ALERT_EMAIL_BCC = "TaskManager.alertEmailBcc";
	
	/** Properties key of alertEmailDisplayBcc in task manager properties file */
	public static final String ALERT_EMAIL_DISPLAY_BCC = "TaskManager.alertEmailDisplayBcc";
	
	/** Properties key of alertEmailSubject in task manager properties file */
	public static final String ALERT_EMAIL_SUBJECT = "TaskManager.alertEmailSubject";
	
	/** Properties key of alertEmailBodyText in task manager properties file */
	public static final String ALERT_EMAIL_BODY = "TaskManager.alertEmailBodyText";
	
	/** Properties key of ORDER_TAG in task manager properties file */
	public static final String ORDER_TAG = "%%FULLFILL_ORD_NUM%%";
	
	/** Properties key of NOTIFICATION_TYPE_TAG in task manager properties file */
	public static final String NOTIFICATION_TYPE_TAG = "%%NOTIFICATION_TYPE%%";
	
	/** Query data properties file name */
	public static final String QUERY_PROPERTIES_FILE = "QueryData";
	
	/** Properties key of getUpdatedRecordsSQL in query data properties file */
	public static final String GET_START_NUMBER_SQL = "QueryData.getStartNum";
	
	/** Properties key of getUpdatedRecordsSQL in query data properties file */
	public static final String GET_RECORDS_SQL = "QueryData.getUpdatedRecords";
	
	/** Properties key of insertTaskResultSQL in query data properties file */
	public static final String INSERT_RESULT_SQL = "QueryData.insertTaskResult";
	
	/** Properties key of the suffix of SQL in query data properties file */
	public static final String SQLNAME_SUFFIX = "_sql";
	
	/** Properties key of the suffix of SQL parameters in query data properties file */
	public static final String SQLPARAMLABEL_SUFFIX = "_paramLabel";
	
	/** Notification template XML file name */
	public static final String NOTIFICATION_TEMPLATE_XML = "notification_template.xml";
}
