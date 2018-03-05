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

public class AjaxUtility {


	
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables", "root", "mysqlsher1");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}

	public static ArrayList<Object> getProducts()
	{
		ArrayList<Object> productsx;
		HashMap<String, Products> smartwatches1;
		HashMap<String, Products> speakers1;
		HashMap<String, Products> headphones1;
		HashMap<String, Products> phones1;
		HashMap<String, Products> laptops1;
		HashMap<String, Products> externalstorages1;
		HashMap<String, Accessories> accessoriesx;
		HashMap<String, Accessories> allProducts;
		
		smartwatches1 = new HashMap<String,Products>();
		speakers1 = new HashMap<String,Products>();
		headphones1 = new HashMap<String,Products>();
		phones1 = new HashMap<String,Products>();
		laptops1 = new HashMap<String,Products>();
		externalstorages1 = new HashMap<String,Products>();
		accessoriesx =new HashMap<String,Accessories>();
		allProducts =new HashMap<String,Accessories>();
		productsx = new ArrayList<Object>();

		try
		{
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables?autoReconnect=true&useSSL=false", "root", "mysqlsher1");
			Products product1;
			Accessories accessories1;
			Accessories accessories2;
			//List ll;
			Statement stat = conn.createStatement ();
			stat.executeQuery("SELECT ID,Category,Retailer,Image,Name, Company, Conditions, Color,Description,Price,Accessories,rQuantity,sQuantity, Sales,onSale, onManufacturerRebates FROM Products");
			ResultSet resultSet = stat.getResultSet();
			
			while (resultSet.next ())
			{
				accessories2=new Accessories();

				accessories2.setId(resultSet.getString("ID"));
				accessories2.setRetailer(resultSet.getString ("Retailer"));
				accessories2.setImage(resultSet.getString ("Image"));
				accessories2.setName(resultSet.getString ("Name"));
				accessories2.setCompany(resultSet.getString ("Company"));
				accessories2.setCondition(resultSet.getString ("Conditions"));
				accessories2.setColor(resultSet.getString ("Color"));
				accessories2.setDescription(resultSet.getString("Description"));
				accessories2.setPrice(resultSet.getFloat ("Price"));
				//List items = Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*"));
				accessories2.setrQuantity(resultSet.getInt("rQuantity"));
			   	accessories2.setsQuantity(resultSet.getInt("sQuantity"));
			   	accessories2.setSales(resultSet.getDouble("Sales"));
			   	accessories2.setOnSale(resultSet.getBoolean("onSale"));
			   	accessories2.setOnRebates(resultSet.getBoolean("onManufacturerRebates"));

			   	allProducts.put(resultSet.getString("ID"), accessories2);

				if(resultSet.getString("Category").equals("smartwatches")){
					product1=new Products();
				product1.setId(resultSet.getString("ID"));
				product1.setRetailer(resultSet.getString ("Retailer"));
				product1.setImage(resultSet.getString ("Image"));
				product1.setName(resultSet.getString ("Name"));
				product1.setCompany(resultSet.getString ("Company"));
				product1.setCondition(resultSet.getString ("Conditions"));
				product1.setColor(resultSet.getString ("Color"));
				product1.setDescription(resultSet.getString("Description"));
				product1.setPrice(resultSet.getFloat ("Price"));
				//List items = Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*"));
				product1.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	product1.setrQuantity(resultSet.getInt("rQuantity"));
			   	product1.setsQuantity(resultSet.getInt("sQuantity"));
			   	product1.setSales(resultSet.getDouble("Sales"));
			   	product1.setOnSale(resultSet.getBoolean("onSale"));
			   	product1.setOnRebates(resultSet.getBoolean("onManufacturerRebates"));

			   	

				smartwatches1.put(resultSet.getString("ID"), product1);
				}else if(resultSet.getString("Category").equals("speakers")){
					product1=new Products();
				product1.setId(resultSet.getString("ID"));
				product1.setRetailer(resultSet.getString ("Retailer"));
				product1.setImage(resultSet.getString ("Image"));
				product1.setName(resultSet.getString ("Name"));
				product1.setCompany(resultSet.getString ("Company"));
				product1.setCondition(resultSet.getString ("Conditions"));
				product1.setColor(resultSet.getString ("Color"));
				product1.setDescription(resultSet.getString("Description"));
				product1.setPrice(resultSet.getFloat ("Price"));
				//List items = Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*"));
				product1.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	product1.setrQuantity(resultSet.getInt("rQuantity"));
			   	product1.setsQuantity(resultSet.getInt("sQuantity"));
			   	product1.setSales(resultSet.getDouble("Sales"));
			   	product1.setOnSale(resultSet.getBoolean("onSale"));
			   	product1.setOnRebates(resultSet.getBoolean("onManufacturerRebates"));

			   	//System.out.println(items);
			   	//break;
				//product = new Products(orderId,userId,orderDate, deliveryDate, shippingAddress, totalAmount, ccn);
				speakers1.put(resultSet.getString("ID"), product1);
				}else if(resultSet.getString("Category").equals("headphones")){
					product1=new Products();
				product1.setId(resultSet.getString("ID"));
				product1.setRetailer(resultSet.getString ("Retailer"));
				product1.setImage(resultSet.getString ("Image"));
				product1.setName(resultSet.getString ("Name"));
				product1.setCompany(resultSet.getString ("Company"));
				product1.setCondition(resultSet.getString ("Conditions"));
				product1.setColor(resultSet.getString ("Color"));
				product1.setDescription(resultSet.getString("Description"));
				product1.setPrice(resultSet.getFloat ("Price"));
				//List items = Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*"));
				product1.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	product1.setrQuantity(resultSet.getInt("rQuantity"));
			   	product1.setsQuantity(resultSet.getInt("sQuantity"));
			   	product1.setSales(resultSet.getDouble("Sales"));
			   	product1.setOnSale(resultSet.getBoolean("onSale"));
			   	product1.setOnRebates(resultSet.getBoolean("onManufacturerRebates"));
			   	//System.out.println(items);
			   	//break;
				//product = new Products(orderId,userId,orderDate, deliveryDate, shippingAddress, totalAmount, ccn);
				headphones1.put(resultSet.getString("ID"), product1);
				}else if(resultSet.getString("Category").equals("phones")){
					product1=new Products();
				product1.setId(resultSet.getString("ID"));
				product1.setRetailer(resultSet.getString ("Retailer"));
				product1.setImage(resultSet.getString ("Image"));
				product1.setName(resultSet.getString ("Name"));
				product1.setCompany(resultSet.getString ("Company"));
				product1.setCondition(resultSet.getString ("Conditions"));
				product1.setColor(resultSet.getString ("Color"));
				product1.setDescription(resultSet.getString("Description"));
				product1.setPrice(resultSet.getFloat ("Price"));
				//List items = Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*"));
				product1.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	product1.setrQuantity(resultSet.getInt("rQuantity"));
			   	product1.setsQuantity(resultSet.getInt("sQuantity"));
			   	product1.setSales(resultSet.getDouble("Sales"));
			   	product1.setOnSale(resultSet.getBoolean("onSale"));
			   	product1.setOnRebates(resultSet.getBoolean("onManufacturerRebates"));
			   	//System.out.println(items);
			   	//break;
				//product = new Products(orderId,userId,orderDate, deliveryDate, shippingAddress, totalAmount, ccn);
				phones1.put(resultSet.getString("ID"), product1);
				}else if(resultSet.getString("Category").equals("laptops")){
					product1=new Products();
				product1.setId(resultSet.getString("ID"));
				product1.setRetailer(resultSet.getString ("Retailer"));
				product1.setImage(resultSet.getString ("Image"));
				product1.setName(resultSet.getString ("Name"));
				product1.setCompany(resultSet.getString ("Company"));
				product1.setCondition(resultSet.getString ("Conditions"));
				product1.setColor(resultSet.getString ("Color"));
				product1.setDescription(resultSet.getString("Description"));
				product1.setPrice(resultSet.getFloat ("Price"));
				//List items = Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*"));
				product1.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	product1.setrQuantity(resultSet.getInt("rQuantity"));
			   	product1.setsQuantity(resultSet.getInt("sQuantity"));
			   	product1.setSales(resultSet.getDouble("Sales"));
			   	product1.setOnSale(resultSet.getBoolean("onSale"));
			   	product1.setOnRebates(resultSet.getBoolean("onManufacturerRebates"));
			   	//System.out.println(items);
			   	//break;
				//product = new Products(orderId,userId,orderDate, deliveryDate, shippingAddress, totalAmount, ccn);
				laptops1.put(resultSet.getString("ID"), product1);
				}else if(resultSet.getString("Category").equals("externalstorages")){
					product1=new Products();
				product1.setId(resultSet.getString("ID"));
				product1.setRetailer(resultSet.getString ("Retailer"));
				product1.setImage(resultSet.getString ("Image"));
				product1.setName(resultSet.getString ("Name"));
				product1.setCompany(resultSet.getString ("Company"));
				product1.setCondition(resultSet.getString ("Conditions"));
				product1.setColor(resultSet.getString ("Color"));
				product1.setDescription(resultSet.getString("Description"));
				product1.setPrice(resultSet.getFloat ("Price"));
				//List items = Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*"));
				product1.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	product1.setrQuantity(resultSet.getInt("rQuantity"));
			   	product1.setsQuantity(resultSet.getInt("sQuantity"));
			   	product1.setSales(resultSet.getDouble("Sales"));
			   	product1.setOnSale(resultSet.getBoolean("onSale"));
			   	product1.setOnRebates(resultSet.getBoolean("onManufacturerRebates"));
			   	//System.out.println(items);
			   	//break;
				//product = new Products(orderId,userId,orderDate, deliveryDate, shippingAddress, totalAmount, ccn);
				externalstorages1.put(resultSet.getString("ID"), product1);
				}else if(resultSet.getString("Category").equals("accessories")){
					
					accessories1=new Accessories();
				accessories1.setId(resultSet.getString("ID"));
				accessories1.setRetailer(resultSet.getString ("Retailer"));
				accessories1.setImage(resultSet.getString ("Image"));
				accessories1.setName(resultSet.getString ("Name"));
				accessories1.setCompany(resultSet.getString ("Company"));
				accessories1.setCondition(resultSet.getString ("Conditions"));
				accessories1.setColor(resultSet.getString ("Color"));
				accessories1.setDescription(resultSet.getString("Description"));
				accessories1.setPrice(resultSet.getFloat ("Price"));
				accessories1.setrQuantity(resultSet.getInt("rQuantity"));
			   	accessories1.setsQuantity(resultSet.getInt("sQuantity"));
			   	accessories1.setSales(resultSet.getDouble("Sales"));
			   	accessories1.setOnSale(resultSet.getBoolean("onSale"));
			   	accessories1.setOnRebates(resultSet.getBoolean("onManufacturerRebates"));
				//List items = Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*"));
				//product1.setAIDs(Arrays.asList(resultSet.getString ("Accessories").substring(1, resultSet.getString ("Accessories").length()-1).split("\\s*,\\s*")));//convert to list
			   	//System.out.println(items);
			   	//break;
				//product = new Products(orderId,userId,orderDate, deliveryDate, shippingAddress, totalAmount, ccn);
				accessoriesx.put(resultSet.getString("ID"), accessories1);
				}
			}
			resultSet.close();
			stat.close ();
			
			productsx.add(smartwatches1);
			productsx.add(speakers1);
			productsx.add(headphones1);
			productsx.add(phones1);
			productsx.add(laptops1);
			productsx.add(externalstorages1);
			productsx.add(accessoriesx);
			productsx.add(allProducts);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}return productsx;
	}

	
	public static void main(String args[]){
		
		
		
	}
	
}


