package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/HomePage")

public class HomePageServlet extends HttpServlet{

	@Override
<<<<<<< HEAD
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

=======
	protected void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		req.getParameter("nom");
>>>>>>> branch 'master' of https://github.com/PowerGame01/power4.git
	}
	
}
