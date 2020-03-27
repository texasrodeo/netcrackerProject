package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Purchase;
import com.netcrackerTask.backend.business.service.StoreService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class UserController {

    private final StoreService storeService;

    public UserController(final StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasRole('USER')")
    public String profile(){
        return "profile";
    }

    @GetMapping("user/orders")
    @PreAuthorize("hasRole('USER')")
    public String getOrderd(Model model, @RequestParam("id") long id){
        List<Account> items = storeService.getOrdersForUser(id);
        if(items.size()==0){
            model.addAttribute("message", "Здесь пока ничего нет");
        }
        else {
            model.addAttribute("items", items);
        }
        return "orders";
    }




}
