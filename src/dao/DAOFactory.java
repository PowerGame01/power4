package dao;


public class DAOFactory {
	
	 public static PositionDAO getPositionDAO() {
	        return new PositionDAO();
	    }
	 
	 public static GameDAO getGameDAO() {
		 return new GameDAO();
	 }
	 
	 public static PlayerDAO getPlayerDAO() {
		 return new PlayerDAO();
	 }
}


