package com.netcrackerTask.backend.webrest;

import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller

public class AdminController {
    private final StoreService storeService;

    @Autowired
    public AdminController(final StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/admin/addgame")
    @PreAuthorize("hasRole('ADMIN')")
    public String addgame(){
        return "addgame";
    }




    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String main(){
        return "adminPanel";
    }

    @GetMapping("/admin/addaccount")
    @PreAuthorize("hasRole('ADMIN')")
    public String addaccount(Model model){
        model.addAttribute("games", storeService.findAllgames());
        return "addaccount";
    }

    @PostMapping("/admin/addaccount")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, String> sendaccount(@RequestParam("game") String gameId, Model model, Account account){
        Map<String, String> res = new HashMap<>();
        account.setGameId(Long.parseLong(gameId));
        account.setStatus("FREE");
        storeService.addAccount(account);


        res.put("message", "Аккаунт добавлен");
        return res;
    }



}
