package cat.xtec.ioc.controller;
import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.service.VendedorDAOService;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/administrador")
public class AdministradorRestController {
    @Autowired
    VendedorDAOService vendedorDAOService;
    
    @Autowired
    InmuebleDAORepository inmuebleService;
    
    public AdministradorRestController(){}
    
    public AdministradorRestController(VendedorDAOService vendedorDAOService, InmuebleDAORepository inmuebleService){
        this.vendedorDAOService=vendedorDAOService;
        this.inmuebleService=inmuebleService;
    }

    // todos los vendedores
    @RequestMapping( value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<Vendedor> getAllVendedores(HttpServletRequest request, HttpServletResponse response) {
        Cookie ck[]=request.getCookies();
        String email = ck[0].getValue();
        Vendedor vendedorEmail = vendedorDAOService.getVendedorByEmail(email);
         if (vendedorEmail.getRol().equalsIgnoreCase("administrador")){
            return this.vendedorDAOService.getAllVendedor();
         }else{
             return null;
         }

    }
    
    // Eliminar Vendedor
    //curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/dawInmoExpress/administrador/
    @RequestMapping(value = ("/{idVendedor}"), method = RequestMethod.DELETE)
    public @ResponseBody
     ResponseEntity<Vendedor> deleteVendedor(@PathVariable("idVendedor") Integer idVendedor){
         Vendedor vendedor = vendedorDAOService.getVendedorByIdVendedor(idVendedor);
        this.vendedorDAOService.deleteVendedor(vendedor);
        return new ResponseEntity<>(vendedor, HttpStatus.OK);
     }
    
    
}
