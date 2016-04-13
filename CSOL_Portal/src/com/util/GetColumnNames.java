package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetColumnNames {

	private final String db_driver = "com.ibm.db2.jcc.DB2Driver";
	private final String url = "jdbc:db2://c03z0080.boulder.ibm.com:3700/gtmstagu";

	public GetColumnNames() {
		
	}

	public List<String> queryColumns(String tableName) throws SQLException {

		List<String> columns = new ArrayList<>();

		Connection conn = this.getconnection();
		String strsql = "select COLUMN_NAME from sysibm.columns where table_schema = 'GTM' and table_name = ?";
		PreparedStatement pstmt = conn.prepareStatement(strsql);
		pstmt.setString(1, tableName);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String fname = rs.getString("COLUMN_NAME");
			columns.add(fname);
			System.out.println(" -- column : " + fname);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return columns;
	}

	private Connection getconnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName(db_driver);
			conn = DriverManager.getConnection(url, "vnddlzbz", "IBM19921026");
		} catch (ClassNotFoundException ex) {

		}
		return conn;
	}

	// main
	/*public static void main(String[] args) throws SQLException {
		GetColumnNames jdbctest1 = new GetColumnNames();
		List<String> listl = jdbctest1.queryColumns();
		for (int i = 0; i < listl.size(); i++) {
			System.out.println(listl.get(i));
		}
	}*/

}
