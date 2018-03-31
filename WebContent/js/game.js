/**
 * 
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
		// Index of the current td
		col = $this.index();
		// Index of the tr containing the current td
		row = $this.closest('tr').index();
		// Checking if the current td that has been clicked is empty
		// If empty we draw the move of the player
		tunr = playerTurn();
		if ($this.text() == ""){
			$this.text(turn).
				css("color","red").
				css("font-size","50px");
		// If there is already a cross or a circle we show a popup alert
		}else{
			alert ("Wrong move");
		}
		// Checking the winning condition
		if (hasWonHor(row) == 1){
			alert("you won")
		}
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
		for (var i = 0 ; i < 5 ; i++){
			if ($("tr").eq(rowNum).children().eq(i).text() == player){
				count++;
				if (count > 3){
					return 1;
				}
			}
		}
		count = 0;
		// Second loop checks for a win starting from the second cell of a row
		for (var i = 1 ; i < 5 ; i++){
			if ($("tr").eq(rowNum).children().eq(i).text() == player){
				count++;
				if (count > 3){
					return 1;
				}
			}
		}
		count = 0;
		return 0;
	}

})