package beans;

public class Position {
	
	int id;
	int row;
	int col;
	int player;
	
	
	public Position(int id, int row, int col, int player) {
		super();
		this.id = id;
		this.row = row;
		this.col = col;
		this.player = player;
	}

	public Position(int row, int col, int player) {
		super();
		this.row = row;
		this.col = col;
		this.player = player;
	}

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
	
	
}
