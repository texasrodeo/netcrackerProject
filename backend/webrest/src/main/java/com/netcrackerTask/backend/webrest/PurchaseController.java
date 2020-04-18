package com.netcrackerTask.backend.webrest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.implementations.StoreServiceImpl;
import com.netcrackerTask.backend.business.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class PurchaseController {

    private final StoreServiceImpl storeService;

    /**
     * Constructor.
     * @param storeService service associated with store
     */
    @Autowired
    public PurchaseController(final StoreServiceImpl storeService){
        this.storeService = storeService;
    }

    /**
     * Gets accounts that user has put in his bag
     *@param id user id
     * @return map with accounts and their summary price
     */
    @GetMapping("/bag")
    public Map<String,Object> getBag(@RequestParam ("id") Long id){
        Map<String,Object> result = new HashMap<>();
        List<Account> accounts = storeService.getBagItemsForUser(id);
        result.put("accounts", accounts);
        result.put("sum", storeService.getPriceSum(accounts));
        return result;
    }

    /**
     * Add account to user bag
     * @param id account id
     *@param userId user id
     *@return message about operation success
     */
    @GetMapping("/addtocart")
    public Map<String,String> addtocart(@RequestParam("id") Long id, @RequestParam ("userId") Long userId){
        Map<String, String> result = new HashMap<>();
        if(storeService.addPurchase(id, userId))
            result.put("message","Аккаунт успешно доабвлен в корзину") ;
        else
            result.put("message", "Ошибка: аккаунт уже зарезервирован.");
        return result;
    }

    /**
     * Removes account from bag
     * @param accountId account id
     */
    @GetMapping("bag/removePurchase")
    public void remove(@RequestParam("id") Long accountId){
        storeService.removePurchase(accountId);
    }

}
