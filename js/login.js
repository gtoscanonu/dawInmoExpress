//Al fer click al boto de login
$('#login').click(function(){
	
	//Si l'email i el password estan buits es llença l'error
	if($('#email').val()== "" || $('#password').val()== ""){
		$('#success').addClass("alert alert-danger");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong> Introdueix les dades necessaries');
	
	//Sino enviem les dades de login
	}else{

        $.ajax({
                url : 'http://localhost:8080/dawInmoExpress/login?email=' + $('#email').val() + '&password=' + $.md5($('#password').val()),
                method : 'get', 
                dataType : 'json',
				headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json' 
				},
                success : function(response){
                       //codigo de exito
					   if(response.missatge=="L'usuari no és valid"){
						   $('#success').addClass("alert alert-info");
							$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> '+ response.missatge +'</ Strong>');
							//Esperem 3 s i refresquem la pàgina
							setTimeout(function(){location.reload()},3000);
							
					   }else{
						   //Guardem les dades al sessionstorage del navegador
						   sessionStorage.idvendedor = response.idVendedor;
						   sessionStorage.email = response.email;
						   sessionStorage.nombre = response.nombre;
						   sessionStorage.telefono = response.telefono;
						   sessionStorage.rol = response.rol;
						   //var result = jQuery.parseJSON(response.entity);
						   sessionStorage.token = response.token;
						   
						   //Mostrem alerta que s'ha produit el login de l'usuari
						   $('#success').addClass("alert alert-success");
							$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong>'+response.email+'</ Strong> has fet login');
							
							//Esperem 3 s i redirigim la pàgina a index.html
							setTimeout(function(){location.replace("index.html")},3000);
					   }
                },
                error: function(error){
                       //codigo error
					   $('#success').addClass("alert alert-danger");
					   $('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR EN LA OPERACIÓ! </ Strong>');
					   //Esperem 2 s i refresquem la pàgina
					   setTimeout(function(){location.reload()},2000);
                }
        });
	}
});

