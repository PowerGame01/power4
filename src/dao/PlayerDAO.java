package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import beans.Player;

public class PlayerDAO extends DAO<Player>{
	
	private final String TABLE = "player";
	@Override
	public Player find(Integer id) {
		Player player = null;
		try {
			String request = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement ps = this.connection.prepareStatement(request,
            Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, id);
			ResultSet result = ps.executeQuery();
			if (result.first()) {
                player = new Player(
                        id,
                        result.getString("name"),
                        result.getBoolean("waiting")
                );
            }
		}catch (SQLException ex){
			Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return player;
	}

	@Override
	public Player create(Player player) {
		try {
			String request = "INSERT INTO" + TABLE + "(name,waiting) VALUES (?,?)";
			PreparedStatement ps = this.connection.prepareStatement(request,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, player.getName());
			ps.setBoolean(2, player.isWaiting());
			ResultSet rs = ps.getGeneratedKeys();
            int last_inserted_id;
            if (rs.first()) { // Si on a des id créés on lit le premier
                last_inserted_id = rs.getInt(1);
                // On récupère l'enregistrement créé
                player = this.find(last_inserted_id);
            }
		}catch (SQLException ex) {
			Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return player;
	}

	@Override
	public Player update(Player player) {
		try {
			String request = "UPDATE " + TABLE + "WHERE id = ?";
			PreparedStatement ps = this.connection.prepareStatement(request);
			ps.setBoolean(3, player.isWaiting());
		}catch (Exception ex) {
			Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return player;
	}

	@Override
	public void delete(Player player) {
		try {
            String request = "DELETE FROM " + TABLE + " WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(request);
            ps.setLong(1, player.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public Player findByNameWaiting(String name) {
		Player player = null;
		try {
			String request = "SELECT * FROM" + TABLE +"WHERE name !="+name+"AND waiting = true";
			Object req = request;
			player = (Player) req;
		} catch (Exception ex) {
			Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return player;
	}
	
}