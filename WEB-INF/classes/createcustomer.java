import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class createcustomer extends HttpServlet {
	
	Customer customer;
	HashMap<String,Customer> customers;
	String firstName;
	String lastName;
    String emailId;
	String phoneNumber;
	String password;
	String rePassword;
	
	public void init()
	{

		customers = MySqlDataStoreUtilities.getCustomers();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		emailId = request.getParameter("emailId");
		phoneNumber = request.getParameter("phoneNumber");
		password = request.getParameter("password");
		rePassword = request.getParameter("rePassword");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Smart Portables</title>");
		out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head><body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"start selected\"><a href=\"salesmanservlet\">Customer List</a></li>");
		out.println("<li><a href=\"salesmanservlet?productType=createCustomer\">Create Customer</a></li>");
		out.println("<li><a href=\"salesmanservlet?productType=addOrder\">Add Order</a></li>");
		out.println("<li><a href=\"salesmanservlet?productType=updateOrder\">Update Order</a></li>");
		out.println("<li><a href=\"salesmanservlet?productType=deleteOrder\">Delete Order</a></li>");
		out.println("<li><a href=\"logoutservlet\">Logout</a></li></ul></nav>");
		
		
		
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
				out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Smart Portables</title>");
				out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head><body><div id=\"container\">");
				out.println("<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2></header>");
				out.println("<h3 align=\"center\">User already exists!</h3>");
				out.println("</body></html>");
				flag=1;
			}
			
		}}

		if(flag==0){
			
		
			MySqlDataStoreUtilities.registerCustomer(firstName, lastName, emailId,phoneNumber, password);
		
			out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Smart Portables</title>");
			out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head><body><div id=\"container\">");
			out.println("<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2></header>");
			out.println("<h3 align=\"center\">Account Created Successfully. Please Login to continue</h3>");
		}
	}
	
		out.println("</div></fieldset></article</div></div></body></html>");
		out.close();

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
			
			
}


