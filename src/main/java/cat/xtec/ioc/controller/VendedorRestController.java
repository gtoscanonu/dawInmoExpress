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
@RequestMapping("/vendedor")
public class VendedorRestController {

    @Autowired
    VendedorDAOService vendedorDAOService;
    
    @Autowired
    InmuebleDAORepository inmuebleService;
    
    public VendedorRestController(){}
    
    public VendedorRestController(VendedorDAOService vendedorDAOService, InmuebleDAORepository inmuebleService){
        this.vendedorDAOService=vendedorDAOService;
        this.inmuebleService=inmuebleService;
    }

    // todos los vendedores
    @RequestMapping( value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<Vendedor> getAllVendedores() {
         
    return this.vendedorDAOService.getAllVendedor();
        
    }
    
    // crear vendedor
    //curl -H "Content-Type: application/json" -X POST -d "{\"nombre\":\"vendedor\",\"email\":\"inmobiliaria1@gmail.com\",\"telefono\":\"123123123\"}" http://localhost:8080/dawm07eac4domotic/vendedor/nuevoVendedor
    @RequestMapping(value = ("/nuevoVendedor"), method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Vendedor> createVendedor(@RequestBody Vendedor vendedor){
        this.vendedorDAOService.addVendedor(vendedor);
        return new ResponseEntity<>(vendedor, HttpStatus.CREATED);
    
    }
    
    // Actualizar Vendedor
    //curl -H "Content-Type: application/json" -X PUT -d "{\"nombre\":\"vendedorPrueba\",\"email\":\"inmo@gmail.com\",\"telefono\":\"123123123\"}" http://localhost:8080/dawm07eac4domotic/vendedor/14
    @RequestMapping(value = ("/{idVendedor}"), method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Vendedor> updateVendedor(@PathVariable("idVendedor") Integer idVendedor,@RequestBody Vendedor vendedor){
        vendedor.setIdVendedor(idVendedor);
        this.vendedorDAOService.updateVendedor(vendedor);
        return new ResponseEntity<>(vendedor, HttpStatus.OK);
    }
    
    // Eliminar Vendedor
    //curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/dawm07eac4domotic/vendedor/6
    @RequestMapping(value = ("/{idVendedor}"), method = RequestMethod.DELETE)
    public @ResponseBody
     ResponseEntity<Vendedor> deleteVendedor(@PathVariable("idVendedor") Integer idVendedor){
         Vendedor vendedor = vendedorDAOService.getVendedorByIdVendedor(idVendedor);
        this.vendedorDAOService.deleteVendedor(vendedor);
        return new ResponseEntity<>(vendedor, HttpStatus.OK);
     }
     
    // atributos de 1 vendedor
    @RequestMapping(value = ("/{idVendedor}"), method = RequestMethod.GET)
    public @ResponseBody Vendedor getVendedorById(@PathVariable("idVendedor") Integer idVendedor){
        return this.vendedorDAOService.getVendedorByIdVendedor(idVendedor);
    }
    
}
