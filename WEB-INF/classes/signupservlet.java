import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class signupservlet extends HttpServlet
{
	Customer customer;
	HashMap<String,Customer> customers;
	String firstName;
	String lastName;
    String emailId;
	String phoneNumber;
	String password;
	String rePassword;
	
	fillHashmap fH;
	
	public void init()
	{
		//fH = new fillHashmap();
		customers = MySqlDataStoreUtilities.getCustomers();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	firstName = request.getParameter("firstName");
	lastName = request.getParameter("lastName");
	emailId = request.getParameter("emailId");
	phoneNumber = request.getParameter("phoneNumber");
	password = request.getParameter("password");
	rePassword = request.getParameter("rePassword");
	
	
	if(firstName != null && firstName.length() != 0)
		firstName = firstName.trim();

	if(lastName != null && lastName.length() != 0)
        lastName = lastName.trim();

	if(emailId != null && emailId.length() != 0)
        emailId = emailId.trim();

	if(phoneNumber != null && phoneNumber.length() != 0)
        phoneNumber = phoneNumber.trim();

	if(password != null && password.length() != 0)
        password = password.trim();
	int flag=0;
	if(password.equals(rePassword) && firstName != "" && lastName != "" && emailId != "" && phoneNumber != "" && password != "" && rePassword != "")
	{
		customers = MySqlDataStoreUtilities.getCustomers();
		if(customers!=null){
		for(Map.Entry<String,Customer> m :customers.entrySet())
		{
			Customer c = m.getValue();
			
			if(c.getEmailId().equals(emailId))
			{
				out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
				out.println("<title>Al Hamdan</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
				out.println("</head><body onload=\"init()\"><div id=\"container\">");
				out.println("<header><img src=\"images/AlHamdan.jpg\" style=\"width:70px;height:70px;\"><h4>AL-HAMDAN</h4></header>");
				out.println("<h3 align=\"center\">User already exists!</h3>");
				out.println("</body></html>");
				flag=1;
			}
			
		}}

		if(flag==0){
			
		
			MySqlDataStoreUtilities.registerCustomer(firstName, lastName, emailId,phoneNumber, password);
		
			out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			out.println("<title>Al Hamdan</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
			out.println("</head><body onload=\"init()\"><div id=\"container\">");
			out.println("<header><img src=\"images/AlHamdan.jpg\" style=\"width:70px;height:70px;\"><h4>AL-HAMDAN</h4></header>");
			out.println("<h3 align=\"center\">Account Created Successfully. Please Login to continue</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			out.println("<form action=\"/AlHamdanHotels/loginservlet\" method=\"get\">");
			out.println("<p>Please go to the login page.</p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Login\" type=\"submit\" /></p>");
			out.println("</form></div></fieldset></div></body></html>");
		}
	}
	else
	{
		out.println("<h3>The following error may have occured:<h3>");
		out.println("<p>1: Any one field may have been kept empty</p>");
		out.println("<p>2: Passwords do not match</p>");
		out.println("<p>Please go back and try again !</p>");
		
	}
	
	out.println("</div></fieldset></article</div></div></body></html>");
	out.close();
	
	}
	
}