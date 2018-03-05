import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
import java.io.*;

public class ProductSAXParser extends DefaultHandler {
	
	Products smartwatch;
	Products speaker;
	Products headphone;
	Products phone;
	Products laptop;
	Products externalstorage;
	Accessories swaccessory;
	Accessories saccessory;
	Accessories hpaccessory;
	Accessories paccessory;
	Accessories laccessory;
	Accessories eaccessory;
    
	HashMap<String,Products> smartwatches;
	HashMap<String,Products> speakers;
	HashMap<String,Products> headphones;
	HashMap<String,Products> phones;
	HashMap<String,Products> laptops;
	HashMap<String,Products> externalstorages;
	HashMap<String,Accessories> swaccessories;
	HashMap<String,Accessories> saccessories;
	HashMap<String,Accessories> hpaccessories;
	HashMap<String,Accessories> paccessories;
	HashMap<String,Accessories> laccessories;
	HashMap<String,Accessories> eaccessories;
	List ll;
	String keyelement;

	ArrayList<Object> products;
	
    String productsXml = "/home/lastwalker/apache-tomcat-7.0.34/webapps/A1/WEB-INF/classes/SAXParser/ProductCatalog.xml";
	
    String evalue;
	
	int x=1;
	int y=1;
	int z=1;
	int tv=1;

    public ArrayList<Object> loadDataStore() {
        
		smartwatches = new HashMap<String,Products>();
		speakers = new HashMap<String,Products>();
		headphones = new HashMap<String,Products>();
		phones = new HashMap<String,Products>();
		laptops = new HashMap<String,Products>();
		externalstorages = new HashMap<String,Products>();
		
		products = new ArrayList<Object>();
		
        parseDocument(productsXml);
		
		
		products.add(smartwatches);
		products.add(speakers);
		products.add(headphones);
		products.add(phones);
		products.add(laptops);
		products.add(externalstorages);
		
		return products;
    }

