package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.service.VendedorDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class loginController {
    @Autowired
    VendedorDAOService vendedorDAOService;
    
    public loginController(){}
    
    public loginController(VendedorDAOService vendedorDAOService){
        this.vendedorDAOService=vendedorDAOService;
    }
    
  /*  
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(Model model) {
        model.addAttribute("error", "true");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "login";
    }
    
  */  
    // login vendedor
    @RequestMapping(value = ("/login"), method = RequestMethod.GET)
    public @ResponseBody
    Vendedor validarVendedor(@RequestParam("email") String email, @RequestParam("password") String password){
        return vendedorDAOService.validarVendedor(email, password);
    }
    
    //Registrarse Sign-up
    //curl -H "Content-Type: application/json" -X POST -d "{\"nombre\":\"vendedor\",\"email\":\"inmobiliaria1@gmail.com\",\"telefono\":\"123123123\",\"password\":\"123123123\",\"rol\":\"administrador\"}" http://localhost:8080/dawInmoExpress/registrar-se
    @RequestMapping(value = ("/registrar-se"), method = RequestMethod.POST)
    public @ResponseBody
    String createVendedor(@RequestBody Vendedor vendedor){
        
        vendedor.setRol("vendedor");

        if (vendedorDAOService.validarVendedor(vendedor.getEmail(), vendedor.getPassword()) == null){
            this.vendedorDAOService.addVendedor(vendedor);    
            return "{\"missatge\" : \"Usuari " + vendedor.getEmail() + " registrat\"}";
          }else{
            return "{\"missatge\" : \"Usuari " + vendedor.getEmail() + " ja registrat anteriorment\"}";
        }
    }
        
}
