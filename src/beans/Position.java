package beans;

//Create a position object

public class Position {
	
	private Integer id;
	private int row;
	private int col;
	private Integer player;
	
	//Constructor with parameters
	public Position(int id, int row, int col, int player) {
		this.id = id;
		this.row = row;
		this.col = col;
		this.player = player;
	}

	public Position(int row, int col, int player) {
		this.row = row;
		this.col = col;
		this.player = player;
	}

	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", row=" + row + ", col=" + col + ", player=" + player + "]";
	}
	
	
}
