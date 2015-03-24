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
		<p class="erreurMessage">${logErr}</p>
	</div>

	<div id="content">
		<!-- Menu -->
		<c:import url="inc/menu.jsp" />
		<div id="presentation">
			<div id='inscForm'>
				<h3>Inscription :</h3>
				<FORM id='inscriptionForm' method='post' action='inscription'>
					<input type='text' value='pseudo' name='pseudo' /> <input
						type='text' value='mdp' name='mdp' /><input type='submit'
						name='Valider' class='inscr' />
				</FORM>
				${form.erreurs['email']} ${form.erreurs['name']}
				${form.erreurs['conf']} ${form.erreurs['pwd']}
				${form.erreurs['sendemail']} ${form.resultat}
				
			</div>
		</div>
	</div>
</body>
</html>