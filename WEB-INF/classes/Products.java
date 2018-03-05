

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Products implements java.io.Serializable {
	 
	String ProductId;
	String ProductName;
    String Category;
    String Type;
    String HotelName;
    String Price;
    String Location;
	String Image;
    String SQuantity;
    String RQuantity;
    String Sales;
    String Description;
    String Accessories;
    
    HashMap<String,Accessories> accessories;
    List ll;
  
    public Products(){
    }

	void setProductId(String id) {
		this.ProductId = id;
	}

	public String getProductId() {
		return ProductId;
	}

	void setProductName(String id) {
		this.ProductName = id;
	}

	public String getProductName() {
		return ProductName;
	}

	void setCategory(String id) {
		this.Category = id;
	}

	public String getCategory() {
		return Category;
	}
	
	void setType(String id) {
		this.Type = id;
	}

	public String getType() {
		return Type;
	}

	void setHotelName(String id) {
		this.HotelName = id;
	}

	public String getHotelName() {
		return HotelName;
	}

	void setPrice(String id) {
		this.Price = id;
	}

	public String getPrice() {
		return Price;
	}

	void setLocation(String id) {
		this.Location = id;
	}

	public String getLocation() {
		return Location;
	}

	void setImage(String id) {
		this.Image = id;
	}

	public String getImage() {
		return Image;
	}

	void setSQuantity(String id) {
		this.SQuantity = id;
	}

	public String getSQuantity() {
		return SQuantity;
	}

	void setRQuantity(String id) {
		this.RQuantity = id;
	}

	public String getRQuantity() {
		return RQuantity;
	}

	void setSales(String id) {
		this.Sales = id;
	}

	public String getSales() {
		return Sales;
	}

	void setDescription(String id) {
		this.Description = id;
	}

	public String getDescription() {
		return Description;
	}
	/*void setAccessories(String id) {
		this.Accessories = id;
	}

	public String getAccessories() {
		return Accessories;
	}*/

		void setAIDs(List ll) {
		this.ll = ll;
	}

	public List getAIDs() {
			return ll;
		}

	void setAccessories(HashMap<String,Accessories> accessories) {
			this.accessories = accessories;
	}

	public HashMap<String,Accessories> getAccessories() {
			return accessories;
	}






}