package com.netcrackerTask.backend.webrest;

import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.persistence.UserRepository;
import com.netcrackerTask.backend.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/signin")
    public Map<String, String> logout(@RequestHeader("username") String username,
                                      @RequestHeader("password") String password){
        Map<String,String> result = new HashMap<>();
        User u = userService.findByUsername(username);
        if(u!=null){
            result.put("username",username);
            result.put("password",password);
        }
        else{
            result.put("username", null);
            result.put("password", null);
            result.put("message", "Неверное имя пользователя");
        }

        return result;
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        model.addAttribute("email","Для подтреждения почты, перейдите по ссылке, отправленной Вам на почту.");
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
