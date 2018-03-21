processarInformacio();

var $data = $('#data');
var url = 'http://localhost:8080/dawInmoExpress/';


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
	
	var $item = $('<div class="row"><div class="col-md-7"><a href="#"><img class="img-fluid rounded mb-3 mb-md-0" src="http://placehold.it/700x300" alt=""></a></div><div class="col-md-3"><h3>ID : '+resposta.idVivienda+'</h3><p>Tipus : '+resposta.tipo+'</p><p>Ubicació : '+resposta.ubicacion+'</p><p>Superfície : '+resposta.superficie+'</p><p>Preu : '+resposta.precio+'</p><p>Nombre habitacions : '+resposta.numHabitaciones+'</p></div><div class="col-md-2" style="padding-top:40px"><p>Nombre de Banys : '+resposta.numBanios+'</p><p>Extres : '+resposta.extras+'</p><p>Descripció : '+resposta.descripcion+'</p><br/><a class="btn btn-primary" href="#">Reservar<span class="glyphicon glyphicon-chevron-right"></span></a></div></div>'); 
  
	$data.append($item);

}



