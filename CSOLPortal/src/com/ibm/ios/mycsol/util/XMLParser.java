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
 * DESCRIPTION: XMLParser.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.util;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ibm.ios.mycsol.bean.Notification;

/**
 * This class represents a XMLParser object.
 * Load XML template from properties file, build the whole, complete XML content for mail object.
 * 
 * @version 1.00 07-29-2015
 */
public class XMLParser {

	/** Logger instance */
	public static final Logger logger = LogManager.getLogger("TaskLogger");

	/** Member field of XML template */
	private String template;

	/**
	 * Private constructor for singleton instance.
	 */
	public XMLParser (String templateName) {
		this.template = templateName;
	}

	/**
	 * build the full notification with one Notification object.
	 * @param n
	 * @return
	 */
	public String buildNotification(Notification n) {
		logger.info("buildNotification start.");
		Document content = null;
		try {
			SAXReader reader = new SAXReader();
			content = reader.read(this.template);
			Element root = content.getRootElement();
			// Recursion set text to each element
			this.fillElements(root, n);
			// If PSSD is null, remove element from XML
			if (!"08".equals(n.getNOTIFICATION_TYPE())) {
				Element e = root.element("PSSD");
				root.remove(e);
			}
			// If CAD is null, remove element from XML
			if (!"09".equals(n.getNOTIFICATION_TYPE())) {
				Element e = root.element("CAD");
				root.remove(e);
			}
		} catch (DocumentException e) {
			logger.error(e);
		}
		logger.info("buildNotification end.");
		return content.asXML();
	}

	/**
	 * Private method, recursion model to fill the elements of the whole XML template.
	 * @param e
	 * @param n
	 */
	private void fillElements (Element e, Notification n) {
		logger.info("fillElements start.");
		List nodes = e.elements();
		if (nodes.size() > 0) {
			for (Object obj : nodes) {
				Element child = (Element) obj;
				this.fillElements(child, n);
			}
		}
		else {
			e.setText(n.getValue(e.getName()));
		}
		logger.info("fillElements end.");
	}
}
