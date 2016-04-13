package com.newutil;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertTablesandColumns {

	private final String db_driver = "com.ibm.db2.jcc.DB2Driver";
	private final String url = "jdbc:db2://c03z0080.boulder.ibm.com:3700/gtmstagu";
	private final String userName = "vnddlzbz";
	private final String passWord = "IBM19921026";
	
	

//	public InsertTablesandColumns() {
//	}

	private Connection getConnection() {

		Connection conn = null;

		try {
			Class.forName(db_driver);
			conn = DriverManager.getConnection(url, userName, passWord);
			System.out.println("test");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	private void executeTableName(Connection conn){
		
		//get table names
		String sql_GetTableNames = "select name from sysibm.systables where type='T' and creator='GTM' ";
		PreparedStatement state = null;
		ResultSet rs = null;

		List<String> tables = new ArrayList<>();
		
		try {			
			String sql = sql_GetTableNames;
			state = conn.prepareStatement(sql);
			rs = state.executeQuery();
			
			if (rs!=null) {
				int count = 0;
				while (rs.next()) {
					count++;
					System.out.println(" --TableName: " + rs.getString("NAME"));
					tables.add(rs.getString("NAME"));
				}
				
				System.out.println(" --Lines£º " + count);
			}else {
				System.out.println(" ERROR: NO INFO QUERIED!");
			}
			
			System.out.println(" --Table Lines: " + tables.size());
			
			for (int i = 0; i < tables.size(); i++) {
				System.out.println("  [" + i + "] table is: " + tables.get(i));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs = null;
			}
			if (state != null) {
				try {
					state.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				state = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		InsertTablesandColumns itac = new InsertTablesandColumns();
		Connection conn = itac.getConnection();
		itac.executeTableName(conn);
	}
}
