package com.netcrackerTask.backend.webrest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.implementations.StoreServiceImpl;
import com.netcrackerTask.backend.business.implementations.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final StoreServiceImpl storeService;

    private final UserServiceImpl userService;

    /**
     * Constructor.
     * @param storeService service associated with store
     * @param userService service associated with users
     */
    public UserController(final StoreServiceImpl storeService, final UserServiceImpl userService) {
        this.storeService = storeService;
        this.userService = userService;
    }

    /**
     * Checks if user has verified email or not.
     * @param id user id.
     * @return map with boolean value about verified email
     */
    @GetMapping("/user/email")
    public Map<String,Boolean> checkEmail(@RequestParam("id") Long id){
        Map<String,Boolean> res = new HashMap<>();
        res.put("res",userService.checkEmail(id));
        return res;
    }

    /**
     * Gets accounts that user has already bought
     * @param id user id.
     * @return map with accounts or error message
     */
    @GetMapping("user/purchases")
    public Map<String,Object> getPurchases(@RequestParam("id") Long id){
        Map<String,Object> res = new HashMap<>();
        List<Account> items = storeService.getOrdersForUser(id);
        if(items.size()==0){
            res.put("message", "Вы пока не совершали покупок");
        }
        else {
            res.put("items", items);
        }
        return res;
    }

}
