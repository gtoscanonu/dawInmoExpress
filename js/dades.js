$(document).ready(activaPagina);

// variables que referenciran els elements del formulari	
	
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
	$("#sli1SliderVal").text(slideEvt.value[0]+'€');
	$("#sli2SliderVal").text(slideEvt.value[1]+'€');
	$pmin = slideEvt.value[0];
	$pmax = slideEvt.value[1];
});
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
	
	var ubicacions=[];
	var tipusInmobles=[];
	
	for (var i=0;i<dades.length;i++)
	{
			var ubicacio=dades[i].ubicacion;
			var tipus= dades[i].tipo;
			
			var a = ubicacions.indexOf(ubicacio);
			if(a==-1){
				ubicacions.push(ubicacio);
			}
			var b = tipusInmobles.indexOf(tipus);
			if(b==-1){
				tipusInmobles.push(tipus);
			}	
	}
	
	for (var j=0;j<ubicacions.length;j++)
	{
		
		$comboUbicacio.append($('<option>').attr('value',ubicacions[j]).html(ubicacions[j]));
	}
	for (var h=0;h<tipusInmobles.length;h++)
	{
		
		$comboTipusInmoble.append($('<option>').attr('value',tipusInmobles[h]).html(tipusInmobles[h]));
	}
}

function mostraDades(){
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
		//eliminem el contigut de data
		$("#data").empty();
		$data.append('<div class="col-lg-3"></div><div class="col-lg-6 portfolio-item"><br/><p>No existeixen inmobles amb aquestes característiques.</p><p>Prova uns altres paràmetres que s\'ajustin amb el que desitges</p></div><div class="col-lg-3"></div>');
	}else{
		//eliminem el contigut de data
		$("#data").empty();
		for (var i = 0; i < dades.length; i++) {

			var $item = processarDada(dades[i]);
			$data.append($item);

		}
	}
}

function processarDada(dada) {
	var $item = $('<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item"><div class="card h-100"><a href="mostrarInmoble.html?id='+dada.idVivienda+'"><img class="card-img-top" src="'+dada.imagen+'" alt="Imatge de l\'inmoble amb ID : '+dada.idVivienda+'"></a><div class="card-body"><h4 class="card-title"><a href="mostrarInmoble.html?id='+dada.idVivienda+'">ID : '+ dada.idVivienda +'</a></h4><p class="card-text">Tipus : '+ dada.tipo +'</p><p class="card-text">Ubicació : '+ dada.ubicacion +'</p><p class="card-text">Preu : '+ dada.precio +'€</p></div></div></div>');
	return $item;
}

