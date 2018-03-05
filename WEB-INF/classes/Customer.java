
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Customer implements java.io.Serializable {
	
	static int cid=1;
	int customerId=0;
	String firstName;
	String lastName;
    String emailId;
	String password;
	String phoneNumber;
    String address;
    String CCNum;
    String CCDate;
	String CCName;
	

    public Customer(String firstName, String lastName,  String emailId, String password, String phoneNumber)
    {
       
	   this.customerId=cid;
	   cid++;
	   this.firstName=firstName;
	   this.lastName=lastName;
	   this.emailId=emailId;
	   this.password=password;
	   this.phoneNumber=phoneNumber;   
    }

	public void setCustomerId(int customerId) 
	{
		this.customerId = customerId;
	}

	public int getCustomerId()
	{
		return customerId;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getFirstName() 
	{
		return firstName;
	}
		
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getLastName()
	{
		return lastName;
	}

	void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getEmailId()
	{
		return emailId;
	}
		
	void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}

	void setAddress(String address)
	{
		this.address = address;
	}

	public String getAddress()
	{
		return address;
	}

	void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	void setCCNum(String ccNumber)
	{
		this.CCNum = CCNum;
	}

	public String getCCNum() 
	{
		return CCNum;
	}

	void setccDate(String CCDate)
	{
		this.CCDate = CCDate;
	}

	public String getCCDate()
	{
		return CCDate;
	}

	void setccName(String ccName) 
	{
		this.CCName = CCName;
	}

	public String getccName()
	{
		return CCName;
	}


}