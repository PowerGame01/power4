package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Player;
import connectionDAO.MySQLConnection;
import dao.PlayerDAO;

@SuppressWarnings("serial")
@WebServlet("/home")

public class HomePageServlet extends HttpServlet{

	@Override
	// override method Service request and response
	protected void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		//forward servlet to the homePage.jsp
		req.getRequestDispatcher("/homePage.jsp").forward(req, res);		
	}
}
