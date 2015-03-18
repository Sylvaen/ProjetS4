<!-- Sert juste a se connecter OU a s'inscrire -->
<!-- Redirections : servlet Inscription OU servlet Connexion  -->
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
			<input class="inputs_login" type="text" value="mdp" name="mdp" /> <input
				class="btnCon" type="submit" value="Connexion" />
		</FORM>

	</div>

	<div id="content">
		<!-- Menu -->
		<c:import url="inc/menu.jsp" />
		<div id="presentation">

			<h2 class="titre">Bienvenue sur projet_s4</h2>

			<p>Si vous n'etes pas encore inscrit, vous pouvez le faire
				ci-dessous. Sinon, connectez-vous.</p>
			<div id="inscr">
				<button class="clickregister" onclick="afficherInscription()">Inscrivez
					vous gratuitement !</button>
			</div>



		</div>
	</div>

</body>
</html>