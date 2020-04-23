package com.netcrackerTask.backend.webrest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Game;
import com.netcrackerTask.backend.business.implementations.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GameStoreController {

    private final StoreServiceImpl storeService;

    /**
     * Constructor.
     * @param storeService service associated with store
     */
    @Autowired
    public GameStoreController(final StoreServiceImpl storeService){
        this.storeService = storeService;
    }

    /**
     * Gets list of game stores
     *@return list of game stores
     */
    @GetMapping("/gamestores")
    public List<Game> gamestores(){
        return storeService.findAllGames();
    }

    /**
     * Gets all accounts in game store
     * @param id store service id
     * @param from minimum accounts price
     * @param to maximum accounts price
     * @param sort type of sort
     *@return map with gamestore name and list of account in it
     */
    @GetMapping("/gamestore")
    public Map<String, Object> gamestore(@RequestParam("id") Long id, @RequestParam(value="from", required = false) String from,
                                         @RequestParam(value="to",required = false) String to, @RequestParam(value="sort",required = false) String sort,
                                         Pageable pageable
                                         )
    {
        Map<String, Object> res = new HashMap<>();
        res.put("header", "Список аккаунтов " + storeService.getGameById(id).getName());
        res.put("accounts", storeService.getAccountsByGameID(id, from, to, sort, pageable));
        return res;
    }

    /**
     * Gets one account
     * @param id account id
     * @return account
     * */
    @GetMapping("/gamestore/account")
    public Account account(@RequestParam("id") Long id)
    {
        return storeService.getAccountById(id);
    }


}
