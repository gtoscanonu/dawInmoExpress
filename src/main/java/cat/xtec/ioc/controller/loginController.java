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
public class loginController {
    @Autowired
    VendedorDAOService vendedorDAOService;
    
    public loginController(){}
    
    public loginController(VendedorDAOService vendedorDAOService){
        this.vendedorDAOService=vendedorDAOService;
    }
    
    // login vendedor
    @RequestMapping(value = ("/login"), method = RequestMethod.GET)
    public @ResponseBody
    String validarVendedor(@RequestParam("email") String email, @RequestParam("password") String password){
       
       String redirecto = "";
       if (vendedorDAOService.validarVendedor(email) == 1){
            Vendedor vendedor =   vendedorDAOService.loginVendedor(email);
            if (vendedor.getPassword().equals(password)){
                if (vendedor.getRol().equals("administrador")){
                    redirecto = "http://localhost:8080/dawInmoExpress/administrador/all";
                }else{
                    redirecto = "http://localhost:8080/dawInmoExpress/venedor/" + vendedor.getIdVendedor();
                }
                return redirecto;
            } else{
                return "La contrasenya és incorrecta";
            }            
       }else{
           return "L'usuari no existeix"; 
       }
    
    }
    
    //Registrarse Sign-up
    //curl -H "Content-Type: application/json" -X POST -d "{\"nombre\":\"vendedor\",\"email\":\"inmobiliaria1@gmail.com\",\"telefono\":\"123123123\",\"password\":\"123123123\",\"rol\":\"administrador\"}" http://localhost:8080/dawInmoExpress/registrar-se
    @RequestMapping(value = ("/registrar-se"), method = RequestMethod.POST)
    public @ResponseBody
    String createVendedor(@RequestBody Vendedor vendedor){
        
        String email = vendedor.getEmail();
        vendedor.setRol("vendedor");

        if (vendedorDAOService.validarVendedor(email) == 0){
            this.vendedorDAOService.addVendedor(vendedor);    
            String redirecto = "http://localhost:8080/dawInmoExpress/venedor/" + vendedor.getIdVendedor();
            return redirecto;
          }else{
            return "L'email ja se ha registrat anteriorment";
        }
    }
        
}
