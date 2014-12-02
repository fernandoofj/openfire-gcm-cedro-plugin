package br.com.cedrotech.openfire.plugin.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.File;
import java.util.List;
import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.util.*;
import br.com.cedrotech.openfire.plugin.gcm.GCMCedroPlugin;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileUploadException;

public final class gcmcedro_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"error.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.apache.jasper.runtime.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n\r\n");
  // Get parameters
	boolean save = request.getParameter("save") != null;
    boolean success = request.getParameter("success") != null;
    boolean error = request.getParameter("error") != null;
    String password = ParamUtils.getParameter(request, "password");
    String badge = ParamUtils.getParameter(request, "badge");
    String sound = ParamUtils.getParameter(request, "sound");
    String production = ParamUtils.getParameter(request, "production");

    GCMCedroPlugin plugin = (GCMCedroPlugin) XMPPServer.getInstance().getPluginManager().getPlugin("openfire-gcmcedro");

    // Handle a save
    if (save) {
        plugin.setPassword(password);
        plugin.setBadge(badge);
        plugin.setSound(sound);
        plugin.setProduction(production);

        try {
            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

            for (FileItem item : multiparts) {
                if (!item.isFormField()) {
                    String filename = item.getName();
                    item.write(new File(GCMCedroPlugin.keystorePath()));
                }
            }
            response.sendRedirect("gcmcedro.jsp?success=true");
            return;
        } catch (Exception e) {
            response.sendRedirect("gcmcedro.jsp?error=true");
            return;
        }

    }

    password = plugin.getPassword();
    badge = Integer.toString(plugin.getBadge());
    sound = plugin.getSound();
    production = plugin.getProduction() ? "true" : "false";

      out.write("\r\n\r\n<html>\r\n    <head>\r\n        <title>Cedro GCM Settings Properties</title>\r\n        <meta name=\"pageID\" content=\"gcmcedro-settings\"/>\r\n    </head>\r\n    <body>\r\n\r\n");
  if (success) { 
      out.write("\r\n    <div class=\"jive-success\">\r\n    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n    <tbody>\r\n        <tr><td class=\"jive-icon\"><img src=\"images/success-16x16.gif\" width=\"16\" height=\"16\" border=\"0\"></td>\r\n        <td class=\"jive-icon-label\">\r\n            Cedro GCM certificate updated successfully.\r\n        </td></tr>\r\n    </tbody>\r\n    </table>\r\n    </div><br>\r\n");
 } 
      out.write("\r\n\r\n<form action=\"gcmcedro.jsp?save\" method=\"post\" enctype=\"multipart/form-data\">\r\n\r\n<div class=\"jive-contentBoxHeader\">Cedro GCM certificate</div>\r\n<div class=\"jive-contentBox\">\r\n    <label for=\"file\">p12 certificate:</label>\r\n    <input type=\"file\" name=\"file\" />\r\n    <br>\r\n\r\n    <label for=\"password\">certificate password:</label>\r\n    <input type=\"password\" name=\"password\" value=\"");
      out.print( password );
      out.write("\" />\r\n    <br>\r\n\r\n    <label for=\"badge\">payload badge</label>\r\n    <input type=\"badge\" name=\"badge\" value=\"");
      out.print( badge );
      out.write("\" />\r\n    <br>\r\n\r\n    <label for=\"sound\">payload sound</label>\r\n    <input type=\"badge\" name=\"sound\" value=\"");
      out.print( sound );
      out.write("\" />\r\n    <br>\r\n\r\n    <label for=\"production\">sandbox or production</label>\r\n    <input type=\"radio\" name=\"production\" value=\"false\" ");
      out.print( production.equals("true") ? "" : "checked" );
      out.write(">Sandbox\r\n    <input type=\"radio\" name=\"production\" value=\"true\" ");
      out.print( production.equals("true") ? "checked" : "" );
      out.write(">Production\r\n</div>\r\n<input type=\"submit\" value=\"Save\">\r\n</form>\r\n\r\n\r\n</body>\r\n</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
