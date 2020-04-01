package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.persistence.AccountRepository;
import com.netcrackerTask.backend.business.persistence.GameRepository;
import com.netcrackerTask.backend.business.service.StoreService;

import com.netcrackerTask.backend.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GameStoreController {


    private final StoreService storeService;

    private final UserService userService;

    @Autowired
    public GameStoreController(final StoreService storeService, final UserService userService){
        this.storeService = storeService;
        this.userService = userService;
    }

    @GetMapping("/gamestore")
    public Map<String, Object> gamestore(@RequestParam("id") Long id,
                                         @RequestParam(value="from", required = false) Integer from,
                                         @RequestParam(value="to",required = false) Integer to,
                                         @RequestParam(value="sort",required = false) String sort)
    {
        Map<String, Object> res = new HashMap<>();
        if(id == 0){
            res.put("header", "Список всех аккунтов");
        }
        else {
            res.put("header", "Список аккаунтов " + storeService.getGameById(id).getName());
        }
        res.put("accounts", storeService.getAccountsByGameID(id, from, to, sort));
        res.put("storeId",id);
        return res;
    }

    @GetMapping("/gamestore/account")
    public Account account(Model model, @RequestParam("id") Long id)
    {
//        model.addAttribute("account" s,toreService.getAccountById(id));
//        return "account";
        return storeService.getAccountById(id);
    }


}
