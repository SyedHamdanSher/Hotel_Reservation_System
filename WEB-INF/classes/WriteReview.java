import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class WriteReview extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
		String firstName = (String)session.getAttribute("firstName");
		String userId = (String)session.getAttribute("userid");

		int C;
        Cart cart;
        cart = (Cart) session.getAttribute("cart");
		
        if(cart == null){
          cart = new Cart();
          session.setAttribute("cart", cart);
          C=0;
          session.setAttribute("C",C);

        }else{C = (int)session.getAttribute("C");}
        
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Alhamdan</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\"><header><h1><a href=\"/\">Al<span>Hamdan</span></a></h1><h2>Buy best for the best</h2>");
		out.println("</header>");
		
		if(firstName != null && !firstName.isEmpty())
		{
			out.println("<h5>Welcome ");
			out.println(firstName);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"loginedhomeservlet\">Home</a></li>");
		}
		else{
			out.println("<nav><ul><li class=\"start selected\"><a href=\"homeservlet\">Home</a></li>");
		}
		
		
		if(firstName != null && !firstName.isEmpty())
		{
			out.println("<li><a href=\"cartservlet\">Cart("+C+")</a></li>");
			out.println("<li><a href=\"vieworderservlet\">Your Orders</a></li>");
			out.println("<li><a href=\"logoutservlet\">Logout</a></li>");
		}
		else
		{
			out.println("<li><a href=\"loginservlet\">Login</a></li>");
			out.println("<li><a href=\"signup\">SignUp</a></li>");
		}
		
		out.println("</ul></nav><img class=\"header-image\" src=\"images/homepage.jpg\" alt=\"Advertisment Image Here\" />");
		
		String productType = request.getParameter("productType");
		String productName = request.getParameter("productName");
		String image = request.getParameter("image");
		double price = Double.parseDouble(request.getParameter("price"));
		String color = request.getParameter("color");
		String condition = request.getParameter("condition");
		String manufacturer = request.getParameter("company");
		String retailer = request.getParameter("retailer");
		
		String productOnSale = "Yes";
		
		String manufacturerRebate = "No";
		String emailId = userId;
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		String reviewDate = dateFormat.format(today);
		
			
				
			out.println("<div id=\"body\"><article class=\"expanded\"><h3 align=\"center\">Write a Review</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			out.println("<form action=\"WriteReview\" method=\"POST\">");
			
			out.println("<input type='hidden' name='productType' value='"+productType+"'>");
			out.println("<input type='hidden' name='productName' value='"+productName+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='manufacturer' value='"+manufacturer+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");

			out.println("<input type='hidden' name='productOnSale' value='"+productOnSale+"'>");
			out.println("<input type='hidden' name='manufacturerRebate' value='"+manufacturerRebate+"'>");
			out.println("<input type='hidden' name='emailId' value='"+emailId+"'>");
			out.println("<input type='hidden' name='reviewDate' value='"+reviewDate+"'>");
			
			
			out.println("<p><label><b>Product Category: </b></label>"+productType+"</p>");
			out.println("<p><label><b>Product Name: </b></label>"+productName+"</p>");
			out.println("<p><label><b>Product Price: </b></label>"+price+"</p>");
			out.println("<p><label><b>Retailer Name: </b></label>"+retailer+"</p>");
			
			//out.println("<p><label><b>Retailer Name: </b></label><input name=\"retailer\" type=\"text\"></p>");
			out.println("<p><label><b>Retailer Zip: </b></label><input name=\"retailerZip\" type=\"text\"></p>");
			out.println("<p><label><b>Retailer City: </b></label><input name=\"retailerCity\"type=\"text\" /></p>");
			out.println("<p><label><b>Retailer State: </b></label><input name=\"retailerState\"type=\"text\" /></p>");
			
			out.println("<p><label><b>Product On Sale: </b></label>"+productOnSale+"</p>");
			out.println("<p><label><b>Manufacturer Name: </b></label>"+manufacturer+"</p>");
			out.println("<p><label>Manufacturer Rebate:</label>"+manufacturerRebate+"</p>");
			
			out.println("<p><label><b>User Id: </b></label>"+emailId+"</p>");
			
			out.println("<p><label><b>User Age: </b></label><input name=\"userAge\" type=\"text\"></p>");
			out.println("<p><label><b>User Gender: </b></label><input name=\"userGender\"type=\"text\" /></p>");
			out.println("<p><label><b>User Occupation: </b></label><input name=\"userOccupation\"type=\"text\" /></p>");
			
			out.println("<p><label><b>Review Rating: </b></label><select name='reviewRating'><option name='reviewRating' value='1' selected>1</option><option name='reviewRating' value='2'>2</option>");
			out.println("<option name='reviewRating' value='3'>3</option><option name='reviewRating' value='4'>4 </option><option name='reviewRating' value='5'>5</option></select></p>");
			
			out.println("<p><label><b>Review Date: </b></label>"+reviewDate+"</p>");
			out.println("<p><label><b>Review Text: </b></label><textarea rows=\"4\" cols=\"50\" name=\"reviewText\"></textarea>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\"  class=\"formbutton\" value=\"Submit Review\" type=\"submit\" /></p>");
			
			out.println("</form></div></fieldset></article><div class=\"clear\"></div></div>");
		
		//printSideBar(out);
		
		out.close();
	
}

