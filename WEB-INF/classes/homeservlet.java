import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class homeservlet extends HttpServlet
{
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	RequestDispatcher view = request.getRequestDispatcher("Home.html");
	view.forward(request, response);

}
}