
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
		 
	HashMap<String, List<Object>> cartItems;
		
		float totalAmount;
		 
	    public Cart(){
	     cartItems = new HashMap<String, List<Object>>();
		 totalAmount=0;
	      
	    }
	    public HashMap getCartItems(){
	        return cartItems;
	    }
	    public void addToCart(String id, String productName, float price, int quantity, String image){
	    	List<Object> a = new ArrayList<Object>();
			a.add(id);
			a.add(productName);
			a.add(price);
	    	a.add(quantity);
			a.add(image);
	    	cartItems.put(id, a);
	    }
	     
		public void deleteFromCart(String id){
	        cartItems.remove(id);
	    }
		
		public void setTotalAmount(float totalAmount)
		{
			this.totalAmount = totalAmount;
		}
		
		public float getTotalAmount()
		{
			return totalAmount;
		}
}

