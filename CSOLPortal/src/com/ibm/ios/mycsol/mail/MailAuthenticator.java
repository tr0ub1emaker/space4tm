/*
 *============================================================================
 *
 * IBM Confidential
 *
 * (C) Copyright IBM Corp. 2003.
 *
 * The source code for this program is not published or otherwise divested of
 * its trade secrets, irrespective of what has been deposited with the U.S.
 * Copyright office.
 *
 * ===========================================================================
 * Module Information:
 *
 * DESCRIPTION: MailAuthenticator.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * This class represents a custom Authentication for SMTP. It overrides the
 * PasswordAuthentication class in javax.mail.Authenticator.
 *
 * @version 1.00 07-29-2015
 */
public class MailAuthenticator extends Authenticator {

	/** The SMTP User Name. */
	private String userName;

	/** The SMTP Password. */
	private String password;

	/**
	 * This is the constructor for the MailAuthenticator class.
	 * @param aName The user name.
	 * @param aPassword The password.
	 */
	public MailAuthenticator(String aName, String aPassword) {
		// Set the User Name.
		this.userName = aName;

		// Set the Password.
		this.password = aPassword;
	}

	/**
	 * This method sets the SMTP User Name.
	 * @param aName The SMTP User Name.
	 */
	public void setUserName(String aName) {
		// Set the SMTP User Name.
		this.userName = aName;
	}

	/**
	 * This method sets the SMTP Password.
	 * @param aPassword The SMTP password.
	 */
	public void setPassword(String aPassword) {
		// Set the SMTP Password.
		this.password = aPassword;
	}

	/**
	 * This method overrides the PasswordAuthentication method in the
	 * javax.mail.Authenticator class to provide the userName and
	 * password from another source.
	 * 
	 * @return javax.mail.PasswordAuthentication
	 */
	public PasswordAuthentication getPasswordAuthentication() {
		// Return a new PasswordAuthentication with the UserName and Password.
		return new PasswordAuthentication(this.userName, this.password);
	}
}