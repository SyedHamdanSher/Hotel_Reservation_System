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
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ViewReviews extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String fName = null;
		if (session!=null){
		fName=(String)session.getAttribute("firstName");}

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
		
		if(fName != null && !fName.isEmpty())
		{
			out.println("<h5>Welcome ");
			out.println(fName);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"loginedhomeservlet\">Home</a></li>");
		}else{
			out.println("<nav><ul><li class=\"start selected\"><a href=\"homeservlet\">Home</a></li>");
		}
		out.println("<li class=\"\"><a href=\"contentservlet?productType=All\">Products</a></li>");
		out.println("<li><a href=\"contentservlet?productType=Accessories\">Accessories</a></li>");
		
		
		if(fName != null && !fName.isEmpty())
		{
			out.println("<li><a href=\"cartservlet\">Cart("+C+")</a></li>");
			out.println("<li><a href=\"vieworderservlet\">Your Orders</a></li>");
			out.println("<li><a href=\"logoutservlet\">Logout</a></li></ul></nav>");
		}
		else
		{
			out.println("<li><a href=\"loginservlet\">Login</a></li>");
			out.println("<li><a href=\"signup\">SignUp</a></li></ul></nav>");
		}
		out.println("<div id=\"body\">");
		
		
		String userid=(String)session.getAttribute("userid");
		
		String productName = request.getParameter("productName");
		
		HashMap<String,Review> reviews;
		reviews = MongoDBDataStoreUtilities.getReviews();
		
		HashMap<String,Review> thisProductReviews;
		thisProductReviews = new HashMap<String, Review>();
		
		for(Map.Entry<String,Review> m :reviews.entrySet())
		{
			String key = m.getKey();
			Review c = m.getValue();
			
			if(c.getProductName().equals(productName))
			{
				thisProductReviews.put(key, c);
			}
		}
		
		
		if(thisProductReviews.isEmpty()){
			out.println("<section id=\"content\">");
			out.println("<article>");
			out.println("<h3>There are no Reviews for this product<h3>");
			out.println("</article>");
			out.println("</section>");

			/*out.println("<aside class=\"sidebar\">");

			out.println("<ul><li><h4><a href=\"contentservlet?productType=Smart Watches\">Smart Watches</a></h4></li>");
			out.println("<li><h4><a href=\"contentservlet?productType=Speakers\">Speakers</a></h4></li>");
			out.println("<li><h4><a href=\"contentservlet?productType=Headphones\">Headphones</a></h4></li>");
			out.println("<li><h4><a href=\"contentservlet?productType=Phones\">Phones</a></h4></li>");
			out.println("<li><h4><a href=\"contentservlet?productType=Laptops\">Laptops</a></h4></li>");
			out.println("<li><h4><a href=\"contentservlet?productType=External Storages\">External Storage</a></h4></li>");
			out.println("</ul></aside><div class=\"clear\"></div></div>");*/
	
			out.println("<footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
			out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
			out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
			out.println("<p>&copy; Al Hamdan 2017. by Syed Hamdan Sher</p></div></footer></div>");
			out.println("</body></html>");
		}
		else	
		{
			String name;
			String emailId;
			int reviewRating;
			Date date;
			String reviewText;
			
			String retailer;
			String retailerZip;
			String retailerCity;

			out.println("<section id=\"content\">");
			
			out.println("<h3 align='center'>Product Reviews</h3>");
			out.println("<table>");
			out.println("<tr><td>No.</td><td>Product Name</td><td>Email Id</td><td>Review Rating</td><td>Review Date</td><td>Retailer</td><td>Retailer Zip</td><td>Retailer City</td><td>Review text</td></tr>");
				
			int no=1;
			
				for(Map.Entry<String,Review> m :thisProductReviews.entrySet()){
					
					Review c = m.getValue();
					
					name = c.getProductName();
					emailId = c.getEmailId();
					reviewRating = c.getReviewRating();
					
					retailer = c.getRetailer();
					retailerZip = c.getRetailerZip();
					retailerCity = c.getRetailerCity();
					
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					date = c.getReviewDate();
					String reviewDate = dateFormat.format(date);
					
					reviewText = c.getReviewText();
					
					out.println("<tr><td>"+no+"</td>");
					out.println("<td>"+name+"</td>");
					out.println("<td>"+emailId+"</td>");
					out.println("<td>"+reviewRating+"</td>");
					out.println("<td>"+reviewDate+"</td>");
					out.println("<td>"+retailer+"</td>");
					out.println("<td>"+retailerZip+"</td>");
					out.println("<td>"+retailerCity+"</td>");
					out.println("<td>"+reviewText+"</td></tr>");
					
					no++;
					
				}
			
			out.println("</table>");
			out.println("</section>");

			out.println("<aside class=\"sidebar\">");

			
	
	out.println("</ul></aside><div class=\"clear\"></div></div>");

	
	out.println("<footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
	out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
	out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
	out.println("<p>&copy; Al Hamdan 2017. by Syed Hamdan Sher</p></div></footer></div>");
	out.println("</body></html>");	
		}
	}
}