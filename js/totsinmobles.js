//Primera funció que s'executa al tenir tota la pagina html carregada
$(document).ready(activaPagina);

// variables que referencien els elements del formulari	
	
var $pmin, $pmax, $nHab, $comboUbicacio, $comboTipusInmoble, $tipusAnunci, $data;

/**
*   inicialitza les referencies a les dades del formulari;
*   omple el combo de ubicacio i tipus d'inmoble amb dades de font externa;
*   vincula els canvis als combos a l'accio de mostrar les dades
*/
function activaPagina(){
	
	$nHab=$("#nHab");
	$comboUbicacio=$("#ubicacio");
	$comboTipusInmoble=$("#tipus");
	$comboTipusAnunci=$("#anunci");
	$data=$("#data");
	
	$("#sli").slider({
		id:"sli",
		min:0,
		max:250000,
		step:100,
			precision:0,
		orientation:'horizontal',
		value:[0,250000],
		range:true,
		selection:'after',
		tooltip: 'always',
		tooltip_position:'bottom',
		enabled:true,
		formatter: function formatter(value) {
			if (Array.isArray(value)) {
				return value[0] + " : " + value[1];
			} else {
			  return value;
			}
		},
	});
		
	$pmin = 0;
	$pmax = 250000;
	
	$nHab.val(1);
	

	$.ajax({
		url: 'http://localhost:8080/dawInmoExpress/',
		dataType: 'json',
		success: function(dades) {ompleCombos(dades); processarResposta(dades);},
	});
	
	/**
*   Events de la pàgina
*/
$("#sli").on("slide", function(slideEvt) {
	//Si es mou la barra de preus, assignem el valor dels texts de sota
	$("#sli1SliderVal").text(slideEvt.value[0]+'€');
	$("#sli2SliderVal").text(slideEvt.value[1]+'€');
	$pmin = slideEvt.value[0];
	$pmax = slideEvt.value[1];
});
//Al variar alguna cosa, mostrem les noves dades
$("#sli").on('change',mostraDades);

$("#nHab").on('change',mostraDades);
$("#ubicacio").on('change',mostraDades);
$("#tipus").on('change',mostraDades);
$("#anunci").on('change',mostraDades);
}


/**
*   omple els combo a partir de les dades obtingudes del servidor extern
*/
function ompleCombos(dades,status,jqXHR){
	
	//Creem 2 array buits
	var ubicacions=[];
	var tipusInmobles=[];
	
	for (var i=0;i<dades.length;i++)
	{
			//Obtenim les dades que volem consultar de dins la info obtinguda per la peticio ajax
			var ubicacio=dades[i].ubicacion;
			var tipus= dades[i].tipo;
			
			var a = ubicacions.indexOf(ubicacio);
			//Si el valor no es troba ja dins l'array, l'afegim
			if(a==-1){
				//L'afegim a l'array
				ubicacions.push(ubicacio);
			}
			var b = tipusInmobles.indexOf(tipus);
			//Si el valor no es troba ja dins l'array, l'afegim
			if(b==-1){
				//L'afegim a l'array
				tipusInmobles.push(tipus);
			}	
	}
	
	for (var j=0;j<ubicacions.length;j++)
	{
		//Afegim les diferents opcions del camp ubicacio al combo html
		$comboUbicacio.append($('<option>').attr('value',ubicacions[j]).html(ubicacions[j]));
	}
	for (var h=0;h<tipusInmobles.length;h++)
	{
		//Afegim les diferents opcions del camp tipus al combo html
		$comboTipusInmoble.append($('<option>').attr('value',tipusInmobles[h]).html(tipusInmobles[h]));
	}
}

function mostraDades(){
	
	//Assignem quins camps de l'html llegirà la peticio ajax
	$nHab=$("#nHab");
	$comboUbicacio=$("#ubicacio");
	$comboTipusInmoble=$("#tipus");
	$comboTipusAnunci=$("#anunci");
	$data=$("#data");
	
	//eliminem el contigut de data
	$("#data").empty();
	
	$.ajax({
		url: 'http://localhost:8080/dawInmoExpress/inmoble/filtre?pMin=' + $pmin +'&pMax=' + $pmax + '&nHab=' + $nHab.val() + '&ubicacion=' + $comboUbicacio.val() + '&tipo=' + $comboTipusInmoble.val() + '&anuncio=' + $comboTipusAnunci.val(),
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
		//eliminem el contigut de data previ
		$("#data").empty();
		//Si no hi han dades d'inmoble
		$data.append('<div class="col-lg-3"></div><div class="col-lg-6 portfolio-item"><br/><p>No existeixen inmobles amb aquestes característiques.</p><p>Prova uns altres paràmetres que s\'ajustin amb el que desitges</p></div><div class="col-lg-3"></div>');
	}else{
		//eliminem el contigut de data previ
		$("#data").empty();
		
		//Omplim amb les dades obtingudes de la peticio
		for (var i = 0; i < dades.length; i++) {

			var $item = processarDada(dades[i]);
			//Adjuntem l'element al camp data de l'html
			$data.append($item);

		}
	}
}

function processarDada(dada) {
	
	//Creem cadascun dels elements que es ficaran al camp data del document HTML
	var $item = $('<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item"><div class="card h-100"><a href="mostrarInmoble.html?id='+
	dada.idVivienda+'"><img class="card-img-top" src="'+dada.imagen+'" alt="Imatge de l\inmoble : '+dada.idVivienda
	+'"></a><div class="card-body"><h4 class="card-title"><a href="mostrarInmoble.html?id='+dada.idVivienda+'">ID : '
	+ dada.idVivienda +'</a></h4><p class="card-text"><strong>Anunci :</strong> '+ dada.anuncio +'</p><p class="card-text"><strong>Tipus :</strong> '
	+ dada.tipo +'</p><p class="card-text"><strong>Ubicació</strong> : '+ dada.ubicacion +'</p><p class="card-text"><strong>Preu :</strong> '
	+ dada.precio +'€</p></div></div></div>');
	
	return $item;
}

