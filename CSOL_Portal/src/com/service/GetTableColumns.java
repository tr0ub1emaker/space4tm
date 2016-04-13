package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class GetTableColumns {

	public void getColumns(HttpServletRequest request, String tableName) {

		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		System.out.println(" ----> GetTableColumns.java");
		System.out.println("       tableName --> " + tableName);
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			System.out.println("123");
			
			ct = DriverManager.getConnection("jdbc:db2://c03z0080.boulder.ibm.com:3700/gtmstagu", "vnddlzbz",
					"IBM19921026");
			
			System.out.println("45643");
			
			ps = (PreparedStatement) ct
					.prepareStatement("select NAME from sysibm.syscolumns where tbname = ? AND TBCREATOR = 'GTM'");
			ps.setObject(1, tableName);
			
			System.out.println(" getColumnsSuccess!");
			
			rs = ps.executeQuery();
			
			
			while (rs.next()) {
				System.out.println("[行号：" + rs.getRow() + "]\t" + rs.getString(1) );
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs = null;
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ps = null;
			}
			if (ct != null) {
				try {
					ct.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ct = null;
			}
		}
		// out.println("username"+username);

	}
}
