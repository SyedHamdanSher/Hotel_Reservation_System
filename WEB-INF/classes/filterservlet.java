import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.AggregationOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class filterservlet extends HttpServlet {

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

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");
	//PrintWriter out= response.getWriter();
	//out.println("Working ");
	String[] galleryhotelname = request.getParameterValues("HotelName");
			String superior = request.getParameter("superior");
			String deluxe = request.getParameter("deluxe");
			String premier= request.getParameter("premier");
			String plus= request.getParameter("plus");
			String price1= request.getParameter("50");
			String price2 = request.getParameter("100");
		
			String price3 = request.getParameter("200");
			String chicago = request.getParameter("chicago");
			String newyork = request.getParameter("newyork");
			String washington = request.getParameter("washington");
			
			
		
						
			//Get the filters selected
			//Filter - Simple Search
			String[] filters = request.getParameterValues("queryCheckBox");
			//Filters - Group By
			
			
			
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
		out.println("<strong>Search Products: </strong>");
		out.println("<input type='text' name='searchId' size='40' id='searchId' onkeyup='doCompletion()' placeholder='Search Here...'>");
		out.println("<div id='auto-row'>");
		out.println("<table border='0' id='complete-table' class='popupBox'></table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		
		out.println("</header>");
		
		
			out.println("<h5>Welcome ");
			out.println(fName);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"loginedhomeservlet\">Home</a></li>");
		
		
		
		out.println("<li class=\"\"><a href=\"contentservlet?productType=Rooms\">Rooms</a></li>");
		out.println("<li><a href=\"contentservlet?productType=Suites\">Suites</a></li>");
		out.println("<li><a href=\"contentservlet?productType=LR\">Leisure Facilities</a></li>");
		out.println("<li><a href=\"contentservlet?productType=Dinning\">Dinning</a></li>");
		
		
			out.println("<li><a href=\"cartservlet\">Cart("+C+")</a></li>");
			out.println("<li><a href=\"vieworderservlet\">Your Orders</a></li></ul></nav>");
	
			out.println("</ul></nav>");
		
		out.println("<div id=\"body\">");
		

		Boolean HotelName = request.getParameterMap().containsKey("HotelName");
		if(HotelName==true){
		
		out.println("<br><br><h1>Hotel Gallery</h1>");
		int length = galleryhotelname.length;

		//out.println(galleryhotelname[0]);
		
try{
		for(int i=0;i<length;i++){
		if(galleryhotelname[i].equals("HotelOne") ){
			out.println("<br><br><h2>Hotel One</h2>");
			HashMap<String,String>images = MySqlDataStoreUtilities.getImages("Hotel 1");
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
          
			 for(Map.Entry<String,String> s :images.entrySet()){
		    
		    out.println("<a href=#><img style=\"width:300px;height:300px;\" style=\"display:block;\"  src=\"roomimages/");
            out.println(s.getValue());
            out.println("\" /></a>");
            out.println("</td>");
            out.println("&nbsp;");
            out.println("&nbsp;");
           
           }
			 
            out.println("</table>");
            
		}	
		}
		
		}catch(Exception e){

		}


try{
		for(int i=0;i<length;i++){
		if(galleryhotelname[i].equals("HotelTwo") ){
		out.println("<br><br><h2>Hotel Two</h2>");
			HashMap<String,String>images = MySqlDataStoreUtilities.getImages("Hotel 2");
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
          
			 for(Map.Entry<String,String> s :images.entrySet()){
		    
		    out.println("<a href=#><img style=\"width:300px;height:300px;\" style=\"display:block;\"  src=\"roomimages/");
            out.println(s.getValue());
            out.println("\" /></a>");
            out.println("</td>");
            out.println("&nbsp;");
            out.println("&nbsp;");
           }
			 
            out.println("</table>");
		}	
		}
		
		}catch(Exception e){

		}





		try{
		for(int i=0;i<length;i++){
		if(galleryhotelname[i].equals("HotelThree") ){
			out.println("<br><br><h2>Hotel Three</h2>");
			HashMap<String,String>images = MySqlDataStoreUtilities.getImages("Hotel 3");
			out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
          
			 for(Map.Entry<String,String> s :images.entrySet()){
		    
		    out.println("<a href=#><img style=\"width:300px;height:300px;\" style=\"display:block;\"  src=\"roomimages/");
            out.println(s.getValue());
            out.println("\" /></a>");
            out.println("</td>");
            out.println("&nbsp;");
            out.println("&nbsp;");
           }
			 
            out.println("</table>");

		}	
		}
		
		}catch(Exception e){

		}		
		

		}


Boolean allFilters = request.getParameterMap().containsKey("queryCheckBox");
	int i=0;	
		if(allFilters == true){
try{
		System.out.println("Inside Try Statement");
		String roomType,price,Location;
		roomType = "%";
		price = "%";
		Location = "%";
		int lg = filters.length;
			System.out.println(lg);		
		for(i=0;i<lg;i++){
			System.out.println("Inside For Statement");
								if(filters[i].equals("superior")){

				if(roomType.equals("%")){
					roomType = "Superior";
					
				}
				else{
					roomType = "%";
				}
				}
			
			if(filters[i].equals("deluxe")){
				if(roomType.equals("%")){
				roomType = "Deluxe";	
				}
				else{
					roomType = "%";
				}

			}
			if(filters[i].equals("premier")){
				if(roomType.equals("%")){
				roomType = "Premier";
				
				}
				else{
					roomType = "%";
				}
				}

			if(filters[i].equals("plus")){
				if(roomType.equals("%")){
				
				roomType = "PremierPlus";
				
				}
				else{
					roomType = "%";
				}


				}
			if(filters[i].equals("50")){
					if(price.equals("%")){
				
					price = "50";
				
				}
				else{
					price = "1000";
				}

				
			}
			if(filters[i].equals("100")){
				if(price.equals("%")){
				
					price = "100";
				
				}
				else{
					price = "1000";
				}

				}
			if(filters[i].equals("200")){
				if(price.equals("%")){
				
					price = "200";
				
				}
				else{
					price = "1000";
				}

				}
			if(filters[i].equals("chicago")){
				if(Location.equals("%")){
				
				Location = "Chicago";
				
				}
				else{
					Location = "%";
				}


				
			}
			if(filters[i].equals("newyork")){
				
				if(Location.equals("%")){
				
				Location = "NewYork";
				
				}
				else{
					Location = "%";
				}
			}
			if(filters[i].equals("washington")){
				
						if(Location.equals("%")){
				
							Location = "WashingtonDC";

				}
				else{
					Location = "%";
				}
			}



		

			}
		
		Boolean rt,p,l;
		rt = true;
		p = true;
		l = true;
		if(roomType.equals("%")){
			rt = false;
			System.out.println(rt);
		}
		if(price.equals("1000")||price.equals("%")){
			p = false;
			System.out.println(p);
		}
		if(Location.equals("%")){
			l = false;
			System.out.println(l);
		}
		HashMap<String,Products> FilteredProducts = MySqlDataStoreUtilities.getFilteredProducts(rt,p,l,roomType,price,Location);





            out.println("<article><h3>Filter Results</h3></article>");
            out.println("<article class=\"expanded\">");
            
            for(Map.Entry<String,Products> m :FilteredProducts.entrySet()){
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
			
			

			















		}catch(Exception e){
			System.out.println(e);

	}		
		
		


	

	}	
		








		out.println("</div id='body'>");
		

		
		
}


}
