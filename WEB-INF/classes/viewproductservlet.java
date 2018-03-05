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


public class viewproductservlet extends HttpServlet {
	
	Order order;
	OrderDataStore ods;
	HashMap<String,Order> orders;
	
	public void init() throws ServletException{

		ods = new OrderDataStore();
		orders = new HashMap<String, Order>();
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String fName=(String)session.getAttribute("firstName");
	
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Smart Portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\"><header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2>");
		out.println("</header>");
		
		if(fName != null && !fName.isEmpty())
		{
			out.println("<h5>Welcome ");
			out.println(fName);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"loginedhomeservlet\">Home</a></li>");
		}
		else{
			out.println("<nav><ul><li class=\"start selected\"><a href=\"Home.html\">Home</a></li>");
		}
		
		
		out.println("<li class=\"\"><a href=\"contentservlet?productType=Smartphones\">SmartPhones</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Tablets\">Tablets</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Laptops\">Laptops</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Televisions\">Televisions</a></li>");
		out.println("<li><a href=\"#\">Accessories</a></li>");
		
		//out.println("<li><a href=\"#\">Cart(0)</a></li>");
		//out.println("<li><a href=\"#\">Your Orders</a></li>");
		
		if(fname != null && !fname.isEmpty())
		{
			out.println("<li><a href=\"ViewCartServlet\">Cart</a></li>");
			out.println("<li><a href=\"ViewOrders\">Your Orders</a></li>");
			out.println("<li><a href=\"LogoutServlet\">Logout</a></li>");
		}
		else
		{
			out.println("<li><a href=\"LoginServlet\">Login</a></li>");
			out.println("<li><a href=\"SignUp.html\">SignUp</a></li>");
			out.println("<li><a href=\"ViewCartServlet\">Cart</a></li>");
		}
		//out.println("<li><a href=\"LogoutServlet\">Logout</a></li>");
		
		out.println("</ul></nav><img class=\"header-image\" src=\"images/home.jpg\" alt=\"Advertisment Image Here\" />");
	
	if(fname==null)
	{
		out.println("<h1>Please Login to View Orders!!!!!!!!!!!!!!!</h1>");		
	}	 
	else
	{
		String userid=(String)session.getAttribute("userid");
		
		orders = ods.getOrderHashMap();
		
		if(orders.isEmpty()){
			out.println("<h3>There are no Orders for this Userid<h3>");
		}
		else	
		{
			String Ordernum="",productname="",orderDate="",shipping="",delivery;
			
			out.println("<h3>Your Orders</h3>");
			out.println("<table>");
			out.println("<tr><td>User Id</td><td>Order Num</td><td>Price</td><td>orderDate</td><td>Delivery Date</td><td>Shipping Address</td></tr>");
				
				for(Map.Entry<String,Order> m :orders.entrySet()){
					System.out.println(m.getKey());
					Order c = m.getValue();
					
					Ordernum = c.getorderId();
					//productname = (String)c.getOrderItems.get(0);
					float price = c.gettotalAmount();
					orderDate = c.getorderDate();
					shipping = c.getdeliveryAddress();
					delivery = c.getdeliveryDate();
				
					out.println("<form action='cancelorderservlet'>");
					out.println("<input type='hidden' name='userid' value='"+userid+"'>");
					out.println("<input type='hidden' name='product' value='"+productname+"'>");
					out.println("<input type='hidden' name='ordernum' value='"+Ordernum+"'>");
					out.println("<input type='hidden' name='delivery' value='"+delivery+"'>");
					out.println("<td>"+userid+"</td>");
					out.println("<td>"+Ordernum+"</td>");
					
					out.println("<td>"+price+"</td>");
					out.println("<td>"+orderDate+"</td>");
					out.println("<td>"+delivery+"</td>");
					out.println("<td>"+shipping+"</td>");
					out.println("<td><input type='submit'  value='Cancel Order'></td></tr>");
					out.println("</form>");	
				
				}
			
			out.println("</table>");	
		}
 
	}
}
}