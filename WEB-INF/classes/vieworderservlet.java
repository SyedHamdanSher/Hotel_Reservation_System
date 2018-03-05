import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.*;
import java.util.Set;
import java.util.Date;
import java.util.*;


public class vieworderservlet extends HttpServlet {
	
	Order order;
	OrderDataStore ods;
	HashMap<String,Order> orders;
	
	public void init() throws ServletException{

		ods = new OrderDataStore();
		orders = new HashMap<String, Order>();
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		int C;
        Cart cart;

        
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
        cart = (Cart) session.getAttribute("cart");
        //String productType = request.getParameter("productType");
        
		String firstName = (String)session.getAttribute("firstName");
		if(cart == null){
          cart = new Cart();
          session.setAttribute("cart", cart);
          C=0;
          session.setAttribute("C",C);

        }else{C = (int)session.getAttribute("C");
    	cart = (Cart) session.getAttribute("cart");}
        
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Al Hamdan</title><link rel=\"stylesheet\" href=\"styles1.css\" type=\"text/css\" />");
		out.println("<script type=\"text/javascript\" src=\"JS/javascript.js\"></script><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script><script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script></head>");
		
		if(firstName != null && !firstName.isEmpty())
		{
			out.println("<body onload='init()'><div id=\"container\"><header><div align=\"right\" ><a href=\"logoutservlet\">Logout</a><br></div><img src=\"images/AlHamdan.jpg\" alt=\"\" style=\"width:70px;height:70px;\"><h4>AL-HAMDAN</h4>");
		
		}else{
			out.println("<body onload='init()'><div id=\"container\"><header><div align=\"right\" ><a href=\"Login.html\" >Login</a><a href=\"SignUP.html\">Signup</a><br></div><img src=\"images/AlHamdan.jpg\" alt=\"\" style=\"width:70px;height:70px;\"><h4>AL-HAMDAN</h4>");
		
		}
		//out.println("<body onload='init()'><div id=\"container\"><header><div align=\"right\" ><a href=\"Login.html\" >Login</a><a href=\"SignUP.html\">Signup</a><br></div><img src=\"images/AlHamdan.jpg\" alt=\"\" style=\"width:70px;height:70px;\"><h4>AL-HAMDAN</h4>");
		out.println("<form  name='autofillform1' action='autocomplete'>");
		out.println("<div name='autofillform'>");
		out.println("<strong>Search Products: </strong>");
		out.println("<input type='text' name='searchId' size='40' id='searchId' onkeyup='doCompletion()' placeholder='Search Here...'>");
		out.println("<div id='auto-row'>");
		out.println("<table border='0' id='complete-table' class='popupBox'></table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		
		out.println("</header>");
		
		if(firstName != null && !firstName.isEmpty())
		{
			out.println("<h5>Welcome ");
			out.println(firstName);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"loginedhomeservlet\">Home</a></li>");
		}else{
			out.println("<nav><ul><li class=\"start selected\"><a href=\"homeservlet\">Home</a></li>");
		}
		out.println("<li class=\"\"><a href=\"contentservlet?productType=Rooms\">Rooms</a></li>");
		out.println("<li><a href=\"contentservlet?productType=Suites\">Suites</a></li>");
		out.println("<li><a href=\"contentservlet?productType=LR\">Leisure Facilities</a></li>");
		out.println("<li><a href=\"contentservlet?productType=Dinning\">Dinning</a></li>");
		
		if(firstName != null && !firstName.isEmpty())
		{
			out.println("<li><a href=\"cartservlet\">Bag("+C+")</a></li>");
			out.println("<li><a href=\"vieworderservlet\">Your Bookikngs</a></li></ul></nav>");
		}else{
			out.println("</ul></nav>");
		}

		out.println("<div id=\"body\">");
		out.println("<section id=\"content\">");
		
	if(firstName==null)
	{
		out.println("<h1>Please Login to View Orders!</h1>");		
	}	 
	else
	{ 
		String userIdx=(String)session.getAttribute("userid");
		
		//orders = ods.getOrderHashMap();

		HashMap<String,Order> order;
		order = MySqlDataStoreUtilities.getCustomerOrder();

		HashMap<String,Order> customerOrders;
		customerOrders = new HashMap<String, Order>();

		for(Map.Entry<String,Order> m :order.entrySet())
		{
			String key = m.getKey();
			Order c = m.getValue();
			
			if(c.getUserId().equals(userIdx))
			{
				customerOrders.put(key, c);
			}
			
		}
		
		if(customerOrders.isEmpty()){
			out.println("<h3>There are no Orders for this Userid<h3>");
		}
		else	
		{
			String orderId, userId, orderDate, deliveryDate, shippingAddress, ccn;String totalAmount;
			
			out.println("<h3>Your Bookings</h3>");
			out.println("<table>");
			out.println("<thead><tr><td>User Id</td><td>Order Num</td><td>CheckIn Date</td><td>CheckOut Date</td><td>Product</td><td>Amount</td></tr></thead>");
			out.println("<tbody>");
			for(Map.Entry<String,Order> m :customerOrders.entrySet()){

					boolean flag;
					Order c = m.getValue();
					
					orderId = c.getOrderId();
					userId = c.getUserId();
					orderDate = c.getCheckInData();
					deliveryDate = c.getCheckOutData();
					shippingAddress = c.getProductName();
					totalAmount = c.getPrice();
					
					out.println("<tr>");
					out.println("<form action='cancelorderservlet' method='get'>");
					out.println("<input type='hidden' name='orderId' value='"+orderId+"'>");
					out.println("<input type='hidden' name='userId' value='"+userId+"'>");
					out.println("<input type='hidden' name='orderDate' value='"+orderDate+"'>");
					out.println("<input type='hidden' name='deliveryDate' value='"+deliveryDate+"'>");
					out.println("<input type='hidden' name='shippingAddress' value='"+shippingAddress+"'>");
					out.println("<input type='hidden' name='totalAmount' value='"+totalAmount+"'>");

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
		}
		out.println("</section>");
		out.println("<aside class=\"sidebar\">");

			out.println("<ul><li><h4>Hotel Information</h4>");
            out.println("<ul>");
                        out.println("<li><a href=\"contentservlet?productType=H1\">Hotel 1</a><br></li>");
                        out.println("<li><a href=\"contentservlet?productType=H2\">Hotel 2</a><br></li>");
                        out.println("<li><a href=\"contentservlet?productType=H3\">Hotel 3</a><br></li>");
            out.println("</ul>");
              
            out.println("</ul>");
            
            out.println("<ul><li><h4>Trending</h4>");
            out.println("<ul>");
            out.println("<li><a href=\"TrendingServlet?trendType=MR\">Most Reviewed</a><br></li>");
                        out.println("<li><a href=\"TrendingServlet?trendType=ML\">Most Liked</a><br></li>");
            out.println("</ul>");
            out.println("</ul></ul></aside>");
            out.println("<div class=\"clear\"></div></div>");

	
	out.println("<footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
	out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
	out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
	out.println("<p>&copy; AlHamdanHotels 2017. by AlHamdan</p></div></footer></div>");
	out.println("</body></html>");	
 
	}
	}
}