    private void parseDocument(String xmlfile) {
		
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
			File f = new File(xmlfile);
			parser.parse(f, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
	
	
	public void printSmartWatches()
	{
		for(Map.Entry<String,Products> m :smartwatches.entrySet())
		{
			System.out.println(m.getKey());
			Products s = m.getValue();
			System.out.println("\t Id : "+s.getId());
			System.out.println("\t Company : "+s.getCompany());
			System.out.println("\t Price : "+s.getPrice());
			System.out.println("\t Accessories : "+s.getAIDs());
		}	
	}
	public void printSpeakers()
	{
		for(Map.Entry<String,Products> m :speakers.entrySet())
		{
			System.out.println(m.getKey());
			Products ss= m.getValue();
			System.out.println("\t Id : "+ss.getId());
			System.out.println("\t Company : "+ss.getCompany());
			System.out.println("\t Price : "+ss.getPrice());
			System.out.println("\t Accessories : "+ss.getAIDs());
		}	
	}

	public void printHeadphones()
	{
		for(Map.Entry<String,Products> m :headphones.entrySet())
		{
			System.out.println(m.getKey());
			Products h = m.getValue();
			System.out.println("\t Id : "+h.getId());
			System.out.println("\t Company : "+h.getCompany());
			System.out.println("\t Price : "+h.getPrice());
			System.out.println("\t Accessories : "+h.getAIDs());
		}	
	}

	public void printPhones()
	{
		for(Map.Entry<String,Products> m :phones.entrySet())
		{
			System.out.println(m.getKey());
			Products p = m.getValue();
			System.out.println("\t Id : "+p.getId());
			System.out.println("\t Company : "+p.getCompany());
			System.out.println("\t Price : "+p.getPrice());
			System.out.println("\t Accessories : "+p.getAIDs());
		}	
	}
	public void printLaptops()
	{
		for(Map.Entry<String,Products> m :laptops.entrySet())
		{
			System.out.println(m.getKey());
			Products l = m.getValue();
			System.out.println("\t Id : "+l.getId());
			System.out.println("\t Company : "+l.getCompany());
			System.out.println("\t Price : "+l.getPrice());
			System.out.println("\t Accessories : "+l.getAIDs());
		}	
	}
	public void printExternalstorages()
	{
		for(Map.Entry<String,Products> m :externalstorages.entrySet())
		{
			System.out.println(m.getKey());
			Products e = m.getValue();
			System.out.println("\t Id : "+e.getId());
			System.out.println("\t Company : "+e.getCompany());
			System.out.println("\t Price : "+e.getPrice());
			System.out.println("\t Accessories : "+e.getAIDs());
		}	
	}

    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
  
        if (elementName.equalsIgnoreCase("smartwatches")) {
        	keyelement="smartwatches";
            smartwatch = new Products();
            ll = new ArrayList();
            smartwatch.setId(attributes.getValue("id"));
            smartwatch.setRetailer(attributes.getValue("retailer"));
            
        }
		
		
		if (elementName.equalsIgnoreCase("speaker")) {
			keyelement="speaker";
            speaker = new Products();
            ll = new ArrayList();
            speaker.setId(attributes.getValue("id"));
            speaker.setRetailer(attributes.getValue("retailer"));
        }
		
		if (elementName.equalsIgnoreCase("laptop")) {
			keyelement="laptop";
            laptop = new Products();
            ll = new ArrayList();
            laptop.setId(attributes.getValue("id"));
            laptop.setRetailer(attributes.getValue("retailer"));
        }
		
		if (elementName.equalsIgnoreCase("headphone")) {
			keyelement="headphone";
            headphone = new Products();
            ll = new ArrayList();
            headphone.setId(attributes.getValue("id"));
            headphone.setRetailer(attributes.getValue("retailer"));
        }

        if (elementName.equalsIgnoreCase("phone")) {
        	keyelement="phone";
            phone = new Products();
            ll = new ArrayList();
            phone.setId(attributes.getValue("id"));
            phone.setRetailer(attributes.getValue("retailer"));
        }
        if (elementName.equalsIgnoreCase("externalstorage")) {
        	keyelement="externalstorage";
            externalstorage = new Products();
            ll = new ArrayList();
            externalstorage.setId(attributes.getValue("id"));
            externalstorage.setRetailer(attributes.getValue("retailer"));
        }
		
    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
//Fetching values of smartwatches
    	//System.out.println(element);
		 
        if (element.equals("smartwatches")) {

			smartwatches.put("S"+x, smartwatch);
			x++;
	    	return;
        }
        if (element.equalsIgnoreCase("image") && keyelement=="smartwatches" ) {
            smartwatch.setImage(evalue);
		
	    	return;
        }

        if (element.equalsIgnoreCase("name")&& keyelement=="smartwatches") {
            smartwatch.setName(evalue);
			
	    return;
        }
		if (element.equalsIgnoreCase("company") && keyelement=="smartwatches") {
            smartwatch.setCompany(evalue);
			
	    return;
        }
		if (element.equalsIgnoreCase("condition")&& keyelement=="smartwatches") {
            smartwatch.setCondition(evalue);
			
	    return;
        }
		if(element.equalsIgnoreCase("price")&& keyelement=="smartwatches"){
            smartwatch.setPrice(Float.parseFloat(evalue));
			
	    return;
        }
        if(element.equalsIgnoreCase("color")&& keyelement=="smartwatches"){
           smartwatch.setColor(evalue);
		  
	    return;
        }
		if(element.equalsIgnoreCase("description")&& keyelement=="smartwatches"){
            smartwatch.setDescription(evalue);
	    return;
        }
		if(element.equals("accessory")&& keyelement=="smartwatches"){

				ll.add(evalue);
				smartwatch.setAIDs(ll);
	    	return;
        } 
        if (element.equals("speaker")) {

			speakers.put("Sp"+x, smartwatch);
			x++;
	    	return;
        }
        if (element.equalsIgnoreCase("image") && keyelement=="speaker" ) {
            speaker.setImage(evalue);
		
	    	return;
        }

        if (element.equalsIgnoreCase("name")&& keyelement=="speaker") {
            speaker.setName(evalue);
			
	    return;
        }
		if (element.equalsIgnoreCase("company") && keyelement=="speaker") {
            speaker.setCompany(evalue);
			
	    return;
        }
		if (element.equalsIgnoreCase("condition")&& keyelement=="speaker") {
            speaker.setCondition(evalue);
			
	    return;
        }
		if(element.equalsIgnoreCase("price")&& keyelement=="speaker"){
            speaker.setPrice(Float.parseFloat(evalue));
			
	    return;
        }
        if(element.equalsIgnoreCase("color")&& keyelement=="speaker"){
           speaker.setColor(evalue);
		  
	    return;
        }
		if(element.equalsIgnoreCase("description")&& keyelement=="speaker"){
            speaker.setDescription(evalue);
	    return;
        }
		if(element.equals("accessory")&& keyelement=="speaker"){

				ll.add(evalue);
				speaker.setAIDs(ll);
	    	return;
        }

        if (element.equals("laptop")) {

			laptops.put("l"+x, smartwatch);
			x++;
	    	return;
        }
        if (element.equalsIgnoreCase("image") && keyelement=="laptop" ) {
            laptop.setImage(evalue);
		
	    	return;
        }

        if (element.equalsIgnoreCase("name")&& keyelement=="laptop") {
            laptop.setName(evalue);
			
	    return;
        }
		if (element.equalsIgnoreCase("company") && keyelement=="laptop") {
            laptop.setCompany(evalue);
			
	    return;
        }
		if (element.equalsIgnoreCase("condition")&& keyelement=="laptop") {
            laptop.setCondition(evalue);
			
	    return;
        }
		if(element.equalsIgnoreCase("price")&& keyelement=="laptop"){
            laptop.setPrice(Float.parseFloat(evalue));
			
	    return;
        }
        if(element.equalsIgnoreCase("color")&& keyelement=="laptop"){
           laptop.setColor(evalue);
		  
	    return;
        }
		if(element.equalsIgnoreCase("description")&& keyelement=="laptop"){
            laptop.setDescription(evalue);
	    return;
        }
		if(element.equals("accessory")&& keyelement=="laptop"){

				ll.add(evalue);
				laptop.setAIDs(ll);
	    	return;
        }
        if (element.equals("headphone")) {

			headphones.put("h"+x, smartwatch);
			x++;
	    	return;
        }
        if (element.equalsIgnoreCase("image") && keyelement=="headphone" ) {
            headphone.setImage(evalue);
		
	    	return;
        }

        if (element.equalsIgnoreCase("name")&& keyelement=="headphone") {
            headphone.setName(evalue);
			
	    return;
        }
		if (element.equalsIgnoreCase("company") && keyelement=="headphone") {
            headphone.setCompany(evalue);
			
	    return;
        }
		if (element.equalsIgnoreCase("condition")&& keyelement=="headphone") {
            headphone.setCondition(evalue);
			
	    return;
        }
		if(element.equalsIgnoreCase("price")&& keyelement=="headphone"){
            headphone.setPrice(Float.parseFloat(evalue));
			
	    return;
        }
        if(element.equalsIgnoreCase("color")&& keyelement=="headphone"){
           headphone.setColor(evalue);
		  
	    return;
        }
		if(element.equalsIgnoreCase("description")&& keyelement=="headphone"){
            headphone.setDescription(evalue);
	    return;
        }
		if(element.equals("accessory")&& keyelement=="headphone"){

				ll.add(evalue);
				headphone.setAIDs(ll);
	    	return;
        }
        if (element.equals("phone")) {

			phones.put("ph"+x, smartwatch);
			x++;
	    	return;
        }
        if (element.equalsIgnoreCase("image") && keyelement=="phone" ) {
            phone.setImage(evalue);
		
	    	return;
        }

        if (element.equalsIgnoreCase("name")&& keyelement=="phone") {
            phone.setName(evalue);
			
	    return;
        }
		if (element.equalsIgnoreCase("company") && keyelement=="phone") {
            phone.setCompany(evalue);
			
	    return;
        }
		if (element.equalsIgnoreCase("condition")&& keyelement=="phone") {
            phone.setCondition(evalue);
			
	    return;
        }
		if(element.equalsIgnoreCase("price")&& keyelement=="phone"){
            phone.setPrice(Float.parseFloat(evalue));
			
	    return;
        }
        if(element.equalsIgnoreCase("color")&& keyelement=="phone"){
           phone.setColor(evalue);
		  
	    return;
        }
		if(element.equalsIgnoreCase("description")&& keyelement=="phone"){
            phone.setDescription(evalue);
	    return;
        }
		if(element.equals("accessory")&& keyelement=="phone"){

				ll.add(evalue);
				phone.setAIDs(ll);
	    	return;
        }
        if (element.equals("externalstorage")) {

			externalstorages.put("es"+x, smartwatch);
			x++;
	    	return;
        }
        if (element.equalsIgnoreCase("image") && keyelement=="externalstorage" ) {
            externalstorage.setImage(evalue);
		
	    	return;
        }

        if (element.equalsIgnoreCase("name")&& keyelement=="externalstorage") {
            externalstorage.setName(evalue);
			
	    return;
        }
		if (element.equalsIgnoreCase("company") && keyelement=="externalstorage") {
            externalstorage.setCompany(evalue);
			
	    return;
        }
		if (element.equalsIgnoreCase("condition")&& keyelement=="externalstorage") {
            externalstorage.setCondition(evalue);
			
	    return;
        }
		if(element.equalsIgnoreCase("price")&& keyelement=="externalstorage"){
            externalstorage.setPrice(Float.parseFloat(evalue));
			
	    return;
        }
        if(element.equalsIgnoreCase("color")&& keyelement=="externalstorage"){
           externalstorage.setColor(evalue);
		  
	    return;
        }
		if(element.equalsIgnoreCase("description")&& keyelement=="externalstorage"){
            externalstorage.setDescription(evalue);
	    return;
        }
		if(element.equals("accessory")&& keyelement=="externalstorage"){

				ll.add(evalue);
				externalstorage.setAIDs(ll);
	    	return;
        }	
}	
		
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        evalue = new String(content, begin, end);
		//System.out.println(elementValueRead);
		return;
    }
	
	
	public static void main(String[] args) {
		 ProductSAXParser sp = new ProductSAXParser();
         sp.loadDataStore();
		 sp.printSmartWatches();
		 sp.printSpeakers();
		 sp.printHeadphones();
		 sp.printPhones();
		 sp.printLaptops();
		 sp.printExternalstorages();
    }
	
	
}