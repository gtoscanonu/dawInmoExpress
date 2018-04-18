$('#login').click(function(){
	
	if($('#email').val()== "" || $('#password').val()== ""){
		$('#success').addClass("alert alert-danger");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong> Introdueix les dades necessaries');
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
					   if(response==null){
						   $('#success').addClass("alert alert-info");
							$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong>');
							//alert( "No es troba l'usuari" );
							
					   }else{
						   sessionStorage.idvendedor = response.idVendedor;
						   sessionStorage.email = response.email;
						   sessionStorage.nombre = response.nombre;
						   sessionStorage.telefono = response.telefono;
						   sessionStorage.rol = response.rol;
						   $('#success').addClass("alert alert-success");
							$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong>'+response.email+'</ Strong> has fet login');
							setTimeout(function(){location.replace("index.html")},3000);
					   }
                },
                error: function(error){
                       //codigo error
					   $('#success').addClass("alert alert-danger");
					   $('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong>NO es troba l\'usuari.');
					   setTimeout(function(){location.reload()},2000);
                }
        });
	}
});

