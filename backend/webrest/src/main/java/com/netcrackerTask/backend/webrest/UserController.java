package com.netcrackerTask.backend.webrest;


import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.service.implementations.StoreService;
import com.netcrackerTask.backend.business.service.implementations.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final StoreService storeService;

    private final UserService userService;

    private static final String NO_PURCHASES= "Вы пока не совершали покупок";

    public UserController(final StoreService storeService, final UserService userService) {
        this.storeService = storeService;
        this.userService = userService;
    }


    @GetMapping("/user/email")
    @PreAuthorize("hasRole('USER')")
    public Map<String, Object> checkEmail(@RequestParam("id") String id) {
        Map<String, Object> res = new HashMap<>();
        res.put("res", userService.checkEmail(Long.parseLong(id)));
        return res;
    }

    @GetMapping("user/purchases")
    @PreAuthorize("hasRole('USER')")
    public Map<String, Object> getPurchases(@RequestParam("id") long id) {
        Map<String, Object> res = new HashMap<>();
        List<Account> items = storeService.getOrdersForUser(id);

        if (items.size() == 0) {
            res.put("message", NO_PURCHASES);
        } else {
            res.put("items", items);
        }
        return res;
    }

}
