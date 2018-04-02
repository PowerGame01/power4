package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.Position;

public class PositionDAO extends DAO<Position>{

	private final String TABLE = "positions";
	
	@Override
	public Position find(Integer id) {
		Position position = null;
		try {
			String request = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement ps = this.connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, id);
			ResultSet result = ps.executeQuery();
			if (result.first()) {
                position = new Position(
                        id,
                        result.getInt("row"),
                        result.getInt("col"),
                        result.getInt("player")
                );
            }
		}catch (SQLException ex){
			Logger.getLogger(PositionDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return position;
	}

	@Override
	public Position create(Position position) {
		try {
			//System.out.println(this.connection.isClosed());
			String request = "INSERT INTO " + TABLE + " (row,col,id_player) VALUES (?,?,?)";
//			System.out.println("PlayerDAO before ps");
			PreparedStatement ps = this.connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
			System.out.println(position.toString());
			ps.setInt(1, position.getRow());
			ps.setInt(2, position.getCol());
			ps.setInt(3, position.getPlayer());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
            int last_inserted_id;
            if (rs.first()) { // Si on a des id créés on lit le premier
                last_inserted_id = rs.getInt(1);
                // On récupère l'enregistrement créé
                position = this.find(last_inserted_id);
            }
		}catch (SQLException ex) {
			Logger.getLogger(PositionDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return position;
	}

	@Override
	public Position update(Position obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Position position) {
		try {
            String request = "DELETE FROM " + TABLE + " WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(request);
            ps.setLong(1, position.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PositionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void deleteAll(int playerId) {
		try {
            String request = "DELETE FROM " + TABLE + " WHERE player_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(request);
            ps.setLong(1, playerId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PositionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

}
