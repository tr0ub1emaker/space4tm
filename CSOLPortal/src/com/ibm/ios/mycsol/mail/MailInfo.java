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
 * DESCRIPTION: MailInfo.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.mail;

import java.util.Vector;

/**
 * This class represents a mail object.
 * 
 * @version 1.00 07-29-2015
 */
public class MailInfo {
	private String from;
	
	private String displayFrom;
	
	private String recipientTo;
	
	private String displayRecipientTo;
	
	private String recipientCc;
	
	private String displayRecipientCc;
	
	private String recipientBcc;
	
	private String displayRecipientBcc;
	
	private String subject;
	
	private String bodyText;
	
	private Vector<String> attachFiles;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getDisplayFrom() {
		return displayFrom;
	}

	public void setDisplayFrom(String displayFrom) {
		this.displayFrom = displayFrom;
	}

	public String getRecipientTo() {
		return recipientTo;
	}

	public void setRecipientTo(String recipientTo) {
		this.recipientTo = recipientTo;
	}

	public String getDisplayRecipientTo() {
		return displayRecipientTo;
	}

	public void setDisplayRecipientTo(String displayRecipientTo) {
		this.displayRecipientTo = displayRecipientTo;
	}

	public String getRecipientCc() {
		return recipientCc;
	}

	public void setRecipientCc(String recipientCc) {
		this.recipientCc = recipientCc;
	}

	public String getDisplayRecipientCc() {
		return displayRecipientCc;
	}

	public void setDisplayRecipientCc(String displayRecipientCc) {
		this.displayRecipientCc = displayRecipientCc;
	}

	public String getRecipientBcc() {
		return recipientBcc;
	}

	public void setRecipientBcc(String recipientBcc) {
		this.recipientBcc = recipientBcc;
	}

	public String getDisplayRecipientBcc() {
		return displayRecipientBcc;
	}

	public void setDisplayRecipientBcc(String displayRecipientBcc) {
		this.displayRecipientBcc = displayRecipientBcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}

	public Vector<String> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(Vector<String> attachFiles) {
		this.attachFiles = attachFiles;
	}
	
}
