package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Purchase;
import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.service.StoreService;
import com.netcrackerTask.backend.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class PurchaseController {
    private final UserService userService;
    private final StoreService storeService;

    @Autowired
    public PurchaseController(final StoreService storeService, final UserService userService){
        this.storeService = storeService;
        this.userService = userService;
    }

    @GetMapping("/bag")
    @PreAuthorize("hasRole('USER')")
    public List<Account> getBag(@RequestParam("id") Long id){
        List<Account> items = storeService.getBagItemsForUser(id);
        return items;
    }


    @GetMapping("/addtocart")
    @PreAuthorize("hasRole('USER')")
    public Map<String,String> addtocart(@RequestParam("id") Long id){
        Account account = storeService.getAccountById(id);
        Map<String, String> result = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            String name=auth.getName();
            User user= userService.findByUsername(name);
            if(user!=null){
                storeService.addPurchase(id, user.getId());
                //model.addAttribute("addedToCart","Акаунт успешно добавлен в корзину");
                result.put("message","Аккаунт успешно доабвлен в корзину") ;
                return result;
            }
        }
        result.put("message", "Ошибка");
        return result;
    }

    @GetMapping("bag/removePurchase")
    @PreAuthorize("hasRole('USER')")
    public String remove(@RequestParam("id") Long accountId){

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();

        String name = auth.getName();
        User user = userService.findByUsername(name);

        storeService.removePurchase(accountId);
        return "redirect:/bag?id="+user.getId().toString();
    }



}
