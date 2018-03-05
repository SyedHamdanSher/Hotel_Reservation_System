import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class deleteitemservlet extends HttpServlet {
	
	
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
		String firstName = (String)session.getAttribute("firstName");
        int C = (int)session.getAttribute("C");
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Smart Portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\"><header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2>");
		out.println("</header>");
		
		if(firstName != null && !firstName.isEmpty())
		{
			System.out.println("Inside welcome string");
			out.println("<h5>Welcome ");
			out.println(firstName);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"loginedhomeservlet\">Home</a></li>");
		}
		else{
			out.println("<nav><ul><li class=\"start selected\"><a href=\"homeservlet\">Home</a></li>");
		}
		
		out.println("<li class=\"\"><a href=\"contentservlet?productType=All\">Products</a></li>");
		out.println("<li><a href=\"contentservlet?productType=Accessories\">Accessories</a></li>");
		
		if(firstName != null && !firstName.isEmpty())
		{
			out.println("<li><a href=\"cartservlet\">Cart"+C+"</a></li>");
			out.println("<li><a href=\"vieworderservlet\">Your Orders</a></li>");
			out.println("<li><a href=\"logoutservlet\">Logout</a></li>");
		}
		else
		{
			out.println("<li><a href=\"loginservlet\">Login</a></li>");
			out.println("<li><a href=\"signUP.html\">SignUp</a></li>");
			out.println("<li><a href=\"cartservlet\">Cart("+C+")</a></li>");
		}
		
		out.println("</ul></nav><img class=\"header-image\" src=\"images/homepage.jpg\" alt=\"Advertisment Image Here\" />");
		
		String action = request.getParameter("action1");
			
			
		if(action.equals("Remove"))
		{
			String id = request.getParameter("id");
			Cart cartObject;
			cartObject = (Cart) session.getAttribute("cart");
			cartObject.deleteItemFromCart(id);
			MySqlDataStoreUtilities.updateProductCount(id, "del");
			C=C-1;
			session.setAttribute("cart", cartObject);
			session.setAttribute("C", C);
			cartObject = (Cart) session.getAttribute("cart");
			C = (int)session.getAttribute("C");
			RequestDispatcher rd = request.getRequestDispatcher("cartservlet");
			rd.forward(request,response);
		}
		
		if(action.equals("CheckOut"))
		{	
			RequestDispatcher rd = request.getRequestDispatcher("checkoutservlet");
			rd.forward(request,response);
		}
		
		printSideBar(out);
		
		out.close();
	
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