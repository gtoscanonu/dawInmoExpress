package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.service.InmuebleService;
import cat.xtec.ioc.service.VendedorDAOService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/venedor")
public class VendedorRestController {

    @Autowired
    VendedorDAOService vendedorDAOService;
    
    @Autowired
    InmuebleService inmuebleService;
    
    public VendedorRestController(){}
    
    public VendedorRestController(VendedorDAOService vendedorDAOService, InmuebleService inmuebleService){
        this.vendedorDAOService=vendedorDAOService;
        this.inmuebleService=inmuebleService;
    }

    // atributos de 1 vendedor
    //http://localhost:8080/dawInmoExpress/venedor/3
    @RequestMapping(value = ("/{idVendedor}"), method = RequestMethod.GET)
    //public @ResponseBody Vendedor getVendedorById(@PathVariable("idVendedor") Integer idVendedor){
    public @ResponseBody Vendedor getVendedorById(@PathVariable("idVendedor") Integer idVendedor, HttpServletRequest request, HttpServletResponse response){
        
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Vendedor vendedorEmail = vendedorDAOService.getVendedorByEmail(email);
        if (vendedorEmail.getIdVendedor() == idVendedor){
            return this.vendedorDAOService.getVendedorByIdVendedor(idVendedor);
        }else{
            return null;
        }  
    }
    
    // Actualizar Vendedor
    //curl -H "Content-Type: application/json" -X PUT -d "{\"nombre\":\"vendedorPrueba\",\"email\":\"inmo@gmail.com\",\"telefono\":\"123123123\",\"password\":\"123123123\",\"rol\":\"administrador\"}" http://localhost:8080/dawInmoExpress/venedor/14/editar
    @RequestMapping(value = ("/{idVendedor}/editar"), method = RequestMethod.PUT)
    public @ResponseBody
    String updateVendedor(@PathVariable("idVendedor") Integer idVendedor,@RequestBody Vendedor vendedor){
        
        return this.vendedorDAOService.updateVendedor(vendedor);
    }
     
    // Todos los inmuebles de un vendedor
    // http://localhost:8080/dawInmoExpress/venedor/3/inmobles
    @RequestMapping(value = ("{idVendedor}/inmobles"), method =  RequestMethod.GET)
    public @ResponseBody Set<Inmueble> getAllInmueblesByVendedor(@PathVariable("idVendedor") Integer idVendedor){
        return this.inmuebleService.getAllInmueblesByVendedor(idVendedor);
    }
    
    //Eliminar inmueble
    //curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/dawInmoExpress/venedor/14/inmobles/9
    @RequestMapping(value = ("{idVendedor}/inmoble/{idVivienda}"), method = RequestMethod.DELETE)
    public @ResponseBody
     String deleteInmueble(@PathVariable("idVendedor") Integer idVendedor, @PathVariable("idVivienda") Integer idVivienda){
        Inmueble inmueble = inmuebleService.getInmuebleById(idVivienda);
        this.inmuebleService.deletefk(inmueble, idVendedor);
        this.inmuebleService.deleteInmueble(inmueble, idVendedor);
        return "{\"missatge\" : \"Usuari eliminat correctament\"}";
    }
  
   //Actualizar Inmueble con atributo imagen 
    @RequestMapping(value = ("{idVendedor}/inmoble/{idVivienda}/editar"), method = RequestMethod.PUT)
    public @ResponseBody
    String updateInmueble(@PathVariable("idVendedor") Integer idVendedor, @PathVariable("idVivienda") Integer idVivienda, 
            @RequestBody Inmueble inmueble) throws IOException {
        
        this.inmuebleService.updateInmueble(inmueble);
        return "{\"missatge\" : \"Inmoble modificat correctament\"}";
    }
    
 

    // Crear un inmueble recibiendo una imagen desde el cliente
    @RequestMapping(value = ("{idVendedor}/inmoble/nouInmoble"), method = RequestMethod.POST)
    public @ResponseBody 
    String createInmueble(@PathVariable("idVendedor") Integer idVendedor, 
            @RequestBody Inmueble inmueble) throws IOException {
        
        this.inmuebleService.addInmueble(inmueble, idVendedor);

       return "{\"missatge\" : \"Inmoble registrat correctament\"}";
    } 

           
} 