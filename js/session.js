//Si al sessionstorage el rol és d'administrador
if(sessionStorage.getItem('rol')=='administrador'){
	//Eliminem les classes següents que evitaven l'aparició d'aquests elements de l'HTML
	$('li.admin').removeClass("novisible");
	$('a.vendre').removeClass("novisible");
	$('hr.vendre').removeClass("novisible");
	$('a.vendre').removeClass("oculta");
}
//Si al sessionstorage es troba guardat un email
if(sessionStorage.getItem('email')!=null){
	//Amaguem l'element de login i el de registre d'usuari
	$('li.altacompte').addClass("novisible");
	$('li.login').addClass("novisible");
	
	//Afegim nous elements
	//Boto d'usuari
	$('a.adduser').html('<i class="fa fa-user-circle"></i>&nbsp' + sessionStorage.getItem("email"));
	$('a.adduser').attr('href', 'user.html?id='+sessionStorage.getItem("idvendedor"));
	$('li.user').removeClass("novisible");
	
	//Boto dels inmobles d'aquell usuari
	$('a.addinmoble').attr('href', 'meusinmobles.html?id='+sessionStorage.getItem("idvendedor"));
	$('li.inmobles').removeClass("novisible");
	
	//Boto de logout
	$('li.logout').removeClass("novisible");
	$('#logout').attr('onclick', 'logout()');
	
	//fem apareixer el boto de publicar al index.html
	$('a.vendre').removeClass("novisible");
	
	//fem apareixer una nova opció al menu de serveis de index-html
	$('hr.vendre').removeClass("novisible");
	
	//Amaguem el text que apareixia que l'usuari s'havia de loguejar a la opció de publicar inmobles
	$('p.vendre').addClass("novisible");
}

function logout(){
	
	//Mostrem dialeg de confirmacio
    var opcion = confirm("Estàs segur que vols desconnectar la sessió?");
    if (opcion == true) { 
		//fem el logout
        $.ajax({
                url: 'http://localhost:8080/dawInmoExpress/logout/'+ sessionStorage.idvendedor +'?token='+sessionStorage.token, 
                method : 'get', 
				success : function(){
					//codigo de exito
					sessionStorage.clear();
					//redirigim la pàgina a index.html
					location.replace("index.html");
						
				},
				error : function(error){
					//codigo error
					sessionStorage.clear();
					location.replace("index.html");
				}
		});
	} else {
		alert("Has cancel·lat l'operació");
	}
		
}