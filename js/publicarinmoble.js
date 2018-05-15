//Al fer click es recuperen les dades del HTML per a crear un immoble
$('#createinmoble').click(function(){
	//Si el camp anunci, ubicacio, superficie, preu, habitacions o banys no tenen dades, salta error per que es completin les dades
	if( $('#anunci').val() == "" || $('#ubicacio').val()=="" || $('#superficie').val() == "" || $('#preu').val() == "" || $('#habitacions').val() == "" || $('#banys').val() == ""){
		$('#success').addClass("alert alert-danger");
		$('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR! </ Strong>Completa les dades de l\'inmoble si us plau');	
	
	//Sino, s'envien les dades
	}else{
        var data = { tipo : $('#tipus').val(), anuncio : $('#anunci').val(), ubicacion : $('#ubicacio').val(), superficie : $('#superficie').val(), precio : $('#preu').val(), numHabitaciones : $('#habitacions').val(), numBanios : $('#banys').val(), extras : $('#extres').val(), descripcion : $('#descripcio').val(), imagen : $('.thumb').attr('src') };
        $.ajax({
                url : 'http://localhost:8080/dawInmoExpress/venedor/' + sessionStorage.getItem("idvendedor") + '/inmoble/nouInmoble?token='+sessionStorage.token,
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
						$('#success').html('<a class="close" data-dismiss="alert"> × </a>'+response.missatge);
						//Esperem 3 s i redirigim a la pàgina de meusinmobles.html del venedor
					   setTimeout(function(){location.replace('meusinmobles.html?id='+sessionStorage.getItem("idvendedor"))},3000);
                },
                error: function(error){
                       //codigo error
					   $('#success').addClass("alert alert-danger");
					   $('#success').html('<a class="close" data-dismiss="alert"> × </a><Strong> ERROR EN LA OPERACIÓ! </ Strong>NO S\'ha inserit l\'inmoble');
                }
        });
	}
});


//Funcio per obtenir els arxius d'imatges que es pugen al servidor des del client
function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object

    // Loop through the FileList and render image files as thumbnails.
    for (var i = 0, f; f = files[i]; i++) {

      // Only process image files.
      if (!f.type.match('image.*')) {
        continue;
      }

      var reader = new FileReader();

      // Closure to capture the file information.
      reader.onload = (function(theFile) {
        return function(e) {
          // Render thumbnail.
          var span = document.createElement('span');
          span.innerHTML = ['<img class="thumb" src="', e.target.result,
                            '" title="', escape(theFile.name), '"/>'].join('');
          document.getElementById('list').insertBefore(span, null);
        };
      })(f);

      // Read in the image file as a data URL.
      reader.readAsDataURL(f);
    }
  }

  document.getElementById('files').addEventListener('change', handleFileSelect, false);