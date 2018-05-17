package cat.xtec.ioc.controller;
import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.service.VendedorDAOService;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import utils.Variables;

/**
 * Definimos el controller AdministradorRestController con dos servicios para los usuarios con privilegio administrador.
 */

@Controller
@RequestMapping("/administrador")
public class AdministradorRestController {
    @Autowired
    VendedorDAOService vendedorDAOService;
    
    @Autowired
    InmuebleDAORepository inmuebleService;
    
    Variables variables = new Variables();
    
    public AdministradorRestController(){}
    
    public AdministradorRestController(VendedorDAOService vendedorDAOService, InmuebleDAORepository inmuebleService){
        this.vendedorDAOService=vendedorDAOService;
        this.inmuebleService=inmuebleService;
    }

    /**
    * Servicio que devuelve todos los usuarios registrados en BD
     * @param token
     * @param request
     * @param response
     * @return retornamos un json con la lista de todos los usuarios registrados en BD
    */
    
    @RequestMapping( value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<Vendedor> getAllVendedores(@QueryParam("token") String token, HttpServletRequest request, HttpServletResponse response) {
        if(variables.checkToken(token)){
            Vendedor vendedor = this.vendedorDAOService.getVendedorByIdVendedor(variables.getUser(token));
            if(vendedor.getRol().equalsIgnoreCase("administrador")){
                return this.vendedorDAOService.getAllVendedor();
            }else{
                return null;
            }
        }else{
            return null;
        }  

    }
    
    /**
    * Servicio que elimina de la BD un determinado vendedor
     * @param idVendedor
     * @param token
     * @return un string con un mensaje indicando si se ha realizado la operación
    */
    @RequestMapping(value = ("/{idVendedor}"), method = RequestMethod.DELETE)
    public @ResponseBody
     String deleteVendedor(@PathVariable("idVendedor") Integer idVendedor, @QueryParam("token") String token){
        if(variables.checkToken(token)){
            Vendedor vendedor = this.vendedorDAOService.getVendedorByIdVendedor(variables.getUser(token));
           if(vendedor.getIdVendedor().equals(idVendedor)|| vendedor.getRol().equals("administrador")){
                vendedor = vendedorDAOService.getVendedorByIdVendedor(idVendedor);
                this.vendedorDAOService.deleteVendedor(vendedor);
                return "{\"missatge\" : \"S'ha esborrat l'usuari amb ID : "+idVendedor+"\"}";
           }else{
               return "{\"missatge\" : \"No es pot esborrar l'usuari per seguretat\"}";
           }
            
        }
        else{
            return "{\"missatge\" : \"Token no valid\"}"; 
        }
        
     }
    
    
}
