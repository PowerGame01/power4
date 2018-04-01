package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.Position;

public class PositionDAO extends DAO<Position>{

	private final String TABLE = "position";
	
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
			Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return position;
	}

	@Override
	public Position create(Position obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position update(Position obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Position obj) {
		// TODO Auto-generated method stub
		
	}

}
