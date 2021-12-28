package pl.olapp.chat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    //@ResponseBody
    public String mainPage(Model model) {
        return "home";
    }

}
