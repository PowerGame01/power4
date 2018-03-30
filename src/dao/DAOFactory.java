package dao;


public class DAOFactory {
	
	 public static PositionDAO getPositionDAO() {
	        return new PositionDAO();
	    }
	 
	 public static GameDAO GameDAO() {
		 return new GameDAO();
	 }
	 
	 public static PlayerDAO PlayerDAO() {
		 return new PlayerDAO();
	 }
}
