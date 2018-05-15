processarInformacio();

var $data = $('#data');
var url = 'http://localhost:8080/dawInmoExpress/';

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
		//obtenim les dades dels inmobles d'un id venedor determinat
		url: 'http://localhost:8080/dawInmoExpress/venedor/'+ getQueryVariable('id') + '/inmobles/?token='+sessionStorage.token,
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
		//Si no hi han dades d'inmoble
		$data.append('<div class="col-lg-3"></div><div class="col-lg-6 portfolio-item text-center"><br/><p>No tens cap inmoble registrat al sistema.</p></div><div class="col-lg-3"></div>');
	}else{
		for (var i = 0; i < dades.length; i++) {

			var $item = processarDada(dades[i]);
			//Adjuntem l'element al camp data de l'html
			$data.append($item);

		}
	}
}

function processarDada(resposta) {
	//Creem cadascun dels elements que es ficaran al camp data del document HTML
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
				'Extres : <textarea class="form-control" placeholder="Extres" id="extres'+resposta.idVivienda+'" required data-validation-required-message="Si us plau insereix els extres.">'+resposta.extras+'</textarea>'+
				'<p class="help-block text-danger"></p>'+
				'<p class="text-info"><strong>Inmoble visitat :</strong> '+resposta.contadorvisitas+' vegades</p>'+
				'</div><div class="col-md-2" style="padding-top:68px">'+
				'Nombre habitacions : <input type="number" class="form-control" placeholder="Nombre d\'habitacions" id="habitacions'+resposta.idVivienda+'" value="'+resposta.numHabitaciones+'" required data-validation-required-message="Si us plau insereix el num Habitacions.">'+
				'<p class="help-block text-danger"></p>'+
				'Nombre de Banys : <input type="number" class="form-control" placeholder="Nombre de Banys" id="banys'+resposta.idVivienda+'" value="'+resposta.numBanios+'" required data-validation-required-message="Si us plau insereix el num Banys.">'+
				'<p class="help-block text-danger"></p>'+
				'Preu (€): <input type="number" class="form-control" placeholder="Preu en €" id="preu'+resposta.idVivienda+'" value="'+resposta.precio+'" required data-validation-required-message="Si us plau insereix el preu.">'+
				'<p class="help-block text-danger"></p>'+
				'Descripció : <textarea class="form-control" placeholder="Descripció" id="descripcio'+resposta.idVivienda+'" required data-validation-required-message="Si us plau insereix la descripció.">'+resposta.descripcion+'</textarea>'+
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
	//Si el camp anunci, ubicacio, superficie, preu, habitacions o banys no tenen dades, salta error per que es completin les dades
	if( $('#anunci'+id).val() == "" || $('#ubicacio'+id).val()=="" || $('#superficie'+id).val() == "" || $('#preu'+id).val() == "" || $('#habitacions'+id).val() == "" || $('#banys'+id).val() == ""){
		$('#success'+id).addClass("alert alert-danger");
		$('#success'+id).html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong>Completa les dades de l\'inmoble si us plau');	
	
	//Sino, s'envien les dades
	}else{
        var data = { idVivienda : id, tipo : $('#tipus'+id).val(), anuncio : $('#anunci'+id).val(), ubicacion : $('#ubicacio'+id).val(), superficie : $('#superficie'+id).val(), precio : $('#preu'+id).val(), numHabitaciones : $('#habitacions'+id).val(), numBanios : $('#banys'+id).val(), extras : $('#extres'+id).val(), descripcion : $('#descripcio'+id).val(), imagen : $('#imagen'+id).attr('src') };
        $.ajax({
                url : 'http://localhost:8080/dawInmoExpress/venedor/' + sessionStorage.getItem("idvendedor")+'/inmoble/'+id+'/editar?token='+sessionStorage.token,
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
						//Esperem 3 s i refresquem la pàgina
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
	//Mostrem dialeg de confirmacio
	var opcion = confirm("Estàs segur que vols esborrar l'inmoble amb ID: "+id+" ?");
    if (opcion == true) {
        $.ajax({
			//Peticio d'eliminar un inmoble
            url : 'http://localhost:8080/dawInmoExpress/venedor/'+sessionStorage.getItem("idvendedor")+'/inmoble/'+id+'?token='+sessionStorage.token, 
            method : 'delete', 

            success : function(response){
                //codigo de exito
				$('#success'+id).addClass("alert alert-success");
				$('#success'+id).html('<a class="close" data-dismiss="alert"> × </a>S\'ha esborrat l\'inmoble amb ID : '+id);
				//Esperem 3 s i refresquem la pàgina
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