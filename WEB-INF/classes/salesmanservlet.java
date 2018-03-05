import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class salesmanservlet extends HttpServlet {
	//HashMap<String,Customer> customers;
	String firstName;
	String lastName;
    String emailId;
	String phoneNumber;
	String password;
	Order order;
		OrderDataStore ods;
		HashMap<String,Order> orders;
		HashMap<String,Customer> customers;
	
		public void init() throws ServletException{

		ods = new OrderDataStore();
		orders = new HashMap<String, Order>();
		customers = MySqlDataStoreUtilities.getCustomers();
		}
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String fName = null;
		fName=(String)session.getAttribute("firstName2");
		
		String productType = request.getParameter("productType");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Smart Portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\"><header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2>");
		out.println("</header>");
		
		out.println("<h5>Welcome ");
		out.println(fName);
		out.println("!</h5>");

		out.println("<nav><ul><li class=\"start selected\"><a href=\"salesmanservlet\">Customer List</a></li><li class=\"\"><a href=\"salesmanservlet?productType=createCustomer\">Create Customer</a></li><li class=\"\"><a href=\"salesmanservlet?productType=addOrder\">Add Order</a></li><li><a href=\"salesmanservlet?productType=updateOrder\">Update Order</a></li><li><a href=\"salesmanservlet?productType=orderList\">Order List</a></li><li><a href=\"logoutservlet\">Logout</a></li></ul></nav>");
		out.println("<div id=\"body\"><article>");

		if(productType == null)
		{
			
			customers = MySqlDataStoreUtilities.getCustomers();
			
			String Ordernum="",productname="",orderDate="",shipping="",delivery;
			
			out.println("<div id=\"body\"><article><h3 align=\"center\">Customer List</h3>");
			out.println("<fieldset><div style=\"width:800; margin-right:auto; margin-left:auto;\">");
			out.println("<table>");
			out.println("<tr><td>Name</td><td>Email Id</td><td>Phone Number</td><td>Password</td></tr>");
				
				for(Map.Entry<String,Customer> m :customers.entrySet()){
					System.out.println(m.getKey());
					Customer c = m.getValue();
					
					firstName = c.getFirstName();
					lastName = c.getLastName();
					emailId = c.getEmailId();
					phoneNumber = c.getPhoneNumber();
					password = c.getPassword();
				
					out.println("<td>"+firstName+" "+lastName+"</td>");
					out.println("<td>"+emailId+"</td>");
					out.println("<td>"+phoneNumber+"</td>");
					out.println("<td>"+password+"</td></tr>");
				
				}
			
			out.println("</table>");
			out.println("</div></fieldset></article</div></div></body></html>");
		}
		else if(productType.equals("createCustomer"))
		{	
			out.println("<div id=\"body\"><article><h3 align=\"center\">Create a new Customer</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			out.println("<form action=\"createcustomer\" method=\"get\">");
			out.println("<p><label for=\"name\">First Name:</label><input name=\"firstName\" id=\"firstName\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label for=\"email\">Last Name:</label><input name=\"lastName\" id=\"lastName\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Phone Number:</label><input name=\"phoneNumber\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Email Id:</label><input name=\"emailId\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Password:</label><input name=\"password\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Re Enter Password:</label><input name=\"rePassword\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Create Customer\" type=\"submit\" /></p>");
			out.println("</form></div></fieldset></article</div></div></body></html>");
		}
		else if(productType.equals("orderList")){
		out.println("<div id=\"body\"><article><h3 align=\"center\">Display Customer Orders</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
			out.println("<form action=\"salesmanservlet\" method=\"post\">");
			out.println("<input type='hidden' name='queryType' value='displayOrders'>");
			//out.println("<p><label>Customer Email Id:</label><input name=\"customerEmailId\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Display Customer Orders\" type=\"submit\" /></p>");
			
			out.println("</form></div></fieldset></article</div></div></body></html>");
		}

	else if(productType.equals("updateOrder")){

		out.println("<div id=\"body\"><article><h3 align=\"center\">Update Order</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
			out.println("<form action=\"salesmanservlet\" method=\"post\">");
			out.println("<input type='hidden' name='queryType' value='updateThisOrder'>");
			out.println("<p><label>Order Id:</label><input name=\"customerOrder\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Display Order To Update\" type=\"submit\" /></p>");
			
			out.println("</form></div></fieldset></article</div></div></body></html>");
		}
		else if(productType.equals("addOrder"))
		{	
			Random r = new Random();
			int Low = 1;
			int High = 572431;
			int R = r.nextInt(High-Low) + Low;
			String orderId = "B#"+R;
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date today = new Date();
			String orderDate = dateFormat.format(today).toString();
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 14);
			
			Date date = cal.getTime();
			String DATE_FORMAT = "MM/dd/yyyy"; 
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
			String deliveryDate = sdf.format(date);
			//StrserId, String userId,String orderDate, String deliveryDate,String shippingAddress,double totalAmount,String creditCardNumber
			out.println("<div id=\"body\"><article><h3 align=\"center\">Add Order</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			out.println("<form action=\"salesmanservlet\" method=\"post\">");
			out.println("<input type='hidden' name='queryType' value='addOrder'>");
			out.println("<input type='hidden' name='orderId' value='"+orderId+"'>");
			out.println("<input type='hidden' name='orderDate' value='"+orderDate+"'>");
			out.println("<input type='hidden' name='deliveryDate' value='"+deliveryDate+"'>");
			
			out.println("<p><label>Customer Email Id:</label><input name=\"customerEmailId\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Delivery Address:</label><input name=\"deliveryAddress\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Total Price:</label><input name=\"itemPrice\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>CCN:</label><input name=\"ccn\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Order Date: </label>"+orderDate+"</p>");
			out.println("<p><label>Delivery Date: </label>"+deliveryDate+"</p>");
			out.println("<p><label>Order Id:</label>"+orderId+"</p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Add Order\" type=\"submit\" /></p>");
			out.println("</form></div></fieldset></article</div></div></body></html>");
		}
	
		
		out.println("</div></article><footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
		out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
		out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
		out.println("<p>&copy; Smart Portables 2017. by Syed Hamdan Sher</p></div></footer></div>");
		out.println("</body></html>");

	
		out.close();		
	}
	
  	public void doPost(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {

      	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String queryType="";
		queryType = request.getParameter("queryType");
		
        
		
		if(queryType==null)
		{
			doGet(request, response);
		}
		
		if(queryType.equals("addOrder"))
		{
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
			
			String emailId = request.getParameter("customerEmailId");
			String shippingAddress = request.getParameter("deliveryAddress");
			Double totalAmount = Double.parseDouble(request.getParameter("itemPrice"));
			String ccNumber = request.getParameter("ccn");
			String orderId = request.getParameter("orderId");
			String orderDate = request.getParameter("orderDate");
			String deliveryDate = request.getParameter("deliveryDate");
			
			MySqlDataStoreUtilities.insertCustomerOrder(orderId, emailId,orderDate, deliveryDate, shippingAddress,totalAmount,ccNumber);
			
			out.println("<h3><br><br>Order No "+orderId+" for Customer "+emailId+" has been Placed Succesfully.</h3><br><br>");
		}
		
		if(queryType.equals("displayOrders"))
		{
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
			
			String thisCustomer = request.getParameter("customerEmailId");
			//boolean isCustomerExist = false;
			
			HashMap<String,Order> orders;
			orders = MySqlDataStoreUtilities.getCustomerOrder();

			String orderId, userId, orderDate, deliveryDate, shippingAddress, ccn;Float totalAmount;
	
			//orders = ods.getOrderHashMap();
	
			out.println("<div id=\"body\"><article><h3 align=\"center\">Order List</h3>");
			out.println("<fieldset><div style=\"width:800; margin-right:auto; margin-left:auto;\">");
			
			out.println("<h3>Your Orders</h3>");
			out.println("<table>");
			out.println("<thead><tr><td>User Id</td><td>Order Num</td><td>Order Date</td><td>Delivery Date</td><td>Shipping Address</td><td>Amount</td><td>Action</td></tr></thead>");
			out.println("<tbody>");
			for(Map.Entry<String,Order> m :orders.entrySet()){

					boolean flag;
					Order c = m.getValue();
					
					orderId = c.getOrderId();
					userId = c.getCustomerEmailId();
					orderDate = c.getOrderDate();
					deliveryDate = c.getDeliveryDate();
					shippingAddress = c.getDeliveryAddress();
					totalAmount = c.getTotalAmount();
					ccn = c.getCCN();
					
					out.println("<tr>");
					out.println("<form action='cancelorderservlet' method='POST'>");
					out.println("<input type='hidden' name='orderId' value='"+orderId+"'>");
					out.println("<input type='hidden' name='userId' value='"+userId+"'>");
					out.println("<input type='hidden' name='orderDate' value='"+orderDate+"'>");
					out.println("<input type='hidden' name='deliveryDate' value='"+deliveryDate+"'>");
					out.println("<input type='hidden' name='shippingAddress' value='"+shippingAddress+"'>");
					out.println("<input type='hidden' name='totalAmount' value='"+totalAmount+"'>");
					out.println("<input type='hidden' name='ccn' value='"+ccn+"'>");

					out.println("<td>"+userId+"</td>");
					out.println("<td>"+orderId+"</td>");
					
					out.println("<td>"+orderDate+"</td>");
					out.println("<td>"+deliveryDate+"</td>");
					out.println("<td>"+shippingAddress+"</td>");
					out.println("<td>"+totalAmount+"</td>");
					out.println("<td><input type='submit' class='btn btn-danger'  value='Cancel Order'></td></tr>");
					out.println("</form>");	
				
				}
				out.println("</tbody>");
			out.println("</table>");		
			
			out.println("</div></fieldset></article></div></div></body></html>");
		}
		
		if(queryType.equals("updateThisOrder"))
		{
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
			
			String thisOrder = request.getParameter("customerOrder");
			
			HashMap<String,Order> orders;
			orders = MySqlDataStoreUtilities.getCustomerOrder();

			String orderId, userId, orderDate, deliveryDate, shippingAddress, ccn;Float totalAmount;
			
			out.println("<div id=\"body\"><article><h3 align=\"center\">Update Order</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
			for(Map.Entry<String,Order> m :orders.entrySet()){

					boolean flag;
					Order c = m.getValue();
					
					orderId = c.getOrderId();
					userId = c.getCustomerEmailId();
					orderDate = c.getOrderDate();
					deliveryDate = c.getDeliveryDate();
					shippingAddress = c.getDeliveryAddress();
					totalAmount = c.getTotalAmount();
					ccn = c.getCCN();

					if(thisOrder.equals(orderId))
					{
						
						out.println("<form action=\"updateorderbysalesmanservlet\" method=\"post\">");
						out.println("<input type='hidden' name='queryType' value='updateOrder'>");
						out.println("<input type='hidden' name='orderId' value='"+orderId+"'>");
						out.println("<input type='hidden' name='orderDate' value='"+orderDate+"'>");
						out.println("<p><label>Customer Email Id: </label><input name=\"customerEmailId\" id=\"email\" value='"+userId+"' type=\"text\" /></p>");
						out.println("<p><label>Delivery Address: </label><input name=\"deliveryAddress\" id=\"email\" value='"+shippingAddress+"' type=\"text\" /></p>");
						out.println("<p><label>Total Amount: </label><input name=\"itemPrice\" id=\"email\" value='"+totalAmount+"' type=\"text\" /></p>");
						out.println("<p><label>Credit Card Number: </label><input name=\"ccn\" id=\"email\" value='"+ccn+"' type=\"text\" /></p>");
						out.println("<p><label>Order Date: </label>"+orderDate+"</p>");
						out.println("<p><label>Delivery Date: </label><input name=\"deliveryDate\" id=\"email\" value='"+deliveryDate+"' type=\"text\" /></p>");
						out.println("<p><label>Order Id: </label>"+orderId+"</p>");
						out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Order\" type=\"submit\" /></p></form>");
					}
	
			}
	
			out.println("</div></fieldset></article</div></div></body></html>");
		}

      }
  }



