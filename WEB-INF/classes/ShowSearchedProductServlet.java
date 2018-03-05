import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ShowSearchedProductServlet extends HttpServlet
{
	ArrayList<Object> products;
    HashMap<String, Products> rooms;
    HashMap<String, Products> lr;
    HashMap<String, Products> su;
    HashMap<String, Products> di;
    MySqlDataStoreUtilities mysql = new MySqlDataStoreUtilities();
    HashMap<String, Products> allProducts;
    
    void loadXML()
    {
        try{
        products = mysql.getProducts();
        
        rooms = (HashMap<String,Products>)products.get(0);
        lr = (HashMap<String,Products>)products.get(1);
        su = (HashMap<String,Products>)products.get(2);
        di = (HashMap<String,Products>)products.get(3);
        allProducts = (HashMap<String, Products>)products.get(4);
        
        
        }catch(Exception E){
        System.out.println("Exception");
        }
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		loadXML();
		
		HttpSession session = request.getSession(false);
		String fName = null;
		
		if(session != null)
		{
			fName=(String)session.getAttribute("firstName");
		}
		
		
		String productName = (String)request.getAttribute("productName");
		String productType = "Products";

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		//session.setAttribute("Accessories", accessories);
		int C=0;
        Cart cart=null;
        if(session != null){
        cart = (Cart) session.getAttribute("cart");}
		
        if(cart == null){
          cart = new Cart();
          if(session != null){
          session.setAttribute("cart", cart);
          C=0;
          session.setAttribute("C",C);}

        }else{if(session != null){C = (int)session.getAttribute("C");}}

		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Al Hamdan</title><link rel=\"stylesheet\" href=\"styles1.css\" type=\"text/css\" />");
		out.println("<script type=\"text/javascript\" src=\"JS/javascript.js\"></script></head>");
		if(fName != null && !fName.isEmpty())
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
		
		if(fName != null && !fName.isEmpty())
		{
			out.println("<h5>Welcome ");
			out.println(fName);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"loginedhomeservlet\">Home</a></li>");
		}else{
			out.println("<nav><ul><li class=\"start selected\"><a href=\"homeservlet\">Home</a></li>");
		}
		out.println("<li class=\"\"><a href=\"contentservlet?productType=Rooms\">Rooms</a></li>");
		out.println("<li><a href=\"contentservlet?productType=Suites\">Suites</a></li>");
		out.println("<li><a href=\"contentservlet?productType=LR\">Leisure Facilities</a></li>");
		out.println("<li><a href=\"contentservlet?productType=Dinning\">Dinning</a></li>");
		
		if(fName != null && !fName.isEmpty())
		{
			out.println("<li><a href=\"cartservlet\">Cart("+C+")</a></li>");
			out.println("<li><a href=\"vieworderservlet\">Your Orders</a></li></ul></nav>");
		}else{
			out.println("</ul></nav>");
		}

		out.println("<div id=\"body\">");
		out.println("<section id=\"content\">");
		
		if(productType.equals("Products"))
		{
			out.println("<article><h3>Searched Product</h3></article>");
			out.println("<article class=\"expanded\">");
			
			for(Map.Entry<String,Products> m :allProducts.entrySet()){
				
				Products s = m.getValue();
				
				String id = s.getProductId();
				
				if (id != null) {
					id = id.trim().toLowerCase();
				}
				
				//System.out.println("productName: "+productName);
				//System.out.println("s.getName(): "+s.getName());
				if(productName.equals(id))
				{
					out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
            		out.println("<tr><td width=\"30%\">");
            		out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
            		out.println(s.getImage());
            		out.println("\" /></a>");
            		out.println("</td>");
            		out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
            		out.println(s.getProductName());
            		out.println("</b></td></tr><tr><td width=\"40\"><b>Name:</b>");
            		out.println(s.getHotelName());
            		out.println("</td></tr><tr><td><b>Location:</b>");
            		out.println(s.getLocation());
            		out.println("</td></tr></table></td>");
            		out.println("<td width=\"30%\"><table><tr><td><b>Price:");
            		out.println(s.getPrice());
					
					
					if(fName != null && !fName.isEmpty())
            {
                out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
                out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getProductName() +"' value = 'Write Review'>");
                out.println("<input type='hidden' name='productType' value='"+productType+"'>");
                out.println("<input type='hidden' name='productName' value='"+s.getProductName()+"'>");
                out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
                out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
                out.println("<input type='hidden' name='color' value='"+s.getLocation()+"'>");
            
                out.println("<input type='hidden' name='company' value='"+s.getHotelName()+"'>");
                out.println("<input type='hidden' name='quantity' value='"+1+"'>");
                out.println("</form></td></tr>");
                
                out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
                out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getProductName() +"' value = 'View Review'>");
                out.println("<input type='hidden' name='productType' value='"+productType+"'>");
                out.println("<input type='hidden' name='productName' value='"+s.getProductName()+"'>");
                out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
                out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
                out.println("<input type='hidden' name='color' value='"+s.getLocation()+"'>");
            
                out.println("<input type='hidden' name='company' value='"+s.getHotelName()+"'>");
                out.println("<input type='hidden' name='quantity' value='"+1+"'>");
                out.println("</form></td></tr>");

                out.println("<tr><td><form method = 'get' action = 'CheckAvailability'>");
                out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getProductName() +"' value = 'Check Availability'>");
                out.println("<input type='hidden' name='productType' value='"+productType+"'>");
                out.println("<input type='hidden' name='productName' value='"+s.getProductName()+"'>");
                out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
                out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
                out.println("<input type='hidden' name='location' value='"+s.getLocation()+"'>");
            
                out.println("<input type='hidden' name='hotelname' value='"+s.getHotelName()+"'>");
                out.println("<input type='hidden' name='productID' value='"+s.getProductId()+"'>");
                out.println("<input type='hidden' name='quantity' value='"+1+"'>");
                out.println("<tr><td><label for=\"from\">From</label><input type=\"text\" id=\"from\" placeholder=\"MM/dd/yyyy\" name=\"from\"><label for=\"to\">to</label><input type=\"text\" id=\"to\" placeholder=\"MM/dd/yyyy\" name=\"to\"></td></tr>");
                out.println("</form></td></tr>");



                out.println("<tr><td><form method = 'get' action = 'addtocartservlet'>");
                out.println("<input class = 'button' type = 'submit' name = '"+ s.getProductName() +"' value = 'Add to Cart'>");
                out.println("<input type='hidden' name='productName' value='"+s.getProductName()+"'>");
                out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
                out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
                out.println("<input type='hidden' name='quantity' value='"+1+"'>");
                session.setAttribute("pro",s.getAIDs());//session.setAttribute(s.getId(),s.getAIDs());
                out.println("<input type='hidden' name='id' value='"+s.getProductId()+"'>");
                out.println("</form></td></tr>");
                out.println("</table></td></tr></table>");
            }
            else{
                out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
                out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getProductName() +"' value = 'View Review'>");
                out.println("<input type='hidden' name='productType' value='"+productType+"'>");
                out.println("<input type='hidden' name='productName' value='"+s.getProductName()+"'>");
                out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
                out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
                out.println("<input type='hidden' name='color' value='"+s.getLocation()+"'>");
            
                out.println("<input type='hidden' name='company' value='"+s.getHotelName()+"'>");
                out.println("<input type='hidden' name='quantity' value='"+1+"'>");
                out.println("</form></td></tr>");

                out.println("<tr><td><form method = 'get' action = 'CheckAvailability'>");
                out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getProductName() +"' value = 'Check Availability'>");
                out.println("<input type='hidden' name='productType' value='"+productType+"'>");
                out.println("<input type='hidden' name='productName' value='"+s.getProductName()+"'>");
                out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
                out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
                out.println("<input type='hidden' name='location' value='"+s.getLocation()+"'>");
            
                out.println("<input type='hidden' name='hotelname' value='"+s.getHotelName()+"'>");
                out.println("<input type='hidden' name='productID' value='"+s.getProductId()+"'>");
                out.println("<input type='hidden' name='quantity' value='"+1+"'>");
                out.println("<tr><td><label for=\"from\">From</label><input type=\"text\" id=\"from\" placeholder=\"MM/dd/yyyy\" name=\"from\"><label for=\"to\">to</label><input type=\"text\" id=\"to\" placeholder=\"MM/dd/yyyy\" name=\"to\"></td></tr>");
                out.println("</form></td></tr>");

                out.println("</table></td></tr></table>");
            }
			
		}
			
		}
	}
		
		out.println("</article></section>");
		//out.println("<div class=\"clear\"></div></div></div>");
		
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
            out.println("</ul>");
	out.println("</aside><div class=\"clear\"></div></div>");
	
	out.println("</div><footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
	out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
	out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
	out.println("<p>&copy; AlHamdanHotels 2017. by AlHamdan</p></div></footer></div>");
	out.println("</body></html>");

		out.close();
	}
	
}