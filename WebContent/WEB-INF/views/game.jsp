<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="css/game.css">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/game.js"></script>
<title>Power4</title>
</head>
<body>
	<h1>Power4</h1>
	<div>
		<output id="playerName">You are the player : ${plName }</output><br>
		<output id="opponentName">Opponent is : ${opName }</output>
	</div>
	<table width="500" height="500">
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<section>
		<output id="victory"></output>
	</section>
</body>
</html>