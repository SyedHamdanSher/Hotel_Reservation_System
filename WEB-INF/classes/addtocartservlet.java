import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class addtocartservlet extends HttpServlet {
	
	
  	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
		response.setContentType("text/html;charset=UTF-8");
		
        HttpSession session = request.getSession();
        
		int C;
        Cart cart;
        cart = (Cart) session.getAttribute("cart");
		
        if(cart == null){
          cart = new Cart();
          session.setAttribute("cart", cart);
          C=0;
          session.setAttribute("C",C);

        }
		
        
		String productName = request.getParameter("productName");
		String productId = request.getParameter("id");
        Float price = Float.parseFloat(request.getParameter("price"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		String image = request.getParameter("image");
		
		cart.addToCart(productId, productName, price, quantity, image);
        session.setAttribute("cart", cart);		
        
        RequestDispatcher rd = request.getRequestDispatcher("cartservlet");
		rd.forward(request,response);
	
	}
}