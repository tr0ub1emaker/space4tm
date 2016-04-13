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
 * DESCRIPTION: TaskProcessor.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.task;

import java.util.ArrayList;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.ios.mycsol.bean.Notification;
import com.ibm.ios.mycsol.bean.TaskInfo;
import com.ibm.ios.mycsol.dao.DAOResultFetcher;
import com.ibm.ios.mycsol.mail.SMTPClient;
import com.ibm.ios.mycsol.util.Constants;
import com.ibm.ios.mycsol.util.Timer;
import com.ibm.ios.mycsol.util.XMLParser;

/**
 * This class represents the TaskProcessor for my CSOL.
 * This class, which implements interface Runnable, is the main processor of this application.
 * Application schedule to run this processor with default delay time.
 * Main process: check records updated after last task, 
 * 				 generate updated records, 
 * 				 fetch records and filter customers, 
 * 				 send alert email to customers.
 * @version 1.00 07-29-2015
 */
public class TaskProcessor implements Runnable {

	/** Logger instance */
	public static final Logger logger = LogManager.getLogger("TaskLogger");
	
	/** Instance of LogicHandler */
	private BusinessLogicHandler logicHandler;

	/** Instance of DAOResultFetcher, used to fetch records and filter customers. */
	private DAOResultFetcher daoResultFetcher;
	
	/** Notification object */
	private Notification notification;
	
	/** The time when processor started */
	private String startTime;
	
	/** The number current task start at */
	private long startNumber;
	
	/** The number current task finish at */
	private String finishNumber;

	/** List of MimeMessage object, the collection of mails that waiting to be sent. */
	private ArrayList<MimeMessage> mailList;

	/** XMLParser instance */
	private XMLParser xmlParser;
	
	/** SMTPClient instance */
	private SMTPClient smtpClient;

	/**
	 * This method execute before loop process.
	 * Used to initialize variables, prepare parameters for loop process.
	 */
	private void initProcess () {
		this.startTime = Timer.getCurrentTime();
		this.logicHandler = new BusinessLogicHandler();
	}
	
	/**
	 * This method is the condition, the switch of loop process.
	 * Used to check if necessary to start loop process.
	 * In this processor, the condition is checking the updated records after last task.
	 * @return
	 */
	private boolean initLoop () {
		logger.info("Task processor initLoop start.");
		// Get the start number of current task, which is the end number + 1 of the last task.
		this.startNumber = this.logicHandler.getStartNum();
		
		// Get the updated records count after last task.
		if (this.logicHandler.getRecordsCount(this.startNumber) <= 0) {
			// If no records found after last task.
			// Terminate the current processor.
			return false;
		}
		// If there are records been updated after last task.
		// Generate the result set.
		// Prepare result set fetcher for loop process.
		this.daoResultFetcher = this.logicHandler.getUpdateRecords(this.startNumber);
		this.notification = new Notification();
		this.mailList = new ArrayList<MimeMessage>();
		this.smtpClient = SMTPClient.getInstance();
		this.xmlParser = new XMLParser(Constants.NOTIFICATION_TEMPLATE_XML);
		logger.info("Task processor initLoop end.");
		return true;
	}
	
	/**
	 * This method is part of loop process.
	 * Used to fetch result set.
	 * Default result is 0, if no records left in result set, return -1 as result to end the loop process.
	 * @return
	 */
	private int fetchProcess () {
		logger.info("Task processor fetch process start.");
		// Default result
		int ret = 0;
		// Fetch Notification object
		Notification n = (Notification) this.daoResultFetcher.fetch(this.notification);
		// If fetcher is in the end, return -1.
		if (n == null) {
			ret = -1;
		}
		logger.info("Task processor fetch process end. ret:" + ret);
		return ret;
	}
	
	/**
	 * This method is the main process of the whole processor.
	 * Handling the data fetched in fetchProcess method.
	 * In this processor, process Notification object, prepare email for customers.
	 */
	private void mainProcess () {
		logger.info("Task processor main process start.");
		this.finishNumber = this.notification.getID();
		MimeMessage message = this.logicHandler.createMessage(this.smtpClient.getMailSession());
		this.notification.setValue("NOTIFICATION_TIME", Timer.formatTime(new Date()));
		String xmlContent = this.xmlParser.buildNotification(this.notification);
		message = this.logicHandler.setSubject(message, this.notification);
		message = this.logicHandler.setBodyText(message, null, xmlContent);
		this.mailList.add(message);
		logger.info("Task processor main process end.");
	}
	
	/**
	 * This method is executed after loop process.
	 * Used to count, integrate, backup or store the data processed in loop process.
	 * In this processor, store the TaskInfo into DB.
	 */
	private void endLoop() {
		logger.info("Task processor endLoop start.");
		// Send notifications.
		this.smtpClient.sendMail(this.mailList);

		// Store task result 
		// Initialize new TaskInfo object.
		TaskInfo taskInfo = new TaskInfo();
		// set parameters of TaskInfo object
		taskInfo.setSTART_TIME(this.startTime);
		taskInfo.setFINISH_TIME(Timer.getCurrentTime());
		taskInfo.setNOTIFICATION_COUNT(String.valueOf(this.mailList.size()));
		taskInfo.setFINISH_NUM(this.finishNumber);
		// insert into DB
		this.logicHandler.insertTaskResult(taskInfo);
		logger.info("Task processor endLoop end.");
	}
	
	/**
	 * This method is the end of the whole processor.
	 * Used to release resource initialized in this processor.
	 */
	private void endProcess () {
		logger.info("Task processor endProcess start.");
		this.daoResultFetcher.release();
		logger.info("Task processor endProcess end.");
	}
	
	/**
	 * Override run method.
	 * This method is the controller of the whole processor.
	 */
	public void run() {
		// Prepare parameters for loop process.
		initProcess();
		// Check before loop process.
		if (initLoop()) {
			// Fetch result set.
			while (fetchProcess() != -1) {
				// main process. Filter customers is processed here.
				mainProcess();
			}
			// end loop. Send alert email to customers.
			endLoop();
		}
		// release resources.
		endProcess();
	}

}
