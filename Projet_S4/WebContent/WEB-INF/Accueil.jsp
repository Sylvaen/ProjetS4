<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h2> Bonjour ! C'est votre page d'accueil </h2>
	
	<h3> Vos infos :</h3>
	<h4> Pseudo :  ${pseudo}</h4>

	<p> Vous pouvez voir ici toutes les parties en cours </p>
	<p> Il est possible d'en creer une ou d'en rejoindre une </p>

	<!-- CREER PARTIE  -->
	<FORM method="get" action="Partie"> 
		<input type="hidden" value="${pseudo}" name ="nom_joueur1" />
		<input type="text" value="Nom partie" name ="nom"/>
		<input type="submit" value="Creer partie" name="creer"/>	
	</FORM>
	
	<!-- REJOINDRE PARTIE  -->
	<p> Voici les parties que vous pouvez rejoindre </p>
	<p> 
	<c:forEach items="${parties}" var="par">
		Nom: ${par.nom} ; Cr�ateur: ${par.joueur1}
		<br>
	</c:forEach>
	</p>
</body>
</html>