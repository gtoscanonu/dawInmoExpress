package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.service.InmuebleService;
import cat.xtec.ioc.service.VendedorDAOService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Variables;

/**
 * Definimos el controller vendedor con todos los servicios para obtener y actualizar los datos de un vendedor y de un inmueble.
 */

@Controller
@RequestMapping("/venedor")
public class VendedorRestController {

    @Autowired
    VendedorDAOService vendedorDAOService;
    
    @Autowired
    InmuebleService inmuebleService;
    
    Variables variables = new Variables();
    Vendedor vendedor;
    
    public VendedorRestController(){}
    
    public VendedorRestController(VendedorDAOService vendedorDAOService, InmuebleService inmuebleService){
        this.vendedorDAOService=vendedorDAOService;
        this.inmuebleService=inmuebleService;
    }

    /**
    * Servicio que devuelve los atributos de un vendedor
    * 
     * @param idVendedor
     * @param token
     * @param request
     * @param response
     * @return devuelve json los atributos de un vendedor
    */
    @RequestMapping(value = ("/{idVendedor}"), method = RequestMethod.GET)
    //public @ResponseBody Vendedor getVendedorById(@PathVariable("idVendedor") Integer idVendedor){
    public @ResponseBody Vendedor getVendedorById(@PathVariable("idVendedor") Integer idVendedor, @QueryParam("token") String token, HttpServletRequest request, HttpServletResponse response){
       if(variables.checkToken(token)){
           vendedor = this.vendedorDAOService.getVendedorByIdVendedor(variables.getUser(token));
           if(vendedor.getIdVendedor().equals(idVendedor)){
                return this.vendedorDAOService.getVendedorByIdVendedor(idVendedor);
           }else{
               return null;
           }
       }else{
            return null;
       }
    }
    
    /**
    * Servicio que actualiza los atributos de un vendedor
     * @param idVendedor
     * @param token
     * @param vendedor
     * @return un String indicando si se ha actualizado los atributos del vendedor
    */   
    @RequestMapping(value = ("/{idVendedor}/editar"), method = RequestMethod.PUT)
    public @ResponseBody
    String updateVendedor(@PathVariable("idVendedor") Integer idVendedor, @QueryParam("token") String token, @RequestBody Vendedor vendedor){
        if(variables.checkToken(token)){
            Vendedor vendedorToken = this.vendedorDAOService.getVendedorByIdVendedor(variables.getUser(token));
           if(vendedorToken.getIdVendedor().equals(idVendedor)){
                return this.vendedorDAOService.updateVendedor(vendedor);
           }else{
               return "{\"missatge\" : \"L'usuari no s'ha modificat per seguretat\"}";
           }
            
        }
        else{
            return "{\"missatge\" : \"Token no valid\"}"; 
        }
    }
     
    /**
    * Servicio que devuelve la lista de inmuebles de un vendedor
     * @param idVendedor
     * @param token
     * @return  devuelve la lista de inmuebles de un vendedor
    */ 
    @RequestMapping(value = ("{idVendedor}/inmobles"), method =  RequestMethod.GET)
    public @ResponseBody Set<Inmueble> getAllInmueblesByVendedor(@PathVariable("idVendedor") Integer idVendedor, @QueryParam("token") String token){
        if(variables.checkToken(token)){
            Vendedor vendedor = this.vendedorDAOService.getVendedorByIdVendedor(variables.getUser(token));
            if(vendedor.getIdVendedor().equals(idVendedor)){
                return this.inmuebleService.getAllInmueblesByVendedor(idVendedor);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    
    /**
    * Servicio que elimina un inmueble de un determinado vendedor
     * @param idVendedor
     * @param idVivienda
     * @param token
     * @return Retorna un String con el mensaje si se ha eliminado el inmueble
    */ 
    @RequestMapping(value = ("{idVendedor}/inmoble/{idVivienda}"), method = RequestMethod.DELETE)
    public @ResponseBody
     String deleteInmueble(@PathVariable("idVendedor") Integer idVendedor, @PathVariable("idVivienda") Integer idVivienda, @QueryParam("token") String token){
        if(variables.checkToken(token)){
             vendedor = this.vendedorDAOService.getVendedorByIdVendedor(variables.getUser(token));
           if(vendedor.getIdVendedor().equals(idVendedor)){
                Inmueble inmueble = inmuebleService.getInmuebleById(idVivienda);
                this.inmuebleService.deletefk(inmueble, idVendedor);
                this.inmuebleService.deleteInmueble(inmueble, idVendedor);
                return "{\"missatge\" : \"Inmoble eliminat correctament\"}";
           }else{
               return "{\"missatge\" : \"Inmoble no eliminat per seguretat\"}";
           }
            
        }else{
            return "{\"missatge\" : \"Token no valid\"}"; 
        }
        
    }
  
    /**
    * Servicio que actualiza los atributos de un inmueble
     * @param idVendedor
     * @param idVivienda
     * @param token
     * @param inmueble
     * @return  Retorna un string con el mensaje indicando si se han actualizado los atributos de un inmueble
    */ 
     
    @RequestMapping(value = ("{idVendedor}/inmoble/{idVivienda}/editar"), method = RequestMethod.PUT)
    public @ResponseBody
    String updateInmueble(@PathVariable("idVendedor") Integer idVendedor, @PathVariable("idVivienda") Integer idVivienda, @QueryParam("token") String token, 
            @RequestBody Inmueble inmueble) throws IOException {
        
        if(variables.checkToken(token)){
            vendedor = this.vendedorDAOService.getVendedorByIdVendedor(variables.getUser(token));
            if(vendedor.getIdVendedor().equals(idVendedor)){
                this.inmuebleService.updateInmueble(inmueble);
                return "{\"missatge\" : \"Inmoble modificat correctament\"}";
            }else{
                return "{\"missatge\" : \"Inmoble no modificat per seguretat\"}";
            }
        }else{
            return "{\"missatge\" : \"Token no valid\"}"; 
        }
    }
    
 

    /**
    * Servicio que crea un inmueble
     * @param idVendedor
     * @param token
     * @param inmueble
     * @return Retorna un string indicando si se ha creado el inmueble
    */ 
    @RequestMapping(value = ("{idVendedor}/inmoble/nouInmoble"), method = RequestMethod.POST)
    public @ResponseBody 
    String createInmueble(@PathVariable("idVendedor") Integer idVendedor, @QueryParam("token") String token, 
            @RequestBody Inmueble inmueble) throws IOException {
        
        if(variables.checkToken(token)){
            vendedor = this.vendedorDAOService.getVendedorByIdVendedor(variables.getUser(token));
            if(vendedor.getIdVendedor().equals(idVendedor)){
                this.inmuebleService.addInmueble(inmueble, idVendedor);
                return "{\"missatge\" : \"Inmoble registrat correctament\"}";
            }else{
                return "{\"missatge\" : \"Inmoble no registrat per seguretat\"}";
            }
        }else{
            return "{\"missatge\" : \"Token no valid\"}"; 
        }
    }      
} 