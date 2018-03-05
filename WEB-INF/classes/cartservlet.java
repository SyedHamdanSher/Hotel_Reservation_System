import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class cartservlet extends HttpServlet {
	ArrayList<Object> products;
    HashMap<String, Products> rooms;
    HashMap<String, Products> lr;
    HashMap<String, Products> su;
    HashMap<String, Products> di;
    MySqlDataStoreUtilities mysql = new MySqlDataStoreUtilities();
	

	
	void loadXML()
	{
		try{
		products = mysql.getProducts();
		
		rooms = (HashMap<String,Products>)products.get(0);
        lr = (HashMap<String,Products>)products.get(1);
        su = (HashMap<String,Products>)products.get(2);
        di = (HashMap<String,Products>)products.get(3);
		
		}catch(Exception E){
		System.out.println("Exception");
		}
	}
	
	
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  		loadXML();
      	
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
			out.println("<li><a href=\"vieworderservlet\">Your Bookings</a></li></ul></nav>");
		}else{
			out.println("</ul></nav>");
		}

		out.println("<div id=\"body\">");
		out.println("<section id=\"content\">");
		out.println("<article class=\"expanded\">");
		
		String typeOfProduct = request.getParameter("productType");
		String productCompany = request.getParameter("company");
		
		if(firstName != null && !firstName.isEmpty())
		{
			if(cart==null)
			{
				out.println("<h2>Cart is Empty</h2>");
			}
			else
			{
				 HashMap<String, List<Object>> items = cart.getCartItems();
			
					if(items.isEmpty())
					{
					out.println("<h1>Cart is Empty </h1>");
					out.println("<tr>");
					out.println("<td>");
					out.println("</td>");
					out.println("</tr>");
						
					}
					else
					{
						out.println("<h1>List of current items in Cart</h1>");
					out.println("<hr>");
					out.println("<table border='2' bordercolor=\"#ff0000\">");
					out.println("<tr><th>Product image</th><th>Product Detail</th><th>Cost&nbsp&nbsp&nbsp&nbsp</th><th>Quantity</th><th>Remove</th>");
					
					String key = "";
					for (Map.Entry<String, List<Object>> entry : items.entrySet()) {
						key = entry.getKey();
						List<Object> values = entry.getValue();
						out.println("<form action='RemoveProductServlet'><input type='hidden' name='name' value='" + key + "'>");

						out.println("<tr><th><img src ='images/" + values.get(4) + "' width = '100' height = '100'></th><th>" + values.get(1) + "  </th><th>" + "$" + values.get(2) + "</th>");
						out.println("<td><select name='" + key + "'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td>");
						out.println("<td><input  class = 'formbutton' type='submit' name='value' value='Remove'></td></tr></form>");

					}
					out.println("<form action='RemoveProductServlet'>");
					out.println("<tr><td align='center' colspan='5'><input class = 'formbutton' type='submit' name='value' value='Checkout'></td></form>");
					out.println("</tr></table>");
				}
			}
		} else {
			out.println("<p>Please login to add items in your cart !");
		}

		out.println("<br><br><br><br><br><br>");
		
		out.println("</article>");

			out.println("</section>");
		
		
		out.println("<aside class=\"sidebar\">");
                              
            out.println("<ul><li><h4>Leisure Type</h4>");
            out.println("<ul><li><form>");
            out.println("<div class=\"block form-group\">");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX1\">");
                            
            out.println("<label for=\"CHECKBOX1\"><b>Indian Style Spa<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX2\">");
                            
                            out.println("<label for=\"CHECKBOX2\"><b>Fitness Center<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX3\">");
                            
                            out.println("<label for=\"CHECKBOX3\"><b>Sauna<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");

                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX4\">");
                            
                            out.println("<label for=\"CHECKBOX4\"><b>CG Kids Club<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");

                            out.println("<ul><li><h4>Price</h4>");
							out.println("<br>");
							out.println("<div><input type=\"checkbox\" id=\"CHECKBOX5\">");
                            out.println("<label for=\"CHECKBOX5\"><b>100$ - 200$<b></label>");
							out.println("</div>");
							out.println("</br>");
							
							out.println("<div><input type=\"checkbox\" id=\"CHECKBOX6\">");
							
                            out.println("<label for=\"CHECKBOX6\"><b>200$ - 300$<b></label>");
							out.println("</div>");
							out.println("</br>");
							
							out.println("<div><input type=\"checkbox\" id=\"CHECKBOX7\">");
							
                            out.println("<label for=\"CHECKBOX7\"><b>300$ +<b></label>");
							out.println("</div>");
							out.println("</br>");
							
							out.println("<ul><li><h4>Location</h4>");
							out.println("<br><div><input type=\"checkbox\" id=\"CHECKBOX8\">");
							
                            out.println("<label for=\"CHECKBOX8\"><b>Chicago<b></label>");
							out.println("</div>");
							out.println("</br>");
							
							out.println("<div><input type=\"checkbox\" id=\"CHECKBOX9\">");
							
                            out.println("<label for=\"CHECKBOX9\"><b>NewYork<b></label>");
							out.println("</div>");
							out.println("</br><div><input type=\"checkbox\" id=\"CHECKBOX10\">");
							
                            out.println("<label for=\"CHECKBOX10\"><b>Washington D.C<b></label>");
							out.println("</div>");
							out.println("</br>");

							out.println("<div><input type=\"checkbox\" id=\"CHECKBOX11\">");
                            
            				out.println("<label for=\"CHECKBOX11\"><b>HOTEL 1<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX12\">");
                            
                            out.println("<label for=\"CHECKBOX12\"><b>HOTEL 2<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX13\">");
                            
                            out.println("<label for=\"CHECKBOX13\"><b>HOTEL 3<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            
                            out.println("<div class=\"block\">");
                            out.println("<button align='center' class = 'formbutton' id=\"useGsFunctions\">GO</button>");
                            out.println("</div>");
         out.println("</form></li>");
                        
            out.println("</ul></ul>");
             
            
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
		
		out.println("</section>");
		
		out.println("<div class=\"clear\"></div></div>");
	
	out.println("</div><footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
	out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
	out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
	out.println("<p>&copy; AlHamdanHotels 2017. by AlHamdan</p></div></footer></div>");
	out.println("</body></html>");

		out.close();
		
	}

}