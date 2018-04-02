package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.Game;

public class GameDAO extends DAO<Game>{
private final String TABLE = "game";
	@Override
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
		return game;
	}

	@Override
	public Game create(Game game) {
		try {
			//System.out.println(this.connection.isClosed());
			String request = "INSERT INTO " + TABLE + " (id_player,id_player_1) VALUES (?,?)";
//			System.out.println("PlayerDAO before ps");
			PreparedStatement ps = this.connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
			System.out.println(game.toString());
			ps.setInt(1, game.getId_player());
			ps.setInt(2, game.getId_player());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
            int last_inserted_id;
            if (rs.first()) { // Si on a des id créés on lit le premier
                last_inserted_id = rs.getInt(1);
                // On récupère l'enregistrement créé
                game = this.find(last_inserted_id);
            }
		}catch (SQLException ex) {
			Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return game;
	}

	@Override
	public Game update(Game obj) {
		// TODO Auto-generated method stub
		return null;
	}

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
