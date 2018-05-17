package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.service.VendedorDAOService;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Variables;

/**
 * Definimos el controller loginController con los servicios de login, logout y registro.
 */


@Controller
public class loginController {
    @Autowired
    VendedorDAOService vendedorDAOService;
    Variables variables = new Variables();
    
    public loginController(){}
    
    public loginController(VendedorDAOService vendedorDAOService){
        this.vendedorDAOService=vendedorDAOService;
    }
    
    /**
    * Servicio de login de un  vendedor
     * @param email
     * @param password
     * @return Retorna un string con la frase de login válido o no válido
    */
    
    @RequestMapping(value = ("/login"), method = RequestMethod.GET)
    public @ResponseBody String validarVendedor(@QueryParam("email") String email, @QueryParam("password") String password){
        
        Vendedor vendedor = vendedorDAOService.validarVendedor(email, password);
        if((vendedor==null)){
            return "{\"missatge\" : \"L'usuari no és valid\"}";
        }else{
            String token = md5(email + LocalDateTime.now().toString());
            variables.addUserSession(vendedor.getIdVendedor() , token);
            String resultat = "{\"token\":\"" + token + "\"," + vendedor.vendedorInString(vendedor) + "\"}";
            return resultat;
        }
    }
    
    /**
    * Servicio de logout vendedor
     * @param id
     * @param token
     * @return retorna un string con el resultado del logout
    */
  
    @RequestMapping(value = ("/logout/{idVendedor}"), method = RequestMethod.GET)
    public @ResponseBody String disconnectVenedorByLogin(@PathVariable("idVendedor") Integer id, @QueryParam("token") String token) {
        if(variables.checkToken(token)){
            variables.removeUserSession(id, token);
            return "{\"missatge\" : \"Usuari " + id + " desconnectat\"}";
        }else{
            return "{\"missatge\" : \"El token no es valid\"}";
        }
    }
    
    /**
     * @param vendedor
     * @return Retorna un string con el resultado del registro.
    */
    
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

    // -------------Metodes de codificacio md5 i SHA1---------------------------------------------------
    
    
    /**
     * Codificació de userDAOs string amb métodes md5 o sha1
     * @param txt, text in plain format
     * @param hashType MD5 OR SHA1
     * @return hash in hashType 
     */
    public static String getHash(String txt, String hashType) {
        try {
                    java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
                    byte[] array = md.digest(txt.getBytes());
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < array.length; ++i) {
                        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
                 }
                    return sb.toString();
            } catch (java.security.NoSuchAlgorithmException e) {
                //error action
            }
            return null;
    }

    public static String md5(String txt) {
        return getHash(txt, "MD5");
    }

    public static String sha1(String txt) {
        return getHash(txt, "SHA1");
    }
}
