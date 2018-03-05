import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class updateproduct extends HttpServlet {
	
	//String id, String category,String retailer, String image, String name, String company, String conditions,String color,String description, float price, String accessories;
	//HashMap<String,Products> products;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String fName = null;
		String dip=null;
		fName=(String)session.getAttribute("firstName1");

		
		String id = request.getParameter("id");
		
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Smart Portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\"><header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2>");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
		out.println("<script type='text/javascript' src='myscript.js'></script>"); 
		out.println("</header>");
		
		out.println("<h5>Welcome ");
		out.println(fName);
		out.println("!</h5>");

		out.println("<nav><ul><li class=\"start selected\"><a href=\"storemanagerservlet?productType=AddP\">Add Product</a></li><li><a href=\"storemanagerservlet?productType=DeleteP\">Delete Product</a></li><li><a href=\"storemanagerservlet?productType=UpdateP\">Update Product</a></li><li><a href=#>Data Analytics</a></li><li><a href=\"logoutservlet\">Logout</a></li></ul></nav>");
		
		
		
		
	if(id != null && id.length() != 0)
        id = id.trim();

	


	if(id != "")
	{
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Smart Portables</title>");
			out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head><body><div id=\"container\">");
			out.println("<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2></header>");

		out.println("<h3 align=\"center\">Update Product ID :"+id+"</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			out.println("<form action=\"updateproduct\" method=\"post\">");
			
		out.println("<p><label for=\"name\">ID:</label><input name=\"id\" id=\"firstName\" value="+id+" type=\"text\" /></p><p><label for=\"email\">Name:</label><input name=\"name\" id=\"lastName\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Retailer:</label><input name=\"retailer\" id=\"lastName\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Type:</label><input name=\"productType\" id=\"ad\" value=\"\" type=\"text\" /></p>");
		out.println("<p><label for=\"email\">Company:</label><input name=\"company\" id=\"email\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Color:</label>");				
		out.println("<input name=\"color\" id=\"email\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Condition:</label><input name=\"condition\" id=\"email\" value=\"\" type=\"text\" /></p>");
		out.println("<p><label for=\"email\">Description:</label><input name=\"description\" id=\"email\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Price:</label><input name=\"price\" id=\"email\" value=\"\" type=\"float\" /></p><p><label for=\"email\">Accessories [item1,item2,item3] formate:</label><input name=\"accessories\" id=\"email\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Image Name:</label><input name=\"imagePath\" id=\"email\" value=\"\" type=\"text\" /></p>");				
		out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Store\" type=\"submit\" /></p></form></div></fieldset>");
		
		
	}
	
		out.println("</div></fieldset></article</div></div></body></html>");
		out.close();

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String fName = null;
		String dip=null;
		fName=(String)session.getAttribute("firstName1");

		String category = request.getParameter("productType");
		String retailer = request.getParameter("retailer");
		String name = request.getParameter("name");
		String company = request.getParameter("company");
		String color = request.getParameter("color");
		String condition = request.getParameter("condition");
		String description = request.getParameter("description");
		float price = Float.parseFloat(request.getParameter("price"));
		String accessories = request.getParameter("accessories");
		String image = request.getParameter("imagePath");
		String id = request.getParameter("id");
		
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Smart Portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\"><header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2>");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
		out.println("<script type='text/javascript' src='myscript.js'></script>"); 
		out.println("</header>");
		
		out.println("<h5>Welcome ");
		out.println(fName);
		out.println("!</h5>");

		out.println("<nav><ul><li class=\"start selected\"><a href=\"storemanagerservlet?productType=AddP\">Add Product</a></li><li><a href=\"storemanagerservlet?productType=DeleteP\">Delete Product</a></li><li><a href=\"storemanagerservlet?productType=UpdateP\">Update Product</a></li><li><a href=#>Data Analytics</a></li><li><a href=\"logoutservlet\">Logout</a></li></ul></nav>");
		
		MySqlDataStoreUtilities.updateProductByProduct(id, category,retailer, image,name, company,condition,color,description, price, accessories);
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Smart Portables</title>");
		out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head><body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2></header>");
		out.println("<h3 align=\"center\">Product Updated Successfully! </h3>");

	}
			
			
}


