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
 * DESCRIPTION: DAOResultFetcher.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.ios.mycsol.bean.BeanBase;

/**
 * This class represents a fetcher object,
 * 		which accept the DAO result and provide a fetch method to handle the result one by one.
 * 
 * @version 1.00 07-29-2015
 */
public class DAOResultFetcher {
	
	/** Logger instance */
	public static final Logger logger = LogManager.getLogger("TaskLogger");
	
	private PreparedStatement psmt;
	
	/** The resultSet that needed to be handle */
	private ResultSet resultSet;

	/**
	 * Protected constructor only for DAO object.
	 */
	protected DAOResultFetcher() {
	}
	
	/**
	 * Protected constructor only for DAO object.
	 */
	protected DAOResultFetcher(PreparedStatement ps, ResultSet rs) {
		this.psmt = ps;
		this.resultSet = rs;
	}

	/**
	 * Public method for all class to fetch resultSet one by one.
	 * And build one record to one bean object to return as result.
	 * @param bean
	 * @return
	 */
	public BeanBase fetch(BeanBase bean) {
		logger.info("DAO resultset fetch start.");
		if (this.resultSet == null) {
			logger.error("DB resultset is null.");
			return null;
		}
		if (bean == null) {
			logger.error("Data bean is null.");
			return null;
		}
		try {
			// When result set has next record, fetch next.
			if (this.resultSet.next()) {
				ResultSetMetaData rsmd = this.resultSet.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					bean.setValue(rsmd.getColumnName(i), this.resultSet.getString(i));
				}
			}
			// When result set reach the end, return null.
			else {
				return null;
			}
		} catch (SQLException e) {
			logger.error("SQLException occurred when fetching resultset.", e);
		}
		logger.info(bean);
		logger.info("DAO resultset fetch end.");
		return bean;
	}
	
	/**
	 * This method must be executed after the resultSet has reach the last one.
	 */
	public void release() {
		try {
			if (this.psmt != null) {
				this.psmt.close();
			}
			if (this.resultSet != null) {
				// Release the result set.
				this.resultSet.close();
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		logger.info("DAO result set releases successfully.");
	}
}
