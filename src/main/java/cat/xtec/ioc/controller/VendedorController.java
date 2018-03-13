package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.*;
import cat.xtec.ioc.repository.VendedorDAORepository;
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
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    VendedorDAOService vendedorDAOService;
  //  @Autowired
  //  VendedorDAORepository vendedorDAORepository;

    /*
     TODOS LOS VENDEDORES
     */
    @RequestMapping("/all")
    public ModelAndView allVendedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("vendedores");
        modelview.getModelMap().addAttribute("vendedores", vendedorDAOService.getAllVendedor());
        return modelview;
    }
    
    @RequestMapping("{idVendedor}/delete")
    public String DeleteVendedor(@PathVariable("idVendedor") Integer idVendedor, HttpServletRequest request, HttpServletResponse response){
        Vendedor vendedor=vendedorDAOService.getVendedorByIdVendedor(idVendedor);
        vendedorDAOService.deleteVendedor(vendedor);
    return "redirect:/vendedores/all";
    }
    
    
}
