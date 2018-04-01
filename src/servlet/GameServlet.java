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

@WebServlet("/power4")
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
		
		/*Position positionNew = new Position(0,row,col,playerNew.getId());
		
		new PositionDAO().create(positionNew);
		*/
		try {
			//System.out.println("start");
			Class.forName("com.mysql.jdbc.Driver");
			DAO<Position> positionDAO = DAOFactory.getPositionDAO();
			//System.out.println("after DAO");
			Position test = new Position(0,row,col,1);
			//System.out.println(test.toString());
//			Player test2 = new Player("Jean", true);
			test = positionDAO.create(test);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
