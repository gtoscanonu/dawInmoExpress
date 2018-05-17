package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.service.InmuebleService;
import cat.xtec.ioc.service.VendedorDAOService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Definimos el controller ControllerRestBusquedas con servicios de búsquedas de inmuebles y filtros.
 */

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
    
    /**
    * Servicio que devuelve la lista de todos los inmuebles de todos los usuarios
     * @return lista de todos los inmuebles de todos los usuarios
    */

    @RequestMapping(method =  RequestMethod.GET)
    public @ResponseBody List<Inmueble> getAllInmuebles(){
        return this.inmuebleService.getAllInmuebles();
    }
    
    /**
    * Servicio que devuelve los atributos de un inmueble
     * @param idVivienda
     * @return un json son los atributos de un inmueble
    */

    @RequestMapping(value = ("/{idVivienda}"), method =  RequestMethod.GET)
    public @ResponseBody Inmueble getInmuebleById(@PathVariable("idVivienda") Integer idVivienda){
        return this.inmuebleService.getInmuebleById(idVivienda);
    }
    
    /**
    * Servicio que devuelve la lista de los inmuebles de un determinado anuncio.
     * @param anuncio
     * @return  devuelve la lista de los inmuebles de un determinado anuncio.
    */
    
    @RequestMapping(value = ("/inmoble/{anuncio}"), method = RequestMethod.GET)
    public @ResponseBody List<Inmueble> getInmueblesByAnuncio(@PathVariable("anuncio") String anuncio){
        return this.inmuebleService.getInmueblesByAnuncio(anuncio);
    }
    
    /**
    * Servicio que devuelve la lista de los inmuebles de un filtro de búsqueda
     * @param pMin
     * @param pMax
     * @param nHab
     * @param ubicacion
     * @param tipo
     * @param anuncio
     * @return la lista de los inmuebles de un filtro de búsqueda
    */
    
    @RequestMapping(value = ("/inmoble/filtre"), method = RequestMethod.GET)
    public @ResponseBody List<Inmueble> getCriterya(
            @RequestParam("pMin") float pMin,
            @RequestParam("pMax") float pMax,
            @RequestParam("nHab") Integer nHab,
            @RequestParam("ubicacion") String ubicacion,
            @RequestParam("tipo") String tipo,
            @RequestParam("anuncio") String anuncio){

        return this.inmuebleService.getQueryCriteria(pMin, pMax, nHab, ubicacion, tipo, anuncio);

    }
    
    /**
    * Servicio que devuelve la información del vendedor de una vivienda
     * @param idVivienda
     * @return Un json con los datos de un vendedor
    */ 
   @RequestMapping(value = ("/infovendedor/{idVivienda}"), method =  RequestMethod.GET)
    public @ResponseBody Vendedor getVendedorInfo(@PathVariable("idVivienda") Integer idVivienda){
        return this.vendedorDAOService.getVendedorInfomation(idVivienda);
    } 
}
