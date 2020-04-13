package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.service.implementations.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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


    @Autowired
    public GameStoreController(final StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/gamestore")
    public Map<String, Object> gamestore(@RequestParam("id") Long id,
                                         @RequestParam(value="from", required = false) String from,
                                         @RequestParam(value="to",required = false) String to,
                                         @RequestParam(value="sort",required = false) String sort,
                                          Pageable pageable
                                         )
    {
        Map<String, Object> res = new HashMap<>();
        res.put("header", "Список аккаунтов " + storeService.getGameById(id).getName());

        res.put("accounts", storeService.getAccountsByGameID(id, from, to, sort, pageable));
        return res;
    }

    @GetMapping("/gamestore/account")
    public Account account(@RequestParam("id") Long id)
    {
        return storeService.getAccountById(id);
    }


}
