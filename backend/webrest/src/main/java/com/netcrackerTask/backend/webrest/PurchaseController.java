package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class PurchaseController {
    private final StoreService storeService;

    @Autowired
    public PurchaseController(final StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/bag")
    public String getBag(Model model, @RequestParam("id") long id){
        model.addAttribute("items", storeService.getBagItemsForUser(id));
        return "bag";
    }

    @GetMapping("/purchese")
    public String getPurchase(Model model, @RequestParam("id") long id){
        model.addAttribute("items", storeService.getPurchaseItemsForUser(id));
        return "purchase";
    }

//    @GetMapping("/bag/add")
//    public String addToBag(Model model, @RequestParam("id") long id){
//        storeService.addToBag(id, )
//        model.addAttribute("items", storeService.getBagItemsForUser(id));
//        return "bag";
//    }


}
