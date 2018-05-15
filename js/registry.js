//Registrar un usuari
function registrar(){
	//Si l'email, el password, el nom o el t.mobil estan buit, creem alerta
	if($('#email').val()== "" || $('#password').val()== "" || $('#name').val()== "" || $('#tfmobil').val()== ""){
		expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		//Si l'email no acompleix el patró anterior, creem alerta
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
		//Si falta qualsevol dels altres camps, creem alerta per a que l'usuari completi les dades
		$('#success').addClass("alert alert-danger");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong> Introdueix les dades necessaries');
		return;
	
	//Sino, s'envien les dades
	}else{
        var data = { email : $('#email').val(), password : $.md5($('#password').val()), nombre : $('#name').val(), telefono : $('#tfmobil').val() };
        $.ajax({
                url : 'http://localhost:8080/dawInmoExpress/registrar-se',
                data : JSON.stringify(data), 
                method : 'post', 
                dataType : 'json',
				headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json' 
				},
                success : function(response){
                       //codigo de exito
					   $('#success').addClass("alert alert-success");
					   $('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong>'+response.missatge+'</Strong>&emsp;Ja pots fer login'); 
                },
                error: function(error){
                       //codigo error
					   $('#success').addClass("alert alert-danger");
					   $('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR EN LA OPERACIÓ! </ Strong> NO s\'ha inserit l\'usuari.'); 
                }
        });
	}
}
