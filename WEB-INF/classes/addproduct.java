import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class addproduct extends HttpServlet {
	
	//String id, String category,String retailer, String image, String name, String company, String conditions,String color,String description, float price, String accessories;
	//HashMap<String,Products> products;
	
	
	public void init()
	{

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String fName = null;
		String dip=null;
		fName=(String)session.getAttribute("firstName1");

		String category = request.getParameter("productType");
		String id = request.getParameter("id");
		String retailer = request.getParameter("retailer");
		String name = request.getParameter("name");
		String company = request.getParameter("company");
		String color = request.getParameter("color");
		String condition = request.getParameter("condition");
		String description = request.getParameter("description");
		float price = Float.parseFloat(request.getParameter("price"));
		String accessories = request.getParameter("accessories");
		String image = request.getParameter("imagePath");
		
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
		
		
		
		if(category != null && category.length() != 0)
		category = category.trim();

	if(id != null && id.length() != 0)
        id = id.trim();

	if(retailer != null && retailer.length() != 0)
        retailer = retailer.trim();

	if(name != null && name.length() != 0)
        name = name.trim();

	if(company != null && company.length() != 0)
       company = company.trim();
   if(color != null && color.length() != 0)
       color = color.trim();
   if(condition != null && condition.length() != 0)
       condition = condition.trim();
   if(description != null && description.length() != 0)
       description = description.trim();

   if(accessories != null && accessories.length() != 0)
       accessories = accessories.trim();
   if(image != null && image.length() != 0)
       image = image.trim();


	if(category != "" && id != "" && retailer != "" && name != "" && company != "" && color != "" && condition != "" && description != "" && accessories != "" && image != "")
	{
		
		
			MySqlDataStoreUtilities.insertProductByProduct(id, category,retailer, image,name, company, condition, color, description, price, accessories);
		
			out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Smart Portables</title>");
			out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head><body><div id=\"container\">");
			out.println("<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2></header>");
			out.println("<h3 align=\"center\">Product added Successfully! </h3>");
	}
	
		out.println("</div></fieldset></article</div></div></body></html>");
		out.close();

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
			
			
}


