/**
 * 
 */

jQuery(document).ready(function() {
	
	var col;
	var row;
	var turn = "X";
	var player = "X";
	var count = 0;
	
	$("td").on("click", function() {
		var $this = $(this);
		col = $this.index();
		row = $this.closest('tr').index();
		if ($this.text() == ""){
			$this.text(playerTurn()).
				css("color","red").
				css("font-size","50px").
				css("padding","0px").
				css("margin","0px");
		}else{
			alert ("Wrong move");
		}
		
		if (hasWon() == 1){
			alert("you won")
		}
		witchTd(col, row);
	})
	
	function wichTd(col, row){
		var x = 0;
			if(row > 0)
				x = row * 5 + (col + 1);
			else x += (col+1);
		console.log(x);
	}
	
	function playerTurn() {
		return (turn == "X") ? turn="O" : turn="X";
	}
	
	function hasWon(rowNum) {

	}

})