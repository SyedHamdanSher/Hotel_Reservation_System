import java.util.*;
import java.io.*;

public class fillHashmap{

	public HashMap<String, Customer> customers = new HashMap<String,Customer>();
	public String customerFile = "/home/lastwalker/apache-tomcat-7.0.34/webapps/A1/WEB-INF/classes/customerData";

	void fillRandomCustomers()
	{

		Customer c1 = new Customer("Syed", "Sher", "ssher1@hawk.iit.edu", "password123", "6304077735");
		System.out.println("\t Customer Id : "+c1.getCustomerId());
		customers.put(c1.getEmailId(), c1);
	}

	//Fetching CustomerHashMap from file
	public HashMap<String, Customer> getCustomerHashMap()
	{
		try{
			InputStream custData = fillHashmap.class.getResourceAsStream("customerData");
			ObjectInputStream ois=new ObjectInputStream(custData);
			HashMap<String,Customer> mapFile=(HashMap<String,Customer>)ois.readObject();

			ois.close();
			
			System.out.println("Inside read method");
			for(Map.Entry<String,Customer> m :mapFile.entrySet()){
					System.out.println(m.getKey());
			Customer c = m.getValue();
			System.out.println("\t Customer Id : "+c.getCustomerId());
			System.out.println("\t First Name : "+c.getFirstName());
			System.out.println("\t Last Name : "+c.getLastName());
			}
			
			return mapFile;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception occured in reading or getting hashmap from file");
		}
		return null;
	}

	//writing customer hashmap to file
	public void  writeCustomerHashMap(HashMap<String, Customer> custUpdate){

		try{
			File custHashmap=new File(customerFile);
			FileOutputStream fos=new FileOutputStream(custHashmap);
			ObjectOutputStream oos=new ObjectOutputStream(fos);

				oos.writeObject(custUpdate);
				oos.flush();
				oos.close();
				fos.close();
			
		}catch(Exception e){
			System.out.println("Could not write customers to CustomerHashMap ...!");
		}
	}
	
// Private Methods to populate for first time

	private void  writeCustomerHashMap(){

		try{
			File custHashmap=new File("customerData");
			FileOutputStream fos=new FileOutputStream(custHashmap);
				ObjectOutputStream oos=new ObjectOutputStream(fos);

				oos.writeObject(customers);
				oos.flush();
				oos.close();
				fos.close();
			
		}catch(Exception e){
			System.out.println("Hey Could NOT Write customers to CustomerHashMap ...");
		}
	}
	
	private void readCustomerHashmap() {

		try{
			File custHashmap=new File("customerData");
			FileInputStream fis=new FileInputStream(custHashmap);
			ObjectInputStream ois=new ObjectInputStream(fis);

			HashMap<String,Customer> mapFile=(HashMap<String,Customer>)ois.readObject();

			ois.close();
			fis.close();
			System.out.println("Inside readCustomerHashmap()");
			System.out.println("map in File: "+mapFile);
			for(Map.Entry<String,Customer> m :mapFile.entrySet()){
					System.out.println(m.getKey());
			Customer c = m.getValue();
			System.out.println("\t Customer Id : "+c.getCustomerId());
			System.out.println("\t First Name : "+c.getFirstName());
			System.out.println("\t Last Name : "+c.getLastName());
			}
		}catch(Exception e){
			
			System.out.println("Inside exception");
		}

	}


	private void testDrive(){
		fillRandomCustomers();
		writeCustomerHashMap();
		readCustomerHashmap();
		
	}
	
	
	public static void main(String args[]){
        fillHashmap fH = new fillHashmap();
		fH.testDrive();
	}
	
	
}