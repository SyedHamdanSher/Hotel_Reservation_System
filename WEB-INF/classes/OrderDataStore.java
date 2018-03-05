import java.util.*;
import java.io.*;

public class OrderDataStore{

	public HashMap<String, Order> Orders = new HashMap<String,Order>();
	public String orderDataFile = "/home/lastwalker/apache-tomcat-7.0.34/webapps/A1/WEB-INF/classes/orderData";

	void fillRandomOrders(){

			ArrayList<String> orderItems = new ArrayList<String>();
			orderItems.add("Item1");
			orderItems.add("Item2");
			
			Order ob1 = new Order("SP#471209", "1200938", "ssher1@hawk.iit.edu", "2017/09/28 03:55:28", "10/12/2017", "Chicago,IL", 100, orderItems);
			System.out.println("\t Order Id : "+ob1.getOrderId());
			Orders.put(ob1.getCustomerEmailId(), ob1);
			
	}

	public HashMap<String, Order> getOrderHashMap()
	{
		try{
			InputStream orderData = OrderDataStore.class.getResourceAsStream("orderData");
			ObjectInputStream ois=new ObjectInputStream(orderData);
			HashMap<String,Order> mapFile=(HashMap<String,Order>)ois.readObject();

			
			
			for(Map.Entry<String,Order> m :mapFile.entrySet()){
				System.out.println(m.getKey());
				Order ob = m.getValue();
				System.out.println("\t Order Id : "+ob.getOrderId());
				System.out.println("\t Email Id : "+ob.getCustomerEmailId());
				System.out.println("\t Total Amount : "+ob.getTotalAmount());
			}
			ois.close();
			return mapFile;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("getOrderHashMap() Exception");
		}
		return null;
	}

	public void  writeOrderHashMap(HashMap<String, Order> ordersUpdated){

		try{
			File orderHashmap=new File(orderDataFile);
			FileOutputStream fos=new FileOutputStream(orderHashmap);
			ObjectOutputStream oos=new ObjectOutputStream(fos);

			oos.writeObject(ordersUpdated);
			oos.flush();
			oos.close();
			fos.close();
			
		}catch(Exception e){
			System.out.println("writeOrderHashMap Exception");
		}
	}
	
//testing
	private void  writeOrderHashMap(){

		try{
			File OrderHashmap=new File(orderDataFile);
			FileOutputStream fos=new FileOutputStream(OrderHashmap);
				ObjectOutputStream oos=new ObjectOutputStream(fos);

				oos.writeObject(Orders);
				oos.flush();
				oos.close();
				fos.close();
			
		}catch(Exception e){
			System.out.println("writeOrderHashMap Exception");
		}
	}
	
	private void readOrderHashmap() {

		try{
			File OrderHashmap=new File(orderDataFile);
			FileInputStream fis=new FileInputStream(OrderHashmap);
			ObjectInputStream ois=new ObjectInputStream(fis);

			HashMap<String,Order> mapFile=(HashMap<String,Order>)ois.readObject();

			ois.close();
			fis.close();
			System.out.println("map in File: "+mapFile);
			for(Map.Entry<String,Order> m :mapFile.entrySet()){
			System.out.println(m.getKey());
			Order ob = m.getValue();
			System.out.println("\t Order Id : "+ob.getOrderId());
			System.out.println("\t Email Id : "+ob.getCustomerEmailId());
			System.out.println("\t Total Amount : "+ob.getTotalAmount());
			}
		}catch(Exception e){
			
			System.out.println("readOrderHashmap() exception");
		}

	}


	private void testDrive(){
		//fillRandomOrders();
		//writeOrderHashMap();
		readOrderHashmap();
		
	}
	
	
	public static void main(String args[]){
        OrderDataStore ods = new OrderDataStore();
		ods.testDrive();
	}
	
	
}