import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class loginedhomeservlet extends HttpServlet
{
	Customer customer;
	HashMap<String,Customer> customers;
    String emailId;
	String password;
	
	fillHashmap fH;
	
	public void init()
	{
		fH = new fillHashmap();
		customers = new HashMap<String, Customer>();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	HttpSession session = request.getSession();
	String fName = (String)session.getAttribute("firstName");
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
		out.println("<script type=\"text/javascript\" src=\"JS/javascript.js\"></script><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script><script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script></head>");
		//if(fName != null && !fName.isEmpty())
		//{
			out.println("<body onload='init()'><div id=\"container\"><header><div align=\"right\" ><a href=\"logoutservlet\">Logout</a><br></div><img src=\"images/AlHamdan.jpg\" alt=\"\" style=\"width:70px;height:70px;\"><h4>AL-HAMDAN</h4>");
		
		//}else{
			//out.println("<body onload='init()'><div id=\"container\"><header><div align=\"right\" ><a href=\"Login.html\" >Login</a><a href=\"SignUP.html\">Signup</a><br></div><img src=\"images/AlHamdan.jpg\" alt=\"\" style=\"width:70px;height:70px;\"><h4>AL-HAMDAN</h4>");
		
		//}
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
	out.println("<section id=\"content\">");
	out.println("<article>");
	out.println("<div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\">");
            out.println("<ol class=\"carousel-indicators\">");
                out.println("<li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>");
                out.println("<li data-target=\"#myCarousel\" data-slide-to=\"1\"></li>");
                out.println("<li data-target=\"#myCarousel\" data-slide-to=\"2\"></li></ol>");
            out.println("<div class=\"carousel-inner\">");
            out.println("<div class=\"item active\">");
                out.println("<img src=\"coimage/AlHamdan1.jpg\" alt=\"Image not found AlHamdan1\" class = \"img-responsive\">");
            out.println("</div>");
            out.println("<div class=\"item\">");
            out.println("<img src=\"coimage/AlHamdan2.jpg\" alt=\"Image not found AlHamdan2.jpg\" class = \"img-responsive\">");
            out.println("</div>");
            out.println("<div class=\"item\">");
            out.println("<img src=\"coimage/AlHamdan3.jpg\" alt=\"Image not found AlHamdan3.jpg\" class = \"img-responsive\">");
            out.println("</div>");
            out.println("</div>");
            out.println("<a class=\"left carousel-control\" href=\"#myCarousel\" data-slide=\"prev\">");
            out.println("<span class=\"glyphicon glyphicon-chevron-left\"></span>");
            out.println("<span class=\"sr-only\">Previous</span>");
            out.println("</a>");
            out.println("<a class=\"right carousel-control\" href=\"#myCarousel\" data-slide=\"next\">");
            out.println("<span class=\"glyphicon glyphicon-chevron-right\"></span>");
            out.println("<span class=\"sr-only\">Next</span>");
            out.println("</a>");
        out.println("</div>");
                                        
        out.println("<h3>Overview</h3>");
          out.println("<p>At the Al Hamdan hotel, we bring you an experience of your life time! We have luxury the rooms, fancy dip in our pools, pampering in our Spa. We give you the break you deserve! We are devoted to make everyone feel like a cherished guest</p>");   
           out.println("<p> Our warm inviting decor and thoughtful staff will soon have you calling this hotel your \"home\" away from home! Our hotel offers unparalleled service and easy access to everything the city has to offer. Other amenities including free wireless Internet access! </p>");
            out.println("<p>You are welcome to view our image gallery and hotel services pages. We welcome you with open hearts.</p>");
        out.println("</article>");
	out.println("</section>");
	
	out.println("<aside class=\"sidebar\">");
                              
            out.println("<ul><li><h4>Gallery</h4>");
            out.println("<ul><li><form method = 'get' action = 'filterservlet'>");
            out.println("<div class=\"block form-group\">");
            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX1\" name=\"HotelName\" value =\"HotelOne\">");
                            
            out.println("<label for=\"CHECKBOX1\"><b>HOTEL 1<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX1\" name=\"HotelName\" value =\"HotelTwo\">");
                            
                            out.println("<label for=\"CHECKBOX2\"><b>HOTEL 2<b></label>");
                            out.println("</div>");
                            
                            out.println("</br>");
                            
                            out.println("<div><input type=\"checkbox\" id=\"CHECKBOX3\" name=\"HotelName\" value =\"HotelThree\">");
                            
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
            out.println("</ul></ul></aside>");out.println("<div class=\"clear\"></div></div>");

	
	out.println("<footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
	out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
	out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
	out.println("<p>&copy; Smart Portables 2017. by Syed Hamdan Sher</p></div></footer></div>");
	out.println("</body></html>");
	
	
	out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
	}
	
	
}