import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class checkoutservlet extends HttpServlet {
	
	
  	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
        String customerEmailId=(String)session.getAttribute("userid");
		String firstName = (String)session.getAttribute("firstName");
		int C = (int)session.getAttribute("C");
        
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

		out.println("<div id=\"body\">");
		out.println("<section id=\"content\">");
		//==================================Display code starts=====================================
		
		String action = request.getParameter("value");
			
		if(action.equals("Checkout"))
		{
			Cart cartFunctionality;
			cartFunctionality = (Cart) session.getAttribute("cart");
			HashMap<String, List<Object>> cartItems = cartFunctionality.getCartItems();
					
			int noOfProducts=0;
			float priceOfProduct=0;
			float totalAmount=0;
			int count=0;
			out.println("<div id=\"body\">" +

					"<table border=\\\"2\\\"  bordercolor=\"#ff0000\"><tr><td width=\\\"\\40\">"+
					"<h3 align='center'>Personal Information</h3>");
			out.println("<form action=\"confirmationservlet\" method=\"POST\">");
			out.println("<tr>" +
					"<td width=\"\"><b align='center'>First Name::</b>");
			out.println("<input name=\'firstName\' type=\'text\'>");
			out.println("</td>" +
					"<td width=\"\"><b align='center'>Last Name:");
			out.println("<input name=\"lastName\"type=\"text\" />");
			out.println("</td>" +
					"<td width=\"\"><b align='center'>Phone Number:");
			out.println("<input name=\"phoneNumber\"type=\"text\" />");
			out.println("</td>" +
					"</tr><tr><td width=\"\"><b align='center'>User Name</b>");
			out.println("<p><label name='emailId' ><b align='center'>"+customerEmailId+"</b></label>");
			out.println("</td>" +
					"<td width=\"\"><b align='center'>Address:</b>");
			out.println("<textarea rows=\"4\" cols=\"20\" name=\"shippingAddress\"></textarea>");
			out.println("</td>" +
					"</tr>");
			
			for(Map.Entry<String, List<Object>> entry : cartItems.entrySet()){
				List<Object> values = entry.getValue();
				priceOfProduct=(float)values.get(2);
				noOfProducts=(int)values.get(3);
				out.println("<tr><td width=\"\"><b align='center'>"+values.get(1)+" Date From</b>");
			out.println("<input name='dateFrom"+values.get(0)+"' type=\"date\"></p>");
			out.println("</td>" +
					"<td width=\"\"><b align='center'>"+values.get(1)+" Date To</b>");
			out.println("<input name='dateTo"+values.get(0)+"' type=\"date\"></p>");
			out.println("</td></tr>");
					
				

				totalAmount=totalAmount + priceOfProduct*noOfProducts;

			}
			out.println("</table>");

			out.println("<table border=\\\"2\\\"  bordercolor=\"#ff0000\"><tr><td width=\\\"40\\\"><b>"+
			"<h3 align=\"center\">Payment Information</h3>");
			out.println("<tr>" +
					"<td width=\"40\"><b align='center'>Card Holder name:</b>");
			out.println("<input name=\"cardHolderName\" type=\"text\"></p>");
			out.println("</td>" +
					"<td width=\"40\"><b align='center'>Credit Card Number:</b>");
			out.println("<input name=\"ccNumber\"type=\"text\" /></p>");
			out.println("</td>" +
					"<td width=\"40\"><b align='center'>Expriy Date:</b>");
			out.println("<input name=\"expDate\"type=\"text\" /></p>");
			out.println("</td>" +
					"</tr><td width=\"40\"><b align='center'>CVV:</b>");
			out.println("<input name=\"cvv\"type=\"password\" /></p>");
			out.println("</td>" +
					"<tr><td width=\"40\"><b align='center'></b>");
			out.println("<p><label name='finalAmount' value='amount'>Price per Day:<b align='center'>"+totalAmount+"</b></label>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Confirm Payment\" type=\"submit\" /></p>");
			out.println("</form>" +
					"</table>"+
					"</div>" +
					"<div class=\"clear\">" +
					"</div>");
			cartFunctionality.setTotalAmount(totalAmount);

			
			
		}
		out.println("</article></section>");
		out.println("<footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
	out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
	out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
	out.println("<p>&copy; AlHamdanHotels 2017. by AlHamdan</p></div></footer></div>");
	out.println("</body></html>");

		//printSideBar(out);
		
		out.close();
	
}


	/*public void printSideBar(PrintWriter out)
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
	}*/

}