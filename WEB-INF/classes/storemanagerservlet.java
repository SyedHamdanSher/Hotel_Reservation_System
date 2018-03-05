import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class storemanagerservlet extends HttpServlet {

	ArrayList<Object> products;
	HashMap<String, Products> smartwatches;
	HashMap<String, Products> speakers;
	HashMap<String, Products> headphones;
	HashMap<String, Products> phones;
	HashMap<String, Products> laptops;
	HashMap<String, Products> externalstorages;
	HashMap<String, Accessories> accessories;
	ArrayList<String> productName;
	ArrayList<Integer> available;
	ArrayList<String> productName1;
	ArrayList<Double> total;
	Order order;
	HashMap<String,Order> orders;
	
	MySqlDataStoreUtilities mysql = new MySqlDataStoreUtilities();
	
	void loadXML()
	{
		try{
		products = mysql.getProducts();
		
		smartwatches = (HashMap<String,Products>)products.get(0);
		speakers = (HashMap<String, Products>)products.get(1);
		headphones = (HashMap<String, Products>)products.get(2);
		phones = (HashMap<String, Products>)products.get(3);
		laptops = (HashMap<String, Products>)products.get(4);
		externalstorages = (HashMap<String, Products>)products.get(5);
		accessories = (HashMap<String, Accessories>)products.get(6);

		orders = mysql.getDailySales();
		
		}catch(Exception E){
		System.out.println("Exception");
		}
	}
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		loadXML();
		
		HttpSession session = request.getSession();
		String fName = null;
		String dip=null;
		fName=(String)session.getAttribute("firstName1");
		
		String productType = request.getParameter("productType");
		productName = new ArrayList<String>();
		productName1 = new ArrayList<String>();
		available = new ArrayList<Integer>();
		total = new ArrayList<Double>();
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Al Hamdan</title><link rel=\"stylesheet\" href=\"styles1.css\" type=\"text/css\" />");
		out.println("<script type=\"text/javascript\" src=\"JS/javascript.js\"></script></head>");
		if(fName != null && !fName.isEmpty())
		{
			out.println("<body onload='init()'><div id=\"container\"><header><div align=\"right\" ><a href=\"logoutservlet\">Logout</a><br></div><img src=\"images/AlHamdan.jpg\" alt=\"\" style=\"width:70px;height:70px;\"><h4>AL-HAMDAN</h4>");
		
		}else{
			out.println("<body onload='init()'><div id=\"container\"><header><div align=\"right\" ><a href=\"Login.html\" >Login</a><a href=\"SignUP.html\">Signup</a><br></div><img src=\"images/AlHamdan.jpg\" alt=\"\" style=\"width:70px;height:70px;\"><h4>AL-HAMDAN</h4>");
		
		}

		out.println("</header>");
		
		out.println("<h5>Welcome ");
		out.println(fName);
		out.println("!</h5>");

		out.println("<nav><ul><li class=\"start selected\"><a href=\"storemanagerservlet?productType=AddP\">Add</a></li><li><a href=\"storemanagerservlet?productType=DeleteP\">Delete</a></li><li><a href=\"storemanagerservlet?productType=UpdateP\">Update</a></li><li><a href=\"storemanagerservlet?productType=#\">Data Analytics</a></li><li><a href=\"logoutservlet\">Logout</a></li></ul></nav>");
		out.println("<div id=\"body\">");
	if(productType.equals("AddP")){

			out.println("<h3 align=\"center\">Add new Product</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			out.println("<form action=\"addproduct\" method=\"get\">");
			
		out.println("<p><label for=\"name\">ID:</label><input name=\"id\" id=\"firstName\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Name:</label><input name=\"name\" id=\"lastName\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Retailer:</label><input name=\"retailer\" id=\"lastName\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Type:</label><input name=\"productType\" id=\"ad\" value=\"\" type=\"text\" /></p>");
		out.println("<p><label for=\"email\">Company:</label><input name=\"company\" id=\"email\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Color:</label>");				
		out.println("<input name=\"color\" id=\"email\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Condition:</label><input name=\"condition\" id=\"email\" value=\"\" type=\"text\" /></p>");
		out.println("<p><label for=\"email\">Description:</label><input name=\"description\" id=\"email\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Price:</label><input name=\"price\" id=\"email\" value=\"\" type=\"float\" /></p><p><label for=\"email\">Accessories [item1,item2,item3] formate:</label><input name=\"accessories\" id=\"email\" value=\"\" type=\"text\" /></p><p><label for=\"email\">Image Name:</label><input name=\"imagePath\" id=\"email\" value=\"\" type=\"text\" /></p>");				
		out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Add to Store\" type=\"submit\" /></p></form></div></fieldset>");
	}

	if(productType.equals("DA")){

		//out.println("<section id=\"content\">");
		
		out.println("<article><h3>Please select the type of report you want</h3></article>");
		//out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
		out.println("<div><h4>Report</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=IR\">Inventory Report</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=SR\">Sales Report</a></li></ul>");
		out.println("</div></div>");

		/*out.println("<aside class=\"sidebar\">");

		out.println("<ul><li><h4>Report</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=IR\">Inventory Report</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=SR\">Sales Report</a></li></ul></li></ul></aside>");*/

	}

	if(productType.equals("IR")){
		
		out.println("<h3 align=\"center\">Inventory Report</h3>");
		out.println("<aside class=\"sidebar\">");

		out.println("<ul><li><h4>Report</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=IR\">Inventory Report</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=SR\">Sales Report</a></li></ul></li></ul>");
		out.println("<ul><li><h4>Options</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=CA\">Check Availability</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=BCI\">Bar Chart </a></li><li><a href=\"storemanagerservlet?productType=POS\">Products OnSales</a></li><li><a href=\"storemanagerservlet?productType=POR\">Products OnRebates</a></li></ul></li></ul></aside>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
			out.println("<div class='col-xs-12'>");
					out.println("<table class='table'>");
						out.println("<thead>");
							//out.println("<th>");
								out.println("<td>Id</td>");
								out.println("<td>Name</td>");
								out.println("<td>Price</td>");
								out.println("<td>Remaining Quantity</td>");
							//out.println("</th>");
						out.println("</thead>");
						
						out.println("<tbody>");
						
						
			for(Map.Entry<String,Products> m :smartwatches.entrySet()){
				Products s = m.getValue();
							out.println("<tr>");
							// //out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			for(Map.Entry<String,Products> m :speakers.entrySet()){
				Products s = m.getValue();
							out.println("<tr>");
							// //out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			for(Map.Entry<String,Products> m :headphones.entrySet()){
				Products s = m.getValue();
							out.println("<tr>");
							// //out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			for(Map.Entry<String,Products> m :phones.entrySet()){
				Products s = m.getValue();
							out.println("<tr>");
							// //out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			for(Map.Entry<String,Products> m :laptops.entrySet()){
				Products s = m.getValue();
							out.println("<tr>");
							// //out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			for(Map.Entry<String,Products> m :externalstorages.entrySet()){
				Products s = m.getValue();
							out.println("<tr>");
							// //out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			out.println("</tbody>");
					out.println("</table>");
				out.println("</div>");
			out.println("</div></fieldset></div>");
			
	}

	if(productType.equals("CA")){
		
		out.println("<h3 align=\"center\">Availability Report</h3>");
		out.println("<aside class=\"sidebar\">");

		out.println("<ul><li><h4>Report</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=IR\">Inventory Report</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=SR\">Sales Report</a></li></ul></li></ul>");
		out.println("<ul><li><h4>Options</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=CA\">Check Availability</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=BCI\">Bar Chart </a></li><li><a href=\"storemanagerservlet?productType=POS\">Products OnSales</a></li><li><a href=\"storemanagerservlet?productType=POR\">Products OnRebates</a></li></ul></li></ul></aside>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
			out.println("<div class='col-xs-12'>");
					out.println("<table class='table'>");
						out.println("<thead>");
							//out.println("<th>");
								out.println("<td>Id</td>");
								out.println("<td>Name</td>");
								out.println("<td>Price</td>");
								out.println("<td>Remaining Quantity</td>");
							//out.println("</th>");
						out.println("</thead>");
						
						out.println("<tbody>");
						
						
			for(Map.Entry<String,Products> m :smartwatches.entrySet()){
				Products s = m.getValue();
							out.println("<tr>");
							// //out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			for(Map.Entry<String,Products> m :speakers.entrySet()){
				Products s = m.getValue();
							out.println("<tr>");
							// //out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			for(Map.Entry<String,Products> m :headphones.entrySet()){
				Products s = m.getValue();
							out.println("<tr>");
							// //out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			for(Map.Entry<String,Products> m :phones.entrySet()){
				Products s = m.getValue();
							out.println("<tr>");
							// //out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			for(Map.Entry<String,Products> m :laptops.entrySet()){
				Products s = m.getValue();
							out.println("<tr>");
							// //out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			for(Map.Entry<String,Products> m :externalstorages.entrySet()){
				Products s = m.getValue();
							out.println("<tr>");
							// //out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			out.println("</tbody>");
					out.println("</table>");
				out.println("</div>");
			out.println("</div></fieldset></div>");
			
	}

	if(productType.equals("POS")){
		
		out.println("<h3 align=\"center\">Products On Sales</h3>");
		out.println("<aside class=\"sidebar\">");

		out.println("<ul><li><h4>Report</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=IR\">Inventory Report</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=SR\">Sales Report</a></li></ul></li></ul>");
		out.println("<ul><li><h4>Options</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=CA\">Check Availability</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=BCI\">Bar Chart </a></li><li><a href=\"storemanagerservlet?productType=POS\">Products OnSales</a></li><li><a href=\"storemanagerservlet?productType=POR\">Products OnRebates</a></li></ul></li></ul></aside>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");

			out.println("<div class='col-xs-12'>");
					out.println("<table class='table'>");
						out.println("<thead>");
							//out.println("<th>");
								out.println("<td>Id</td>");
								out.println("<td>Name</td>");
								out.println("<td>Price</td>");
								//out.println("<td>On Sale</td>");
							//out.println("</th>");
						out.println("</thead>");
						
						out.println("<tbody>");
						
						
			for(Map.Entry<String,Products> m :smartwatches.entrySet()){
				Products s = m.getValue();
				Boolean sale=s.getOnSale();
				//productName.add(s.getName());
				//available.add(s.getrQuantity());
				if (sale) {
							out.println("<tr>");
							// out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
				}
			}
			for(Map.Entry<String,Products> m :speakers.entrySet()){
				Products s = m.getValue();
				Boolean sale=s.getOnSale();
				//String sale=s.getSale();
				//productName.add(s.getName());
				//available.add(s.getrQuantity());
				if (sale) {
							out.println("<tr>");
							// out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
				}
			}
			for(Map.Entry<String,Products> m :headphones.entrySet()){
				Products s = m.getValue();
				Boolean sale=s.getOnSale();
				//productName.add(s.getName());
				//available.add(s.getrQuantity());
				if (sale) {
							out.println("<tr>");
							// out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
				}
			}
			for(Map.Entry<String,Products> m :phones.entrySet()){
				Products s = m.getValue();
				Boolean sale=s.getOnSale();
				//productName.add(s.getName());
				//available.add(s.getrQuantity());
				if (sale) {
							out.println("<tr>");
							// out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
				}
			}
			for(Map.Entry<String,Products> m :laptops.entrySet()){
				Products s = m.getValue();
				Boolean sale=s.getOnSale();
				//productName.add(s.getName());
				//available.add(s.getrQuantity());
				if (sale) {
							out.println("<tr>");
							// out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
				}
			}
			for(Map.Entry<String,Products> m :externalstorages.entrySet()){
				Products s = m.getValue();
				Boolean sale=s.getOnSale();
				//productName.add(s.getName());
				//available.add(s.getrQuantity());
				if (sale) {
							out.println("<tr>");
							// out.println("<form action='AddProductsDB' method='post'>");
							// out.println("<input type='hidden' name='id' value='"+s.getId()+"'>");
							// out.println("<input type='hidden' name='type' value='Smartphone'>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
				}
			}
			out.println("</tbody>");
					out.println("</table>");
				out.println("</div>");
			
			out.println("</div></fieldset></div>");
			
	}

	if(productType.equals("BCI")){
		
		out.println("<h3 align=\"center\">Bar Chart Report</h3>");
		out.println("<aside class=\"sidebar\">");

		out.println("<ul><li><h4>Report</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=IR\">Inventory Report</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=SR\">Sales Report</a></li></ul></li></ul>");
		out.println("<ul><li><h4>Options</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=CA\">Check Availability</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=BCI\">Bar Chart </a></li><li><a href=\"storemanagerservlet?productType=POS\">Products OnSales</a></li><li><a href=\"storemanagerservlet?productType=POR\">Products OnRebates</a></li></ul></li></ul></aside>");
		out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
		out.println("</div></fieldset></div>");
		out.println("<div class='col-xs-10'>");
					
						out.println("<div id='barchart_material' height=1000px width=100%></div>");
						
			
				out.println("</div>");

		for(Map.Entry<String,Products> m :smartwatches.entrySet()){
				Products s = m.getValue();
				productName.add(s.getName());
				available.add(s.getrQuantity());
			}
			for(Map.Entry<String,Products> m :speakers.entrySet()){
				Products s = m.getValue();
				productName.add(s.getName());
				available.add(s.getrQuantity());
			}
			for(Map.Entry<String,Products> m :headphones.entrySet()){
				Products s = m.getValue();
				productName.add(s.getName());
				available.add(s.getrQuantity());
			}
			for(Map.Entry<String,Products> m :phones.entrySet()){
				Products s = m.getValue();
				productName.add(s.getName());
				available.add(s.getrQuantity());
			}
			for(Map.Entry<String,Products> m :laptops.entrySet()){
				Products s = m.getValue();
				productName.add(s.getName());
				available.add(s.getrQuantity());
			}
			for(Map.Entry<String,Products> m :externalstorages.entrySet()){
				Products s = m.getValue();
				productName.add(s.getName());
				available.add(s.getrQuantity());
			}
		

		
		out.println("<script type='text/javascript' src='JS/loader.js'></script>");
		out.println("<script type='text/javascript'>");
		  out.println("google.charts.load('current', {'packages':['bar']});");
		  out.println("google.charts.setOnLoadCallback(drawChart);");

		  out.println("function drawChart() {");
			out.println("var data = google.visualization.arrayToDataTable([");
			  out.println("['Product Name', 'Availability']");
			   
			  for (int i=0;i<productName.size(); i++){
					out.println(",['"+productName.get(i)+"',"+available.get(i)+"]");
					
			  }
			  
				out.println("]);");
			
			
			out.println("var options = {");
			  out.println("chart: {");
				out.println("title: 'Smart Portables',");
				out.println("subtitle: 'Product and there availability',");
				//
			  out.println("},");
			  out.println("'width':1000,'height':1000,");
			  out.println("bars: 'horizontal' // Required for Material Bar Charts.");
			out.println("};");

			out.println("var chart = new google.charts.Bar(document.getElementById('barchart_material'));");
			out.println("var container = document.getElementById('barchart_material');");
			// throw error for testing
			out.println("google.visualization.events.addListener(chart, 'ready', function () {");
			  out.println("throw new Error('Test Google Error');");
			out.println("});");

			// listen for error
			out.println("google.visualization.events.addListener(chart, 'error', function (err) {");
			  // check error
			  out.println("console.log(err.id, err.message);");

			  // remove error
			  out.println("google.visualization.errors.removeError(err.id);");

			  // remove all errors
			  out.println("google.visualization.errors.removeAll(container);");
			out.println("});");

			out.println("chart.draw(data, google.charts.Bar.convertOptions(options));");
		  out.println("}");
		out.println("</script>");
		
			
	}

	if(productType.equals("POR")){
		
		out.println("<h3 align=\"center\">Product On Manufacturer Rebate</h3>");
		out.println("<aside class=\"sidebar\">");

		out.println("<ul><li><h4>Report</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=IR\">Inventory Report</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=SR\">Sales Report</a></li></ul></li></ul>");
		out.println("<ul><li><h4>Options</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=CA\">Check Availability</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=BCI\">Bar Chart </a></li><li><a href=\"storemanagerservlet?productType=POS\">Products OnSales</a></li><li><a href=\"storemanagerservlet?productType=POR\">Products OnRebates</a></li></ul></li></ul></aside>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
			out.println("<div class='col-xs-12'>");
					out.println("<table class='table'>");
						out.println("<thead>");
							//out.println("<th>");
								out.println("<td>Id</td>");
								out.println("<td>Name</td>");
								out.println("<td>Price</td>");
								out.println("<td>Stock Available</td>");
							//out.println("</th>");
						out.println("</thead>");
						
						out.println("<tbody>");
						
						
			for(Map.Entry<String,Products> m :smartwatches.entrySet()){
				Products s = m.getValue();
				Boolean rebate=s.getOnRebates();
				
				if (rebate) {
							out.println("<tr>");
							
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
				}
			}

			for(Map.Entry<String,Products> m :speakers.entrySet()){
				Products s = m.getValue();
				Boolean rebate=s.getOnRebates();
				
				if (rebate) {
							out.println("<tr>");
							
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
				}
			}

			for(Map.Entry<String,Products> m :headphones.entrySet()){
				Products s = m.getValue();
				Boolean rebate=s.getOnRebates();
				
				if (rebate) {
							out.println("<tr>");
							
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
				}
			}

			for(Map.Entry<String,Products> m :phones.entrySet()){
				Products s = m.getValue();
				Boolean rebate=s.getOnRebates();
				
				if (rebate) {
							out.println("<tr>");
							
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
				}
			}

			for(Map.Entry<String,Products> m :laptops.entrySet()){
				Products s = m.getValue();
				Boolean rebate=s.getOnRebates();
				
				if (rebate) {
							out.println("<tr>");
							
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
				}
			}

			for(Map.Entry<String,Products> m :externalstorages.entrySet()){
				Products s = m.getValue();
				Boolean rebate=s.getOnRebates();
				
				if (rebate) {
							out.println("<tr>");
							
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getrQuantity()+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
				}
			}
			out.println("</tbody>");
					out.println("</table>");
				out.println("</div>");

			out.println("</div></fieldset></div>");
			
	}

	if(productType.equals("SR")){
		
		out.println("<h3 align=\"center\">Sales Report</h3>");

		out.println("<aside class=\"sidebar\">");

		out.println("<ul><li><h4>Report</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=IR\">Inventory Report</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=SR\">Sales Report</a></li></ul></li></ul>");
		out.println("<ul><li><h4>Options</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=PS\">Products Sold</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=BCS\">Bar Chart </a></li><li><a href=\"storemanagerservlet?productType=DS\">Daily Sales</a></li></ul></li></ul></aside>");
		out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
			out.println("</div></fieldset></div>");
	}

	if(productType.equals("PS")){
		
		out.println("<h3 align=\"center\">Products Sold</h3>");

		out.println("<aside class=\"sidebar\">");

		out.println("<ul><li><h4>Report</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=IR\">Inventory Report</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=SR\">Sales Report</a></li></ul></li></ul>");
		out.println("<ul><li><h4>Options</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=PS\">Products Sold</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=BCS\">Bar Chart </a></li><li><a href=\"storemanagerservlet?productType=DS\">Daily Sales</a></li></ul></li></ul></aside>");
		out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
		out.println("<div class='col-xs-12'>");
					out.println("<table class='table'>");
						out.println("<thead>");
							//out.println("<th>");
								out.println("<td>Id</td>");
								out.println("<td>Name</td>");
								out.println("<td>Price</td>");
								out.println("<td>Quantity Sold</td>");
								out.println("<td>Total Sales</td>");
							//out.println("</th>");
						out.println("</thead>");
						
						out.println("<tbody>");
		for(Map.Entry<String,Products> m :smartwatches.entrySet()){
				Products s = m.getValue();
				double sale = s.getSales();
				
							out.println("<tr>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getsQuantity()+"</td>");
								out.println("<td>"+sale+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}

			for(Map.Entry<String,Products> m :speakers.entrySet()){
				Products s = m.getValue();
				double sale = s.getSales();
				
							out.println("<tr>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getsQuantity()+"</td>");
								out.println("<td>"+sale+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}

			for(Map.Entry<String,Products> m :headphones.entrySet()){
				Products s = m.getValue();
				double sale = s.getSales();
				
							out.println("<tr>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getsQuantity()+"</td>");
								out.println("<td>"+sale+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}

			for(Map.Entry<String,Products> m :phones.entrySet()){
				Products s = m.getValue();
				double sale = s.getSales();
				
							out.println("<tr>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getsQuantity()+"</td>");
								out.println("<td>"+sale+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			for(Map.Entry<String,Products> m :laptops.entrySet()){
				Products s = m.getValue();
				double sale = s.getSales();
				
							out.println("<tr>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getsQuantity()+"</td>");
								out.println("<td>"+sale+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}

			for(Map.Entry<String,Products> m :externalstorages.entrySet()){
				Products s = m.getValue();
				double sale = s.getSales();
				
							out.println("<tr>");
								
								out.println("<td>"+s.getId()+"</td>");
								out.println("<td>"+s.getName()+"</td}>");
								out.println("<td>"+s.getPrice()+"</td>");
								out.println("<td>"+s.getsQuantity()+"</td>");
								out.println("<td>"+sale+"</td>");
								//out.println("<td><a href='#' class='btn btn-danger del-prod'>Delete</a></td>");
							out.println("</tr>");
			}
			out.println("</tbody>");
					out.println("</table>");
				out.println("</div>");

		out.println("</div></fieldset></div>");
	}

	if(productType.equals("BCS")){
		
		out.println("<h3 align=\"center\">Bar Chart</h3>");

		out.println("<aside class=\"sidebar\">");

		out.println("<ul><li><h4>Report</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=IR\">Inventory Report</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=SR\">Sales Report</a></li></ul></li></ul>");
		out.println("<ul><li><h4>Options</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=PS\">Products Sold</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=BCS\">Bar Chart </a></li><li><a href=\"storemanagerservlet?productType=DS\">Daily Sales</a></li></ul></li></ul></aside>");
		out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			out.println("</div></fieldset></div>");
			out.println("<div class='col-xs-12'>");
					
						out.println("<div id='barchart_material' height=1000px width=100%></div>");
						
			
				out.println("</div>");

				for(Map.Entry<String,Products> m :smartwatches.entrySet()){
				Products s = m.getValue();
				productName1.add(s.getName());
				total.add(s.getSales());
			}
			for(Map.Entry<String,Products> m :speakers.entrySet()){
				Products s = m.getValue();
				productName1.add(s.getName());
				total.add(s.getSales());
			}
			for(Map.Entry<String,Products> m :headphones.entrySet()){
				Products s = m.getValue();
				productName1.add(s.getName());
				total.add(s.getSales());
			}
			for(Map.Entry<String,Products> m :phones.entrySet()){
				Products s = m.getValue();
				productName1.add(s.getName());
				total.add(s.getSales());
			}
			for(Map.Entry<String,Products> m :laptops.entrySet()){
				Products s = m.getValue();
				productName1.add(s.getName());
				total.add(s.getSales());
			}
			for(Map.Entry<String,Products> m :externalstorages.entrySet()){
				Products s = m.getValue();
				productName1.add(s.getName());
				total.add(s.getSales());
			}

			out.println("<script type='text/javascript' src='JS/loader.js'></script>");
		out.println("<script type='text/javascript'>");
		  out.println("google.charts.load('current', {'packages':['bar']});");
		  out.println("google.charts.setOnLoadCallback(drawChart);");

		  out.println("function drawChart() {");
			out.println("var data = google.visualization.arrayToDataTable([");
			  out.println("['Product Name', 'Total Sales', { role: 'style' }]");
			   
			  for (int i=0;i<productName1.size(); i++){
					out.println(",['"+productName1.get(i)+"',"+total.get(i)+", 'color: #e5e4e2']");
					//System.out.println("["+productName.get(i)+","+ totalSales.get(i)+"]");
					
			  }
			  
				out.println("]);");
			
			
			out.println("var options = {");
			  out.println("chart: {");
				out.println("title: 'Smart Portables',");
				out.println("subtitle: 'Product total Sales',");
				//
			  out.println("},");
			  out.println("'width':1000,'height':1000,");
			  out.println("bars: 'horizontal' // Required for Material Bar Charts.");
			out.println("};");

			out.println("var chart = new google.charts.Bar(document.getElementById('barchart_material'));");
			out.println("var container = document.getElementById('barchart_material');");
			// throw error for testing
			out.println("google.visualization.events.addListener(chart, 'ready', function () {");
			  out.println("throw new Error('Test Google Error');");
			out.println("});");

			// listen for error
			out.println("google.visualization.events.addListener(chart, 'error', function (err) {");
			  // check error
			  out.println("console.log(err.id, err.message);");

			  // remove error
			  out.println("google.visualization.errors.removeError(err.id);");

			  // remove all errors
			  out.println("google.visualization.errors.removeAll(container);");
			out.println("});");

			out.println("chart.draw(data, google.charts.Bar.convertOptions(options));");
		  out.println("}");
		out.println("</script>");
		
	}

	if(productType.equals("DS")){
		
		out.println("<h3 align=\"center\">Daily Sales</h3>");

		out.println("<aside class=\"sidebar\">");

		out.println("<ul><li><h4>Report</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=IR\">Inventory Report</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=SR\">Sales Report</a></li></ul></li></ul>");
		out.println("<ul><li><h4>Options</h4><ul>");
		out.println("<li><a href=\"storemanagerservlet?productType=PS\">Products Sold</a></li>");
		out.println("<li><a href=\"storemanagerservlet?productType=BCS\">Bar Chart </a></li><li><a href=\"storemanagerservlet?productType=DS\">Daily Sales</a></li></ul></li></ul></aside>");
		out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
			String orderId="",productname="",orderDate="",shipping="",deliveryDate, name="";
			out.println("<div class='col-xs-12'>");
						out.println("<table class='table table-striped'>");
						out.println("<thead>");
						  out.println("<tr>");
							out.println("<th>Date</th>");
							out.println("<th>Total Sales</th>");
						  out.println("</tr>");
						out.println("</thead>");
						out.println("<tbody>");
							
							for(Map.Entry<String,Order> m :orders.entrySet()){
								Order o = m.getValue();
								
								
								double price = o.getTotalAmount();
								
								orderDate = o.getOrderDate();
								String finalString="";
								
								try {
								/*DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
								Date date = (Date)formatter.parse(orderDate);
								SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
								 finalString = newFormat.format(date);*/
								}
								catch (Exception e) {
								 //The handling for the code
								}
							
								out.println("<tr>");
								
								out.println("<td>"+orderDate+"</td>");
								
								out.println("<td>"+price+"</td>");
									
								out.println("</tr>");
							
							}
							out.println("</tbody>");
						out.println("</table>");
						out.println("</div>");

						out.println("</div></fieldset></div>");
	}

	

	if(productType.equals("DeleteP")){
		
		for(Map.Entry<String,Products> m1 :smartwatches.entrySet()){
			Products s1 = m1.getValue();
			out.println("<table class='main-parent' style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s1.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s1.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s1.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s1.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s1.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{	

			//out.println("<form action=\"deleteproduct\" method=\"get\">");
			//out.println("<input type='hidden' name='id' value='"+s1.getId()+"'>");
			
			//out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Delete\" type=\"submit\" /></p></form>");
				//out.println("<tr><td><form method = 'get' action =\"/A1/storemanagerservlet?productType=DeleteP\" >");
				//out.println("<input class = 'button' type = 'submit' name = '"+ s1.getName() +"' value = 'Delete'>");
				//out.println("<input type='hidden' name='productName' value='"+s1.getName()+"'>");
				//out.println("<input type='hidden' name='image' value='"+s1.getImage()+"'>");
				//out.println("<input type='hidden' name='price' value='"+s1.getPrice()+"'>");
				
				//out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				//out.println("</form></td></tr>");
				//out.println("</table></td></tr></table>");
				out.println("<tr><td><a href=deleteproduct?id="+s1.getId()+" class=\"button del-prod\">Delete</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=deleteproduct class=\"button\">Delete</a></td></tr></table></td></tr></table>");
			}
			
			}

			for(Map.Entry<String,Products> m2 :speakers.entrySet()){
			Products s2 = m2.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s2.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s2.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s2.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s2.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s2.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{
				
				//out.println("<form action=\"deleteproduct\" method=\"get\">");
			//out.println("<input type='hidden' name='id' value='"+s2.getId()+"'>");
			
			//out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Delete\" type=\"submit\" /></p></form>");
			out.println("<tr><td><a href=deleteproduct?id="+s2.getId()+" class=\"button del-prod\">Delete</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=# class=\"button\">Delete</a></td></tr></table></td></tr></table>");
			}
			
			}
			for(Map.Entry<String,Products> m3 :headphones.entrySet()){
			Products s3 = m3.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s3.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s3.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s3.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s3.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s3.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{
				
				out.println("<tr><td><a href=deleteproduct?id="+s3.getId()+" class=\"button del-prod\">Delete</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=# class=\"button\">Delete</a></td></tr></table></td></tr></table>");
			}
			
			}
			for(Map.Entry<String,Products> m4 :phones.entrySet()){
			Products s4 = m4.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s4.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s4.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s4.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s4.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s4.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{
				
				out.println("<tr><td><a href=deleteproduct?id="+s4.getId()+" class=\"button del-prod\">Delete</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=# class=\"button\">Delete</a></td></tr></table></td></tr></table>");
			}
			
			}
			for(Map.Entry<String,Products> m5 :laptops.entrySet()){
			Products s5 = m5.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s5.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s5.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s5.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s5.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s5.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{
				out.println("<tr><td><a href=deleteproduct?id="+s5.getId()+" class=\"button del-prod\">Delete</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=# class=\"button\">Delete</a></td></tr></table></td></tr></table>");
			}
			
			}
			for(Map.Entry<String,Products> m6 :externalstorages.entrySet()){
			Products s6 = m6.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s6.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s6.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s6.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s6.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s6.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{
				out.println("<tr><td><a href=deleteproduct?id="+s6.getId()+" class=\"button del-prod\">Delete</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=# class=\"button\">Delete</a></td></tr></table></td></tr></table>");
			}
			
			}

			for(Map.Entry<String,Accessories> m :accessories.entrySet()){
			Accessories s = m.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{
				out.println("<tr><td><a href=deleteproduct?id="+s.getId()+" class=\"button del-prod\">Delete</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=# class=\"button\">Delete</a></td></tr></table></td></tr></table>");
			}
			
			}
	}

	if(productType.equals("UpdateP")){
		
		for(Map.Entry<String,Products> m1 :smartwatches.entrySet()){
			Products s1 = m1.getValue();
			out.println("<table class='main-parent' style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s1.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s1.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s1.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s1.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s1.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{	
				out.println("<tr><td><a href=updateproduct?id="+s1.getId()+" class=\"button del-prod\">Update</a></td></tr></table></td></tr></table>");
			//out.println("<form action=\"deleteproduct\" method=\"get\">");
			//out.println("<input type='hidden' name='id' value='"+s1.getId()+"'>");
			
			//out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update\" type=\"submit\" /></p></form>");
				//out.println("<tr><td><form method = 'get' action =\"/A1/storemanagerservlet?productType=DeleteP\" >");
				//out.println("<input class = 'button' type = 'submit' name = '"+ s1.getName() +"' value = 'Delete'>");
				//out.println("<input type='hidden' name='productName' value='"+s1.getName()+"'>");
				//out.println("<input type='hidden' name='image' value='"+s1.getImage()+"'>");
				//out.println("<input type='hidden' name='price' value='"+s1.getPrice()+"'>");
				
				//out.println("<input type='hidden' name='quantity' value='"+1+"'>");
				//out.println("</form></td></tr>");
				//out.println("</table></td></tr></table>");
				//out.println("<tr><td><a href=deleteproduct class=\"button del-prod\">Delete</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=# class=\"button\">Update</a></td></tr></table></td></tr></table>");
			}
			
			}

			for(Map.Entry<String,Products> m2 :speakers.entrySet()){
			Products s2 = m2.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s2.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s2.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s2.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s2.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s2.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{
				
				out.println("<tr><td><a href=updateproduct?id="+s2.getId()+" class=\"button del-prod\">Update</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=# class=\"button\">Delete</a></td></tr></table></td></tr></table>");
			}
			
			}
			for(Map.Entry<String,Products> m3 :headphones.entrySet()){
			Products s3 = m3.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s3.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s3.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s3.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s3.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s3.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{
				
				out.println("<tr><td><a href=updateproduct?id="+s3.getId()+" class=\"button del-prod\">Update</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=# class=\"button\">Delete</a></td></tr></table></td></tr></table>");
			}
			
			}
			for(Map.Entry<String,Products> m4 :phones.entrySet()){
			Products s4 = m4.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s4.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s4.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s4.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s4.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s4.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{
				
				out.println("<tr><td><a href=updateproduct?id="+s4.getId()+" class=\"button del-prod\">Update</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=# class=\"button\">Delete</a></td></tr></table></td></tr></table>");
			}
			
			}
			for(Map.Entry<String,Products> m5 :laptops.entrySet()){
			Products s5 = m5.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s5.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s5.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s5.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s5.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s5.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{
				out.println("<tr><td><a href=updateproduct?id="+s5.getId()+" class=\"button del-prod\">Update</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=# class=\"button\">Delete</a></td></tr></table></td></tr></table>");
			}
			
			}
			for(Map.Entry<String,Products> m6 :externalstorages.entrySet()){
			Products s6 = m6.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s6.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s6.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s6.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s6.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s6.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{
				out.println("<tr><td><a href=updateproduct?id="+s6.getId()+" class=\"button del-prod\">Update</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=# class=\"button\">Delete</a></td></tr></table></td></tr></table>");
			}
			
			}

			for(Map.Entry<String,Accessories> m :accessories.entrySet()){
			Accessories s = m.getValue();
			
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr><td width=\"30%\">");
			out.println("<a href=#><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/");
			out.println(s.getImage());
			out.println("\" /></a>");
			out.println("</td>");
			out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
			out.println(s.getName());
			out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
			out.println(s.getColor());
			out.println("</td></tr><tr><td><b>Condition:</b>");
			out.println(s.getCondition());
			out.println("</td></tr></table></td>");
			out.println("<td width=\"30%\"><table><tr><td><b>Price:");
			out.println(s.getPrice());
			//
			
			
			
			if(fName != null && !fName.isEmpty())
			{
				out.println("<tr><td><a href=updateproduct?id="+s.getId()+" class=\"button del-prod\">Update</a></td></tr></table></td></tr></table>");
			}
			else{
				out.println("<tr><td><a href=updateproduct?"+s.getId()+" class=\"button\">Update</a></td></tr></table></td></tr></table>");
			}
			
			}
	}
		
		out.println("<footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
		out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
		out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
		out.println("<p>&copy; Smart Portables 2017. by Syed Hamdan Sher</p></div></footer>");
		out.println("</div></body></html>");
	
		out.close();		
	}
	
  	public void doPost(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
      	doGet(request,response);

      }
}


