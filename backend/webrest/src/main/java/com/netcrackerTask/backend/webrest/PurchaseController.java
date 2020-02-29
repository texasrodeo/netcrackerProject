package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Purchase;
import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.service.StoreService;
import com.netcrackerTask.backend.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class PurchaseController {
    private final UserService userService;
    private final StoreService storeService;

    @Autowired
    public PurchaseController(final StoreService storeService, final UserService userService){
        this.storeService = storeService;
        this.userService = userService;
    }

    @GetMapping("/bag")
    public String getBag(Model model, @RequestParam("id") long id){
        List<Purchase> items = storeService.getBagItemsForUser(id);
        if(items.size()==0){
            model.addAttribute("message", "Здесь пока ничего нет");
        }
        else {
            List<Account> accountsInBag = storeService.getAccountsInBag(items);
            model.addAttribute("items", accountsInBag);
        }
        return "bag";
    }

    @GetMapping("/purchase")
    public String getPurchase(Model model, @RequestParam("id") long id){
        model.addAttribute("items", storeService.getPurchaseItemsForUser(id));
        return "purchase";
    }

    @GetMapping("/addtocart")
    public String addtocart(Model model, @RequestParam("id") Long id){
        Account account = storeService.getAccountById(id);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            String name=auth.getName();
            User user= userService.findByUsername(name);
            if(user!=null){
                storeService.addPurchase(id, user.getId());
                model.addAttribute("addedToCart","Акаунт успешно добавлен в корзину");
            }
        }
        return "redirect:/gamestore?id="+account.getGameId();
    }

//    @GetMapping("/bag/add")
//    public String addToBag(Model model, @RequestParam("id") long id){
//        storeService.addToBag(id, )
//        model.addAttribute("items", storeService.getBagItemsForUser(id));
//        return "bag";
//    }


}
