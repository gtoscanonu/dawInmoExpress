package cat.xtec.ioc.controller;
import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.service.VendedorDAOService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
    public @ResponseBody List<Vendedor> getAllVendedores() {
        return this.vendedorDAOService.getAllVendedor();
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
