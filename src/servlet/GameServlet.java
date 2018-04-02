package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Player;
import beans.Position;
import connectionDAO.MySQLConnection;
import dao.DAO;
import dao.DAOFactory;
import dao.PositionDAO;

@WebServlet("/game")
@SuppressWarnings("serial")
public class GameServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		new MySQLConnection();
		
		Player playerNew = new Player(0,"Paul",true);
		int col = Integer.parseInt(req.getParameter("col"));
		System.out.println("col : "+col);
		int row = Integer.parseInt(req.getParameter("row"));
		System.out.println("row : "+row);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			DAO<Position> positionDAO = DAOFactory.getPositionDAO();
			Position test = new Position(0,row,col,2);
			test = positionDAO.create(test);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("/game.jsp").forward(req, res);
	}
	
}
