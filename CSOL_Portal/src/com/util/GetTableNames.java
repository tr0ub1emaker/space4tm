package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class GetTableNames {

	public List<String> getTables() {

		Connection ct = null;
		PreparedStatement statement = null;
		//Statement statement = null;
		ResultSet rs = null;

		List<String> tables = new ArrayList<>();
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			
			ct = DriverManager.getConnection("jdbc:db2://c03z0080.boulder.ibm.com:3700/gtmstagu", "vnddlzbz",
					"IBM19921026");
			
			String sql = "select name from sysibm.systables where type='T' and creator='GTM' ";
			
//			statement = ct.createStatement();
			statement = ct.prepareStatement(sql);
//			rs = statement.executeQuery(sql);
			rs = statement.executeQuery();
			
			if (rs!=null) {
				int count = 0;
				while (rs.next()) {
					count++;
					System.out.println(rs.getString("NAME"));
					tables.add(rs.getString("NAME"));
				}
				
				System.out.println("ÐÐÊý£º " + count);
			}else {
				
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
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				statement = null;
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
		return tables;

	}
	public static void main(String[] args) {
		 GetTableNames gt = new GetTableNames();
		 gt.getTables();
	}/**/
}
