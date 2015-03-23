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
	<div id="presentation">

		<h2>Bonjour ! C'est votre page d'accueil</h2>

		<p>Vous pouvez voir ici toutes les parties en cours</p>
		<p>Il est possible d'en creer une ou d'en rejoindre une</p>

		<!-- CREER PARTIE  -->


		<FORM method="POST" action="createPartie">
			<input type="hidden" value="${sessionScope.user.name}"
				name="nom_joueur1" /> <input type="text" value="Nom partie"
				name="nom" /> <input type="submit" value="Creer partie"
				name="creer" />
		</FORM>


		<!-- Rejoindre les parties ou l'utilisateur joue actuellement  -->
		<p class="sousTitre">
			Vos parties en cours:
			<c:forEach items="${partieEnCours}" var="part">
				Nom: ${part.nom} J1: ${part.user1.name} J2: ${part.user2.name}
				
				<c:if test="${empty part.user2}">aucun</c:if>

				<form method="POST" action="JouerPartie">
					<input type="submit" value="Jouer" name=${part.nom
					} />
				</form>

				<br>
			</c:forEach>
		</p>


		<!-- Rejoindre les parties ou il manque un joueur  -->
		
		<p class="sousTitre">
			Les parties disponibles :
			<c:forEach items="${available}" var="part">
				Nom: ${part.nom} J1: ${part.user1.name} J2: ${part.user2.name}
				<c:if test="${empty part.user2}">aucun</c:if>
				
				<form method="POST" action="RejoindrePartie">
				<input type="submit" value="Rejoindre" name="Rejoindre" />
				<input type="hidden" value="${part.nom}" name="partie" />
				<input type="hidden" value="${part.user1.name}" name="joueur1" />
				</form>
				<br>
			</c:forEach>
		</p>



		<%-- 	ANCIENNE METHODE
		<!-- REJOINDRE PARTIE  -->
		<p>Voici les parties que vous pouvez rejoindre</p>
		<p>
			<c:forEach items="" var="par">
		Nom: ${par.nom} ; Createur: ${par.joueur1.pseudo}
		
		 <!-- rejoindre une partie -->
				<form action="RejoindrePartie" method="post">
					<input type="submit" value="Rejoindre" name="Rejoindre" /> <input
						type="hidden" value="${par.nom}" name="partie" /> <input
						type="hidden" value="${par.joueur1.pseudo}" name="joueur1" />
				</form>
			</c:forEach>
		</p> --%>
	</div>
</div>