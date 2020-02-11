package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class GameStoreController {
    private final StoreService storeService;

    @Autowired
    public GameStoreController(final StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/gamestore")
    public String gamestore(Model model, @RequestParam("id") long id)
    {
        if(id == 0){
            model.addAttribute("header", "Список всех аккунтов");
        }
        else {
            model.addAttribute("header", "Список аккаунтов " + storeService.getGameNameById(id));
        }

        model.addAttribute("accounts", storeService.getAllAccounts(id));
        return "gamestore";
    }

    @GetMapping("/gamestore/account")
    public String account(Model model, @RequestParam("id") long id)
    {
        model.addAttribute("account", storeService.getById(id, Account.class));
//        model.addAttribute("gamename")
        return "account";
    }
}
