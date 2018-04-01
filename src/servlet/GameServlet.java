package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/power4")
@SuppressWarnings("serial")
public class GameServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		int col = Integer.parseInt(req.getParameter("col"));
		System.out.println("col : "+col);
		int row = Integer.parseInt(req.getParameter("row"));
		System.out.println("row : "+row);
	}

	
}
