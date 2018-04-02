package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
			PreparedStatement ps = this.connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
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
//			System.out.println(this.connection.isClosed());
			String request = "INSERT INTO " + TABLE + " (name,waiting) VALUES (?,?)";
//			System.out.println("PlayerDAO before ps");
			PreparedStatement ps = this.connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
			System.out.println(player.toString());
			ps.setString(1, player.getName());
			ps.setBoolean(2, player.isWaiting());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
            int last_inserted_id;
            if (rs.first()) { // Si on a des id créés on lit le premier
                last_inserted_id = rs.getInt(1);
                // On récupère l'enregistrement créé
                player = this.find(last_inserted_id);
            }
		}catch (SQLException ex) {
			if(ex instanceof SQLIntegrityConstraintViolationException) {
		        return this.findByName(player.getName());
		    }
			Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return player;
	}

	@Override
	public Player update(Player player) {
		try {
			String request = "UPDATE " + TABLE + " SET name = ?, waiting = ? WHERE id = ?";
			PreparedStatement ps = this.connection.prepareStatement(request);
			ps.setString(1, player.getName());
			ps.setBoolean(2, player.isWaiting());
			ps.setInt(3, player.getId());
			ps.executeUpdate();
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
	
	public Player findOpponent(String name) {
		Player player = null;
		try {
			String request = "SELECT * FROM " + TABLE + " WHERE name != ? AND waiting = 1";
			PreparedStatement ps = this.connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			ResultSet result = ps.executeQuery();
//			System.out.println(request);
//			Object req = request;
//			player = (Player) req;
//			System.out.println(player.toString());
			if (result.first()) {
                player = new Player(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getBoolean("waiting")
                );
            }
		} catch (Exception ex) {
			Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return player;
	}
	
	public Player findByName(String name) {
		Player player = null;
		try {
			String request = "SELECT * FROM " + TABLE + " WHERE name = ?";
			PreparedStatement ps = this.connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			ResultSet result = ps.executeQuery();
			if (result.first()) {
                player = new Player(
                        result.getInt("id"),
                        result.getString("name"),
                        true
                );
            }
			this.update(player);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;	
	}
	
}