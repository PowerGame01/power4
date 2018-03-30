package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Game;

public class GameDAO extends DAO<Game>{
private final String TABLE = "game";
	@Override
	public Game find(Integer id) {
		Game game = null;
		try {
			String request = "SELECT* FROM" + TABLE + "WHERE id = ?";
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
		return game;
	}

	@Override
	public Game create(Game obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game update(Game obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Game obj) {
		// TODO Auto-generated method stub
		
	}

}
