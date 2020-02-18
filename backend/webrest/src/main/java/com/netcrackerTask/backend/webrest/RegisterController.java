package com.netcrackerTask.backend.webrest;

import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.persistence.UserRepository;
import com.netcrackerTask.backend.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
//        user.setRoles(Collections.singleton(Role.ROLE_USER));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
       return "redirect:/login";
    }



}
