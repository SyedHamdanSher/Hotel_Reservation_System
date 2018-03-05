import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class contentservlet extends HttpServlet
{
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
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		loadXML();
		
		HttpSession session = request.getSession();
		String fName = null;
		if(session!=null)
			fName=(String)session.getAttribute("firstName");
		
		String productType = request.getParameter("productType");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		//session.setAttribute("Accessories", accessories);
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
		out.println("<strong>Search Hotels: </strong>");
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
			out.println("<li><a href=\"cartservlet\">Bag("+C+")</a></li>");
			out.println("<li><a href=\"vieworderservlet\">Your Bookings</a></li></ul></nav>");
		}else{
			out.println("</ul></nav>");
		}
		
		
		
		out.println("<div id=\"body\">");

		if(productType.equals("H1"))
		{
			out.println("<section id=\"content\">");
			out.println("<h3>Hotel 1</h3>");
            out.println("<article >");
        
            out.println("<br><h3>Location Information</h3><br>");

            out.println("</p>The Hotel 1 is ideally located in the heart of Chicago downtown, and just a short drive to Michigan Lake and Willis Tower. The Art Institute of Chicago, the Millenium park is less than a mile from our hotel."); 

            out.println("<p></p><p>The Hotel 1 can be easily reached by car, taxi or public transport and is located approximately 40 minutes from Chicago's O'Hare International Airport and 30 minutes from Midway International Airport.</p>");

            out.println("We look forward to welcoming you for your next holidays!");
                out.println("</article>");

                out.println("<article >");
        
            out.println("<br><h3>Hotel Services</h3><br>");
            out.println("<p><img  src=\"images/facilities1.jpg\" alt=\"\" style=\"width:300px;height:200px;\"></br><b>Internet</b></p>");
        out.println("<p><img  src=\"images/facilities2.jpg\" alt=\"\" style=\"width:300px;height:200px;\"></br><b>Parking</b></p>");
        out.println("<p><img  src=\"images/facilities3.jpg\" alt=\"\" style=\"width:300px;height:200px;\"></br><b>Doctor On Call</b></p>");
        
        out.println("</article>");

        out.println("<article >");
            out.println("<br><h3>Leisure Facilities</h3><br>");
            out.println("<p>Let our professional spa therapists relax you with a range of contemporary and traditional treatments, enjoy a dip in one of our three outdoor pools or indulge in the indoor heated pool. Enjoy the sports and adventurous activities</p>");
            out.println("<img  src=\"images/leisure1.jpg\" alt=\"\" style=\"width:300px;height:200px;\">");
            out.println("<img  src=\"images/leisure2.jpg\" alt=\"\" style=\"width:300px;height:200px;\">");
            out.println("<img  src=\"images/leisure4.jpg\" alt=\"\" style=\"width:300px;height:200px;\">");
        out.println("</article>");

			out.println("</section>");
		
		out.println("<aside class=\"sidebar\">");
                              
            out.println("<ul><li><h4>Gallery</h4>");
            out.println("<ul><li><form method = 'post' action = 'filterservlet'>");
            out.println("<div class=\"block form-group\">");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX1\">");
                            
            out.println("<label for=\"CHECKBOX1\"><b>HOTEL 1<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX2\">");
                            
                            out.println("<label for=\"CHECKBOX2\"><b>HOTEL 2<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX3\">");
                            
                            out.println("<label for=\"CHECKBOX3\"><b>HOTEL 3<b></label>");
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
		}

		if(productType.equals("H2"))
		{
			out.println("<section id=\"content\">");
			
			out.println("<h3>Hotel 2</h3>");
            out.println("<article >");
        
        out.println("<br><h3>Location Information</h3><br>");

       out.println("</p>The Hotel 2 is ideally located in the heart of Chicago downtown, and just a short drive to Michigan Lake and Willis Tower. The Art Institute of Chicago, the Millenium park is less than a mile from our hotel."); 

out.println("<p></p><p>The Hotel 2 can be easily reached by car, taxi or public transport and is located approximately 40 minutes from Chicago's O'Hare International Airport and 30 minutes from Midway International Airport.</p>");

out.println("We look forward to welcoming you for your next holidays!");
        out.println("</article>");

        out.println("<article >");
        
        out.println("<br><h3>Hotel Services</h3><br>");
        out.println("<p><img  src=\"images/facilities1.jpg\" alt=\"\" style=\"width:300px;height:200px;\"></br><b>Internet</b></p>");
        out.println("<p><img  src=\"images/facilities2.jpg\" alt=\"\" style=\"width:300px;height:200px;\"></br><b>Parking</b></p>");
        out.println("<p><img  src=\"images/facilities3.jpg\" alt=\"\" style=\"width:300px;height:200px;\"></br><b>Doctor On Call</b></p>");
        
        out.println("</article>");

        out.println("<article >");
            out.println("<br><h3>Leisure Facilities</h3><br>");
            out.println("<p>Let our professional spa therapists relax you with a range of contemporary and traditional treatments, enjoy a dip in one of our three outdoor pools or indulge in the indoor heated pool. Enjoy the sports and adventurous activities</p>");
            out.println("<img  src=\"images/leisure1.jpg\" alt=\"\" style=\"width:300px;height:200px;\">");
            out.println("<img  src=\"images/leisure2.jpg\" alt=\"\" style=\"width:300px;height:200px;\">");
            out.println("<img  src=\"images/leisure4.jpg\" alt=\"\" style=\"width:300px;height:200px;\">");
        out.println("</article>");
			out.println("</section>");
		
		out.println("<aside class=\"sidebar\">");
                              
            out.println("<ul><li><h4>Gallery</h4>");
            out.println("<ul><li><form method = 'post' action = 'filterservlet'>");
            out.println("<div class=\"block form-group\">");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX1\">");
                            
            out.println("<label for=\"CHECKBOX1\"><b>HOTEL 1<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX2\">");
                            
                            out.println("<label for=\"CHECKBOX2\"><b>HOTEL 2<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX3\">");
                            
                            out.println("<label for=\"CHECKBOX3\"><b>HOTEL 3<b></label>");
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
		}

		if(productType.equals("H3"))
		{
			out.println("<section id=\"content\">");
			out.println("<h3>Hotel 3</h3>");
            out.println("<article >");
        
        out.println("<br><h3>Location Information</h3><br>");

       out.println("</p>The Hotel 3 is ideally located in the heart of Chicago downtown, and just a short drive to Michigan Lake and Willis Tower. The Art Institute of Chicago, the Millenium park is less than a mile from our hotel."); 

out.println("<p></p><p>The Hotel 3 can be easily reached by car, taxi or public transport and is located approximately 40 minutes from Chicago's O'Hare International Airport and 30 minutes from Midway International Airport.</p>");

out.println("We look forward to welcoming you for your next holidays!");
        out.println("</article>");

        out.println("<article >");
        
        out.println("<br><h3>Hotel Services</h3><br>");
        out.println("<p><img  src=\"images/facilities1.jpg\" alt=\"\" style=\"width:300px;height:200px;\"></br><b>Internet</b></p>");
        out.println("<p><img  src=\"images/facilities2.jpg\" alt=\"\" style=\"width:300px;height:200px;\"></br><b>Parking</b></p>");
        out.println("<p><img  src=\"images/facilities3.jpg\" alt=\"\" style=\"width:300px;height:200px;\"></br><b>Doctor On Call</b></p>");
        
        out.println("</article>");

        out.println("<article >");
            out.println("<br><h3>Leisure Facilities</h3><br>");
            out.println("<p>Let our professional spa therapists relax you with a range of contemporary and traditional treatments, enjoy a dip in one of our three outdoor pools or indulge in the indoor heated pool. Enjoy the sports and adventurous activities</p>");
            out.println("<img  src=\"images/leisure1.jpg\" alt=\"\" style=\"width:300px;height:200px;\">");
            out.println("<img  src=\"images/leisure2.jpg\" alt=\"\" style=\"width:300px;height:200px;\">");
            out.println("<img  src=\"images/leisure4.jpg\" alt=\"\" style=\"width:300px;height:200px;\">");
        out.println("</article>");

			out.println("</section>");
		
		out.println("<aside class=\"sidebar\">");
                              
            out.println("<ul><li><h4>Gallery</h4>");
            out.println("<ul><li><form method = 'post' action = 'filterservlet'>");
            out.println("<div class=\"block form-group\">");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX1\">");
                            
            out.println("<label for=\"CHECKBOX1\"><b>HOTEL 1<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX2\">");
                            
                            out.println("<label for=\"CHECKBOX2\"><b>HOTEL 2<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX3\">");
                            
                            out.println("<label for=\"CHECKBOX3\"><b>HOTEL 3<b></label>");
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
		}
		
		
		if(productType.equals("Rooms"))
		{
			out.println("<section id=\"content\">");
            out.println("<article><h3>Rooms</h3></article>");
            out.println("<article class=\"expanded\">");
			
            for(Map.Entry<String,Products> m :rooms.entrySet()){
            Products s = m.getValue();//21-9 ,21-15.
            
            out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
            out.println("<tr><td width=\"30%\">");
            out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"roomimages/");
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
            out.println("</article>");
            
        }
		out.println("</section>");
		out.println("<aside class=\"sidebar\">");
            
            out.println("<ul><li><h4>Room Type</h4>");
            out.println("<ul><li><form method='GET' action='filterservlet'>");
            out.println("<div class=\"block form-group\">");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX1\"  name='queryCheckBox' value='superior'>");
            
            out.println("<label for=\"CHECKBOX1\"><b>Superior<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX2\" name='queryCheckBox' value='deluxe'>");
            
            out.println("<label for=\"CHECKBOX2\"><b>Deluxe<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX3\" name='queryCheckBox' value='premier'>");
            
            out.println("<label for=\"CHECKBOX3\"><b>Premier<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX4\" name='queryCheckBox' value='plus'>");
            
            out.println("<label for=\"CHECKBOX4\"><b>Premier Plus<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<ul><li><h4>Price</h4>");
            out.println("<br>");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX5\" name='queryCheckBox' value='50'>");
            out.println("<label for=\"CHECKBOX5\"><b>50$ - 100$<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX6\" name='queryCheckBox' value='100'>");
            
            out.println("<label for=\"CHECKBOX6\"><b>100$ - 200$<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX7\" name='queryCheckBox' value='200'>");
            
            out.println("<label for=\"CHECKBOX7\"><b>200$ +<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<ul><li><h4>Location</h4>");
            out.println("<br><div><input type=\"checkbox\" id=\"CHECKBOX8\" name='queryCheckBox' value='chicago'>");
            
            out.println("<label for=\"CHECKBOX8\"><b>Chicago<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX9\" name='queryCheckBox' value='newyork'>");
            
            out.println("<label for=\"CHECKBOX9\"><b>NewYork<b></label>");
            out.println("</div>");
            out.println("</br><div><input type=\"checkbox\" id=\"CHECKBOX10\" name='queryCheckBox' value='washington'>");
            
            out.println("<label for=\"CHECKBOX10\"><b>Washington D.C<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            
            out.println("<div class=\"block\">");
            out.println("<input type='submit' value='Go' class='formbutton' align='center'>");
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
            
		}
		
		if(productType.equals("Suites"))
		{
			out.println("<section id=\"content\">");
            out.println("<article><h3>Suites</h3></article>");
            out.println("<article class=\"expanded\">");
            
            for(Map.Entry<String,Products> m :su.entrySet()){
            Products s = m.getValue();//21-9 ,21-15.
            
            out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
            out.println("<tr><td width=\"30%\">");
            out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"simages/");
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
                //session.setAttribute("pro",s.getAIDs());//session.setAttribute(s.getId(),s.getAIDs());
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
            out.println("</article>");
            
        }
			

			out.println("</section>");
		
		 out.println("<aside class=\"sidebar\">");
            
            out.println("<ul><li><h4>Suites Type</h4>");
            out.println("<ul><li><form method='GET' action='filterservlet'>");
            out.println("<div class=\"block form-group\">");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX1\"  name='queryCheckBox' value='executive'>");
            
            out.println("<label for=\"CHECKBOX1\"><b>Executive<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX2\" name='queryCheckBox' value='presidential'>");
            
            out.println("<label for=\"CHECKBOX2\"><b>Presidential<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX3\" name='queryCheckBox' value='royal'>");
            
            out.println("<label for=\"CHECKBOX3\"><b>Royal<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX4\" name='queryCheckBox' value='imperial'>");
            
            out.println("<label for=\"CHECKBOX4\"><b>Imperial<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<ul><li><h4>Price</h4>");
            out.println("<br>");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX5\" name='queryCheckBox' value='50'>");
            out.println("<label for=\"CHECKBOX5\"><b>50$ - 100$<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX6\" name='queryCheckBox' value='100'>");
            
            out.println("<label for=\"CHECKBOX6\"><b>100$ - 200$<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX7\" name='queryCheckBox' value='200'>");
            
            out.println("<label for=\"CHECKBOX7\"><b>200$ +<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<ul><li><h4>Location</h4>");
            out.println("<br><div><input type=\"checkbox\" id=\"CHECKBOX8\" name='queryCheckBox' value='chicago'>");
            
            out.println("<label for=\"CHECKBOX8\"><b>Chicago<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX9\" name='queryCheckBox' value='newyork'>");
            
            out.println("<label for=\"CHECKBOX9\"><b>NewYork<b></label>");
            out.println("</div>");
            out.println("</br><div><input type=\"checkbox\" id=\"CHECKBOX10\" name='queryCheckBox' value='washington'>");
            
            out.println("<label for=\"CHECKBOX10\"><b>Washington D.C<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            
            out.println("<div class=\"block\">");
            out.println("<input type='submit' value='Go' class='formbutton' align='center'>");
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
		}
		
		if(productType.equals("Dinning"))
		{
			out.println("<section id=\"content\">");
            out.println("<article><h3>Dining Option</h3></article>");
            out.println("<article class=\"expanded\">");
            
            for(Map.Entry<String,Products> m :di.entrySet()){
            Products s = m.getValue();//21-9 ,21-15.
            
            out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
            out.println("<tr><td width=\"30%\">");
            out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"dimages/");
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

                /*out.println("<tr><td><form method = 'get' action = 'CheckAvailability'>");
                out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getProductName() +"' value = 'Check Availability'>");
                out.println("<input type='hidden' name='productType' value='"+productType+"'>");
                out.println("<input type='hidden' name='productName' value='"+s.getProductName()+"'>");
                out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
                out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
                out.println("<input type='hidden' name='color' value='"+s.getLocation()+"'>");
            
                out.println("<input type='hidden' name='company' value='"+s.getHotelName()+"'>");
                out.println("<input type='hidden' name='quantity' value='"+1+"'>");
                out.println("<tr><td><label for=\"from\">From</label><input type=\"text\" id=\"from\" placeholder=\"MM/dd/yyyy\" name=\"from\"><label for=\"to\">to</label><input type=\"text\" id=\"to\" placeholder=\"MM/dd/yyyy\" name=\"to\"></td></tr>");
                out.println("</form></td></tr>");*/

                out.println("<tr><td><form method = 'get' action = 'addtocartservlet'>");
                out.println("<input class = 'button' type = 'submit' name = '"+ s.getProductName() +"' value = 'Add to Cart'>");
                out.println("<input type='hidden' name='productName' value='"+s.getProductName()+"'>");
                out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
                out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
                out.println("<input type='hidden' name='quantity' value='"+1+"'>");
                //session.setAttribute("pro",s.getAIDs());//session.setAttribute(s.getId(),s.getAIDs());
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

                /*out.println("<tr><td><form method = 'get' action = 'CheckAvailability'>");
                out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getProductName() +"' value = 'Check Availability'>");
                out.println("<input type='hidden' name='productType' value='"+productType+"'>");
                out.println("<input type='hidden' name='productName' value='"+s.getProductName()+"'>");
                out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
                out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
                out.println("<input type='hidden' name='color' value='"+s.getLocation()+"'>");
            
                out.println("<input type='hidden' name='company' value='"+s.getHotelName()+"'>");
                out.println("<input type='hidden' name='quantity' value='"+1+"'>");
                out.println("<tr><td><label for=\"from\">From</label><input type=\"text\" id=\"from\" placeholder=\"MM/dd/yyyy\" name=\"from\"><label for=\"to\">to</label><input type=\"text\" id=\"to\" placeholder=\"MM/dd/yyyy\" name=\"to\"></td></tr>");
                out.println("</form></td></tr>");*/
                out.println("</table></td></tr></table>");
            }
            out.println("</article>");
            
        }
			
			

			out.println("</section>");
		
		out.println("<aside class=\"sidebar\">");
            
            out.println("<ul><li><h4>DINING LOCATION</h4>");
            out.println("<ul><li><form method='GET' action='filterservlet'>");
            out.println("<div class=\"block form-group\">");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX1\"  name='queryCheckBox' value='chicago'>");
            
            out.println("<label for=\"CHECKBOX1\"><b>Chicago<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX2\" name='queryCheckBox' value='newyork'>");
            
            out.println("<label for=\"CHECKBOX2\"><b>NewYork<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX3\" name='queryCheckBox' value='washington'>");
            
            out.println("<label for=\"CHECKBOX3\"><b>Washington D.C<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<ul><li><h4>Price</h4>");
            out.println("<br>");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX5\" name='queryCheckBox' value='50'>");
            out.println("<label for=\"CHECKBOX5\"><b>50$ - 100$<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX6\" name='queryCheckBox' value='100'>");
            
            out.println("<label for=\"CHECKBOX6\"><b>100$ - 200$<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX7\" name='queryCheckBox' value='200'>");
            
            out.println("<label for=\"CHECKBOX7\"><b>200$ +<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            
            
            
            out.println("<div class=\"block\">");
            out.println("<input type='submit' value='Go' class='formbutton' align='center'>");
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
            out.println("<li><a href=\"contentservlet?productType=SR\">Sort by Rating</a><br></li>");
            out.println("<li><a href=\"TrendingServlet?trendType=MR\">Most Reviewed</a><br></li>");
                        out.println("<li><a href=\"TrendingServlet?trendType=ML\">Most Liked</a><br></li>");
            out.println("</ul>");
            out.println("</ul></ul></aside>");
		}
		
		if(productType.equals("LR"))
		{
			out.println("<section id=\"content\">");
            out.println("<article><h3>Leisure Facilities</h3></article>");
            out.println("<article class=\"expanded\">");
            
            for(Map.Entry<String,Products> m :lr.entrySet()){
            Products s = m.getValue();//21-9 ,21-15.
            
            out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
            out.println("<tr><td width=\"30%\">");
            out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"lfimages/");
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

                /*out.println("<tr><td><form method = 'get' action = 'CheckAvailability'>");
                out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getProductName() +"' value = 'Check Availability'>");
                out.println("<input type='hidden' name='productType' value='"+productType+"'>");
                out.println("<input type='hidden' name='productName' value='"+s.getProductName()+"'>");
                out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
                out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
                out.println("<input type='hidden' name='color' value='"+s.getLocation()+"'>");
            
                out.println("<input type='hidden' name='company' value='"+s.getHotelName()+"'>");
                out.println("<input type='hidden' name='quantity' value='"+1+"'>");
                out.println("<tr><td><label for=\"from\">From</label><input type=\"text\" id=\"from\" placeholder=\"MM/dd/yyyy\" name=\"from\"><label for=\"to\">to</label><input type=\"text\" id=\"to\" placeholder=\"MM/dd/yyyy\" name=\"to\"></td></tr>");
                out.println("</form></td></tr>");*/

                out.println("<tr><td><form method = 'get' action = 'addtocartservlet'>");
                out.println("<input class = 'button' type = 'submit' name = '"+ s.getProductName() +"' value = 'Add to Cart'>");
                out.println("<input type='hidden' name='productName' value='"+s.getProductName()+"'>");
                out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
                out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
                out.println("<input type='hidden' name='quantity' value='"+1+"'>");
                //session.setAttribute("pro",s.getAIDs());//session.setAttribute(s.getId(),s.getAIDs());
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

                /*out.println("<tr><td><form method = 'get' action = 'CheckAvailability'>");
                out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getProductName() +"' value = 'Check Availability'>");
                out.println("<input type='hidden' name='productType' value='"+productType+"'>");
                out.println("<input type='hidden' name='productName' value='"+s.getProductName()+"'>");
                out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
                out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
                out.println("<input type='hidden' name='color' value='"+s.getLocation()+"'>");
            
                out.println("<input type='hidden' name='company' value='"+s.getHotelName()+"'>");
                out.println("<input type='hidden' name='quantity' value='"+1+"'>");
                out.println("<tr><td><label for=\"from\">From</label><input type=\"text\" id=\"from\" placeholder=\"MM/dd/yyyy\" name=\"from\"><label for=\"to\">to</label><input type=\"text\" id=\"to\" placeholder=\"MM/dd/yyyy\" name=\"to\"></td></tr>");
                out.println("</form></td></tr>");*/
                out.println("</table></td></tr></table>");
            }
            out.println("</article>");
            
        }
			
			
			out.println("</section>");
		
		out.println("<aside class=\"sidebar\">");
            
            out.println("<ul><li><h4>Leisure Type</h4>");
            out.println("<ul><li><form method='GET' action='filterservlet'>");
            out.println("<div class=\"block form-group\">");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX1\"  name='queryCheckBox' value='indian'>");
            
            out.println("<label for=\"CHECKBOX1\"><b>Indian Style Spa<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX2\" name='queryCheckBox' value='fitness'>");
            
            out.println("<label for=\"CHECKBOX2\"><b>Fitness Centre<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX3\" name='queryCheckBox' value='sauna'>");
            
            out.println("<label for=\"CHECKBOX3\"><b>Sauna<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX4\" name='queryCheckBox' value='kidsclub'>");
            
            out.println("<label for=\"CHECKBOX4\"><b>CG Kids Club<b></label>");
            out.println("</div>");
            
            out.println("</br>");
            
            out.println("<ul><li><h4>Price</h4>");
            out.println("<br>");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX5\" name='queryCheckBox' value='50'>");
            out.println("<label for=\"CHECKBOX5\"><b>50$ - 100$<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX6\" name='queryCheckBox' value='100'>");
            
            out.println("<label for=\"CHECKBOX6\"><b>100$ - 200$<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX7\" name='queryCheckBox' value='200'>");
            
            out.println("<label for=\"CHECKBOX7\"><b>200$ +<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<ul><li><h4>Location</h4>");
            out.println("<br><div><input type=\"checkbox\" id=\"CHECKBOX8\" name='queryCheckBox' value='chicago'>");
            
            out.println("<label for=\"CHECKBOX8\"><b>Chicago<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX9\" name='queryCheckBox' value='newyork'>");
            
            out.println("<label for=\"CHECKBOX9\"><b>NewYork<b></label>");
            out.println("</div>");
            out.println("</br><div><input type=\"checkbox\" id=\"CHECKBOX10\" name='queryCheckBox' value='washington'>");
            
            out.println("<label for=\"CHECKBOX10\"><b>Washington D.C<b></label>");
            out.println("</div>");
            out.println("</br>");
            
            
            out.println("<div class=\"block\">");
            out.println("<input type='submit' value='Go' class='formbutton' align='center'>");
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
		}

		
		
            out.println("<div class=\"clear\"></div></div>");
	
	out.println("</div><footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
	out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
	out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
	out.println("<p>&copy; AlHamdanHotels 2017. by AlHamdan</p></div></footer></div>");
	out.println("</body></html>");

		out.close();
	}
	
}