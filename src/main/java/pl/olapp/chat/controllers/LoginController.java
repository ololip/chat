package pl.olapp.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.olapp.chat.dto.LoginUser;
import pl.olapp.chat.services.UserService;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("loggedUser")
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public String postLogin(Model model, @ModelAttribute("user") @Validated LoginUser user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "login";
        }
        if(userService.loginUser(user)){
            model.addAttribute("loggedUser", user);
            return "redirect:user_page";
        }
        bindingResult.rejectValue("login", "error.user", "Nieprawidłowy login lub hasło");
        return "login";
    }

    @GetMapping(value = "/login")
    public String getLogin(Model model){
        if(model.containsAttribute("loggedUser"))
            return "redirect:user_page";

        model.addAttribute("user", new LoginUser());
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout(Model model, HttpSession session, SessionStatus status){

        status.setComplete();
        session.invalidate();
        return "redirect:/";
    }
}
