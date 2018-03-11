package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.repository.VendedorDAORepository;
import cat.xtec.ioc.service.InmuebleService;
import cat.xtec.ioc.service.VendedorDAOService;
import java.io.IOException;
import javax.servlet.ServletException;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vendedor")
public class InmuebleController {
    
    @Autowired
    VendedorDAOService vendedorDAOService;
    
    @Autowired
    InmuebleDAORepository inmuebleService;
   
   // @Autowired
   // InmuebleService inmuebleService;
    
    @RequestMapping("/{idVendedor}")
    public ModelAndView allInmueblesByVendedor(@PathVariable("idVendedor") Integer idVendedor, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("InmueblesByVendedor");
        modelview.getModelMap().addAttribute("inmuebles", inmuebleService.getAllInmuebles(idVendedor));
        modelview.getModelMap().addAttribute("vendedor", vendedorDAOService.getVendedorByIdVendedor(idVendedor));
        return modelview;
    }
    
    @RequestMapping("/{idVendedor}/inmueble")
    public ModelAndView getInmuebleById(@RequestParam("idVivienda") Integer idVivienda, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        ModelAndView modelview = new ModelAndView("inmueble");
        
        //String myIdVivienda = request.getParameter("idVivienda");
        Inmueble formInmueble = null;
       // if (idVivienda != null) {
            formInmueble = inmuebleService.getInmuebleById(idVivienda);
       // }
       
        modelview.getModelMap().addAttribute("formInmueble", formInmueble);
        return modelview;
        
    }
    
    @RequestMapping(value ="inmueble/add", method = RequestMethod.POST)
    public String processInmuebleForm(@ModelAttribute("formInmueble") Inmueble formInmueble, BindingResult result){
        inmuebleService.updateInmueble(formInmueble);
        //String redireccion = "/vendedor/" + formInmueble.getIdVivienda();
        return "redirect:/vendedores/all";
    }
    
  
    
}