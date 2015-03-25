var pos;
var alreadyCheck = false;
var mot = new Array ();
var btn;
var lastL;

function selectCase(element) {
	// Document.getElementById(""+element).innerHTML="<img class=\"lettre\"
	// src=\"style/img/alphabet/_.jpg\"></img>"
	// document.getElementById(element).firstChild.src =
	// "style/img/alphabet/_.jpg";
	var img = document.getElementById(element).firstChild;
	var lienImgVide = "http://localhost:8080/Projet_S4/style/img/alphabet/-.jpg";
	var lienImgSelect = "http://localhost:8080/Projet_S4/style/img/alphabet/_.jpg";
	console.log(img.src + " et " + lienImgVide);
	if (!alreadyCheck) {
		if(img.src === lienImgVide){
			img.src = lienImgSelect;
			pos = element;
			alreadyCheck = true;
		}
		else if(img.src === lienImgSelect){
			img.src = lienImgVide;
			pos = element;
			alreadyCheck = false;
		}
		else{
		//Quand on retire la lettre du plateau
			console.log(img.src.charAt((img.src.length)-5));
			var let = img.src.charAt((img.src.length)-5);
			document.getElementById(let).style.visibility = "visible";
			img.src = lienImgSelect;
			pos = element;
			alreadyCheck = true;
		}
	} else {
		if (img.src === lienImgSelect) {
			img.src = lienImgVide;
			alreadyCheck = false;
			console.log("toto:");
			pos = null;
		} else {
			console.log("titi:");
			img.src = lienImgVide;
			alreadyCheck = true;
		}
	}
		
}

function putLetter(lettre) {
	btn = document.getElementById("word");
	if(alreadyCheck){
		console.log("lettre: " + lettre);
		mot.push(lettre);
		console.log("on a cliqué !");
		var lien = "http://localhost:8080/Projet_S4/style/img/alphabet/" + lettre
				+ ".jpg";
		if (pos !== null) {
			console.log("il nest pas null !");
			console.log("pos: " + pos);
			var elem = document.getElementById(pos).firstChild;
			var m = "";
			for(var i = 0; i< mot.length; i++){
				m+=mot[i];
			}
			btn.value = m;
			console.log("elem: " + elem);
			elem.src = lien;
			alreadyCheck = false;
			document.getElementById(lettre).style.visibility = "hidden";
		} else {
			console.log("nulllll");
		}
	}
	
	function validerMots (mot) {
		
	}
	function calculerPoints (mot) {
	}
}