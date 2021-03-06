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
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			playerDAO = DAOFactory.getPlayerDAO();
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}

		if(session.getAttribute("opponent") != null && session.getAttribute("player") != null) {
			req.setAttribute("player", session.getAttribute("player"));
			req.setAttribute("opponent", session.getAttribute("opponent"));
			req.getRequestDispatcher("/game").forward(req, res);
		}else {
			if(session.getAttribute("player") != null) {

				opponent =  ((PlayerDAO) playerDAO).findOpponent(((Player) session.getAttribute("player")).getName());
				session.setAttribute("opponent", ((Player)opponent));
				System.out.println(((PlayerDAO) playerDAO).findOpponent(session.getAttribute("player").toString()));
				if(session.getAttribute("opponent") == null) {
					req.setAttribute("opponent", session.getAttribute("opponent"));
					req.getRequestDispatcher("/WEB-INF/views/waitingRoom.jsp").forward(req, res);
				}else req.getRequestDispatcher("/game").forward(req, res);
			}else {
				try {
					player = new Player(req.getParameter("name"), true);
					player = playerDAO.create(player);
					session.setAttribute("player", ((Player)player));
					req.setAttribute("player", session.getAttribute("player"));
					session.setAttribute("playerName", player.getName());
					System.out.println("session attribut player = " + session.getAttribute("player").toString());
					System.out.println("test" + ((PlayerDAO) playerDAO).findOpponent(session.getAttribute("playerName").toString()));
				} catch (Exception e) {
						e.printStackTrace();
						
				}
				req.getRequestDispatcher("/WEB-INF/views/waitingRoom.jsp").forward(req, res);
			}
			System.out.println("opponent = " + session.getAttribute("opponent"));
			System.out.println("player = " + session.getAttribute("player"));
		}
			
	}
}