package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.service.VendedorDAOService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    Vendedor validarVendedor(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        HttpSession session=request.getSession(true);  
        session.setAttribute("email", email);

        return vendedorDAOService.validarVendedor(email, password);    
    }
  
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
         HttpSession session = request.getSession();
         session.invalidate();
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
