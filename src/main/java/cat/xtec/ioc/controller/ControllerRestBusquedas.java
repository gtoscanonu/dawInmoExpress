package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.service.InmuebleService;
import cat.xtec.ioc.service.VendedorDAOService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/")
public class ControllerRestBusquedas {
    @Autowired
    VendedorDAOService vendedorDAOService;
    
    @Autowired
    InmuebleService inmuebleService;
    
    public ControllerRestBusquedas() {}
    
    public ControllerRestBusquedas(VendedorDAOService vendedorDAOService, InmuebleService inmuebleService){
        this.vendedorDAOService=vendedorDAOService;
        this.inmuebleService=inmuebleService;
    }
    
    //Todos los inmuebles 
    @RequestMapping(method =  RequestMethod.GET)
    public @ResponseBody List<Inmueble> getAllInmuebles(){
        return this.inmuebleService.getAllInmuebles();
    }
    
    // Atributos de un inmueble
    @RequestMapping(value = ("/{idVivienda}"), method =  RequestMethod.GET)
    public @ResponseBody Inmueble getInmuebleById(@PathVariable("idVivienda") Integer idVivienda){
        return this.inmuebleService.getInmuebleById(idVivienda);
    }
    
    // Inmuebles por tipo de anuncio recibo el anuncio de inmueble en la url http://localhost:8080/dawInmoExpress/venta
    @RequestMapping(value = ("/inmuebles/{anuncio}"), method = RequestMethod.GET)
    public @ResponseBody List<Inmueble> getInmueblesByAnuncio(@PathVariable("anuncio") String anuncio){
        return this.inmuebleService.getInmueblesByAnuncio(anuncio);
    }
    
    // Obtener búsqueda (recibimos precio min, precio max, numHabitaciones 1 o +2, ubicación, tipo)
    // Se debe enviar un valor por defecto para cada parámetro
    @RequestMapping(value = ("/inmuebles/{anuncio}/filtro"), method = RequestMethod.GET)
    public @ResponseBody List<Inmueble> getCriterya(
            @RequestParam("pMax") float pMax,
            @RequestParam("pMax") float pMin,
            @RequestParam("nHab") Integer nHab,
            @RequestParam("ubicacion") String ubicacion,
            @RequestParam("tipo") String tipo,
            @PathVariable("anuncio") String anuncio){
        
        return this.inmuebleService.getQueryCriteria(pMin, pMax, nHab, ubicacion, tipo, anuncio);
    }
}
