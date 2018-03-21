package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.service.VendedorDAOService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;


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

    //Actualizar Inmueble
    //curl -H "Content-Type: application/json" -X PUT -d "{\"tipo\":\"pisoPrueb\",\"ubicacion\":\"Ubicacion\",\"superficie\":\"12323.0\",\"precio\":\"1233.0\",\"numHabitaciones\":\"3\",\"numBaños\":\"1\",\"extras\":\"COn ascensor\",\"descripcion\":\"buen barrio\",\"imagen\":\"/home\"}" http://localhost:8080/dawInmoExpress/vendedor/14/19/update
    @RequestMapping(value = ("/{idVivienda}/update"), method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Inmueble> updateInmueble(@PathVariable("idVivienda") Integer idVivienda, @RequestBody Inmueble inmueble){
        inmueble.setIdVivienda(idVivienda);
        this.inmuebleService.updateInmueble(inmueble);
        return new ResponseEntity<>(inmueble, HttpStatus.OK);
    }
   
    //Eliminar inmueble
    //curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/dawInmoExpress/vendedor/14/18
    @RequestMapping(value = ("/{idVivienda}"), method = RequestMethod.DELETE)
    public @ResponseBody
     ResponseEntity<Inmueble> deleteInmueble(@PathVariable("idVendedor") Integer idVendedor, @PathVariable("idVivienda") Integer idVivienda){
        Inmueble inmueble = inmuebleService.getInmuebleById(idVivienda);
        this.inmuebleService.deleteInmueble(inmueble, idVendedor);
        return new ResponseEntity<>(inmueble, HttpStatus.OK);
     }

     
    //Crear Inmueble sin atributo imagen
    //curl -H "Content-Type: application/json" -X POST -d "{\"tipo\":\"pisoTTTT\",\"ubicacion\":\"Ubicacion\",\"superficie\":\"12323.0\",\"precio\":\"1233.0\",\"numHabitaciones\":\"3\",\"numBaños\":\"1\",\"extras\":\"COn ascensor\",\"descripcion\":\"buen barrio\",\"imagen\":\"/home\"}" http://localhost:8080/dawInmoExpress/vendedor/14/nuevoInmueble
    @RequestMapping(value = ("/nuevoInmueble"), method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Inmueble> createInmueble(@PathVariable("idVendedor") Integer idVendedor, @RequestBody Inmueble inmueble){
        Vendedor vendedor = vendedorDAOService.getVendedorByIdVendedor(idVendedor);
        this.inmuebleService.addInmueble(inmueble, vendedor.getIdVendedor());
        return new ResponseEntity<>(inmueble, HttpStatus.CREATED);
    } 
 
   /* 
    // Crear un inmueble recibiendo una imagen desde el cliente
    @RequestMapping(value = ("/nuevoInmueble"), method = RequestMethod.POST)
    public @ResponseBody 
    ResponseEntity<Inmueble> createInmueble(@PathVariable("idVendedor") Integer idVendedor, 
            @RequestParam("tipo") String tipo, 
            @RequestParam("precio") float precio,
            @RequestParam("superficie") float superficie,
            @RequestParam("numHabitaciones") int numHabitaciones,
            @RequestParam("numBanios") int numBanios,
            @RequestParam("ubicacion") String ubicacion,
            @RequestParam("extras") String extras,
            @RequestParam("descripcion") String descripcion,  
            @RequestParam("imagen") MultipartFile file) throws IOException {
        
        Inmueble newInmueble= new Inmueble();
        newInmueble.setDescripcion(descripcion);
        newInmueble.setTipo(tipo);
        newInmueble.setExtras(extras);
        newInmueble.setNumBanios(numBanios);
        newInmueble.setNumHabitaciones(numHabitaciones);
        newInmueble.setUbicacion(ubicacion);
        newInmueble.setPrecio(precio);
        newInmueble.setSuperficie(superficie);
        
        byte[] photoBytes2 = file.getBytes();
        
        this.inmuebleService.addInmueble(newInmueble, idVendedor);
        List<Vendedor> vendedores = vendedorDAOService.getAllVendedor();

        int path = queryIdVivienda(idVendedor);
        String pathVivienda = mkdirDirectorio(idVendedor, path);
        String photoFilePathToSave = pathVivienda + "/imagen.png";
        saveBytesToFile(photoFilePathToSave, photoBytes2);
         
        Inmueble inmuebleImagen = inmuebleService.getInmuebleById(path);
        inmuebleImagen.setImagen(photoFilePathToSave);
        this.inmuebleService.updateInmueble(inmuebleImagen);

        return new ResponseEntity<>(newInmueble, HttpStatus.CREATED);
    } 
   */
    private int queryIdVivienda(int idVendedor){
       int path = 0;
        Set<Inmueble> inmuebles = inmuebleService.getAllInmueblesByVendedor(idVendedor);
        for(Inmueble inmuebleItem : inmuebles){
            if(inmuebleItem.getIdVivienda() > path){
                path = inmuebleItem.getIdVivienda();
            }                
        }
        return path;
   } 

    private String mkdirDirectorio(int idVendedor, int path){ 
        String vendedorPath = "/home/geovany/NetbeansProyects/IOC_PROYECT/dawInmoExpress/src/main/webapp/WEB-INF/img/vendedores/IdVendedor"+ idVendedor;
        File vendedorFile = new File (vendedorPath);
        vendedorFile.mkdir();
        String pathVivienda = (vendedorPath + "/IdVivienda" + path);
        File imagenPath = new File (pathVivienda);
        imagenPath.mkdir();
        
        return pathVivienda;
    }
    
     private static void saveBytesToFile(String filePath, byte[] fileBytes) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(fileBytes);
        outputStream.close();
    }
     
}
