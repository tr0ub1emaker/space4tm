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
 * DESCRIPTION: SMTPClient.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class represents a SMTP client object.
 * Provide a sendMail method for other classes.
 * 
 * @version 1.00 07-29-2015
 */
public class SMTPClient {
	
	/** Logger instance */
	public static final Logger logger = LogManager.getLogger("TaskLogger");
	
	/** The singleton instance of SMTPClient */
	private static final SMTPClient smtpClient = new SMTPClient();
	
	/** The session built at initialization step */
	private Session mailSession;

	/**
	 * Private constructor for the singleton instance.
	 */
	private SMTPClient() {
	}
	
	/**
	 * Public get method to provide the singleton SMTPClient instance.
	 * @return
	 */
	public static SMTPClient getInstance () {
		return smtpClient;
	}
	
	/**
	 * Public get method to provide the singleton mail session.
	 * @return
	 */
	public Session getMailSession () {
		return this.mailSession;
	}

	/**
	 * This method initialize the SMTP client.
	 * 
	 * @param smtpHost java.lang.String
	 * @throws mail.SMTPClientException An SMTPClient Exception occured.
	 */
	public void initialize(String smtpHost) {
		// Call initialize() with a null smtpUserName and smtpPassword.
		initialize(smtpHost, null, null);
	}
	
	/**
	 * This method initialize the SMTP client which required authentication.
	 * 
	 * @param smtpHost java.lang.String
	 * @param smtpUserName java.lang.String
	 * @param smtpPassword java.lang.String
	 * @throws mail.SMTPClientException An SMTPClient Exception occured.
	 */
	public void initialize (String smtpHost,
			String smtpUserName,
			String smtpPassword) {
		logger.info("SMTPClient initialize start.");
		Properties props = System.getProperties();

		// Setup mail server
		props.put("mail.smtp.host", smtpHost);

		// Set the SMTP authentication parameters.
		if ((!"".equals(smtpUserName)) && (!"".equals(smtpPassword))) {

			// Only allow registered users to send mail.
			props.put("mail.smtp.auth", "true");
			logger.info("mail.smtp.auth : true");
			logger.info("User : " + smtpUserName + ", " + "Password : " + smtpPassword);
			// Setup authentication, get session.
			this.mailSession =
				Session.getInstance(props, new MailAuthenticator(smtpUserName, smtpPassword));
		}
		else {
			logger.info("mail.smtp.auth : false");
			// Get the SMTP session.
			this.mailSession = Session.getDefaultInstance(props, null);
		}
		logger.info("SMTPClient initialize end.");
	}

	/**
	 * This method is for send one mail with one mail object.
	 * @param mail
	 */
	public void sendMail (ArrayList<MimeMessage> mailList) {
		logger.info("Send notification email start.");
		try {
			for (MimeMessage m : mailList) {
				// Set sent date
				m.setSentDate(new Date());
				// sent email
				Transport.send(m);
			}
		} catch (MessagingException e) {
			logger.error("", e);
		}
		logger.info("Send notification email end.");
	}
}
