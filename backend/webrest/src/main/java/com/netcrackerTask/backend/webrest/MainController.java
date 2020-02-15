package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.persistence.GameRepository;
import com.netcrackerTask.backend.business.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Controller

public class MainController {

  //  private final StoreService storeService;

//    @Autowired
    private final GameRepository gameRepository;

    @Autowired
    public MainController(final GameRepository gameRepository){
        //this.storeService = storeService;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/main")
    public String main(Model model){
        model.addAttribute("stores", gameRepository.findAll());
        return "main";
    }

    @GetMapping("/categories")
    public String games(Model model){
        return "games";
    }


}
