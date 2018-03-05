import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class confirmationservlet extends HttpServlet {
	
	Order order;
	OrderDataStore ods;
	HashMap<String,Order> orders;
	
	public void init()
	{
		ods = new OrderDataStore();
		orders = new HashMap<String, Order>();
	}
	
  public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
		String firstName = (String)session.getAttribute("firstName");
		int C = (int)session.getAttribute("C");
		C=0;
		session.setAttribute("C",C);
        
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
			out.println("<li><a href=\"cartservlet\">Cart("+C+")</a></li>");
			out.println("<li><a href=\"vieworderservlet\">Your Orders</a></li></ul></nav>");
		}else{
			out.println("</ul></nav>");
		}

		
		String deliveryAdress = request.getParameter("shippingAddress");
		
		//String datetotal = parseInt((request.getParameter("dateFrom") - request.getParameter("dateTo")) / (24 * 3600 * 1000));
		//System.out.println(datetotal);
		Random randomValue = new Random();
		int firstValue = 1;
		int lastValue = 1000;
		int value = randomValue.nextInt(lastValue-firstValue) + firstValue;
		String id = "SP#"+value;
		String confirmationNo = "Confirmation# "+value;
		DateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date1 = new Date();
		String dateOfOrder = simpleDateFormat.format(date1).toString();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 14);
		Date date = calendar.getTime();
		String DATE_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(DATE_FORMAT);
		String dateOfdelivery = simpleDateFormat1.format(date);
		Cart cartFunctionality;
		cartFunctionality = (Cart) session.getAttribute("cart");
		HashMap<String, List<Object>> cartItems = cartFunctionality.getCartItems();
		String customerEmailId=(String)session.getAttribute("userid");
		Float totalAmount=cartFunctionality.getTotalAmount();
		ArrayList<String> orderItems = new ArrayList<String>();
		String orderDetails;
		String productName;
		String productId;
		float productPrice;
		int productQty;

		for(Map.Entry<String, List<Object>> listEntry : cartItems.entrySet()){

			String key = listEntry.getKey();
			List<Object> values = listEntry.getValue();
			productId = (String)values.get(0);
			productName = (String)values.get(1);
			productPrice = (Float)values.get(2);
			productQty = (Integer)values.get(3);
			String fromDate = request.getParameter("dateFrom"+values.get(0));
			String toDate = request.getParameter("dateTo"+values.get(0));
			MySqlDataStoreUtilities.insertCustomerOrder(id, customerEmailId, productId, fromDate.toString(),toDate.toString(), productName, totalAmount.toString());
	
			//MySqlDataStoreUtilities.insertOrder(productName, id, totalAmount,productQty, dateOfOrder,fromDate,toDate,customerEmailId, deliveryAdress);
		}
		session.removeAttribute("cart");
		out.println("<h3><br><br>Your Order with Order Number "+id+" has been booked Succesfully. </h3><br><br>");
		out.close();
		out.println("</div><footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
	out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
	out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
	out.println("<p>&copy; AlHamdanHotels 2017. by AlHamdan</p></div></footer></div>");
	out.println("</body></html>");

		//printSideBar(out);
		
		out.close();
	
}

public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
}


	public void printSideBar(PrintWriter out)
	{
		out.println("<aside class=\"sidebar\">");
	out.println("<ul><li><h4><a href=\"contentservlet?productType=Smart Watches\">Smart Watches</a></h4></li>");
	out.println("<li><h4><a href=\"contentservlet?productType=Speakers\">Speakers</a></h4></li>");
	out.println("<li><h4><a href=\"contentservlet?productType=Headphones\">Headphones</a></h4></li>");
	out.println("<li><h4><a href=\"contentservlet?productType=Phones\">Phones</a></h4></li>");
	out.println("<li><h4><a href=\"contentservlet?productType=Laptops\">Laptops</a></h4></li>");
	out.println("<li><h4><a href=\"contentservlet?productType=External Storages\">External Storage</a></h4></li>");
	out.println("</ul></aside><div class=\"clear\"></div></div>");
	out.println("</div><footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
	out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
	out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
	out.println("<p>&copy; Smart Portables 2017. by Syed Hamdan Sher</p></div></footer></div>");
	out.println("</body></html>");
	}

}