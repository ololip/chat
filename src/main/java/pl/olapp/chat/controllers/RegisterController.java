package pl.olapp.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.olapp.chat.dto.NewUser;
import pl.olapp.chat.services.UserService;

@Controller
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(value = "/register")
    public String postRegister(@ModelAttribute("user") @Validated NewUser user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "register";
        }
        if(userService.userExist(user)){
            bindingResult.rejectValue("login", "error.user", "Użytkownik o takim loginie już istnieje");
            return "register";
        }
        if(!user.getPassword().equals(user.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword", "error.user", "Hasła muszą być takie same");
            return "register";
        }
        userService.createUser(user);
        return "home";
    }

    @GetMapping(value = "/register")
    public String getMapping(Model model){
        model.addAttribute("user", new NewUser());
        return "register";
    }
}
