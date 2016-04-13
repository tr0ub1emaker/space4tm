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
 * DESCRIPTION: DBConnection.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class represents a DB connection object.
 * Provide a singleton instance os DB connection.
 * 
 * @version 1.00 07-29-2015
 */
public class DBConnection {
	
	/** Logger instance */
	public static final Logger logger = LogManager.getLogger("TaskLogger");
	
	/** This is the static initializer which makes this class a singleton. */
	private static final DBConnection DB_CONNECTION = new DBConnection();

	/** The implementation class for the Database Connection. */
	private static Connection connection;

	/** This is the private constructor for the singleton DBConnection. */
	private DBConnection() {
	}
	
	/**
	 * This method returns the singleton instance of the DBConnection.
	 * @return
	 */
	public static DBConnection getInstance() {
		// Return the singleton instance of the DBConnectionPool.
		return DB_CONNECTION;
	}

	/**
	 * Initialize the DB connection.
	 * @param driver
	 * @param url
	 * @param username
	 * @param password
	 * @param defaultTransactionIsolationLevel
	 */
	public static void initialize(String driver, String url, String username,
			String password, String defaultTransactionIsolationLevel) {
		logger.info("DB connection initialize start.");
		try {
			// Load database driver if not already loaded.
			Class.forName(driver);
			
			// Establish network connection to database.
			connection = DriverManager.getConnection(url, username, password);
			
			// Set the Database Transaction Isolation Level to the default.
			// TRANSACTION_READ_UNCOMMITTED
			int transIsolation = 1;
			if("TRANSACTION_NONE".equals(defaultTransactionIsolationLevel)) {
				transIsolation = 0;
			}
			else if ("TRANSACTION_READ_COMMITTED".equals(defaultTransactionIsolationLevel)) {
				transIsolation = 2;
			}
			else if ("TRANSACTION_REPEATABLE_READ".equals(defaultTransactionIsolationLevel)) {
				transIsolation = 4;
			}
			else if ("TRANSACTION_SERIALIZABLE".equals(defaultTransactionIsolationLevel)) {
				transIsolation = 8;
			}
			connection.setTransactionIsolation(transIsolation);
		} catch (ClassNotFoundException e) {
			logger.error("", e);
		} catch (SQLException e) {
			logger.error("", e);
		}
		logger.info("DB connection initialize end.");
	}

	/**
	 * Return singleton connection.
	 * @return
	 */
	public static Connection getConnection() {
		logger.info("Generate singleton DB connection.");
		return connection;
	}

	/**
	 * Close DB connection.
	 * @throws Exception
	 */
	public void closeConnection() throws Exception {
		logger.info("DB connection close start.");
		if (connection != null) {
			if (!connection.isClosed()) {
				// If the connection is not closed, then close it.
				connection.close();
			}
		}
		else {
			throw new Exception("DB connection not exist.");
		}
		logger.info("DB connection close start.");
	}
	
	// TODO
	private boolean testConnection (Connection conn) {
		return false;
	}
}
