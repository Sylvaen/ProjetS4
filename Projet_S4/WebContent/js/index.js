function afficherInscription() {
	var element = document.getElementById("presentation");
	var erreur = '${form.erreurs[\'nom\']}';
	var erreur = '${form.erreurs[\'motdepasse\']}';
	var erreur = '${form.erreurs[\'emailSend\']}';
	var erreur = '${form.erreurs[\'confirmation\']}';
	var erreur = '${form.erreurs[\'email\']}';
	while (element.firstChild) {
		element.removeChild(element.firstChild);
	}

	element.innerHTML = "<div id='inscForm'>" + "<h3>Inscription :</h3>"
	+ "<FORM id='inscriptionForm' method='post' action='inscription'>"
	+ "<input type='text' value='pseudo' name='pseudo' required/> <br><input"
	+ " type='text' value='mdp' name='mdp' required/><br><input type='text' value='email' name='email' required/><br>"
	+ "<input type='submit' name='Valider' class='inscr'/>" + "</FORM>"
	+"</div>";
}

function afficherInscription2() {
	var element = document.getElementById("presentation");
	var erreur = '${form.erreurs[\'nom\']}';
	var erreur = '${form.erreurs[\'motdepasse\']}';
	var erreur = '${form.erreurs[\'emailSend\']}';
	var erreur = '${form.erreurs[\'confirmation\']}';
	var erreur = '${form.erreurs[\'email\']}';

	while (element.firstChild) {
		element.removeChild(element.firstChild);
	}

	element.innerHTML = "<div id='inscForm'>" + "<h3>Inscription :</h3>"
			+ "<FORM id='inscriptionForm' method='post' action='inscription'>"
			+ "<input type='text' value='pseudo' name='pseudo' required/> <br><input"
			+ " type='text' value='mdp' name='mdp' required/><br><input type='text' value='email' name='email' required/><br>"
			+ "<input type='submit' name='Valider' class='inscr'/>" + "</FORM>"
			+ "<p>" + erreur + "</p></div>";
}

function verifMail(champ) {
	var regex = /^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$/;
	if (!regex.test(champ.value)) {
		surligneEmail(champ, true);
		return false;
	} else {
		surligneEmail(champ, false);
		return true;
	}
}

function verifPseudo(champ) {
	if (champ.value.length < 5 || champ.value.length > 25) {

		surlignePseudo(champ, true);
		return false;
	} else {
		surlignePseudo(champ, false);
		return true;
	}
}

function surligneEmail(champ, erreur) {
	if (erreur) {
		document.getElementById("div_email").lastChild.textContent = "Veuillez rentrer une adresse mail valide";
		document.getElementById('div_email').style.color = '#FF0000';
		document.getElementById('div_email').style.fontWeight = 'bold';

	} else {
		document.getElementById("div_email").lastChild.textContent = "";
		document.getElementById('div_email').style.color = '#339900';
		document.getElementById('div_email').style.fontWeight = 'bold';
	}
};

function surlignePseudo(champ, erreur) {
	if (erreur) {
		document.getElementById("div_pseudo").lastChild.textContent = "Le pseudo doit comprendre en 5 et 25 caractères";
		document.getElementById('div_pseudo').style.color = '#FF0000';
		document.getElementById('div_pseudo').style.fontWeight = 'bold';

	} else {
		document.getElementById('div_pseudo').style.color = '#339900';
		document.getElementById('div_pseudo').style.fontWeight = 'bold';
		document.getElementById("div_pseudo").lastChild.textContent = "";

	}
};