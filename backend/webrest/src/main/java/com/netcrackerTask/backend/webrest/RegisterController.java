package com.netcrackerTask.backend.webrest;

import com.netcrackerTask.backend.business.entity.Role;
import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegisterController {


    private UserRepository userRepository;

    @Autowired
    public RegisterController(final UserRepository storeService){
        this.userRepository = storeService;
    }

    @GetMapping("/registration")
    public String register(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Model model){
        if(userRepository.findByUsername(user.getUsername())!=null){
            model.addAttribute("message", "Такой пользователь уже зарегестрирован!");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
       return "redirect:/login";
    }
}
