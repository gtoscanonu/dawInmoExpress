if(sessionStorage.getItem('rol')=='administrador'){
	$('li.estadistiques').removeClass("novisible");
	$('li.admin').removeClass("novisible");
	$('a.vendre').removeClass("novisible");
	$('a.vendre').removeClass("oculta");
}
if(sessionStorage.getItem('email')!=null){
	$('li.altacompte').addClass("novisible");
	$('li.login').addClass("novisible");
	$('a.adduser').html('<i class="fa fa-user-circle"></i>&nbsp' + sessionStorage.getItem("email"));
	$('a.adduser').attr('href', 'user.html?id='+sessionStorage.getItem("idvendedor"));
	$('li.user').removeClass("novisible");
	$('a.addinmoble').attr('href', 'meusinmobles.html?id='+sessionStorage.getItem("idvendedor"));
	$('li.inmobles').removeClass("novisible");
	$('li.logout').removeClass("novisible");
	$('a.vendre').removeClass("novisible");
	$('p.vendre').addClass("novisible");
}

$('#logout').click(function(){

    var opcion = confirm("Estàs segur que vols desconnectar la sessió?");
    if (opcion == true) {   
		sessionStorage.clear();
		location.replace("index.html");
	}
	
});