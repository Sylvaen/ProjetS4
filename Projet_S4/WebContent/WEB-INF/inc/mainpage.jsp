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

		<p>
			Vos parties en cours:
			<c:forEach items="${listPartieEnCours}" var="part">
				Nom: ${part.name} J1: ${part.User1.name} J2: ${part.User2.name}
			</c:forEach>
		</p>
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
		</p>
	</div>
</div>