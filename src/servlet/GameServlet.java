package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Game;
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
		
		HttpSession session = req.getSession();
		
		System.out.println("player in game " +session.getAttribute("player"));
		System.out.println("opponent in game " +session.getAttribute("opponent"));
		if (req.getParameter("col") != null && req.getParameter("row") != null) {
			int col = Integer.parseInt(req.getParameter("col"));
			System.out.println("col : "+col);
			int row = Integer.parseInt(req.getParameter("row"));
			System.out.println("row : "+row);	
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
					DAO<Position> positionDAO = DAOFactory.getPositionDAO();
					Position test = new Position(0,row,col,1);
					test = positionDAO.create(test);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				DAO<Game> gameDAO = DAOFactory.getGameDAO();
				Game testGame = new Game(0,1,2);
				testGame = gameDAO.create(testGame);
		}
		// Fin du if général
		
		
		req.getRequestDispatcher("/game.jsp").forward(req, res);
	}
	
}
