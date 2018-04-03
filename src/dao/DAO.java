package dao;

import java.sql.Connection;

import connectionDAO.MySQLConnection;

//Creating a generic DAO class

public abstract class DAO <T>{
	//Create a connection to the DataBase
	 protected Connection connection = MySQLConnection.getInstance();

	 //Definition of the method to search information in database
	 public abstract T find(Integer id);
	    
	 //Definition of the method to create an object in database	      
	 public abstract T create(T obj);
	 
	 //Definition of the method to update data in Database
	 public abstract T update(T obj);

	 //Definition of the method to delete data in DataBase	 
	 public abstract void delete(T obj);
}
