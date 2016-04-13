package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class Test_GetColumnsByTableName extends DefaultHandler {


	public List<String> getColumnsfromTable(String tableName){
		List<String> columns = new ArrayList<>();
		
		Connection ct = null;
		PreparedStatement pstatement = null;
		//Statement statement = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			
			ct = DriverManager.getConnection("jdbc:db2://c03z0080.boulder.ibm.com:3700/gtmstagu", "vnddlzbz",
					"IBM19921026");
			
			String sql = "SELECT COLNAME FROM SYSCAT.COLUMNS WHERE TABNAME='"+ tableName +"' AND TABSCHEMA='GTM' ";
			//String sql = "SELECT COLNAME FROM SYSCAT.COLUMNS WHERE TABNAME='"+ tableName +"' AND TABSCHEMA='GTM' ";
			
			pstatement = ct.prepareStatement(sql);
			
			rs = pstatement.executeQuery();
			System.out.println(sql);
			while (rs.next()) {
				System.out.println(rs.getString("COLNAME"));
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
			if (pstatement != null) {
				try {
					pstatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pstatement = null;
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
		return columns;

	}
	
	public static void main(String[] args) {
		Test_GetColumnsByTableName ici = new Test_GetColumnsByTableName();
		ici.getColumnsfromTable("SHIP_STATUS_SUMRY");
	}
	
}
