package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.service.InmuebleService;
import cat.xtec.ioc.service.VendedorDAOService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;



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
    public @ResponseBody Vendedor getVendedorById(@PathVariable("idVendedor") Integer idVendedor){
        return this.vendedorDAOService.getVendedorByIdVendedor(idVendedor);
    }
    
    // Actualizar Vendedor
    //curl -H "Content-Type: application/json" -X PUT -d "{\"nombre\":\"vendedorPrueba\",\"email\":\"inmo@gmail.com\",\"telefono\":\"123123123\",\"password\":\"123123123\",\"rol\":\"administrador\"}" http://localhost:8080/dawInmoExpress/venedor/14/editar
    @RequestMapping(value = ("/{idVendedor}/editar"), method = RequestMethod.PUT)
    public @ResponseBody
    String updateVendedor(@PathVariable("idVendedor") Integer idVendedor,@RequestBody Vendedor vendedor){
        vendedor.setIdVendedor(idVendedor);
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
        return "http://localhost:8080/dawInmoExpress/venedor/"+idVendedor+"/inmobles";
    }
     
    //Actualizar Inmueble sin atributo imagen OBSOLETO
    //curl -H "Content-Type: application/json" -X PUT -d "{\"tipo\":\"casaUpdate\",\"ubicacion\":\"Ubicacion\",\"superficie\":\"12323.0\",\"precio\":\"1233.0\",\"numHabitaciones\":\"3\",\"numBanios\":\"1\",\"extras\":\"Con ascensor\",\"descripcion\":\"buen barrio\",\"anuncio\":\"venta\",\"imagen\":\"/home\"}" http://localhost:8080/dawInmoExpress/venedor/3/inmoble/4/editar
    @RequestMapping(value = ("{idVendedor}/inmoble/{idVivienda}/editar"), method = RequestMethod.PUT)
    public @ResponseBody
    String updateInmueble(@PathVariable("idVendedor") Integer idVendedor, @PathVariable("idVivienda") Integer idVivienda, @RequestBody Inmueble inmueble){
        inmueble.setIdVivienda(idVivienda);
        this.inmuebleService.updateInmueble(inmueble);
        return "http://localhost:8080/dawInmoExpress/venedor/"+idVendedor+"/inmobles";
    } 
/*     
    //Crear Inmueble sin atributo imagen OBSOLETO
    //curl -H "Content-Type: application/json" -X POST -d "{\"tipo\":\"pisoTTTT\",\"ubicacion\":\"Ubicacion\",\"superficie\":\"12323.0\",\"precio\":\"1233.0\",\"numHabitaciones\":\"3\",\"numBaños\":\"1\",\"extras\":\"COn ascensor\",\"descripcion\":\"buen barrio\",\"imagen\":\"/home\",\"anuncio\":\"venda\"}" http://localhost:8080/dawInmoExpress/venedor/2/inmoble/nouInmoble
    @RequestMapping(value = ("{idVendedor}/inmoble/nouInmoble"), method = RequestMethod.POST)
    public @ResponseBody
    String createInmueble(@PathVariable("idVendedor") Integer idVendedor, @RequestBody Inmueble inmueble){
        Vendedor vendedor = vendedorDAOService.getVendedorByIdVendedor(idVendedor);
        this.inmuebleService.addInmueble(inmueble, vendedor.getIdVendedor());
        return "http://localhost:8080/dawInmoExpress/venedor/"+idVendedor+"/inmobles";
    } 
    
   //Actualizar Inmueble con atributo imagen 
    @RequestMapping(value = ("{idVendedor}/inmoble/{idVivienda}/editar"), method = RequestMethod.PUT)
    public @ResponseBody
    String updateInmueble(@PathVariable("idVendedor") Integer idVendedor, @PathVariable("idVivienda") Integer idVivienda, 
            @RequestParam("tipo") String tipo, 
            @RequestParam("precio") float precio,
            @RequestParam("superficie") float superficie,
            @RequestParam("numHabitaciones") int numHabitaciones,
            @RequestParam("numBanios") int numBanios,
            @RequestParam("ubicacion") String ubicacion,
            @RequestParam("extras") String extras,
            @RequestParam("descripcion") String descripcion, 
            @RequestParam("anuncio") String anuncio,
            @RequestParam("imagen") MultipartFile file) throws IOException {
       
        Inmueble newInmueble= new Inmueble();
        newInmueble.setDescripcion(descripcion);
        newInmueble.setTipo(tipo);
        newInmueble.setExtras(extras);
        newInmueble.setNumBanios(numBanios);
        newInmueble.setNumHabitaciones(numHabitaciones);
        newInmueble.setUbicacion(ubicacion);
        newInmueble.setPrecio(precio);
        newInmueble.setAnuncio(anuncio);
        newInmueble.setSuperficie(superficie);

        byte[] photoBytes2 = file.getBytes();
        
        int path = queryIdVivienda(idVendedor);
        String pathVivienda = mkdirDirectorio(idVendedor, path);
        String photoFilePathToSave = pathVivienda + "/imagen.png";
        saveBytesToFile(photoFilePathToSave, photoBytes2);
        
        newInmueble.setImagen(photoFilePathToSave);
        newInmueble.setIdVivienda(idVivienda);
        
        this.inmuebleService.updateInmueble(newInmueble);
        return "http://localhost:8080/dawInmoExpress/venedor/"+idVendedor+"/inmobles";
    }
*/    
 

    // Crear un inmueble recibiendo una imagen desde el cliente
    @RequestMapping(value = ("{idVendedor}/inmoble/nouInmoble"), method = RequestMethod.POST)
    public @ResponseBody 
    String createInmueble(@PathVariable("idVendedor") Integer idVendedor, 
            @RequestParam("tipo") String tipo, 
            @RequestParam("precio") float precio,
            @RequestParam("superficie") float superficie,
            @RequestParam("numHabitaciones") int numHabitaciones,
            @RequestParam("numBanios") int numBanios,
            @RequestParam("ubicacion") String ubicacion,
            @RequestParam("extras") String extras,
            @RequestParam("descripcion") String descripcion, 
            @RequestParam("anuncio") String anuncio,
            @RequestParam("imagen") MultipartFile file) throws IOException {
        
        Inmueble newInmueble= new Inmueble();
        newInmueble.setDescripcion(descripcion);
        newInmueble.setTipo(tipo);
        newInmueble.setExtras(extras);
        newInmueble.setNumBanios(numBanios);
        newInmueble.setNumHabitaciones(numHabitaciones);
        newInmueble.setUbicacion(ubicacion);
        newInmueble.setPrecio(precio);
        newInmueble.setAnuncio(anuncio);
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

       return "http://localhost:8080/dawInmoExpress/venedor/"+idVendedor+"/inmobles";
    } 
  
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