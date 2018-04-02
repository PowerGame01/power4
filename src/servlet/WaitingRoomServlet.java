package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import dao.DAOFactory;
import dao.PlayerDAO;
import beans.Player;

@SuppressWarnings("serial")
@WebServlet("/waiting")

public class WaitingRoomServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Player player = new Player(req.getParameter("name"), true);
		Player opponent = null;
		
		
		try {
			System.out.println("start");
			Class.forName("com.mysql.jdbc.Driver");
			DAO<Player> playerDAO = DAOFactory.getPlayerDAO();
//			System.out.println("after DAO");
//			Player test = new Player("Paul", true);
//			System.out.println(test.toString());
//			Player test2 = new Player("Jean", true);
			player = playerDAO.create(player);
			opponent = ((PlayerDAO) playerDAO).findOpponent(player.getName());
			System.out.println(opponent.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("End : ");
		System.out.println("Player = " + player.toString());
		System.out.println("Opponent = " + opponent.toString());
		req.getRequestDispatcher("/waitingRoom.jsp").forward(req, res);
		
	}
	
	

}
