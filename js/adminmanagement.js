$(document).ready(mostraDades);
var compt = 0;

/**
*   realitza la peticio al servidor extern per aconseguir les dades i 
*/
function mostraDades(){

	$.ajax({
		url:'http://localhost:8080/dawInmoExpress/administrador/all',
		success:ompleDades,
		dataType:"json",
		error:informaError,
	});

}


/**
*   activa el procediment per mostrar-les per pantalla un cop rebudes
*/

function ompleDades(dades,status,jqXHR){

	var resultats = new Array();
	
		for(var i=0; i<dades.length; i++){
			var aux=dades[i];
			resultats.push(aux);
		}

	
	mostraTaula(resultats);

}


/**
*  mostra al formulari la taula d'usuaris
*  @param {object} resultat - objecte amb les dades que cal mostrar
*/
function mostraTaula(resultats){
	var $taula = $('#taula');
	if($taula!=null){
		$taula.remove();
	}

	$taula=$('<table id="taula"></table>');
	
	var titols={idVendedor:"ID_usuari",nombre:"Nom",email:"Email",telefono:"Telèfon",rol:"Rol"};
	
	afegirFila($taula,titols);
	
	for(var i=0;i<resultats.length;i++){
		afegirFila($taula,resultats[i]);
	}
	

	$("#data").append($taula);

}

/**
*   afegeix a la taula del DOM una fila que nomes conte el nom passat com a parametre
*   @param {object} taula - taula
*   @param {string} nom - nom a afegir a la taula
*/
function afegirFila($taula,dades){
	var $fila=$novaFila();
	

	if(compt==0){
		novaColumna($fila,dades.idVendedor);
		novaColumna($fila,dades.nombre);
		novaColumna($fila,dades.email);
		novaColumna($fila,dades.telefono);
		novaColumna($fila,dades.rol);
		novaColumna($fila,"Acció");
		compt++;
	}else{
		novaCasella($fila,dades.idVendedor);
		novaCasella($fila,dades.nombre);
		novaCasella($fila,dades.email);
		novaCasella($fila,dades.telefono);
		novaCasella($fila,dades.rol);
		$fila.append($('<button type="button" id="deleteuser" class="btn btn-danger btn-sm" onclick=esborrar('+ dades.idVendedor +')>Eliminar</button>'));
	}
	
	$taula.append($fila);
	
}


/**
*   afegeix a una fila del DOM una casella que conte el text passat com a parametre
*   @param {object} fila - fila del dom on s'afegeix la casella
*   @param {string} text - contingut de la nova casella
*/
function novaCasella($fila,text){

	$fila.append($('<td>'+text+'</td>'));
}

function novaColumna($fila,text){

	$fila.append($('<th>'+text+'</th>'));
}
/**
*   crea una nova fila que es podra afegir a una taula
*	@return {object} nova fila
*/
function $novaFila(){
	return $('<tr></tr>');
		
}

/**
*   Informa d'un error d'acces al servidor que te les dades externes
*/
function informaError(){
	alert("Error accedint al servidor");
}

function esborrar(id){

	var opcion = confirm("Estàs segur que vols esborrar el registre amb ID: "+id+" ?");
    if (opcion == true) {
        $.ajax({
                url : 'http://localhost:8080/dawInmoExpress/administrador/'+ id, 
                method : 'delete', 

                success : function(response){
                       //codigo de exito
					   $('#success').addClass("alert alert-success");
						$('#success').html('<a class="close" data-dismiss="alert"> × </a>S\'ha esborrat l\'usuari amb ID : '+id);
					   mostraDades();
                },
                error: function(error){
                       //codigo error
					   $('#success').addClass("alert alert-danger");
					   $('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR EN LA OPERACIÓ! </ Strong>NO s\'ha esborrat l\'usuari');
					   //alert("ERROR EN LA OPERACIÓ: NO s'ha esborrat l'usuari");
                }
        });
	} else {
		$('#success').addClass("alert alert-info");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR EN LA OPERACIÓ! </ Strong>Has cancel·lat l\'operació');
	    //alert("Has cancel·lat l'operació");
	}
	
}


