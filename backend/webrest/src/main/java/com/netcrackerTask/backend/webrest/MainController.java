package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.persistence.GameRepository;
import com.netcrackerTask.backend.business.persistence.UserRepository;
import com.netcrackerTask.backend.business.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class MainController {

    private final GameRepository gameRepository;

    private final UserRepository userRepository;

    @Autowired
    public MainController(final GameRepository gameRepository, final UserRepository userRepository){
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/main")
    public Map<String,Object> main(){
        Map<String,Object> model = new HashMap<>();
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            String name=auth.getName();
            User user= userRepository.findByUsername(name);
            if(user!=null)
                model.put("userid", user.getId());
        }
        model.put("stores", gameRepository.findAll());
        return model;
    }



    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Неверное имя пользователя или пароль");
        if (logout != null)
            model.addAttribute("message", "Вы успешно вышли");
        return "login";
    }



}
