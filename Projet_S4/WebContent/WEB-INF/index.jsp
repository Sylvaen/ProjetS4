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

	<c:choose>
		<c:when test="${not empty sessionScope.pseudo}">
			<c:import url="inc/mainpage.jsp"/>
		</c:when>
		<c:otherwise>
			<c:import url="inc/welcome.jsp"></c:import>
		</c:otherwise>
	</c:choose>

</body>
</html>