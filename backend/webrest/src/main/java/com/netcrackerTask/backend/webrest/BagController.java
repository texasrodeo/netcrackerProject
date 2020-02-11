package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class BagController {
    private final StoreService storeService;

    @Autowired
    public BagController(final StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/bag")
    public String bag(Model model, @RequestParam("id") long id){
        model.addAttribute("items", storeService.getBagItemsForUser(id));
        return "bag";
    }

//    @GetMapping("/bag/add")
//    public String addToBag(Model model, @RequestParam("id") long id){
//        storeService.addToBag(id, )
//        model.addAttribute("items", storeService.getBagItemsForUser(id));
//        return "bag";
//    }


}
