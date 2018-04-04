package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.Game;

/** Using generic DAO for create GameDAO **/
public class GameDAO extends DAO<Game>{
	
	/** create final variable TABLE game **/
	private final String TABLE = "game";
	@Override
	/** implementation of find method for GameDAO class **/
	public Game find(Integer id) {
		Game game = null;
		try {
			String request = "SELECT* FROM " + TABLE + " WHERE id = ?";
			PreparedStatement ps = this.connection.prepareStatement(request);
			ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            if (result.first()) {
                game = new Game(
                        id,
                        result.getInt(1),
                        result.getInt(2)
                );
            }
		}catch (Exception ex){
			
		}
		/** return a tic-tac-toe **/
		return game;
	}

	@Override
	/** implementation of create method for GameDAO class **/
	public Game create(Game game) {
		try {
			String request = "INSERT INTO " + TABLE + " (id_player,id_player_1) VALUES (?,?)";
			PreparedStatement ps = this.connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, game.getId_player());
			ps.setInt(2, game.getId_player());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
            int last_inserted_id;
            if (rs.first()) { //if we have id created, we read the first
                last_inserted_id = rs.getInt(1);
                //We recover the id created
                game = this.find(last_inserted_id);
            }
		}catch (SQLException ex) {
			Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return game;
	}

	@Override
	public Game update(Game obj) {
		return null;
	}
	/** implementation of delete method for GameDAO class **/
	@Override
	public void delete(Game game) {
		try {
            String request = "DELETE FROM " + TABLE + " WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(request);
            ps.setLong(1, game.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

}

