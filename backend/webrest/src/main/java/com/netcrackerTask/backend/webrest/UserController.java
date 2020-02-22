package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class UserController {

    private final StoreService storeService;

    public UserController(final StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/user/profile")
    public String profile(){
        return "profile";
    }




}
