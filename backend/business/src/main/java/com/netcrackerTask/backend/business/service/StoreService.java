package com.netcrackerTask.backend.business.service;

import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Game;
import com.netcrackerTask.backend.business.persistence.GameAccountStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreService {
    private GameAccountStore gameAccountsStore = new GameAccountStore();

    public List<Account> getAllAccounts(long id){
        return gameAccountsStore.getAllById(Account.class, id);
    }

    public List<Game> getAllgames(){
        return gameAccountsStore.getAll(Game.class);
    }

    public String getGameNameById(long id) {return  gameAccountsStore.getGameNameById(id);}

    public <T> T getById(long id, Class<T> clazz){
        return gameAccountsStore.getById(id, clazz); //так или расписывать в сервисе все методы??
    }

    public List<Account> getBagItemsForUser(long id) {
        List<Long> ids = gameAccountsStore.getPurchaseIdForUser(id);
        List<Account> res = new ArrayList<>();
        for(Long i: ids) {
            res.add(gameAccountsStore.getById(i, Account.class));
        }

        return res;
    }
}
