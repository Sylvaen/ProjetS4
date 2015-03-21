<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/style.css">
<script type="text/javascript" src="js/index.js"></script>
<title>Projet_S4</title>
</head>
<body>

	<div id="content-left">
		<p class="plogin">Connexion</p>
		<FORM method="post" action="connexion">
			<input class="inputs_login" type="text" value="pseudo" name="pseudo" />
			<input class="inputs_login" type="password" value="mdp" name="mdp" />
			<input class="btnCon" type="submit" value="Connexion" />
		</FORM>
		<p class="erreurMessage">${result}</p>
	</div>

	<div id="content">
		<!-- Menu -->
		<c:import url="inc/menu.jsp" />
		<div id="presentation">

			<p>Compte validé avec succès !</p>
		</div>
	</div>

</body>
</html>