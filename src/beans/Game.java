package beans;

//Creating a player object

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

	public Integer getId_player_1() {
		return id_player_1;
	}

	public void setId_player_1(Integer id_player_1) {
		this.id_player_1 = id_player_1;
	}
	
	
}
