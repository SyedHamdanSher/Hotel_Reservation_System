import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Iterator;

public class AutoCompleteServlet extends HttpServlet {

	ArrayList<Object> products;
    HashMap<String, Products> rooms;
    HashMap<String, Products> lr;
    HashMap<String, Products> su;
    HashMap<String, Products> di;
    MySqlDataStoreUtilities mysql = new MySqlDataStoreUtilities();
    HashMap<String, Products> allProducts;
    
    void loadXML()
    {
        try{
        products = mysql.getProducts();
        
        rooms = (HashMap<String,Products>)products.get(0);
        lr = (HashMap<String,Products>)products.get(1);
        su = (HashMap<String,Products>)products.get(2);
        di = (HashMap<String,Products>)products.get(3);
        allProducts = (HashMap<String, Products>)products.get(4);
        
        
        }catch(Exception E){
        System.out.println("Exception");
        }
    }
	

    private ServletContext context;

    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
				
		loadXML();

        String action = request.getParameter("action");
        String targetId = request.getParameter("id");
        StringBuffer sb = new StringBuffer();

        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("loginedhomeservlet").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete")) {

            // check if user sent empty string
            if (!targetId.equals("")) {

                Iterator it = allProducts.keySet().iterator();

                while (it.hasNext()) {
                    String id = (String) it.next();
                    Products product = (Products) allProducts.get(id);

                    if ( // targetId matches product name
                         product.getProductName().toLowerCase().startsWith(targetId) ||
                         // targetId matches product company
                         product.getLocation().toLowerCase().startsWith(targetId)
                         )
					 {

                        sb.append("<product>");
                        sb.append("<id>" + product.getProductId() + "</id>");
                        sb.append("<name>" + product.getProductName() + "</name>");
                        sb.append("<company>" + product.getLocation() + "</company>");
                        sb.append("</product>");
                        namesAdded = true;
                    }
                }
            }

            if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<products>" + sb.toString() + "</products>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action.equals("lookup")) {

                request.setAttribute("product", allProducts.get(targetId));
				request.setAttribute("productName", targetId);
                context.getRequestDispatcher("/ShowSearchedProductServlet").forward(request, response);
				
        }
    }
}
