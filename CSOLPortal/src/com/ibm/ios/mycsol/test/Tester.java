package com.ibm.ios.mycsol.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.ios.mycsol.bean.Notification;
import com.ibm.ios.mycsol.bean.TaskInfo;
import com.ibm.ios.mycsol.dao.DAO;
import com.ibm.ios.mycsol.dao.DAOFactory;
import com.ibm.ios.mycsol.dao.DBConnection;
import com.ibm.ios.mycsol.mail.MailInfo;
import com.ibm.ios.mycsol.mail.SMTPClient;
import com.ibm.ios.mycsol.util.Constants;
import com.ibm.ios.mycsol.util.PropertiesFactory;
import com.ibm.ios.mycsol.util.XMLParser;

public class Tester {

	private ResultSet rs ;
	public static void main(String[] args) {
		Tester t = new Tester();
		t.testMail();
	}
	
	private void testReflect () {
		Notification n = new Notification();
		Method m;
		try {
			m = n.getClass().getDeclaredMethod("setID", String.class);
			m.invoke(n, "2");
			System.out.println(n.getID());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void testXML () {
		XMLParser x = new XMLParser("notification_template.xml");
		PropertiesFactory p = PropertiesFactory.getInstance();
		p.loadProperties(Constants.TASK_PROPERTIES_FILE);//TaskManager
		p.loadProperties(Constants.QUERY_PROPERTIES_FILE);//QueryData
		DBConnection.initialize("com.ibm.db2.jcc.DB2Driver",
				"jdbc:db2://localhost:50000/AMLOCAL",
				"LiJunda",
				"16899052aa",
				"TRANSACTION_READ_UNCOMMITTED");
		DAO dao = DAOFactory.getDAO("QueryData.getUpdatedRecords");
		dao.setParameter("ID", "1");
		ArrayList<Notification> ti = dao.executeSelect(Notification.class);
		if (ti.size() > 0) {
			Notification n = ti.get(0);
			System.out.println(n.toString());
			String s = x.buildNotification(n);
			System.out.println(s);
		}
		
	}
	
	private void testMail (){
		MailInfo m = new MailInfo();
		m.setFrom("vndjdli@cn.ibm.com");
		m.setDisplayFrom("IOS CSOL Portal 1.0 - Server");
		m.setRecipientTo("vndjdli@cn.ibm.com");
		m.setDisplayRecipientTo("Junda Li");
		m.setRecipientCc("yehl@cn.ibm.com");
		m.setDisplayRecipientCc("Hongli Ye");
		m.setRecipientBcc("vnddlzbz@cn.ibm.com");
		m.setDisplayRecipientBcc("Bo Zhang");
		m.setSubject("CSOL Portal Notification Test Mail");
		m.setBodyText("This is the test body text.");
		SMTPClient.getInstance().initialize(
				"d03av02.boulder.ibm.com",
				"",
				"");
		SMTPClient s = SMTPClient.getInstance();
//		s.sendMail(m);
	}
	
	private void testDAO () {
		PropertiesFactory p = PropertiesFactory.getInstance();
		p.loadProperties(Constants.TASK_PROPERTIES_FILE);
		p.loadProperties(Constants.QUERY_PROPERTIES_FILE);
		DBConnection.initialize(p.getStringProperties(Constants.DB_DRIVER),
				p.getStringProperties(Constants.DB_URL),
				p.getStringProperties(Constants.DB_USERNAME),
				p.getStringProperties(Constants.DB_PASSWORD),
				p.getStringProperties(Constants.DB_TRANS_ISOLATE_LEVEL));
		DAO dao = DAOFactory.getDAO(Constants.GET_START_NUMBER_SQL);
		ArrayList<TaskInfo> ti = dao.executeSelect(TaskInfo.class);
		long startNum =2345245;
		if (ti.size() > 0) {
			startNum = Long.parseLong(ti.get(0).getFINISH_NUM());
		}
		System.out.println(startNum);
	}
	
	private void testLog () {
		Exception e = new Exception("afadsfadf");

	}
	
	private void testBean () {
		TaskInfo t = new TaskInfo();
		t.setValue("IID", "123");
		System.out.println(t.getValue("IID"));
	}
	
	private void testTimer() {
		long time = System.currentTimeMillis();
		System.out.println(time);
		Date d = new Date();
		System.out.println(d.toString());
		System.out.println(d.getTime());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS", Locale.US);
		System.out.println(df.format(d));
	}
	
	public void testDB () {
		try {
			DBConnection.initialize("com.ibm.db2.jcc.DB2Driver",
					"jdbc:db2://localhost:50000/AMLOCAL",
					"LiJunda",
					"16899052aa",
					"TRANSACTION_READ_UNCOMMITTED");
			Connection conn = DBConnection.getConnection();
//			PreparedStatement ps = conn.prepareStatement("INSERT INTO GTM.CSOL_NOTIFICATION VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//			ps.setString(1, "2");
//			ps.setString(2, "123");
//			ps.setString(3, "456");
//			ps.setString(4, "FFORDNUMFX04ASD");
//			ps.setString(5, "PONUMGSFGSGREDSF");
//			ps.setString(6, "REFNUMDFSDFDSFSF");
//			ps.setString(7, "I_C");
//			ps.setString(8, "UI_E_P");
//			ps.setString(9, "COMNUM123");
//			ps.setString(10, "CUSTNAME");
//			ps.setString(11, "UISOLDCUST");
//			ps.setString(12, "STMPPNUM");
//			ps.setString(13, "STCMRNUM");
//			ps.setString(14, "789");
//			ps.setString(15, "010");
//			ps.setString(16, "OITEMCD");
//			ps.setString(17, "ITEMCD");
//			ps.setString(18, "2015-08-02");
//			ps.setString(19, "2015-08-03");
//			ps.setString(20, "2015-07-30");
//			ps.setString(21, "2015-08-01");
//			ps.setString(22, "08");
//			ps.setString(23, "2015-08-03 20:36:00.123");
//			int i = ps.executeUpdate();
//			System.out.println(i);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM GTM.CSOL_NOTIFICATION");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					System.out.println(rs.getString(i));
				}
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
