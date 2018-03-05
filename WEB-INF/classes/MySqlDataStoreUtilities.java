import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MySqlDataStoreUtilities {
	
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation", "root", "root");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void registerCustomer(String firstName, String lastName, String emailId, String phoneNumber, String password)
	{
		try
		{
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation?autoReconnect=true&useSSL=false", "root", "root");
			String insertInto = "INSERT INTO Registration(firstName,lastName,emailId,phoneNumber,password) " + "VALUES (?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertInto);
			
			pst.setString(1,firstName);
			pst.setString(2,lastName);
			pst.setString(3,emailId);
			pst.setString(4,phoneNumber);
			pst.setString(5,password);
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static HashMap<String,Customer> getCustomers()
	{
		Customer customer = null;
		HashMap<String,Customer> customers = new HashMap<String, Customer>();
		
		try
		{
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation?autoReconnect=true&useSSL=false", "root", "root");
			Statement stat = conn.createStatement ();
			stat.executeQuery ("SELECT firstName, lastName, emailId, phoneNumber, password FROM Registration");
			ResultSet resultSet = stat.getResultSet();
			
			while (resultSet.next ())
			{
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString ("lastName");
				String emailId = resultSet.getString ("emailId");
				String phoneNumber = resultSet.getString ("phoneNumber");
				String password = resultSet.getString ("password");
			   
				customer = new Customer(firstName, lastName, emailId, password, phoneNumber);
				customers.put(emailId, customer);
			}
			resultSet.close ();
			stat.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return customers;
	}
	public static HashMap<String,Products> getFilteredProducts(Boolean rt,Boolean p ,Boolean l, String roomType,String price,String Location)
	{
		HashMap<String,Products> filteredProducts = new HashMap<String, Products>();

		String rType = "%";
		String Loc = "%";
		String min = "0";
		String max = "10000";
		if(rt.equals(true)){
			rType = roomType;
		}
		if(p.equals(true)){
			if(price.equals("50")){
				min = "50";
				max = "100";

			}else if(price.equals("100")){
				min = "100";
				max = "200";


			}else{
				min = "20";
				max = "1000";

			}


			if(l.equals(true)){
				Loc = Location;
			}




try
		{
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation?autoReconnect=true&useSSL=false", "root", "root");
			Statement stat = conn.createStatement ();
			stat.executeQuery ("select * from hotelreservation.products where Type like "+"'"+rType+"'"+" and Price between "+"'"+min+"'"+" and "+"'"+max+"'"+" and Location like "+"'"+Loc +"'"+" ");
			System.out.println("select * from hotelreservation.products where Type like "+"'"+rType+"'"+" and Price between "+"'"+min+"'"+" and "+"'"+max+"'"+" and Location like "+"'"+Loc +"'"+" ");
			ResultSet resultSet = stat.getResultSet();
			
			while (resultSet.next ())
			{
				
				Products prox=new Products();
				prox.setProductId(resultSet.getString("ProductId"));
				prox.setProductName(resultSet.getString ("ProductName"));
				prox.setCategory(resultSet.getString ("Category"));
				prox.setType(resultSet.getString ("Type"));
				prox.setHotelName(resultSet.getString ("HotelName"));
				
				prox.setPrice(resultSet.getString ("Price"));
				prox.setLocation(resultSet.getString ("Location"));
				prox.setImage(resultSet.getString ("Image"));
				prox.setSQuantity(resultSet.getString ("SQuantity"));
				prox.setRQuantity(resultSet.getString ("RQuantity"));
				prox.setSales(resultSet.getString ("Sales"));
				prox.setDescription(resultSet.getString ("Description"));
				
				prox.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	String filname = resultSet.getString ("ProductName");
				filteredProducts.put(filname,prox);
				System.out.println("Finished Filtering Products");
				System.out.println(filname);
					
			}
			resultSet.close ();
			stat.close ();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		





		}
return filteredProducts;


	}
	public static HashMap<String,String> getImages(String HotelName)
	{

		HashMap<String,String> Images = new HashMap<String, String>();
			
		try
		{
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation?autoReconnect=true&useSSL=false", "root", "root");
			Statement stat = conn.createStatement ();
			stat.executeQuery ("SELECT ProductName,Image FROM products where HotelName =" +"'"+HotelName+"'");
			ResultSet resultSet = stat.getResultSet();
			
			while (resultSet.next ())
			{
				String imageloc = resultSet.getString("Image");
				String pname = resultSet.getString("ProductName");
				Images.put(pname,imageloc);
				//System.out.println("Printing Image Location for gallery");
				//System.out.println(pname+" "+imageloc);
			}
			resultSet.close ();
			stat.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Images;
	}
	
	
	//Calculate total sales per Day
	/*public static HashMap<String,Order> getDailySales()
	{
		Order order = null;
		HashMap<String,Order> orders = new HashMap<String, Order>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation?autoReconnect=true&useSSL=false", "root", "root");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT date(orderDate), SUM(totalAmount) as dailySales FROM CustomerOrders group by date(orderDate);");
			ResultSet resultSet = s.getResultSet();
			
			while (resultSet.next ())
			{
				//String orderId = resultSet.getString("orderId");
				//String userId = resultSet.getString ("userId");
				String orderDate = resultSet.getString ("date(orderDate)");
				//String deliveryDate = resultSet.getString ("deliveryDate");
				//String shippingAddress = resultSet.getString ("shippingAddress");
				double totalAmount = resultSet.getDouble ("dailySales");
				//String ccn = resultSet.getString("creditCardNumber");
			   
				order = new Order("orderId","userId",orderDate, "deliveryDate", "shippingAddress", totalAmount, "ccn");
				orders.put(orderDate, order);
				
			}
			//System.out.println("OrderItems: "+orderItems);
			resultSet.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//System.out.println("OrderItems: "+orders);
		return orders;
	}*/
	
	public static HashMap<String,String> getadminCredentials()
	{
		HashMap<String,String> admins = new HashMap<String, String>();
		
		try
		{
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation?autoReconnect=true&useSSL=false", "root", "root");
			Statement stat = conn.createStatement();
			stat.executeQuery ("SELECT emailId, password FROM administrator");
			ResultSet resultSet = stat.getResultSet();
			while (resultSet.next ())
			{
				String emailAddress = resultSet.getString("emailId");
				String password = resultSet.getString("password");
				admins.put(emailAddress, password);
			}
			resultSet.close ();
			stat.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return admins;
	}
	
	
	
	public static void insertCustomerOrder(String orderId, String userId, String productId, String checkinDate,String checkoutDate, String productName, String price)
	{
		try
		{
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation?autoReconnect=true&useSSL=false", "root", "root");
			String insertInto = "INSERT INTO CustomerOrders(OrderId,UserId,ProductId,CheckInDate, CheckOutDate, ProductName, Price) " + "VALUES (?,?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertInto);
			
			pst.setString(1,orderId);
			pst.setString(2,userId);
			pst.setString(3,productId);
			pst.setString(4,checkinDate);
			pst.setString(5,checkoutDate);
			pst.setString(6,productName);
			pst.setString(7,price);
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static HashMap<String,Order> getCustomerOrder()
	{
		Order order = null;
		HashMap<String,Order> orders = new HashMap<String, Order>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation?autoReconnect=true&useSSL=false", "root", "root");
			
			Statement stat = conn.createStatement ();
			stat.executeQuery("SELECT OrderId,UserId,ProductId,CheckInDate, CheckOutDate, ProductName, Price FROM CustomerOrders");
			ResultSet resultSet = stat.getResultSet();
			
			while (resultSet.next ())
			{
				String orderId = resultSet.getString("OrderId");
				String userId = resultSet.getString ("UserId");
				String productId = resultSet.getString ("ProductId");
				String checkinDate = resultSet.getString ("CheckInDate");
				String checkoutDate = resultSet.getString ("CheckOutDate");
				String productName = resultSet.getString ("ProductName");
				String price = resultSet.getString ("Price");
				
			   
				order = new Order(orderId,userId,productId,checkinDate, checkoutDate, productName, price);
				orders.put(orderId, order);
			}
			resultSet.close();
			stat.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public static void deleteCustomerOrder(String orderId)
	{
		try
		{
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation?autoReconnect=true&useSSL=false", "root", "root");
			String insertInto = "DELETE FROM CustomerOrders where OrderId=?";
			PreparedStatement pst = conn.prepareStatement(insertInto);
			
			pst.setString(1,orderId);
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	public static boolean checkProductAvailability(String productId,String fromDate, String toDate)
	{
		Order order = null;
		HashMap<String,Order> orders = new HashMap<String, Order>();
		boolean flag=false;
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation?autoReconnect=true&useSSL=false", "root", "root");
			PreparedStatement statement =conn.prepareStatement("SELECT OrderId,ProductId,CheckInDate, CheckOutDate, ProductName, Price FROM CustomerOrders Where ProductId= ?");
			statement.setString(1, productId);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next ())
			{
				String orderId = resultSet.getString("OrderId");
				String producId = resultSet.getString ("ProductId");
				String checkinDate = resultSet.getString ("CheckInDate");
				String checkoutDate = resultSet.getString ("CheckOutDate");
				String productName = resultSet.getString ("ProductName");
				String price = resultSet.getString ("Price");
				
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
				Date startDate,endDate,startDateC,endDateC;
				//try {
    				startDate = df.parse(fromDate);
    				endDate = df.parse(toDate);
    				startDateC = df.parse(checkinDate);
    				endDateC = df.parse(checkoutDate);
    				/*String newStartDate = df.format(startDate);
    				String newEndDate = df.format(endDate);
    				String newStartDateC = df.format(startDateC);
    				String newEndDateC = df.format(endDateC);*/
    				
    				flag=isOverLapped(startDate, endDate, startDateC, endDateC);
    				if(flag==true)
    					break;
    				
					//} catch (ParseException e) {
    				//e.printStackTrace();
					//}
			}
			resultSet.close();
			statement.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;	
	}

	public static boolean isOverLapped(Date newStartDate, Date newEndDate, Date existingStartDate, Date existingEndDate) throws NullPointerException{
            if (!((newEndDate.getTime() <= existingStartDate.getTime()) 
               || (newStartDate.getTime() >= existingEndDate.getTime()))) {
                return true;
            } else {
                return false;
            }
        }
	
	public static void updateCustomerOrder(String orderId, String productId, String checkinDate,String checkoutDate, String productName, String price)
	{
		try
		{
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation?autoReconnect=true&useSSL=false", "root", "root");
			String insertInto = "Update CustomerOrders SET ProductId=?, CheckInDate=?,CheckOutDate=?,ProductName=?, Price=? where OrderId=?";
			PreparedStatement pst = conn.prepareStatement(insertInto);
			pst.setString(1,productId);
			pst.setString(2,checkinDate);
			pst.setString(3,checkoutDate);
			pst.setString(4,productName);
			pst.setString(5,price);
			pst.setString(6,orderId);
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static ArrayList<Object> getProducts()
	{
		ArrayList<Object> productsx;
		HashMap<String, Products> rooms1;
		HashMap<String, Products> lr1;
		HashMap<String, Products> su1;
		HashMap<String, Products> di1;
		HashMap<String, Products> pro;		
		rooms1 = new HashMap<String,Products>();
		lr1 = new HashMap<String,Products>();
		su1 = new HashMap<String,Products>();
		di1 = new HashMap<String,Products>();
		pro = new HashMap<String,Products>();
		
		productsx = new ArrayList<Object>();
		try
		{
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation?autoReconnect=true&useSSL=false", "root", "root");
			Products product1;
			Products prox;
			Accessories accessories1;
			
			Statement stat = conn.createStatement ();
			stat.executeQuery("SELECT ProductId ,ProductName ,Category ,Type ,HotelName , Price , Location ,Image, SQuantity,RQuantity, Sales, Description, Accessories FROM Products");
			ResultSet resultSet = stat.getResultSet();
			
			while (resultSet.next ())
			{
				prox=new Products();
				prox.setProductId(resultSet.getString("ProductId"));
				prox.setProductName(resultSet.getString ("ProductName"));
				prox.setCategory(resultSet.getString ("Category"));
				prox.setType(resultSet.getString ("Type"));
				prox.setHotelName(resultSet.getString ("HotelName"));
				
				prox.setPrice(resultSet.getString ("Price"));
				prox.setLocation(resultSet.getString ("Location"));
				prox.setImage(resultSet.getString ("Image"));
				prox.setSQuantity(resultSet.getString ("SQuantity"));
				prox.setRQuantity(resultSet.getString ("RQuantity"));
				prox.setSales(resultSet.getString ("Sales"));
				prox.setDescription(resultSet.getString ("Description"));
				
				prox.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	pro.put(resultSet.getString("ProductId"),prox);

				if(resultSet.getString("Category").equals("Room")){
					product1=new Products();

				product1.setProductId(resultSet.getString("ProductId"));
				product1.setProductName(resultSet.getString ("ProductName"));
				product1.setCategory(resultSet.getString ("Category"));
				product1.setType(resultSet.getString ("Type"));
				product1.setHotelName(resultSet.getString ("HotelName"));
				
				product1.setPrice(resultSet.getString ("Price"));
				product1.setLocation(resultSet.getString ("Location"));
				product1.setImage(resultSet.getString ("Image"));
				product1.setSQuantity(resultSet.getString ("SQuantity"));
				product1.setRQuantity(resultSet.getString ("RQuantity"));
				product1.setSales(resultSet.getString ("Sales"));
				product1.setDescription(resultSet.getString ("Description"));
				
				product1.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	
			   	rooms1.put(resultSet.getString("ProductId"), product1);
				}
				if(resultSet.getString("Category").equals("LF")){
					product1=new Products();
				product1.setProductId(resultSet.getString("ProductId"));
				product1.setProductName(resultSet.getString ("ProductName"));
				product1.setCategory(resultSet.getString ("Category"));
				product1.setType(resultSet.getString ("Type"));
				product1.setHotelName(resultSet.getString ("HotelName"));
				
				product1.setPrice(resultSet.getString ("Price"));
				product1.setLocation(resultSet.getString ("Location"));
				product1.setImage(resultSet.getString ("Image"));
				product1.setSQuantity(resultSet.getString ("SQuantity"));
				product1.setRQuantity(resultSet.getString ("RQuantity"));
				product1.setSales(resultSet.getString ("Sales"));
				product1.setDescription(resultSet.getString ("Description"));
				
				//product1.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	
			   	lr1.put(resultSet.getString("ProductId"), product1);
				}

				if(resultSet.getString("Category").equals("S")){
					product1=new Products();
				product1.setProductId(resultSet.getString("ProductId"));
				product1.setProductName(resultSet.getString ("ProductName"));
				product1.setCategory(resultSet.getString ("Category"));
				product1.setType(resultSet.getString ("Type"));
				product1.setHotelName(resultSet.getString ("HotelName"));
				
				product1.setPrice(resultSet.getString ("Price"));
				product1.setLocation(resultSet.getString ("Location"));
				product1.setImage(resultSet.getString ("Image"));
				product1.setSQuantity(resultSet.getString ("SQuantity"));
				product1.setRQuantity(resultSet.getString ("RQuantity"));
				product1.setSales(resultSet.getString ("Sales"));
				product1.setDescription(resultSet.getString ("Description"));
				
				//product1.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	
			   	su1.put(resultSet.getString("ProductId"), product1);
				}
				if(resultSet.getString("Category").equals("Dining")){
					product1=new Products();
				product1.setProductId(resultSet.getString("ProductId"));
				product1.setProductName(resultSet.getString ("ProductName"));
				product1.setCategory(resultSet.getString ("Category"));
				product1.setType(resultSet.getString ("Type"));
				product1.setHotelName(resultSet.getString ("HotelName"));
				
				product1.setPrice(resultSet.getString ("Price"));
				product1.setLocation(resultSet.getString ("Location"));
				product1.setImage(resultSet.getString ("Image"));
				product1.setSQuantity(resultSet.getString ("SQuantity"));
				product1.setRQuantity(resultSet.getString ("RQuantity"));
				product1.setSales(resultSet.getString ("Sales"));
				product1.setDescription(resultSet.getString ("Description"));
				
				//product1.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	
			   	di1.put(resultSet.getString("ProductId"), product1);
				}
			}
			resultSet.close();
			stat.close ();
			
			productsx.add(rooms1);
			productsx.add(lr1);
			productsx.add(su1);
			productsx.add(di1);
			productsx.add(pro);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}return productsx;
	}

	/*public static void updateProductCount(String prodId, String type)

	{
		try
		{
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables?autoReconnect=true&useSSL=false", "root", "root");
			
				Statement s = conn.createStatement ();
				if(type.equals("add")){
				s.executeQuery ("Select rQuantity, sQuantity, Sales, Price from Products where ID='"+prodId+"'");
				ResultSet rs = s.getResultSet();
				int r=0;
				int s1=0;
				double d=0.0;
				double p=0.0;
				while (rs.next ())
				{
					
					r = rs.getInt("rQuantity");
					s1 = rs.getInt("sQuantity");
					d = rs.getDouble("Sales");
					p = rs.getDouble("Price");
					
				}
				if(r>0){
				r-=1;}

				s1+=1;
				d=d+p;
				String query = "Update Products set rQuantity=?, sQuantity=?, Sales=? where ID='"+prodId+"'";
				
				PreparedStatement ps = conn.prepareStatement(query);
				//ps.setString(1,orderID);
				ps.setInt(1,r);
				ps.setInt(2,s1);
				ps.setDouble(3,d);
				
				ps.execute();
				
				rs.close ();
			}else{
				s.executeQuery ("Select rQuantity, sQuantity, Sales, Price from Products where ID='"+prodId+"'");
				ResultSet rs = s.getResultSet();
				int r=0;
				int s1=0;
				double d=0.0;
				double p=0.0;
				while (rs.next ())
				{
					
					r = rs.getInt("rQuantity");
					s1 = rs.getInt("sQuantity");
					d = rs.getDouble("Sales");
					p = rs.getDouble("Price");
					
				}
				if(r>0){
				r+=1;}

				s1-=1;
				d=d-p;
				String query = "Update Products set rQuantity=?, sQuantity=?, Sales=? where ID='"+prodId+"'";
				
				PreparedStatement ps = conn.prepareStatement(query);
				//ps.setString(1,orderID);
				ps.setInt(1,r);
				ps.setInt(2,s1);
				ps.setDouble(3,d);
				
				ps.execute();
				
				rs.close ();
			}
				s.close ();
			}
			//}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/

//ID,Category,Retailer,Image,Name, Company, Conditions, Color,Description,Price,Accessories
	/*public static void insertProductByProduct(String id, String category,String retailer, String image, String name, String company, String conditions,String color,String description, float price, String accessories)
	{
		try
		{
			
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables?autoReconnect=true&useSSL=false", "root", "root");
			String insertInto = "INSERT INTO Products(ID,Category,Retailer,Image,Name, Company, Conditions, Color,Description,Price,Accessories,rQuantity,sQuantity,Sales,onSale,onManufacturerRebates) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(insertInto);
			
			ps.setString(1,id);
			ps.setString(2,category);
			ps.setString(3,retailer);
			ps.setString(4,image);
			ps.setString(5,name);
			ps.setString(6,company);
			ps.setString(7,conditions);
			ps.setString(8,color);
			ps.setString(9,description);
			ps.setFloat(10,price);
			ps.setString(11,accessories);
			ps.setInt(12,20);
			ps.setInt(13,0);
			ps.setDouble(14,0);
			ps.setBoolean(15,false);
			ps.setBoolean(16,false);
			
			ps.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
	

	/*public static void deleteProductByProduct(String id)
	{
		try
		{
			
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables?autoReconnect=true&useSSL=false", "root", "root");
			String insertInto = "Delete from Products where ID=?; ";
			PreparedStatement ps = conn.prepareStatement(insertInto);
			
			ps.setString(1,id);
			
			ps.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/


	/*public static void updateProductByProduct(String id, String category,String retailer, String image, String name, String company, String conditions,String color,String description, float price, String accessories)
	{
		try
		{
			
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables?autoReconnect=true&useSSL=false", "root", "root");
			String insertInto = "UPDATE Products SET Category = ?,Retailer = ?,Image = ?,Name = ?, Company = ?, Conditions = ?, Color = ?,Description = ?,Price = ?,Accessories = ? WHERE ID = ? " + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(insertInto);
			
			
			ps.setString(1,category);
			ps.setString(2,retailer);
			ps.setString(3,image);
			ps.setString(4,name);
			ps.setString(5,company);
			ps.setString(6,conditions);
			ps.setString(7,color);
			ps.setString(8,description);
			ps.setFloat(9,price);
			ps.setString(10,accessories);
			ps.setString(11,id);
			
			ps.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/

	
	public static void main(String args[]){
		//System.out.println(checkProductAvailability("001R","10/10/17", "10/13/17"));
		
	}
	
}


