package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Purchase;
import com.netcrackerTask.backend.business.service.StoreService;
import com.netcrackerTask.backend.business.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final StoreService storeService;
    private final UserService userService;

    public UserController(final StoreService storeService, final UserService userService) {
        this.storeService = storeService;
        this.userService = userService;
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasRole('USER')")
    public String profile(){
        return "profile";
    }

    @GetMapping("/user/email")
    public Map<String,Object> checkEmail(@RequestParam("id") String id){
        Map<String,Object> res = new HashMap<>();
        res.put("res",userService.checkEmail(Long.parseLong(id)));
        return res;
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
