import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class signup extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	RequestDispatcher view = request.getRequestDispatcher("SignUP.html");
	view.forward(request, response);
}
}