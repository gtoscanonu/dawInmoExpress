processarInformacio();

var $data = $('#data');
var url = 'http://localhost:8080/dawInmoExpress/';

<!-- SCRIPT CONTROL DE COOKIES -->
function controlcookies() {
         // si variable no existe se crea (al clicar en Aceptar)
    localStorage.controlcookie = (localStorage.controlcookie || 0);
 
    localStorage.controlcookie++; // incrementamos cuenta de la cookie
    cookie1.style.display='none'; // Esconde la política de cookies
}

if (localStorage.controlcookie>0){ 
	document.getElementById('cookie1').style.bottom = '-50px';
}


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
  
	for (var i = dades.length-1; i > dades.length-5; i--) {

		var $item = processarDada(dades[i]);
		$data.append($item);

	}
}

function processarDada(dada) {
	var $item = $('<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item"><div class="card h-100"><a href="mostrarInmoble.html?id='+dada.idVivienda+'"><img class="card-img-top" src="'+dada.imagen+'" alt="Imatge de l\inmoble : '+dada.idVivienda+'"></a><div class="card-body"><h4 class="card-title"><a href="mostrarInmoble.html?id='+dada.idVivienda+'">ID : '+ dada.idVivienda +'</a></h4><p class="card-text">Anunci : '+ dada.anuncio +'</p><p class="card-text">Tipus : '+ dada.tipo +'</p><p class="card-text">Ubicació : '+ dada.ubicacion +'</p><p class="card-text">Preu : '+ dada.precio +'€</p></div></div></div>');
	return $item;
}