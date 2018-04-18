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
		url: 'http://localhost:8080/dawInmoExpress/venedor/'+ getQueryVariable('id') + '/inmobles/',
		dataType: 'json',
		beforeSend: function(jqXHR) {
			jqXHR.overrideMimeType('application/json');
		},
		success: processarResposta,
	});
}


function processarResposta(resposta) {
	
	var dades = resposta;  
	
	if (dades.length==0){
		$data.append('<div class="col-lg-3"></div><div class="col-lg-6 portfolio-item text-center"><br/><p>No tens cap inmoble registrat al sistema.</p></div><div class="col-lg-3"></div>');
	}else{
		for (var i = 0; i < dades.length; i++) {

			var $item = processarDada(dades[i]);
			$data.append($item);

		}
	}
}

function processarDada(resposta) {
	var $item = $('<div class="row"><div class="col-md-7">'+
				'<a href="mostrarInmoble.html?id='+resposta.idVivienda+'"><img class="img-fluid rounded mb-3 mb-md-0" id="imagen'+resposta.idVivienda+'" src="'+resposta.imagen+'" alt="imatge de l\'inmoble amb ID : '+resposta.idVivienda+'"></a>'+
				'</div><div class="col-md-3"><h3>ID : '+resposta.idVivienda+'</h3>'+
				'<form action="" name="userinmobles" id="userInmoblesForm" validate>'+
				'Anunci : <select id="anunci'+resposta.idVivienda+'"><option selected="'+resposta.anuncio+'">'+resposta.anuncio+'</option><option>venda</option><option>lloguer</option></select><br/>'+
				'Tipus : <input type="text" class="form-control" placeholder="Tipus d\'inmoble: Casa, pis, local..." id="tipus'+resposta.idVivienda+'" value="'+resposta.tipo+'" required data-validation-required-message="Si us plau introdueix el tipus d\'inmoble.">'+
				'<p class="help-block text-danger"></p>'+
				'Ubicació : <input type="text" class="form-control" placeholder="Ubicació" id="ubicacio'+resposta.idVivienda+'" value="'+resposta.ubicacion+'" required data-validation-required-message="Si us plau introdueix la ubicació.">'+
				'<p class="help-block text-danger"></p>'+
				'Superfície (m2) : <input type="number" class="form-control" placeholder="Superficie en m2" id="superficie'+resposta.idVivienda+'" value="'+resposta.superficie+'" required data-validation-required-message="Si us plau insereix la superficie.">'+
				'<p class="help-block text-danger"></p>'+
				'Preu (€): <input type="number" class="form-control" placeholder="Preu en €" id="preu'+resposta.idVivienda+'" value="'+resposta.precio+'" required data-validation-required-message="Si us plau insereix el preu.">'+
				'<p class="help-block text-danger"></p>'+
				'</div><div class="col-md-2" style="padding-top:68px">'+
				'Nombre habitacions : <input type="number" class="form-control" placeholder="Nombre d\'habitacions" id="habitacions'+resposta.idVivienda+'" value="'+resposta.numHabitaciones+'" required data-validation-required-message="Si us plau insereix el num Habitacions.">'+
				'<p class="help-block text-danger"></p>'+
				'Nombre de Banys : <input type="number" class="form-control" placeholder="Nombre de Banys" id="banys'+resposta.idVivienda+'" value="'+resposta.numBanios+'" required data-validation-required-message="Si us plau insereix el num Banys.">'+
				'<p class="help-block text-danger"></p>'+
				'Extres : <input type="text" class="form-control" placeholder="Extres" id="extres'+resposta.idVivienda+'" value="'+resposta.extras+'" required data-validation-required-message="Si us plau insereix els extres.">'+
				'<p class="help-block text-danger"></p>'+
				'Descripció : <input type="text" class="form-control" placeholder="Descripció" id="descripcio'+resposta.idVivienda+'" value="'+resposta.descripcion+'" required data-validation-required-message="Si us plau insereix la descripció.">'+
				'<p class="help-block text-danger"></p>'+
				'<br/>'+
				'</div><div class="col-md-7"></div><div class="form-group col-md-5"><div id="success' + resposta.idVivienda+ '"></div>'+
				'<button type="submit" id="modifyinmoble'+resposta.idVivienda+'" class="btn btn-warning" onclick=modificar('+resposta.idVivienda+')>Modificar</button>&emsp;'+
				'<button type="button" class="btn btn-danger" id="deleteinmoble'+resposta.idVivienda+'" class="btn btn-link" onclick=esborrar('+resposta.idVivienda+')>Esborrar Inmoble</button>'+
				'</div></form>'+
				'</div></div><div class="col-md-12"><br/><hr class="star-primary"><br/></div>');
	return $item;
}

function modificar(id){
	if( $('#anunci'+id).val() == "" || $('#ubicacio'+id).val()=="" || $('#superficie'+id).val() == "" || $('#preu'+id).val() == "" || $('#habitacions'+id).val() == "" || $('#banys'+id).val() == ""){
		$('#success'+id).addClass("alert alert-danger");
		$('#success'+id).html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong>Completa les dades de l\'inmoble si us plau');	
	}else{
        var data = { idVivienda : id, tipo : $('#tipus'+id).val(), anuncio : $('#anunci'+id).val(), ubicacion : $('#ubicacio'+id).val(), superficie : $('#superficie'+id).val(), precio : $('#preu'+id).val(), numHabitaciones : $('#habitacions'+id).val(), numBanios : $('#banys'+id).val(), extras : $('#extres'+id).val(), descripcion : $('#descripcio'+id).val(), imagen : $('#imagen'+id).attr('src') };
        $.ajax({
                url : 'http://localhost:8080/dawInmoExpress/venedor/' + sessionStorage.getItem("idvendedor")+'/inmoble/'+id+'/editar',
                data : JSON.stringify(data), 
                method : 'put',
				//enctype : 'multipart/form-data',
                dataType : 'json',
				headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json' 
				},
                success : function(response){
                       //codigo de exito
					   $('#success'+id).addClass("alert alert-success");
						$('#success'+id).html('<a class="close" data-dismiss="alert"> × </a>'+response.missatge);
					   setTimeout(function(){location.reload()},3000);

                },
                error: function(error){
                       //codigo error
					   $('#success'+id).addClass("alert alert-warning");
						$('#success'+id).html('<a class="close" data-dismiss="alert"> × </a>ERROR: NO S\'ha modificat l\'inmoble');

                }
        });
	}
}

function esborrar(id){

	var opcion = confirm("Estàs segur que vols esborrar l'inmoble amb ID: "+id+" ?");
    if (opcion == true) {
        $.ajax({
                url : 'http://localhost:8080/dawInmoExpress/venedor/'+sessionStorage.getItem("idvendedor")+'/inmoble/'+id, 
                method : 'delete', 

                success : function(response){
                       //codigo de exito
					   $('#success'+id).addClass("alert alert-success");
						$('#success'+id).html('<a class="close" data-dismiss="alert"> × </a>S\'ha esborrat l\'inmoble amb ID : '+id);
					   setTimeout(function(){location.reload()},3000);
                },
                error: function(error){
                       //codigo error
					   $('#success'+id).addClass("alert alert-danger");
					   $('#success'+id).html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR EN LA OPERACIÓ! </ Strong>NO s\'ha esborrat l\'inmoble');
                }
        });
	} else {
		$('#success'+id).addClass("alert alert-danger");
		$('#success'+id).html('<a class="close" data-dismiss="alert"> × </a>Has cancel·lat l\'operació');
	}
	
}