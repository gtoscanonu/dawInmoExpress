package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.service.VendedorDAOService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@MultipartConfig
@RequestMapping("/vendedorView")
public class InmuebleController {
    
    @Autowired
    VendedorDAOService vendedorDAOService;
    
    @Autowired
    InmuebleDAORepository inmuebleService;
   
   // @Autowired
   // InmuebleService inmuebleService;
    
    //INMUEBLES POR VENDEDOR
    @RequestMapping("/{idVendedor}")
    public ModelAndView allInmueblesByVendedor(@PathVariable("idVendedor") Integer idVendedor, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("InmueblesByVendedor");
        modelview.getModelMap().addAttribute("inmuebles", inmuebleService.getAllInmueblesByVendedor(idVendedor));
        modelview.getModelMap().addAttribute("vendedor", vendedorDAOService.getVendedorByIdVendedor(idVendedor));
        return modelview;
    }
    
    //INMUEBLE POR IDVIVIENDA
    @RequestMapping("/{idVendedor}/inmueble")
    public ModelAndView getInmuebleById(@RequestParam("idVivienda") Integer idVivienda, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        ModelAndView modelview = new ModelAndView("inmueble");
        
        Inmueble formInmueble = null;
            formInmueble = inmuebleService.getInmuebleById(idVivienda);

        modelview.getModelMap().addAttribute("formInmueble", formInmueble);
        return modelview;
        
    }
    
    @RequestMapping(value ="/{idVendedor}/inmueble/update", method = RequestMethod.POST)
    public String processInmuebleForm(@ModelAttribute("formInmueble") Inmueble formInmueble, @PathVariable("idVendedor") Integer idVendedor , BindingResult result){
        inmuebleService.updateInmueble(formInmueble);
        String redireccion = "redirect:/vendedorView/" + idVendedor;
        return redireccion;
    }
   
    @RequestMapping("/{idVendedor}/inmueble/delete")
    public String DeleteInmueble(@PathVariable("idVendedor") Integer idVendedor, @RequestParam("idVivienda") Integer idVivienda,  HttpServletRequest request, HttpServletResponse response){
        Inmueble inmueble= inmuebleService.getInmuebleById(idVivienda);
        Vendedor vendedor = vendedorDAOService.getVendedorByIdVendedor(idVendedor);
        
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        Set<Inmueble> inmuebles = vendedor.getInmuebles();
        System.out.println(inmuebles.size());
        
        
        inmuebleService.deleteInmueble(inmueble, idVendedor);
        
        Set<Inmueble> inmuebles2 = vendedor.getInmuebles();
        System.out.println(inmuebles.size());
        
        
        System.out.println("asdfasdfaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(inmueble.getExtras());
        System.out.println(inmueble.getSuperficie());
        
        
        
        String redireccion = "redirect:/vendedorView/" + idVendedor;
        return redireccion;
    }
    
    
    @RequestMapping(value = "/{idVendedor}/inmueble/nuevoInmueble", method = RequestMethod.GET)
    public ModelAndView getAddNewInmueble(@PathVariable("idVendedor") Integer idVendedor, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("addInmueble");
        Inmueble newInmueble = new Inmueble();
        modelview.getModelMap().addAttribute("idVendedor", idVendedor);
        modelview.getModelMap().addAttribute("newInmueble", newInmueble);
        return modelview;
    }
    
    @RequestMapping(value = "/anadir", method = RequestMethod.GET)
     public ModelAndView getAddNewInmueblfe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       
        ModelAndView modelview = new ModelAndView("formImage");
         modelview.getModelMap().addAttribute("idVendedor", 1);
        return modelview;
    }
    
    @RequestMapping(value = "/{idVendedor}/inmueble/add", method = RequestMethod.POST)
    public String postAddNewInmuebleForm(
            @RequestParam("tipo") String tipo, 
            @RequestParam("precio") float precio,
            @RequestParam("superficie") float superficie,
            @RequestParam("numHabitaciones") int numHabitaciones,
            @RequestParam("numBanios") int numBanios,
            @RequestParam("ubicacion") String ubicacion,
            @RequestParam("extras") String extras,
            @RequestParam("descripcion") String descripcion, 
            @PathVariable("idVendedor") Integer idVendedor, 
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
        
        inmuebleService.addInmueble(newInmueble, idVendedor);
        List<Vendedor> vendedores = vendedorDAOService.getAllVendedor();
        
        int path = queryIdVivienda(idVendedor);
        String pathVivienda = mkdirDirectorio(idVendedor, path);
        String photoFilePathToSave = pathVivienda + "/imagen.png";
        saveBytesToFile(photoFilePathToSave, photoBytes2);
        
        Inmueble inmuebleImagen = inmuebleService.getInmuebleById(path);
        inmuebleImagen.setImagen(photoFilePathToSave);
        inmuebleService.updateInmueble(inmuebleImagen);
        
        List<Vendedor> vendedores2 = vendedorDAOService.getAllVendedor();
        
        String redireccion = "redirect:/vendedorView/" + idVendedor;
        return redireccion;
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
    
   
    
    
    /*
    @RequestMapping(value = "/{idVendedor}/inmueble/add", method = RequestMethod.POST)
    public String postAddNewInmuebleForm(@ModelAttribute("newInmueble") Inmueble newInmueble, @PathVariable("idVendedor") Integer idVendedor, BindingResult result) {
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Intentat accedir amb camps no permesos: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
        Vendedor vendedor = vendedorDAOService.getVendedorByIdVendedor(idVendedor);
        Inmueble inmueble = new Inmueble();
        inmuebleService.addInmueble(newInmueble, vendedor.getIdVendedor());
        String redireccion = "redirect:/vendedorView/" + idVendedor;
        return redireccion;
        
    }*/
    
    /*
    @RequestMapping(value = "/{idVendedor}/inmueble/add", method = RequestMethod.POST)
    public String postAddNewInmuebleForm(@ModelAttribute("newInmueble") Inmueble newInmueble, @PathVariable("idVendedor") Integer idVendedor, BindingResult result) throws IOException {
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Intentat accedir amb camps no permesos: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
        Vendedor vendedor = vendedorDAOService.getVendedorByIdVendedor(idVendedor);
        
        String photoFilePathToSave = "/home/geovany/Escritorio/corr.png";
        
        byte[] photoBytes2 = newInmueble.getImagen();
        saveBytesToFile(photoFilePathToSave, photoBytes2);
 
        inmuebleService.addInmueble(newInmueble, vendedor.getIdVendedor());
        String redireccion = "redirect:/vendedorView/" + idVendedor;
        return redireccion;
        
    }

*/
    
    private static void saveBytesToFile(String filePath, byte[] fileBytes) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(fileBytes);
        outputStream.close();
    }
    
}
