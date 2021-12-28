package pl.olapp.chat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.olapp.chat.dto.LoginUser;

@Controller
@SessionAttributes("loggedUser")
public class UserPageController {

    @GetMapping(value = "/user_page")
    public String userPage(Model model, @SessionAttribute("loggedUser") LoginUser user){
        model.addAttribute("user", user.getLogin());
        return "user_page";
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public String handle(){
        return "redirect:/login";
    }
}
