<!-- Sert juste a se connecter OU a s'inscrire -->
<!-- Redirections : servlet Inscription OU servlet Connexion  -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/style.css">
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/game.js"></script>

<title>Projet_S4</title>
</head>
<body>

	<div id="content-left">
		<p class="plogin">${sessionScope.user.name}</p>
		<p class="infoUser">Parties gagnees: 10</p>
		<p class="infoUser">Ratio: 50%</p>
		<form action="deconnexion">
			<input type="submit" value="Deconnexion" name="Deconnexion" />
		</form>
	</div>
	<div id="content">
		<c:import url="inc/menu.jsp" />
		<div id="game">
			
		</div>
	</div>

</body>
</html>