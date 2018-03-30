package beans;

public class Game {
	
	//Setting Variables
	private Integer id;
	private Integer id_player;
	private Integer id_player_1;
	
	//Constructor
	public Game(Integer id, Integer id_player, Integer id_player_1) {
		this.id = id;
		this.id_player = id_player;
		this.id_player_1 = id_player_1;
	}

	//Generate Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_player() {
		return id_player;
	}

	public void setId_player(Integer id_player) {
		this.id_player = id_player;
	}
	
	//override ToString method
	@Override
    public String toString() {
        return "Game{" + "id=" + id + ", id_player=" + id_player + ", id_player_1=" + id_player_1 + '}';
    }
}
