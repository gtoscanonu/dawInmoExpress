processarInformacio();

var $data = $('#data');
var url = 'http://localhost:8080/dawInmoExpress/';


function processarInformacio() {
	$.ajax({
		//Obtenim les dades dels inmobles d'intercanvi
		url: 'http://localhost:8080/dawInmoExpress/inmoble/intercanvi',
		dataType: 'json',
		beforeSend: function(jqXHR) {
			jqXHR.overrideMimeType('application/json');
		},
		success: processarResposta,
	});
}

function processarResposta(resposta) {
	
	var dades = resposta;  
  
	for (var i = 0; i < dades.length; i++) {

		var $item = processarDada(dades[i]);
		//Adjuntem l'element al camp data de l'html
		$data.append($item);

	}
}

function processarDada(dada) {
	//Creem cadascun dels elements que es ficaran al camp data del document HTML
	var $item = $('<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item"><div class="card h-100"><a href="mostrarInmoble.html?id='
	+dada.idVivienda+'"><img class="card-img-top" src="'+dada.imagen+'" alt="Imatge de l\'inmoble amb ID : '+dada.idVivienda
	+'"></a><div class="card-body"><h4 class="card-title"><a href="mostrarInmoble.html?id='+dada.idVivienda+'">ID : '+ dada.idVivienda 
	+'</a></h4><p class="card-text"><strong>Tipus :</strong> '+ dada.tipo +'</p><p class="card-text"><strong>Ubicació :</strong> '
	+ dada.ubicacion +'</p><p class="card-text"><strong>Preu :</strong> '+ dada.precio +'€</p></div></div></div>');
	return $item;
}