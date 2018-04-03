package beans;

//Creating a Player object

public class Player {
	
	//Setting variables
	private Integer id;
	private String name;
	private boolean waiting;
	
	//Constructor
	public Player(Integer id, String name, boolean waiting) {
		this.id = id;
		this.name = name;
		this.waiting = waiting;
	}
	
	public Player(String name, boolean waiting) {
		this.name = name;
		this.waiting = waiting;
	}
	
	//Generate Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isWaiting() {
		return waiting;
	}

	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}
}