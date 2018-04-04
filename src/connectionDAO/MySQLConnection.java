package connectionDAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MySQLConnection {
    // Using properties file for Connection
    /*
    connection URL
    */
    private static String host;
    private static String port;
    private static String database;
  
    /*
    Login-Password
    */
    private static String user;
    private static String password;  
    /*
    Connection Object
    */
    private static Connection connection;
    //Constructor of MySQLConnection
    public MySQLConnection() {}
    	//create a connection to the database
    	public static Connection getInstance() {
    	        try {
    	        	//Path of properties file
    	        	
    	            Properties configProp = new Properties();
    	            configProp.load(MySQLConnection.class.getResourceAsStream("/configDB.prop"));
    	            host = configProp.getProperty("HOST");
    	            port = configProp.getProperty("PORT");
    	            database = configProp.getProperty("DATABASE");
    	            user = configProp.getProperty("USER");
    	            password = configProp.getProperty("PASSWORD");
    	        } catch (FileNotFoundException ex) {
    	            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
    	        } catch (IOException ex) {
    	            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
    	        }
    	        if (connection == null) {
    	            try{
    	            	//create a connection to the database
    	            	connection = DriverManager.getConnection("jdbc:mysql://"+host+
    	            			":"+port+"/"+database + "?autoReconnect=true&useSSL=false", user, password);
    	            	//create database tables if not exists
    	            	connection.createStatement().execute("CREATE TABLE IF NOT EXISTS player(id int (11) Auto_increment  NOT NULL ,\n" + 
    	            			"        name    Varchar (50) NOT NULL ,\n" + 
    	            			"        waiting Bool NOT NULL ,\n" + 
    	            			"        PRIMARY KEY (id ) ,\n" + 
    	            			"        UNIQUE (name )\n" + 
    	            			")ENGINE=InnoDB;");
    	            	connection.createStatement().execute("CREATE TABLE IF NOT EXISTS gridPosition(id int (11) Auto_increment  NOT NULL ,\n" + 
    	            			"        row       Varchar (2) NOT NULL ,\n" + 
    	            			"        col       Varchar (2) NOT NULL ,\n" + 
    	            			"        id_player Int NOT NULL ,\n" + 
    	            			"        PRIMARY KEY (id ),\n"
    	            			+ "CONSTRAINT FK_gridPosition_id_player FOREIGN KEY (id_player) REFERENCES player(id)"+
    	            			")ENGINE=InnoDB;");
    	            	connection.createStatement().execute("CREATE TABLE IF NOT EXISTS game(id int (11) Auto_increment  NOT NULL ,\n" + 
    	            			"        id_player   Int NOT NULL ,\n" + 
    	            			"        id_player_1 Int NOT NULL ,\n" + 
    	            			"        PRIMARY KEY (id ),"
    	            			+ "\n" + "CONSTRAINT FK_game_id_player FOREIGN KEY (id_player) REFERENCES player(id),"
    	            					+ "CONSTRAINT FK_game_id_player_1 FOREIGN KEY (id_player_1) REFERENCES player(id)"
    	            			+")ENGINE=InnoDB;");
    	            }catch(SQLException ex){
    	                Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null , ex);
    	            }
    	        }
    	    //return a connection    
    	    return connection;
    	}
    
    
}
