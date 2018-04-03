package dao;

//Create a DAO Factory 
public class DAOFactory {
	//Create a PositionDAO object 
	 public static PositionDAO getPositionDAO() {
	        return new PositionDAO();
	 }
	 
	 //Create a GameDAO object
	 public static GameDAO getGameDAO() {
		 return new GameDAO();
	 }
	 
	 //Create a PlayerDAO object
	 public static PlayerDAO getPlayerDAO() {
		 return new PlayerDAO();
	 }
}


