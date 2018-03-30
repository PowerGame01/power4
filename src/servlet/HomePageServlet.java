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

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		new MySQLConnection();
		
		String name = req.getParameter("name");
		//lancer la fonction DAO qui recherche le nom de notre input.
		Player playerExistant = new PlayerDAO().findByNameWaiting(name);
	
		
		if (playerExistant !=null)
			// call the function waitingRoom.
			// waitingRoom(new playerDAO());
		{
			playerExistant.setWaiting(false); // update player
			new PlayerDAO().update(playerExistant); // passe l utilisateur sur la db en mode busy
	}
		else
		{		//waitingRoom(playerA).
			Player playerNew = new Player(0,name,true);
			new PlayerDAO().create(playerNew);
		}
		
		
	}
	
}
