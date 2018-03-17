package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.service.VendedorDAOService;
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
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/vendedor/{idVendedor}")
public class InmuebleRestController {
    
    @Autowired
    VendedorDAOService vendedorDAOService;
    
    @Autowired
    InmuebleDAORepository inmuebleService;
    
    public InmuebleRestController() {}
    
    public InmuebleRestController(VendedorDAOService vendedorDAOService, InmuebleDAORepository inmuebleService){
        this.vendedorDAOService=vendedorDAOService;
        this.inmuebleService=inmuebleService;
    }
    
    // Todos los inmuebles de un vendedor
    @RequestMapping(value = ("/inmuebles"), method =  RequestMethod.GET)
    public @ResponseBody Set<Inmueble> getAllInmueblesByVendedor(@PathVariable("idVendedor") Integer idVendedor){
        return this.inmuebleService.getAllInmueblesByVendedor(idVendedor);
    }
 
    //Crear Inmueble
    //curl -H "Content-Type: application/json" -X POST -d "{\"tipo\":\"pisoTTTT\",\"ubicacion\":\"Ubicacion\",\"superficie\":\"12323.0\",\"precio\":\"1233.0\",\"numHabitaciones\":\"3\",\"numBaños\":\"1\",\"extras\":\"COn ascensor\",\"descripcion\":\"buen barrio\"}" http://localhost:8080/dawm07eac4domotic/vendedor/14/nuevoInmueble
    @RequestMapping(value = ("/nuevoInmueble"), method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Inmueble> createInmueble(@PathVariable("idVendedor") Integer idVendedor, @RequestBody Inmueble inmueble){
        Vendedor vendedor = vendedorDAOService.getVendedorByIdVendedor(idVendedor);
        this.inmuebleService.addInmueble(inmueble, vendedor.getIdVendedor());
        return new ResponseEntity<>(inmueble, HttpStatus.CREATED);
    
    }

    //Actualizar Inmueble
    //curl -H "Content-Type: application/json" -X PUT -d "{\"tipo\":\"pisoPrueb\",\"ubicacion\":\"Ubicacion\",\"superficie\":\"12323.0\",\"precio\":\"1233.0\",\"numHabitaciones\":\"3\",\"numBaños\":\"1\",\"extras\":\"COn ascensor\",\"descripcion\":\"buen barrio\"}" http://localhost:8080/dawm07eac4domotic/vendedor/14/19/update
    @RequestMapping(value = ("/{idVivienda}/update"), method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Inmueble> updateInmueble(@PathVariable("idVivienda") Integer idVivienda, @RequestBody Inmueble inmueble){
        inmueble.setIdVivienda(idVivienda);
        this.inmuebleService.updateInmueble(inmueble);
        return new ResponseEntity<>(inmueble, HttpStatus.OK);
    }
   
    //Eliminar inmueble
    @RequestMapping(value = ("/{idVivienda}"), method = RequestMethod.DELETE)
    public @ResponseBody
     ResponseEntity<Inmueble> deleteInmueble(@PathVariable("idVendedor") Integer idVendedor, @PathVariable("idVivienda") Integer idVivienda){
        Inmueble inmueble = inmuebleService.getInmuebleById(idVivienda);
        this.inmuebleService.deleteInmueble(inmueble, idVendedor);
        return new ResponseEntity<>(inmueble, HttpStatus.OK);
     }
       
}
