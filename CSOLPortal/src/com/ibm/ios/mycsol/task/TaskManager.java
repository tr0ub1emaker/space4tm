package com.ibm.ios.mycsol.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.ios.mycsol.dao.DBConnection;
import com.ibm.ios.mycsol.mail.SMTPClient;
import com.ibm.ios.mycsol.util.Constants;
import com.ibm.ios.mycsol.util.PropertiesFactory;
import com.ibm.ios.mycsol.util.Timer;

public class TaskManager {
	
	/** Logger instance */
	public static final Logger logger = LogManager.getLogger("TaskLogger");
	
	/** Timer */
	public static final Timer taskTimer = new Timer();

	/**
	 * Initialize process is executed in this method.
	 */
	private void initialze() {
		logger.info("Task manager initialize start.");
		// Load and initialize the properties
		this.loadProperties();
		
		// DB connection Initialize
		DBConnection.initialize(PropertiesFactory.getStringProperties(Constants.DB_DRIVER),
				PropertiesFactory.getStringProperties(Constants.DB_URL),
				PropertiesFactory.getStringProperties(Constants.DB_USERNAME),
				PropertiesFactory.getStringProperties(Constants.DB_PASSWORD),
				PropertiesFactory.getStringProperties(Constants.DB_TRANS_ISOLATE_LEVEL));

		// Smtp initialize
		SMTPClient.getInstance().initialize(
				PropertiesFactory.getStringProperties(Constants.SMTP_HOST),
				PropertiesFactory.getStringProperties(Constants.SMTP_USERNAME),
				PropertiesFactory.getStringProperties(Constants.SMTP_PASSWORD));
		logger.info("Task manager initialize end.");
	}

	/**
	 * Load properties from properties files.
	 */
	private void loadProperties() {
		logger.info("Load properties start.");
		// Load the CSOL properties into the PropertiesFactory.
		PropertiesFactory.loadProperties(Constants.TASK_PROPERTIES_FILE);
		
		// Load the Query Data properties into the PropertiesFactory.
		PropertiesFactory.loadProperties(Constants.QUERY_PROPERTIES_FILE);
		logger.info("Load properties end.");
	}

	/**
	 * Launch CSOL Portal task.
	 * The initialization delay time, delay time and interval time 
	 * 		will be set in the properties file.
	 */
	public void launchTask() {
		logger.info("Launch task start.");
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleWithFixedDelay(new TaskProcessor(),
				PropertiesFactory.getIntProperties(Constants.INITIALDELAYTIME),
				PropertiesFactory.getIntProperties(Constants.DELAYTIME),
				TimeUnit.MINUTES);
		logger.info("Launch task end.");
	}

	/**
	 * The main method, the switch of CSOL Portal application.
	 * @param args
	 */
	public static void main(String[] args) {
		TaskManager taskManager = new TaskManager();
		taskManager.initialze();
		taskManager.launchTask();
	}
}
