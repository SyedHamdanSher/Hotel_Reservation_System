import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class updateorderbysalesmanservlet extends HttpServlet {
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String queryType="";
		queryType = request.getParameter("queryType");
		
        out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Smart Portables</title>");
			out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head><body><div id=\"container\">");
			out.println("<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2></header>");
			out.println("<nav><ul>");
			out.println("<li class=\"start selected\"><a href=\"salesmanservlet\">Customer List</a></li>");
			out.println("<li><a href=\"salesmanservlet?productType=createCustomer\">Create Customer</a></li>");
			out.println("<li><a href=\"salesmanservlet?productType=addOrder\">Add Order</a></li>");
			out.println("<li><a href=\"salesmanservlet?productType=updateOrder\">Update Order</a></li>");
			out.println("<li><a href=\"salesmanservlet?productType=orderList\">Order List</a></li>");
			out.println("<li><a href=\"logoutservlet\">Logout</a></li></ul></nav>");
		
		if(queryType.equals("updateOrder"))
		{
			String emailId = request.getParameter("customerEmailId");
			String shippingAddress = request.getParameter("deliveryAddress");
			Double totalAmount = Double.parseDouble(request.getParameter("itemPrice"));
			String ccNumber = request.getParameter("ccn");
			String orderId = request.getParameter("orderId");
			String orderDate = request.getParameter("orderDate");
			String deliveryDate = request.getParameter("deliveryDate");
			
			MySqlDataStoreUtilities.updateCustomerOrder(orderId, emailId,orderDate, deliveryDate, shippingAddress,totalAmount,ccNumber);
			
			out.println("<h3><br><br>Order No "+orderId+" for Customer "+emailId+" has been updated succesfully.</h3><br><br>");
		}
		
		
	}
			
			
}


