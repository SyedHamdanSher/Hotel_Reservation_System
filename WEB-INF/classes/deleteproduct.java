import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class deleteproduct extends HttpServlet {
	
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
		
		
			MySqlDataStoreUtilities.deleteProductByProduct(id);
		
			out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Smart Portables</title>");
			out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head><body><div id=\"container\">");
			out.println("<header><h1><a href=\"/\">Smart<span>Portables</span></a></h1><h2>Buy best for the best</h2></header>");
			out.println("<h3 align=\"center\">Product deleted Successfully! </h3>");
	}
	
		out.println("</div></fieldset></article</div></div></body></html>");
		out.close();

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
			
			
}


