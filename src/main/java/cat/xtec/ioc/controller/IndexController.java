package cat.xtec.ioc.controller;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Random rn = new Random();
        int imageCode = rn.nextInt(5) - 1;
        ModelAndView modelview = new ModelAndView("index");
        modelview.getModelMap().addAttribute("benvinguda", "Benvingut a Control domòtic de l'habitatge");
        modelview.getModelMap().addAttribute("imageCode", imageCode);
        return modelview;
    }

}
