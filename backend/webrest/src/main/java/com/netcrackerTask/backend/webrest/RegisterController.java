package com.netcrackerTask.backend.webrest;

import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.persistence.UserRepository;
import com.netcrackerTask.backend.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegisterController {
    private UserService userService;

    @Autowired
    public RegisterController(final UserService userService){
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String register(){
        return "registration";
    }

//    @GetMapping("/logout")
//    public String logout(Model model ){
//        model.addAttribute("logout","Вы успешно вышли");
//        return "main";
//    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        model.addAttribute("email","Для подтреждения почты, проверьте свой почтовый ящик");
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if(isActivated){
            model.addAttribute("message", "Успешно подтверждено");
        }
        else {
            model.addAttribute("message", "Неверный код");
        }
        return "redirect:/login";
    }



}
