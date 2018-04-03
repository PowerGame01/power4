package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import dao.DAOFactory;
import dao.PlayerDAO;
import beans.Player;

@SuppressWarnings("serial")
@WebServlet("/waiting")

public class WaitingRoomServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		Player player = null;
		Player opponent = null;
		DAO<Player> playerDAO = null;
		
		if(session.getAttribute("player") == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				playerDAO = DAOFactory.getPlayerDAO();
				player = new Player(req.getParameter("name"), true);
				player = playerDAO.create(player);
				session.setAttribute("player", player);
				System.out.println("session attribut player = " + session.getAttribute("player"));
			} catch (Exception e) {
					e.printStackTrace();
			}
		}
		if(session.getAttribute("opponent") == null) {
			try {
				System.out.println("opponent entry");
				opponent = ((PlayerDAO) playerDAO).findOpponent(((Player)session.getAttribute("player")).getName());
				System.out.println(opponent.toString());
				session.setAttribute("opponent", opponent);
				System.out.println(opponent);
				System.out.println("session attribut opponent = " + session.getAttribute("opponent"));
				
				System.out.println("Coucou t'es dans le if");
//				req.setAttribute("player", session.getAttribute("player"));
//				req.setAttribute("opponent", session.getAttribute("opponent"));
				req.getRequestDispatcher("/game").forward(req, res);	
				
			}catch (NullPointerException e) {
				e.getStackTrace();
			}catch (IllegalStateException e) {
				e.getStackTrace();
			}
		} else {	
		
			System.out.println("Coucou");
		
			req.getRequestDispatcher("/waitingRoom.jsp").forward(req, res);
		}
		

	}
}
