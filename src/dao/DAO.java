package dao;

import java.sql.Connection;

public abstract class DAO <T>{
	 protected Connection connection;

	 // Search information in database
	 
	 public abstract T find(Integer id);

	    
	 //  Create an object in database
	      
	 public abstract T create(T obj);

	    

	 // update data in Database
	     
	 public abstract T update(T obj);

	 //delete a data in DataBase
	 
	 public abstract void delete(T obj);
}
