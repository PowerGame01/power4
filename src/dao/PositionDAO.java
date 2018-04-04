package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.Position;

/** Using generic DAO for create PositionDAO **/
public class PositionDAO extends DAO<Position>{

	/** create final variable TABLE gridPosition **/
	private final String TABLE = "gridPosition";
	
	@Override
	/** implementation of find method for PositionDAO class **/
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
                        result.getInt("id_player")
                );
            }
		}catch (SQLException ex){
			Logger.getLogger(PositionDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return position;
	}

	@Override
	/** implementation of create method for PositionDAO class **/
	public Position create(Position position) {
		try {
			String request = "INSERT INTO " + TABLE + " (row,col,id_player) VALUES (?,?,?)";
			PreparedStatement ps = this.connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, position.getRow());
			ps.setInt(2, position.getCol());
			ps.setInt(3, position.getPlayer());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
            int last_inserted_id;
            if (rs.first()) { //if we have id created, we read the first
                last_inserted_id = rs.getInt(1);
              //We recover the id created
                position = this.find(last_inserted_id);
            }
		}catch (SQLException ex) {
			Logger.getLogger(PositionDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return position;
	}

	@Override
	public Position update(Position obj) {
		
		return null;
	}

	@Override
	/** implementation of delete method for PositionDAO class (for delete one element)**/
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
	
	/** Definition and implementation of deleteAll method for PositionDAO class **/
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
