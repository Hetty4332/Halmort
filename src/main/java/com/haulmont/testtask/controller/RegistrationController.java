package com.haulmont.testtask.controller;



import com.haulmont.testtask.model.User;
import com.haulmont.testtask.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserDetailsServiceImpl userService;


    @GetMapping("/login")
    public String login(){

        return "login";

    }
/*
    @GetMapping("/loginError")
    public String loginError(Throwable throwable, Model model) {
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        return "loginError";
    }
*/


    @GetMapping("/")
    public String getMain(Model model){

        return "main";

    }
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

    /*    if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }*/
        /* if (!*/
        userService.saveUser(userForm);
          /* ){ model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }*/

        return "redirect:/login";
    }
}