/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.62
 * Generated at: 2015-11-25 09:13:32 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class testAjax_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>MyHtml.html</title>\r\n");
      out.write("\r\n");
      out.write("<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("<meta http-equiv=\"description\" content=\"this is my page\">\r\n");
      out.write("\r\n");
      out.write("<!--<link rel=\"stylesheet\" type=\"text/css\" href=\"./styles.css\">-->\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tfunction getResult(stateVal) {\r\n");
      out.write("\r\n");
      out.write("\t\t//var url = \"http://localhost:8080/CSOL_Portal/ajaxservlet?state=\" + stateVal;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar localObj = window.location;\r\n");
      out.write("\r\n");
      out.write("\t\tvar contextPath = localObj.pathname.split(\"/\")[1];\r\n");
      out.write("\r\n");
      out.write("\t\tvar basePath = localObj.protocol+\"//\"+localObj.host+\"/\"+contextPath;\r\n");
      out.write("\r\n");
      out.write("\t\tvar server_context=basePath;\r\n");
      out.write("\t\r\n");
      out.write("\t\talert(server_context);\r\n");
      out.write("\t\tvar url = server_context + \"/ajaxservlet?state=\" + stateVal;\r\n");
      out.write("\t\talert(url);\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif (window.XMLHttpRequest) {\r\n");
      out.write("\t\t\treq = new XMLHttpRequest();\r\n");
      out.write("\t\t} else if (window.ActiveXObject) {\r\n");
      out.write("\t\t\treq = new ActiveXObject(\"Microsoft.XMLHTTP\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif (req) {\r\n");
      out.write("\t\t\treq.open(\"GET\", url, true);\r\n");
      out.write("\t\t\treq.onreadystatechange = complete;\r\n");
      out.write("\t\t\treq.send(null);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction complete() {\r\n");
      out.write("\t\tif (req.readyState == 4) {\r\n");
      out.write("\t\t\tif (req.status == 200) {\r\n");
      out.write("\t\t\t\tvar city = req.responseXML.getElementsByTagName(\"city\");\r\n");
      out.write("\t\t\t\t//alert(city.length);\r\n");
      out.write("\t\t\t\tvar str = new Array();\r\n");
      out.write("\t\t\t\tfor (var i = 0; i < city.length; i++) {\r\n");
      out.write("\t\t\t\t\tstr[i] = city[i].firstChild.data;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t//alert(document.getElementById(\"city\"));\r\n");
      out.write("\t\t\t\tbuildSelect(str, document.getElementById(\"city\"));\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction buildSelect(str, sel) {\r\n");
      out.write("\t\tsel.options.length = 0;\r\n");
      out.write("\t\tfor (var i = 0; i < str.length; i++) {\r\n");
      out.write("\t\t\tsel.options[sel.options.length] = new Option(str[i], str[i])\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<select name=\"state\" onChange=\"getResult(this.value)\">\r\n");
      out.write("\t\t<option value=\"\">Select</option>>\r\n");
      out.write("\t\t<option value=\"zj\">ZEHJIANG</option>>\r\n");
      out.write("\t\t<option value=\"zs\">JIANGSU</option>>\r\n");
      out.write("\t</select>\r\n");
      out.write("\t<select id=\"city\">\r\n");
      out.write("\t\t<option value=\"\">CITY</option>\r\n");
      out.write("\t</select>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
