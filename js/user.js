processarInformacio();

var $data = $('#data');
var url = 'http://localhost:8080/dawInmoExpress/';
var passInicial;

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
		url: 'http://localhost:8080/dawInmoExpress/venedor/'+ getQueryVariable('id'),
		dataType: 'json',
		beforeSend: function(jqXHR) {
			jqXHR.overrideMimeType('application/json');
		},
		success: processarResposta,
	});
}

function processarResposta(resposta) {
	
	$('h4.card-title').text('ID d\'usuari : ' + resposta.idVendedor);
	$('#email').attr('value',resposta.email);
	$('#password').attr('value',resposta.password);
	//Guardem el valor inicial del password
	passInicial=resposta.password;
	$('#name').attr('value',resposta.nombre);
	$('#tfmobil').attr('value',resposta.telefono);

}

$('#modifyaccount').click(function(){

	if($('#email').val()== "" || $('#password').val()== "" || $('#name').val()== "" || $('#tfmobil').val()== ""){
		$('#success').addClass("alert alert-danger");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong> Introdueix les dades necessaries');
		return;
	}
	
	expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if ( !expr.test($('#email').val()) ){
		$('#success').addClass("alert alert-danger");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong> La direcció de correu ' + $('#email').val() + ' es incorrecta.');
		return;
	}
	if(($('#password').val()).length < 8){
		$('#success').addClass("alert alert-danger");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong> La contrassenya ha de ser d\'un minim de 8 caracters');
		return;
	}
	if(($('#tfmobil').val()).length < 9 || isNaN($('#tfmobil').val())){
		$('#success').addClass("alert alert-danger");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong> El telèfon ha de ser d\'un minim de 9 digits');
		return;
	}
		
	
		if(passInicial == $('#password').val()){
			var passFinal = $('#password').val();
		}else{
			var passFinal = $.md5($('#password').val());
		}
		
		var data = { idVendedor : sessionStorage.getItem("idvendedor"), email : $('#email').val(), password : passFinal, nombre : $('#name').val(), telefono : $('#tfmobil').val(), rol : sessionStorage.getItem("rol")};
        $.ajax({
                url : 'http://localhost:8080/dawInmoExpress/venedor/'+ sessionStorage.getItem("idvendedor")+'/editar',
                data : JSON.stringify(data), 
                method : 'put',
                dataType : 'json',
				headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json' 
				},
                success : function(response){
                       //codigo de exito
					   $('#success').addClass("alert alert-success");
						$('#success').html('<a class="close" data-dismiss="alert"> × </a>'+response.missatge);
						   sessionStorage.email = $('#email').val();
						   sessionStorage.nombre = $('#name').val();
						   sessionStorage.telefono = $('#tfmobil').val();
					   setTimeout(function(){location.reload()},3000);

                },
                error: function(error){
                       //codigo error
					   $('#success').addClass("alert alert-danger");
					   $('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR EN LA OPERACIÓ! </ Strong>NO S\'ha modificat l\'usuari');

                }
        });
});
	
$('#deleteaccount').click(function(){
    var opcion = confirm("Estàs segur que vols esborrar el teu compte d'usuari?");
    if (opcion == true) {
        $.ajax({
                url : 'http://localhost:8080/dawInmoExpress/administrador/'+ sessionStorage.getItem("idvendedor"), 
                method : 'delete', 

                success : function(response){
                       //codigo de exito
					   $('#success').addClass("alert alert-success");
						$('#success').html('<a class="close" data-dismiss="alert"> × </a>S\'ha esborrat l\'usuari amb ID : '+sessionStorage.getItem("idvendedor"));
					   //alert("S'ha esborrat l'usuari");
					   //S'esborra el contingut de la sessió de l'usuari
					   sessionStorage.clear();
					   //Redireccionem a la pàgina principal
					   setTimeout(function(){location.replace("index.html")},3000);
                },
                error: function(error){
                       //codigo error
					   $('#success').addClass("alert alert-danger");
					   $('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR EN LA OPERACIÓ! </ Strong>NO s\'ha esborrat l\'usuari');
					   //alert("ERROR EN LA OPERACIÓ: NO S'ha esborrat l'usuari");
                }
        });
	} else {
		$('#success').addClass("alert alert-info");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a>Has cancel·lat l\'operació');
	    //alert("Has cancel·lat l'operació");
	}
});
	