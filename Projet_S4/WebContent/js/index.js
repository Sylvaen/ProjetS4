function afficherInscription() {
	var element = document.getElementById("presentation");

	while (element.firstChild) {
		element.removeChild(element.firstChild);
	}

	element.innerHTML = "<div id='inscForm'>" + "<h3>Inscription :</h3>"
			+ "<FORM method='post' action='inscription'>"
			+ "<input type='text' value='pseudo' name='pseudo' /> <input"
			+ " type='text' value='mdp' name='mdp' /> <input type='submit'"
			+ " value='Inscription' />" + "</FORM>" + "</div>";
}