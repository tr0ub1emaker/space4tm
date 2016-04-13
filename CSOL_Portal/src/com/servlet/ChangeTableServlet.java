package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.GetTableColumns;

public class ChangeTableServlet extends HttpServlet {

	public ChangeTableServlet(){
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/xml");
		resp.setHeader("Cache-Control", "no-cache");
		
		
		System.out.println(" --- > ChangeTableServlet.java");
		String tableName = req.getParameter("table");
		System.out.println("       tableName: " + tableName);
		
		StringBuffer sb = new StringBuffer("<table>");
		GetTableColumns gc = new GetTableColumns();
		gc.getColumns(req, tableName);
		sb.append("<column>id</column><column>name</column><column>123123</column>");
		sb.append("</table>");
		
		PrintWriter out = resp.getWriter();
		out.write(sb.toString());
		
		out.close();
	}
}
