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
    
    // Inmuebles por tipo recibo el tipo de inmueble http://localhost:8080/dawInmoExpress/inmuebles?tipo=pisoTTTT
    @RequestMapping(value = ("/inmuebles"), method = RequestMethod.GET)
    public @ResponseBody List<Inmueble> getInmueblesByTipus(@RequestParam("tipo") String tipo){
        return this.inmuebleService.getInmueblesByTipus(tipo);
    }
    
    // Obtener búsqueda (recibimos precio min, precio max, numHabitaciones 1 o +2, ubicación, tipo)
    // Se debe enviar un valor por defecto para cada parámetro
    @RequestMapping(value = ("/busqueda"), method = RequestMethod.GET)
    public @ResponseBody List<Inmueble> getCriterya(
            @RequestParam("pMax") float pMax,
            @RequestParam("pMax") float pMin,
            @RequestParam("nHab") Integer nHab,
            @RequestParam("ubicacion") String ubicacion,
            @RequestParam("tipo") String tipo){
        
        return this.inmuebleService.getQueryCriteria(pMin, pMax, nHab, ubicacion, tipo);
    }
}
