import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class loginservlet extends HttpServlet
{
	Customer customer;
	HashMap<String,Customer> customers;
    String emailId;
	String password;
	HashMap<String,String> admins;
	
	//fillHashmap fH;
	
	public void init()
	{
		//fH = new fillHashmap();
		customers = MySqlDataStoreUtilities.getCustomers();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
	out.println("<title>Al Hamdan</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
	out.println("</head><body onload=\"init()\"><div id=\"container\">");
	out.println("<header><img src=\"images/AlHamdan.jpg\" style=\"width:70px;height:70px;\"><h4>AL-HAMDAN</h4></header>");
	
	out.println("<h3 align=\"center\">Login</h3>");
	out.println("<fieldset>");
	out.println("<div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
	out.println("<form action=\"/AlHamdanHotels/loginservlet\" method=\"POST\">");
	out.println("<p><label>Email Id:</label>");
	out.println("<input name=\"emailId\" id=\"name\" value=\"\" type=\"text\" /></p>");
	out.println("<p><label>Password:</label>");
	out.println("<input name=\"password\" id=\"email\" value=\"\" type=\"password\" /></p>");
	out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Login\" type=\"submit\" /></p>");
	out.println("</form></div></fieldset></div></body></html>");
	out.close();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	HttpSession session = request.getSession();

	emailId = request.getParameter("emailId");
	password = request.getParameter("password");
	
	if(emailId != null && emailId.length() != 0)
	{
        emailId = emailId.trim();
    }

	if(password != null && password.length() != 0)
	{
        password = password.trim();
	}
	
	
	if(emailId.equals("admin") && password.equals("admin123"))
	{
		admins = MySqlDataStoreUtilities.getAdminCredentials();
		session.setAttribute("firstName1","StoreMngr");
		session.setAttribute("userid1",emailId);
		for(Map.Entry<String,String> m :admins.entrySet())
			{
				String id = m.getKey();
				String pwrd = m.getValue();
				
				if(emailId.equals(id) && password.equals(pwrd))
				{
					RequestDispatcher rd = request.getRequestDispatcher("storemanagerservlet?productType=AddP");
					rd.forward(request,response);
					break;
				}
				
			}
		RequestDispatcher rd = request.getRequestDispatcher("storemanagerservlet?productType=AddP");
		rd.forward(request,response);
		
	}
	
	
	if(emailId != "" && password != "" && !emailId.equals("admin"))
	{
		customers = MySqlDataStoreUtilities.getCustomers();
		
		if(customers!=null){
			int flag = 0;
			for(Map.Entry<String,Customer> m :customers.entrySet())
			{	
				Customer c = m.getValue();
			
				if(c.getEmailId().equals(emailId) && c.getPassword().equals(password))
				{				
					session.setAttribute("firstName",c.getFirstName());
					session.setAttribute("userid",emailId);
				
					RequestDispatcher view = request.getRequestDispatcher("loginedhomeservlet");
					view.forward(request, response);
					flag=1;		
				}
			}if(flag==0){out.println("<b>Invalid Username or Password. Please try again !<b>");}}
	}
	else
	{	
		out.println("<b>Invalid Username or Password. Please try again !<b>");
	}
	
	out.close();
	}
	
}
