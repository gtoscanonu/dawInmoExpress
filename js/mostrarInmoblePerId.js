augmentarvisita();
obtenirvenedor();
processarInformacio();

var $data = $('#data');
var url = 'http://localhost:8080/dawInmoExpress/';

//Petició AJAX per augmentar el nombre de visites d'un inmoble +1
function augmentarvisita(){
	$.ajax({
		url: 'http://localhost:8080/dawInmoExpress/contador/'+ getQueryVariable('id') + '/set',
		dataType: 'json',
		beforeSend: function(jqXHR) {
			jqXHR.overrideMimeType('application/json');
		},

	});
}

//Obtenim les dades del venedor d'aquell determinat inmoble
function obtenirvenedor(){
	$.ajax({
		url: 'http://localhost:8080/dawInmoExpress/infovendedor/'+ getQueryVariable('id'),
		dataType: 'json',
		beforeSend: function(jqXHR) {
			jqXHR.overrideMimeType('application/json');
		},
		success: processarvenedor,
	});
	
}

//Assignem les dades del venedor de l'inmoble dins del document HTML
function processarvenedor(resposta) {
	$('p.nom').html('<strong>Nom : </strong>'+resposta.nombre);
	$('p.email').html('<strong>Email : </strong>'+resposta.email);
	$('p.telefon').html('<strong>Telèfon : </strong>'+resposta.telefono);
}

//Funció per obtenir un parametre de  la URL
function getQueryVariable(variable) {
   var query = window.location.search.substring(1);
   var vars = query.split("&");
   for (var i=0; i < vars.length; i++) {
       var pair = vars[i].split("=");
       if(pair[0] == variable) {
           return pair[1];
       }
   }
   return false;
}

function processarInformacio() {
	$.ajax({
		url: 'http://localhost:8080/dawInmoExpress/'+ getQueryVariable('id'),
		dataType: 'json',
		beforeSend: function(jqXHR) {
			jqXHR.overrideMimeType('application/json');
		},
		success: processarResposta,
	});
}

function processarResposta(resposta) {
	//Creem cadascun dels elements que es ficaran al camp data del document HTML
	
	var $item = $('<div class="col-md-6 mover"><a href="mostrarInmoble.html?id='+resposta.idVivienda
	+'"><img class="img-fluid rounded mb-3 mb-md-0" src="'+resposta.imagen
	+'" alt="Imatge de l\'inmoble amb ID : '+resposta.idVivienda
	+'"></a></div><!-- Map Column --><div class="col-lg-6 mb-2 mover"><!-- Embedded Google Map -->'
	+'<iframe width="100%" height="150px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"'
	+' src="https://maps.google.com/maps?hl=es&q='+resposta.ubicacion+'+(Inmoble)&ie=UTF8&t=&z=15&iwloc=B&output=embed"></iframe>'
	+'</div><div class="col-md-3 mover"><h3>ID : '+resposta.idVivienda+'</h3><p class="card-text"><strong>Anunci :</strong> '+ resposta.anuncio
	+'<p><strong>Tipus :</strong> '+resposta.tipo+'</p><p>'+'<strong>Ubicació :</strong> '+resposta.ubicacion+'</p><p><strong>Superfície :</strong> '
	+resposta.superficie+'</p><p><strong>Preu : </strong>'+resposta.precio+'</p><p class="text-info"><strong>Pàgina vista :</strong> '+resposta.contadorvisitas+' vegades</p></div>'
	
	+'<div class="col-md-3 mover" style="padding-top:40px"><p><strong>Nombre habitacions :</strong> '
	+resposta.numHabitaciones+'</p><p><strong>Nombre de Banys :</strong> '
	+resposta.numBanios+'</p><p><strong>Extres :</strong> '+resposta.extras+'</p><p><strong>Descripció :</strong> '+resposta.descripcion
	+'</p><!-- Trigger the modal with a button --><button type="button" class="btn btn-info btn-md" data-toggle="modal" data-target="#dadesVenedor">+ Info</button></div>'); 
  
	$data.append($item);

}



