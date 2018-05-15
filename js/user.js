processarInformacio();

var $data = $('#data');
var url = 'http://localhost:8080/dawInmoExpress/';
var passInicial;

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
	//Obtenim les dades de l'usuari
	$.ajax({
		url: 'http://localhost:8080/dawInmoExpress/venedor/'+ getQueryVariable('id')+'?token='+sessionStorage.token,
		dataType: 'json',
		beforeSend: function(jqXHR) {
			jqXHR.overrideMimeType('application/json');
		},
		success: processarResposta,
	});
}

function processarResposta(resposta) {
	
	if(resposta==""){
		//codigo de exito
		$('#success').addClass("alert alert-success");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a> El token no és vàlid');
	}else{
		//Omplim els camps amb les dades de l'usuari
		$('h4.card-title').text('ID d\'usuari : ' + resposta.idVendedor);
		$('#email').attr('value',resposta.email);
		$('#password').attr('value',resposta.password);
		
		//Guardem el valor inicial del password
		passInicial=resposta.password;
		$('#name').attr('value',resposta.nombre);
		$('#tfmobil').attr('value',resposta.telefono);
	}
}

$('#modifyaccount').click(function(){
	
	//Si l'email, el password, el nom o el t.mobil estan buit, creem alerta
	if($('#email').val()== "" || $('#password').val()== "" || $('#name').val()== "" || $('#tfmobil').val()== ""){
		$('#success').addClass("alert alert-danger");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong> Introdueix les dades necessaries');
		return;
	}
	
	//Si l'email no acompleix el patró anterior, creem alerta
	expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if ( !expr.test($('#email').val()) ){
		$('#success').addClass("alert alert-danger");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong> La direcció de correu ' + $('#email').val() + ' es incorrecta.');
		return;
	}
	
	//Si la contrassenya no té un minim de 8 caracters, creem alerta
	if(($('#password').val()).length < 8){
		$('#success').addClass("alert alert-danger");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong> La contrassenya ha de ser d\'un minim de 8 caracters');
		return;
	}
	
	//Si el t.mobil no té un minim de 9 digits, creem alerta
	if(($('#tfmobil').val()).length < 9 || isNaN($('#tfmobil').val())){
		$('#success').addClass("alert alert-danger");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong> El telèfon ha de ser d\'un minim de 9 digits');
		return;
	}
		
		//Si ambdos passwords son iguals, no cal guardar el valor a la BD
		if(passInicial == $('#password').val()){
			var passFinal = $('#password').val();
		}else{
			//Si són diferents guardem el password a la BD
			var passFinal = $.md5($('#password').val());
		}
		
		//s'envien les dades
		var data = { idVendedor : sessionStorage.getItem("idvendedor"), email : $('#email').val(), password : passFinal, nombre : $('#name').val(), telefono : $('#tfmobil').val(), rol : sessionStorage.getItem("rol")};
        $.ajax({
                url : 'http://localhost:8080/dawInmoExpress/venedor/'+ getQueryVariable('id')+'/editar'+'?token='+sessionStorage.token,
                data : JSON.stringify(data), 
                method : 'put',
                dataType : 'json',
				headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json' 
				},
                success : function(response){

					if((response.missatge== "L'usuari no s'ha modificat per seguretat")||(response.missatge== "Token no valid")){
						$('#success').addClass("alert alert-success");
						$('#success').html('<a class="close" data-dismiss="alert"> × </a>'+response.missatge);
						//Esperem 3 s i refresquem la pàgina					
						setTimeout(function(){location.reload()},3000);
					}else{
						//codigo de exito
						$('#success').addClass("alert alert-success");
						$('#success').html('<a class="close" data-dismiss="alert"> × </a>'+response.missatge);
					
						//Guardem noves dades al sessionStorage
						sessionStorage.email = $('#email').val();
						sessionStorage.nombre = $('#name').val();
						sessionStorage.telefono = $('#tfmobil').val();
						
						//Esperem 3 s i refresquem la pàgina					
						setTimeout(function(){location.reload()},3000);
					}

                },
                error: function(error){
                    //codigo error
					$('#success').addClass("alert alert-danger");
					$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR EN LA OPERACIÓ! </ Strong>NO S\'ha modificat l\'usuari');

                }
        });
});
	
$('#deleteaccount').click(function(){
	
	//Mostrem dialeg de confirmacio
    var opcion = confirm("Estàs segur que vols esborrar el teu compte d'usuari?");
    if (opcion == true) {
		//Peticio d'eliminació d'un usuari
        $.ajax({
                url : 'http://localhost:8080/dawInmoExpress/administrador/'+ getQueryVariable('id')+'?token='+sessionStorage.token, 
                method : 'delete', 
				dataType : 'json',
				headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json' 
				},
                success : function(response){
                       if (response.missatge == "S'ha esborrat l'usuari amb ID : "+getQueryVariable('id')){
						   //codigo de exito
						   $('#success').addClass("alert alert-success");
							$('#success').html('<a class="close" data-dismiss="alert"> × </a>'+response.missatge);

						   //S'esborra el contingut de la sessió de l'usuari
						   sessionStorage.clear();
						   
						   //Redireccionem a la pàgina principal despres de 3s
						   setTimeout(function(){location.replace("index.html")},3000);
					   }else{
						   //codigo error
							$('#success').addClass("alert alert-info");
							$('#success').html('<a class="close" data-dismiss="alert"> × </a>'+response.missatge);
							//Esperem 3 s i refresquem la pàgina					
							setTimeout(function(){location.reload()},3000);
					   }
                },
                error: function(error){
                       //codigo error
					   $('#success').addClass("alert alert-danger");
					   $('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR EN LA OPERACIÓ! </ Strong>NO s\'ha esborrat l\'usuari');
                }
        });
	} else {
		$('#success').addClass("alert alert-info");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a>Has cancel·lat l\'operació');
	}
});
	