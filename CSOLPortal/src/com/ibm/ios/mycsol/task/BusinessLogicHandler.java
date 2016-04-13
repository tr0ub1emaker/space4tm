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
 * DESCRIPTION: LogicHandler.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.task;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.ios.mycsol.bean.Notification;
import com.ibm.ios.mycsol.bean.TaskInfo;
import com.ibm.ios.mycsol.dao.DAO;
import com.ibm.ios.mycsol.dao.DAOFactory;
import com.ibm.ios.mycsol.dao.DAOResultFetcher;
import com.ibm.ios.mycsol.util.Constants;
import com.ibm.ios.mycsol.util.PropertiesFactory;

/**
 * This class represents a handler that is the place to handle business logic.
 * 
 * @version 1.00 07-29-2015
 */
public class BusinessLogicHandler {
	
	/** Logger instance */
	public static final Logger logger = LogManager.getLogger("TaskLogger");
	
	/**
	 * Access DB, execute SQL and get the start number of current task.
	 * @return
	 */
	public long getStartNum () {
		logger.info("getStartNum start.");
		DAO dao = DAOFactory.getDAO(Constants.GET_START_NUMBER_SQL);
		ArrayList<TaskInfo> ti = dao.executeSelect(TaskInfo.class);
		long startNum = 0;
		if (ti.size() > 0) {
			startNum = Long.parseLong(ti.get(0).getFINISH_NUM());
		}
		logger.info("getStartNum end. startNum:" + (startNum + 1));
		return startNum + 1;
	}

	/**
	 * Access DB, execute SQL and get the count of records that current task proceed.
	 * @param startNumber
	 * @return
	 */
	public long getRecordsCount (long startNumber) {
		logger.info("getRecordsCount start.");
		DAO dao = DAOFactory.getDAO(Constants.GET_RECORDS_SQL);
		dao.setParameter("ID", String.valueOf(startNumber));
		int ret = dao.getSelectCount();
		logger.info("getRecordsCount end. RecordsCount:" + ret);
		return ret;
	}
	
	/**
	 * Access DB, execute SQL and get all of the updated records after last task.
	 * Return a fetcher as result for other class to handle the resultSet.
	 * @param startNumber
	 * @return
	 */
	public DAOResultFetcher getUpdateRecords (long startNumber) {
		logger.info("getUpdateRecords start.");
		DAO dao = DAOFactory.getDAO(Constants.GET_RECORDS_SQL);
		dao.setParameter("ID", String.valueOf(startNumber));
		DAOResultFetcher daoResultFetcher = dao.getDAOResultFetcher();
		logger.info("getUpdateRecords end.");
		return daoResultFetcher;
	}
	
	/**
	 * Access DB, execute SQL and insert the result of current task.
	 * @param taskInfo
	 * @return
	 */
	public boolean insertTaskResult (TaskInfo taskInfo) {
		logger.info("getUpdateRecords start.");
		DAO dao = DAOFactory.getDAO(Constants.INSERT_RESULT_SQL);
		dao.setParameter(taskInfo);
		boolean ret = dao.executeUpdate();
		logger.info("getUpdateRecords end." + ret);
		return ret;
	}

	/**
	 * 
	 * @return
	 */
	public MimeMessage createMessage (Session mailSession) {
		logger.info("createMessage start.");
		// Initialize MimeMessage
		MimeMessage m = new MimeMessage(mailSession);
		try {
			// Set email from
			if ("".equals(PropertiesFactory.getStringProperties(Constants.ALERT_EMAIL_DISPLAY_FROM))) {
				m.setFrom(new InternetAddress(PropertiesFactory
						.getStringProperties(Constants.ALERT_EMAIL_FROM)));
			}
			else {
				m.setFrom(new InternetAddress(
						PropertiesFactory.getStringProperties(Constants.ALERT_EMAIL_FROM),
						PropertiesFactory.getStringProperties(Constants.ALERT_EMAIL_DISPLAY_FROM)));
			}

			// Set RecipientTo
			String[] to = PropertiesFactory.getStringProperties(Constants.ALERT_EMAIL_TO).split(",");
			for (String strTO : to) {
				if (!"".equals(strTO)) {
					m.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(strTO)});
				}
			}

//			// Set RecipientCc
//			String[] cc = PropertiesFactory.getStringProperties(Constants.ALERT_EMAIL_CC).split(",");
//			for (String strCc : cc) {
//				if (!"".equals(strCc)) {
//					m.addRecipients(Message.RecipientType.CC, strCc);
//				}
//			}
//
//			// Set RecipientBcc
//			String[] bcc = PropertiesFactory.getStringProperties(Constants.ALERT_EMAIL_BCC).split(",");
//			for (String strBcc : bcc) {
//				if (!"".equals(strBcc)) {
//					m.addRecipients(Message.RecipientType.BCC, new InternetAddress(strBcc));
//				}
//			}

			// Set header
//			m.setHeader("X-Mailer", "CSOL Notification");

		} catch (MessagingException e) {
			logger.error("", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		}
		logger.info("createMessage end.");
		return m;
	}

	/**
	 * 
	 * @param m
	 * @param attachFiles
	 * @param bodyText
	 * @return
	 */
	public MimeMessage setSubject (MimeMessage m, Notification n) {
		logger.info("setSubject start.");
		String subject = "";
		try {
			subject = PropertiesFactory.getStringProperties(
					Constants.ALERT_EMAIL_SUBJECT);
			subject = subject.replace(Constants.ORDER_TAG, n.getFULLFILL_ORD_NUM());
			subject = subject.replace(Constants.NOTIFICATION_TYPE_TAG,
					PropertiesFactory.getStringProperties(
							"TaskManager.NotificationType" + n.getNOTIFICATION_TYPE()));
			m.setSubject(subject);
		} catch (MessagingException e) {
			logger.error("", e);
		}
		logger.info("setSubject end. Subject:" + subject);
		return m;
	}
	
	/**
	 * 
	 * @param m
	 * @param attachFiles
	 * @param bodyText
	 * @return
	 */
	public MimeMessage setBodyText (MimeMessage m, ArrayList<File> attachFiles, String bodyText) {
		logger.info("setBodyText start.");
		// Set content and attach files
		try {
			if (attachFiles == null) {
				MimeBodyPart defaultBodyPart = new MimeBodyPart();
				defaultBodyPart.setText(PropertiesFactory.getStringProperties(Constants.ALERT_EMAIL_BODY));
				MimeBodyPart notificationBodyPart = new MimeBodyPart();
				notificationBodyPart.setText(bodyText);
				MimeMultipart mp = new MimeMultipart();
				mp.addBodyPart(defaultBodyPart);
				mp.addBodyPart(notificationBodyPart);
				m.setContent(mp);
			} else {
				for (File file : attachFiles) {
					// Add attach files
					MimeBodyPart bodyPart = new MimeBodyPart();
					bodyPart.setText(bodyText);
					bodyPart.attachFile(file);
					MimeMultipart mp = new MimeMultipart();
					mp.addBodyPart(bodyPart);
					m.setContent(mp);
				}
			}
		} catch (MessagingException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		logger.info("setBodyText end.");
		return m;
	}
	
}
