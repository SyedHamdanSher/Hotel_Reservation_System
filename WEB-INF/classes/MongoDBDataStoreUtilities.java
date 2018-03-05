import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import com.mongodb.*;


public class MongoDBDataStoreUtilities
{
	static DBCollection myReviews;
	
	public static void getConnection()
	{
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("alHamdanReviews");
		myReviews= db.getCollection("myReviews");
	}
	
	public static void insertReview(String name, String type, double price, String retailer, String retailerZip, String retailerCity,
										String retailerState, String productOnSale, String manufacturer, String manufacturerRebate,
										String emailId, int userAge, String userGender, String userOccupation, int reviewRating,
										Date reviewDate, String reviewText)
	{
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("alHamdanReviews");
		myReviews= db.getCollection("myReviews");
		
		BasicDBObject ob = new BasicDBObject();
		
		ob.put("productName", name);
		ob.put("productType", type);
		ob.put("productPrice", price);
		ob.put("retailer", retailer);
		ob.put("retailerZip", retailerZip);
		ob.put("retailerCity", retailerCity);
		ob.put("retailerState", retailerState);
		ob.put("productOnSale", productOnSale);
		ob.put("manufacturer", manufacturer);
		ob.put("manufacturerRebate", manufacturerRebate);
		ob.put("emailId", emailId);
		ob.put("userAge", userAge);
		ob.put("userGender", userGender);
		ob.put("userOccupation", userOccupation);
		ob.put("reviewRating", reviewRating);
		ob.put("reviewDate", reviewDate);
		ob.put("reviewText", reviewText);
		myReviews.insert(ob);
	}
	
	public static HashMap<String, Review> getReviews()
	{
		HashMap<String, Review> reviews = new HashMap<String, Review>();
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		DB db= mongo.getDB("alHamdanReviews");
		myReviews= db.getCollection("myReviews");
		DBCursor cursor = myReviews.find();
		
		while (cursor.hasNext())
		{
			BasicDBObject obj= (BasicDBObject) cursor.next();
			Review review = new Review(obj.getString("productName"), obj.getString("productType"), obj.getDouble("productPrice")
										,obj.getString("retailer"), obj.getString("retailerZip"),obj.getString("retailerCity")
										,obj.getString("retailerState"), obj.getString("productOnSale"), obj.getString("manufacturer")
										, obj.getString("manufacturerRebate"), obj.getString("emailId"), obj.getInt("userAge")
										, obj.getString("userGender"), obj.getString("userOccupation"), obj.getInt("reviewRating")
										,obj.getDate("reviewDate"), obj.getString("reviewText"));
										reviews.put(obj.getString("productName")+obj.getString("_id"), review);
		}
		return reviews;
	}
	
	public static LinkedHashMap<String, Integer> getTop5ZipCodes()
	{
		LinkedHashMap<String, Integer> top5ZipCodes = new LinkedHashMap<String, Integer>();
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		DB db= mongo.getDB("alHamdanReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerZip")
                                    .append("count", new BasicDBObject("$sum", 1))),
                    new BasicDBObject("$sort", new BasicDBObject("count", -1)),
					new BasicDBObject("$limit", 5)
            );

		String zip="";
		int count = 0;
		
		for (DBObject doc : output.results())
		{
			zip = (String) doc.get("_id");
			count = (Integer) doc.get("count");
			
			top5ZipCodes.put(zip, count);
			
		}

		return top5ZipCodes;
	}
	
	public static LinkedHashMap<String, Double> getTop5LikedProducts()
	{
		LinkedHashMap<String, Double> top5LikedProducts = new LinkedHashMap<String, Double>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("alHamdanReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$productName")
                                    .append("avgRating", new BasicDBObject("$avg", "$reviewRating"))),
                    new BasicDBObject("$sort", new BasicDBObject("avgRating", -1)),
					new BasicDBObject("$limit", 5)
            );

		String productName="";
		double avg = 0;
		
		for (DBObject doc : output.results())
		{
			productName = (String) doc.get("_id");
			avg = (Double) doc.get("avgRating");
			
			top5LikedProducts.put(productName, avg);
		}
		return top5LikedProducts;
	}

	public static LinkedHashMap<String, Integer> getTop5SoldProducts()
	{
		LinkedHashMap<String, Integer> top5SoldProducts = new LinkedHashMap<String, Integer>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("alHamdanReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$productName")
                                    .append("count", new BasicDBObject("$sum", 1))),
                    new BasicDBObject("$sort", new BasicDBObject("count", -1)),
					new BasicDBObject("$limit", 5)
            );

		String productName="";
		int c = 0;
		
		for (DBObject doc : output.results())
		{
			productName = (String) doc.get("_id");
			c = (Integer) doc.get("count");
			
			top5SoldProducts.put(productName, c);
		}
		return top5SoldProducts;
	}
	
	public static void main(String args[]){

		/*insertReview("name","type",111.0,"retailer", "retailerZip","retailerCity",
										"retailerState", "productOnSale","manufacturer","manufacturerRebate",
										"emailId", 21,"userGender","userOccupation",5,
				new Date(2016, 11, 18),"reviewText");*/
		
		//MongoDBDataStoreUtilities.getTop5ZipCodes();
		//MongoDBDataStoreUtilities.getTop5LikedProducts();
	}

}