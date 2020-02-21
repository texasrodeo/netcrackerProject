package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final StoreService storeService;

    public UserController(final StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }
}
