/**
 * personnal note: use numRow++ for vertical win
 */

jQuery(document).ready(function() {
	
	var col;
	var row;
	var turn = "X";
	var player = "X";
	var count = 0;
	var gameStateString = "0000000000000000000000000";
	
	// Main function to create and update the game
	$("td").on("click", function() {
		var $this = $(this);
		// Checking if the current td that has been clicked is empty
		// If empty we draw the move of the player
		if ($this.text() == ""){
			// Index of the current td
			$.post("power4", {"col":col = $this.index(),"row":row = $this.closest('tr').index()})
			
			// Index of the tr containing the current td
			
			// Setting color and size of the player's move
			$this.text(playerTurn()).
				css("color","red").
				css("font-size","50px");
		// If there is already a cross or a circle we show a popup alert
		}else{
			alert ("Wrong move");
		}
		// Checking the winning condition
		if (hasWonHor(row) == 1){
			alert("you wonHor")
		}
		
		if (hasWonVert(col) == 1){
			alert("you wonVert")
		}
		
		/*if (hasWonDiag() == 1){
			alert("you wonDiag")
		}*/
		// If it's the turn of the player, save the move in the changeStateString
		if (turn == player){
			gameState(row, col, 1);
		}
		console.log(gameStateString);
		
	})
	
	// Function to convert the index of the td being numbered from 0 to 4
	// depending on the rows, to a number from 1 to 25.
	function wichTd(col, row){
		var x = 0;
			if(row > 0)
				x = row * 5 + (col + 1);
			else x += (col+1);
		return x;
	}
	
	// This function updates the state of the game using a string
	function gameState(row, col, turn) {
		// The variable gamestring contains 25 characters which represents the 25 cells of the grid
		// Every time a player clicks on a td, we use the index of that td to change
		// a 0 to a 1 in the string
		gameStateString = replaceAt(gameStateString,wichTd(col, row)-1,turn);
		return gameStateString;
	}
	
	function playerTurn() {
		return (turn == "X") ? turn="O" : turn="X";
	}
	
	// Function to put the move of a player in the gameStateString
	function replaceAt(string, index, replace) {
		console.log("index :" + index);
		return string.substring(0, index) + replace + string.substring(index + 1);
	}
	
	// Function to check a horizontal victory
	function hasWonHor(rowNum) {
		// First loop checks for a win starting from the first cell of a row
		for (var j = 0 ; j < 2 ; j++){
			for (var i = 0 ; i < 5 ; i++){
				if ($("tr").eq(rowNum).children().eq(i).text() == player){
					count++;
				}
			}
			if (count > 3){
				return 1;
			}
			count = 0;
		}
		count = 0;
		return 0;
	}
	
	// Function to check a vertical victory
	function hasWonVert(colNum) {
		// First loop checks for a win starting from the first cell of a column
		for (var j = 0 ; j < 2 ; j++){
			count = 0;
			for (var i = 0 ; i < 5 ; i++){
				if ($("tr").eq(i).children().eq(colNum).text() == player){
					count++;
				}
			}
			if (count > 3){
				return 1;
			}
		}
		count = 0;
		return 0;
	}
	
	// Function to check a diagonal victory
	/*function hasWonDiag() {
		var startDiag = [
			[0,0],[0,1]
		];
		// First loop checks for a win starting from the first cell of a row
		for (var i = 0 ; i < 5 ; i++){
			if ($("tr").eq(startDiag[i][0]).children().eq(startDiag[i][1]).text() == player){
				count++;
				if (count > 3){
					return 1;
				}
			}
		}
		count = 0;
		// Second loop checks for a win starting from the second cell of a row
		for (var i = 1 ; i < 5 ; i++){
			if ($("tr").eq(i).children().eq(0).text() == player){
				count++;
				if (count > 3){
					return 1;
				}
			}
		}
		count = 0;
		return 0;
	}*/
})