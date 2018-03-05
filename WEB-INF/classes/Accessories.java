

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Accessories implements java.io.Serializable {
	
	String id;
	String retailer;
    String image;
    String name;
    String company;
    String condition;
    String color;
	String description;
    float price;
    int rQuantity, sQuantity;
	Boolean onRebates, onSale;
	Double sales;
  
    public Accessories(String id,String retailer,String image,String name,String company,String condition,String color,String description,float price){
        this.id=id;
        this.retailer=retailer;
        this.image=image;
        this.name=name;
        this.company=company;
        this.condition=condition;
        this.color=color;
        this.description=description;
        this.price=price;
    }

    public Accessories(){}

	void setId(String id) {
		this.id = id;
	}

	public String getId() {
			return id;
		}

	void setOnRebates(Boolean x) {
		this.onRebates = x;
	}

	public Boolean getOnRebates() {
		return onRebates;
	}

	void setOnSale(Boolean x) {
		this.onSale = x;
	}

	public Boolean getOnSale() {
		return onSale;
	}

	void setrQuantity(int x) {
		rQuantity = x;
	}

	public int getrQuantity() {
		return rQuantity;
	}

	void setsQuantity(int x) {
		sQuantity = x;
	}

	public int getsQuantity() {
		return sQuantity;
	}

	void setSales(Double x) {
		sales = x;
	}

	public Double getSales() {
		return sales;
	}

	void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getRetailer() {
			return retailer;
		}

	void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
			return image;
		}

	void setName(String name) {
		this.name = name;
	}

	public String getName() {
			return name;
		}

	void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
			return company;
		}

	void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCondition() {
			return condition;
		}

	void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
			return color;
		}

	void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
			return description;
		}

	void setPrice(float price) {
		this.price = price;
	}

	public float getPrice() {
			return price;
		}






}
