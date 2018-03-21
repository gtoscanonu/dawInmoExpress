processarInformacio();

var $data = $('#data');
var url = 'http://localhost:8080/dawInmoExpress/';


function processarInformacio() {
	$.ajax({
		url: 'http://localhost:8080/dawInmoExpress/',
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
		$data.append($item);

	}
}

function processarDada(dada) {
	var $item = $('<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item"><div class="card h-100"><a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a><div class="card-body"><h4 class="card-title"><a href="mostrarInmoble.html?id='+dada.idVivienda+'">ID : '+ dada.idVivienda +'</a></h4><p class="card-text">Ubicació : '+ dada.ubicacion +'</p><p class="card-text">Preu : '+ dada.precio +'</p></div></div></div>');
	return $item;
}