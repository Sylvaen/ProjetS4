<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SCR4BBLE</title>
</head>
<body>


	<p class="titre">${nom}</p>
	<p class="plateau">${plateau}</p>

	<p>Vos lettres</p>
	<c:choose>
		<c:when test="${partie.user1.id == sessionScope.user.id}">
				<p>${lettresj1_str}</p>
		</c:when>
		<c:otherwise>
				<p>${lettresj2_str}</p>
		</c:otherwise>
	</c:choose>
	<p>Vous etes le joueur</p>
	<p>${user.name}</p>


</body>
</html>