package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestAjaxServlet extends HttpServlet {

	public TestAjaxServlet() {
        super();
}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("123");
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		
		System.out.println("hello");
		
		String state = request.getParameter("state");
		StringBuffer sb = new StringBuffer("<state>");
		if ("zj".equals(state)) {
			sb.append("<city>hangzhou</city><city>huzhou</city>");
		} else if ("zs".equals(state)) {
			sb.append("<city>nanjing</city><city>yangzhou</city><city>suzhou</city>");
		}
		sb.append("</state>");
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		out.close();
	}
}
