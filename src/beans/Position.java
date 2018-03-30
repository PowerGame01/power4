package beans;

public class Position {
	//Setting Variables
	private Integer id;
	private Integer row;
	private Integer col;
	
	//Constructor
	public Position(Integer id, Integer row, Integer col) {
		this.id = id;
		this.row = row;
		this.col = col;
	}

	//Generate Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
	}
	
	//Override ToString Method
	@Override
    public String toString() {
        return "Position{" + "id=" + id + ", row=" + row + ", col=" + col + '}';
    }
}