public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
		String firstName = (String)session.getAttribute("firstName");
		String userId = (String)session.getAttribute("userid");

		int C;
        Cart cart;
        cart = (Cart) session.getAttribute("cart");
		
        if(cart == null){
          cart = new Cart();
          session.setAttribute("cart", cart);
          C=0;
          session.setAttribute("C",C);

        }else{C = (int)session.getAttribute("C");}
        
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Al Hamdan</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\"><header><h1><a href=\"/\">Al<span>Hamdan</span></a></h1><h2>Buy best for the best</h2>");
		out.println("</header>");
		
		if(firstName != null && !firstName.isEmpty())
		{
			out.println("<h5>Welcome ");
			out.println(firstName);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"loginedhomeservlet\">Home</a></li>");
		}
		else{
			out.println("<nav><ul><li class=\"start selected\"><a href=\"homeservlet\">Home</a></li>");
		}
		
		
		
		if(firstName != null && !firstName.isEmpty())
		{
			out.println("<li><a href=\"cartservlet\">Cart("+C+")</a></li>");
			out.println("<li><a href=\"vieworderservlet\">Your Orders</a></li>");
			out.println("<li><a href=\"logoutservlet\">Logout</a></li>");
		}
		else
		{
			out.println("<li><a href=\"loginservlet\">Login</a></li>");
			out.println("<li><a href=\"signup\">SignUp</a></li>");
		}
		
		out.println("</ul></nav><img class=\"header-image\" src=\"images/homepage.jpg\" alt=\"Advertisment Image Here\" />");
		
		
		String productType = request.getParameter("productType");
		String productName = request.getParameter("productName");
		double price = Double.parseDouble(request.getParameter("price"));
		String retailer = request.getParameter("retailer");
		String retailerZip = request.getParameter("retailerZip");
		String retailerCity = request.getParameter("retailerCity");
		String retailerState = request.getParameter("retailerState");
		String productOnSale = request.getParameter("productOnSale");
		String manufacturer = request.getParameter("manufacturer");
		String manufacturerRebate = request.getParameter("manufacturerRebate");
		String emailId = request.getParameter("emailId");
		int userAge = Integer.parseInt(request.getParameter("userAge"));
		String userGender = request.getParameter("userGender");
		String userOccupation = request.getParameter("userOccupation");
		int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date reviewDate = null;
		try{
			reviewDate = dateFormat.parse(request.getParameter("reviewDate"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		String reviewText = request.getParameter("reviewText");
		
		if(userAge != 0 && userGender != null && userGender.length() != 0 && userOccupation != null && userOccupation.length() != 0
			&& reviewText != null && reviewText.length() != 0
			&& retailerZip != null && retailerZip.length() != 0 && retailerCity != null && retailerCity.length() != 0
			&& retailerState != null && retailerState.length() != 0)
		{
				
			MongoDBDataStoreUtilities.insertReview(productName, productType, price, retailer, retailerZip, retailerCity, retailerState,
													productOnSale, manufacturer, manufacturerRebate, emailId, userAge, userGender,
													userOccupation, reviewRating, reviewDate, reviewText);
													
			out.println("<p>Review has been submitted ! View your review in the products review section");

			


	
	out.println("</div><footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
	out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
	out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
	out.println("<p>&copy; AlHamdan 2017. by Syed Hamdan Sher</p></div></footer></div>");
	out.println("</body></html>");
	out.println("</body>");
		out.println("</html>");
												
		}
		else
		{
			int i =0;
		}			
		
	
	
}

}