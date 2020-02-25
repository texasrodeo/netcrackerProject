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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class GameStoreController {


    private final StoreService storeService;

    private final UserService userService;

    @Autowired
    public GameStoreController(final StoreService storeService, final UserService userService){
        this.storeService = storeService;
        this.userService = userService;
    }

    @GetMapping("/gamestore")
    public String gamestore(Model model, @RequestParam("id") Long id,
                            @RequestParam(value="from", required = false) Integer from,
                            @RequestParam(value="to",required = false) Integer to,
                            @RequestParam(value="sort",required = false) String sort)
    {
        if(id == 0){
            model.addAttribute("header", "Список всех аккунтов");
        }
        else {
            model.addAttribute("header", "Список аккаунтов " + storeService.getGameById(id).getName());
        }
        model.addAttribute("accounts", storeService.getAccountsByGameID(id, from, to, sort));
        model.addAttribute("storeId",id);
        return "gamestore";
    }

    @GetMapping("/gamestore/account")
    public String account(Model model, @RequestParam("id") Long id)
    {
        model.addAttribute("account", storeService.getAccountById(id));
        return "account";
    }


}
