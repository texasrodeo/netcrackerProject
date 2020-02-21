package com.netcrackerTask.backend.webrest;

import com.netcrackerTask.backend.business.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class AdminController {
    private final StoreService storeService;

    @Autowired
    public AdminController(final StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/admin/addgame")
    public String addgame(){
        return "addgame";
    }

    @GetMapping("/admin")
    public String main(){
        return "adminPanel";
    }


}
