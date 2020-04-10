package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.entity.Game;
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

import java.security.Principal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

    private final GameRepository gameRepository;


    @Autowired
    public MainController(final GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @GetMapping("/gamestores")
    public List<Game> main(){
        return (List<Game>) gameRepository.findAll();
    }


}
