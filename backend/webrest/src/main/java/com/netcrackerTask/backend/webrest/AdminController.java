package com.netcrackerTask.backend.webrest;

import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.payloads.AddAccRequest;
import com.netcrackerTask.backend.business.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
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



}
