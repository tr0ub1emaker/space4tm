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
 * DESCRIPTION: DAO.java
 * ===========================================================================
 */
package com.ibm.ios.mycsol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.ios.mycsol.bean.BeanBase;
import com.ibm.ios.mycsol.util.Constants;
import com.ibm.ios.mycsol.util.PropertiesFactory;

/**
 * This class represents a DB access object.
 * Those methods is only for CSOL.
 * 
 * @version 1.00 07-29-2015
 */
public class DAO {
	
	/** Logger instance */
	public static final Logger logger = LogManager.getLogger("TaskLogger");
	
	/** The name of the SQL */
	private String sqlName;
	
	/** The SQL of the DAO */
	private String sql;

	/** DB connection for this DAO */
	private Connection conn;

	/** Parameters list for the PreparedStatement */
	private String[] paramLabels;
	
	/** Parameters value list for the PreparedStatement */
	private HashMap<String, String> paramValues = new HashMap<String, String>();

	/**
	 * Public constructor, called with sql of this DAO.
	 * @param sqlName
	 */
	public DAO (String sqlName) {
		this.sqlName = sqlName;
		this.initialize(sqlName);
	}
	
	/**
	 * Initialize PropertiesFactory, member field: SQL,
	 * 		parameters for PreparedStatement and the DB connection.
	 * @param sqlName
	 */
	private void initialize(String sqlName) {
		logger.info("DAO " + sqlName + " initialize start.");
		this.sql = PropertiesFactory.getStringProperties(sqlName
				+ Constants.SQLNAME_SUFFIX);
		String params = PropertiesFactory.getStringProperties(sqlName
				+ Constants.SQLPARAMLABEL_SUFFIX);
		if (!"".equals(params)) {
			this.paramLabels = params.split(",");
		}
		this.conn = DBConnection.getConnection();
		logger.info("DAO " + sqlName + " initialize end.");
	}

	/**
	 * This method is used to generate the count of one SQL.
	 * @return
	 */
	public int getSelectCount() {
		logger.info("DAO " + this.sqlName + " getSelectCount start.");
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			logger.info(this.sql);
			ps = this.conn.prepareStatement(this.sql);
			this.prepareParameters(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				result ++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.info("DAO " + this.sqlName + " getSelectCount end.");
		return result;
	}
	
	/**
	 * This method is used to execute one SQL and return a bean list as the result.
	 * @param Class<T>
	 * @return
	 */
	public <T> ArrayList executeSelect(Class<T> elementClass) {
		logger.info("DAO " + this.sqlName + " executeSelect start.");
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BeanBase> arr = new ArrayList<BeanBase>();
		try {
			logger.info(this.sql);
			ps = this.conn.prepareStatement(this.sql);
			this.prepareParameters(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				BeanBase element = (BeanBase) elementClass.newInstance();
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					element.setValue(rsmd.getColumnName(i), rs.getString(i).trim());
				}
				arr.add(element);
			}
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				logger.error("", e);
			}
		}
		logger.info("DAO " + this.sqlName + " executeSelect end.");
		return arr;
	}

	/**
	 * This method is for great count result SQL, create a fetcher for the result.
	 * return a fetcher as result.
	 * @return
	 */
	public DAOResultFetcher getDAOResultFetcher() {
		logger.info("DAO " + this.sqlName + " getDAOResultFetcher start.");
		PreparedStatement ps = null;
		ResultSet rs = null;
		DAOResultFetcher daoResultFetcher = null;
		try {
			logger.info(this.sql);
			ps = this.conn.prepareStatement(this.sql);
			this.prepareParameters(ps);
			rs = ps.executeQuery();
			daoResultFetcher = new DAOResultFetcher(ps, rs);
		} catch (Exception e) {
			logger.error("", e);
		}
		logger.info("DAO " + this.sqlName + " getDAOResultFetcher end.");
		return daoResultFetcher;
	}
	
	/**
	 * This method is used to insert or update data to DB.
	 * @return
	 */
	public boolean executeUpdate() {
		logger.info("DAO " + this.sqlName + " executeUpdate start.");
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			logger.info(this.sql);
			ps = this.conn.prepareStatement(this.sql);
			this.prepareParameters(ps);
			int iCol = ps.executeUpdate();
			if (iCol > 0) {
				result = true;
			}
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				logger.error("", e);
			}
		}
		logger.info("DAO " + this.sqlName + " executeUpdate end.");
		return result;
	}
	
	/**
	 * Set the value of one parameter of the PreparedStatement.
	 * @param key
	 * @param value
	 */
	public void setParameter (String key, String value) {
		logger.info("DAO " + this.sqlName + " setParameter(key, value) start.");
		logger.info("Parameter : {key:" + key + ",value:" + value + "}");
		this.paramValues.put(key, value);
		logger.info("DAO " + this.sqlName + " setParameter(key, value) end.");
	}
	
	/**
	 * Set values of parameters of the PreparedStatement with one bean object.
	 * @param bean
	 */
	public void setParameter(BeanBase bean) {
		logger.info("DAO " + this.sqlName + " setParameter(BeanBase) start.");
		for (String key : this.paramLabels) {
			logger.info("Parameter : {key:" + key + ",value:"
					+ bean.getValue(key) + "}");
			this.paramValues.put(key, bean.getValue(key));
		}
		logger.info("DAO " + this.sqlName + " setParameter(BeanBase) end.");
	}

	/**
	 * 
	 * @param ps
	 */
	private void prepareParameters(PreparedStatement ps) {
		try {
			if (this.paramLabels != null) {
				for (int i = 0; i < this.paramLabels.length; i++) {
					ps.setString(i + 1, this.paramValues.get(this.paramLabels[i]));
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
	}

}
