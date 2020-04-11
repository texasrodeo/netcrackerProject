package com.netcrackerTask.backend.webrest;

import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.payloads.AddAccRequest;
import com.netcrackerTask.backend.business.service.ServiceImpl.StoreService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AdminController {
    private final StoreService storeService;

    @Autowired
    public AdminController(final StoreService storeService){
        this.storeService = storeService;
    }


    @PostMapping("/admin/addaccount")
    public Map<String, String> addaccount(@RequestBody AddAccRequest request){
        Map<String, String> res = new HashMap<>();
        Account account= new Account();
        account.setGameId(Long.parseLong(request.getGameId()));
        account.setDescription(request.getDescription());
        account.setPrice(Integer.parseInt(request.getPrice()));
        account.setLogin(request.getLogin());
        account.setPassword(request.getPassword());
        account.setStatus("FREE");
        storeService.addAccount(account);
        res.put("message", "Аккаунт добавлен");
        return res;
    }

    @GetMapping("/admin/removeAccount")
    public Map<String, String> removeAccount(@RequestParam ("accountId") String id){
        Map<String, String> res = new HashMap<>();
        storeService.removeAccount(Long.parseLong(id));
        res.put("message", "Аккаунт удален");
        return res;
    }



}
