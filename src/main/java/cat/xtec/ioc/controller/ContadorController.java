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
 * Definimos el controller ContadorController con dos servicios. Obtener número de visitas y actualizar número de visitas
 */


@Controller
@RequestMapping("/contador")
public class ContadorController {
    @Autowired
    VendedorDAOService vendedorDAOService;
    
    @Autowired
    InmuebleService inmuebleService;
    
    public ContadorController() {}
    
    public ContadorController(VendedorDAOService vendedorDAOService, InmuebleService inmuebleService){
        this.vendedorDAOService=vendedorDAOService;
        this.inmuebleService=inmuebleService;
    }
    
    /**
    * Servicioque obtiene el número de visitas
     * @param idVivienda
     * @return un número entero que es el número de visitas a un determinado inmueble
    */

    @RequestMapping(value = ("/{idVivienda}/get"), method =  RequestMethod.GET)
    public @ResponseBody Integer getContador(@PathVariable("idVivienda") Integer idVivienda){
        return this.inmuebleService.getContadorVisitas(idVivienda);
    }
    
    /**
    * Servicio que actualiza el número de visitas
     * @param idVivienda
    */
    
    @RequestMapping(value = ("/{idVivienda}/set"), method =  RequestMethod.GET)
    public @ResponseBody void setContador(@PathVariable("idVivienda") Integer idVivienda){
        this.inmuebleService.setContadorVisitas(idVivienda);
    }
    
}