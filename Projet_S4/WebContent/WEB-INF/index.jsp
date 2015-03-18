<!-- Sert juste a se connecter OU a s'inscrire -->
<!-- Redirections : servlet Inscription OU servlet Connexion  -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link rel="stylesheet" type="text/css" href="style.css">

<title>Projet_S4</title>
</head>
<body>

	<h2 class="titre">Bienvenue sur projet_s4</h2>

	<p>Si vous n'etes pas encore inscrit, vous pouvez le faire
		ci-dessous. Sinon, connectez-vous.</p>


	<h3> Inscription : </h3>
	<FORM method="post" action="inscription"> 
	<input type="text" value ="pseudo" name="pseudo"/>
	<input type="text" value="mdp" name="mdp" />
	<input type="submit" value="Inscription"/>
	</FORM>
	
	<h3> Connexion : </h3>
	<FORM method="post" action="connexion"> 
	<input type="text" value ="pseudo" name="pseudo"/>
	<input type="text" value="mdp" name="mdp" />
	<input type="submit" value="Connexion"/>
	</FORM>
	




</body>
</html>