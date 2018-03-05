
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Order implements java.io.Serializable {
	
	String orderId;
	String userId;
	String productId;
	String checkinDate;
	String checkoutDate;
	String productName;
	String price;
	ArrayList<String> orderItems;
	

    public Order(String orderId,String userId, String productId,String checkinDate, String checkoutDate,String productName,String price){
    	this.orderId = orderId;
    	this.userId = userId;
		this.productId = productId;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.productName = productName;
		
		this.price = price;
    }

    void setPrice(String ccn) {
		this.price = ccn;
	}

	public String getPrice() {
			return price;
	}


	void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
			return orderId;
		}

	void setUserId(String orderId) {
		this.userId = orderId;
	}

	public String getUserId() {
			return userId;
		}
		
	void setCheckInDate(String deliveryAddress) {
		this.checkinDate = deliveryAddress;
	}

	public String getCheckInData() {
			return checkinDate;
		}

	void setCheckOutDate(String deliveryAddress) {
		this.checkoutDate = deliveryAddress;
	}

	public String getCheckOutData() {
			return checkoutDate;
		}

	void setProductId(String customerEmailId) {
		this.productId = customerEmailId;
	}

	public String getProductId() {
			return productId;
		}

	void setProductName(String orderDate) {
		this.productName = orderDate;
	}

	public String getProductName() {
			return productName;
		}

	void addItem(String item) {
		orderItems.add(item);
	}

	public ArrayList<String> getOrderItems() {
			return orderItems;
		}

}
