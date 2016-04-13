package com.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Old_InsertAllColumns {

	private final String db_driver = "com.ibm.db2.jcc.DB2Driver";
	private final String url = "jdbc:db2://c03z0080.boulder.ibm.com:3700/gtmstagu";
	private List<String> columnNames = new ArrayList<>();
	private Connection getconnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName(db_driver);
			conn = DriverManager.getConnection(url, "vnddlzbz", "IBM19921026");
		} catch (ClassNotFoundException ex) {

		}
		return conn;
	}
	
	//获取某一个table的所有column
	public List<String> queryColumns(String tableName) throws SQLException {

		Connection conn = this.getconnection();
		String strsql = "select COLUMN_NAME from sysibm.columns where table_schema = 'GTM' and table_name = ?";
		PreparedStatement pstmt = conn.prepareStatement(strsql);
		pstmt.setString(1, tableName);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			String fname = rs.getString("COLUMN_NAME");
			System.out.println("   ++ fname: " + fname);
			columnNames.add(fname);
			System.out.println("     -- column : " + fname);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return columnNames;
	}
	
	//获取某一个TableName, 用来向其table下添加columns
	private void matchTableName(String tableName, List<String> columns) {
		
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(new File("out.xml"));
			Element root = document.getRootElement();

			// 得到ROOT下所有的table
			List<Element> tableList = new ArrayList<>();
			tableList = root.elements();

			int index = 0;
			for (Element table : tableList) {

				index++;
				if (tableName.equals(table.attributeValue("name"))) {

					this.insertColumns(table, columns);

					//index的用法
					//tableList.add(index, element);
					break;
				}
			}

			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("GBK");
			XMLWriter writer;

			writer = new XMLWriter(new FileWriter("outWithColumns.xml"), format);
			writer.write(document);
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	// 向某一个table标签中添加所有这个table的columns
	private void insertColumns(Element root, List<String> columns) {

		Element column = null;

		for (int i = 0; i < columns.size(); i++) {
			column = root.addElement("column");
			column.addAttribute("name", columns.get(i));
		}
	}
	
	
	//列出所有tablename
	
	public static void main(String[] args) throws SQLException {
		
		System.out.println("START");
		Old_InsertAllColumns iac = new Old_InsertAllColumns();
		
		List<String> tableNames = new ArrayList<>();
		
		
		GetTableNames getTableNames = new GetTableNames();
		tableNames = getTableNames.getTables();
		
		for (int i = 0; i < tableNames.size(); i++) {
			String currentTableName = tableNames.get(i);
			System.out.println("处理当前table －－》　" + currentTableName);
			iac.columnNames = iac.queryColumns(currentTableName);
			iac.matchTableName(currentTableName, iac.columnNames);
			
			iac.columnNames.clear();
			
		}
	}
	
}
