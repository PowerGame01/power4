/**
 * personnal note: use numRow++ for vertical win
 */

jQuery(document).ready(function() {
	
	var col;
	var row;
	var player = "X";
	var count = 0;
	
	// Main function to create and update the game
	$("td").on("click", function() {
		var $this = $(this);
		// Checking if the current td that has been clicked is empty
		// If empty we draw the move of the player
		if ($this.text() == ""){
			// Index of the current td
			$.post("game", {"col":col = $this.index(),"row":row = $this.closest('tr').index()})
			
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
			$("#victory").text("Victoire");
		}
		
		if (hasWonVert(col) == 1){
			$("#victory").text("Victoire");
		}
		
		if (hasWonDiagRight() == 1){
			$("#victory").text("Victoire");
		}
		
		if (hasWonDiagLeft() == 1){
			$("#victory").text("Victoire");
		}
		// If it's the turn of the player, save the move in the changeStateString
		if (turn == player){
			gameState(row, col, 1);
		}
		console.log(gameStateString);
		
	})

	function playerTurn() {
		return (turn == "X") ? turn="O" : turn="X";
	}

	// Function to check a horizontal victory
	function hasWonHor(rowNum) {
		// First loop checks for a win starting from the first cell of a row
		for (var j = 0 ; j < 2 ; j++){
			for (var i = 0 ; i < 5 ; i++){
				if ($("tr").eq(rowNum).children().eq(i).text() == player){
					count++;
				}else{
					if (i != 0)
						break;
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
				}else{
					if (i != 0)
					break;
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
	function hasWonDiagRight() {
		for (var j = 0 ; j < 4 ; j++){
			var colPos = 0;
			if (j > 1){
				colPos = 1;
			}	
			var rowPos = j % 2;
			count = 0;
			for (var i = colPos ; i < 5 ; i++){
				if ($("tr").eq(rowPos).children().eq(i).text() == player){
					count++;
					rowPos++;
				}else{
					if (i != 0)
						break;
					}
			}
			if (count > 3){
				return 1;
			}
		}
		count = 0;
		return 0;
	}
	
	// Function to check a left to diagonal victory
	function hasWonDiagLeft() {
		for (var j = 0 ; j < 4 ; j++){
			var colPos = 3;
			if (j > 1){
				colPos = 4;
			}	
			var rowPos = j % 2;
			count = 0;
			for (var i = colPos ; i >= 0 ; i--){
				if ($("tr").eq(rowPos).children().eq(i).text() == player){
					count++;
					rowPos++;
				}else{
					if (i != 0)
						break;
					}
			}
			if (count > 3){
				return 1;
			}
		}
		count = 0;
		return 0;
	}
})