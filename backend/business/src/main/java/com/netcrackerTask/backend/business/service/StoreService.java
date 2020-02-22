package com.netcrackerTask.backend.business.service;

import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Game;
import com.netcrackerTask.backend.business.entity.Purchase;
import com.netcrackerTask.backend.business.persistence.AccountRepository;
import com.netcrackerTask.backend.business.persistence.GameAccountStore;
import com.netcrackerTask.backend.business.persistence.GameRepository;
import com.netcrackerTask.backend.business.persistence.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    GameRepository gameRepository;

    public Purchase getPurchaseItemsForUser(long id) {
        return purchaseRepository.getPurchaseByUserId(id);
    }

    public Purchase getBagItemsForUser(long id) {
        return purchaseRepository.getBagItemsForUser(id);
    }

    public Iterable<Game> findAllgames() {
        return gameRepository.findAll();
    }

    public Iterable<Account> getAccountsByGameID(long id, Integer from, Integer to, String sort){

            if(from!= null && to !=null){
                if(sort==null)
                    return accountRepository.getAccountsByGameIdAndPriceBetween(id, from, to);
                else {
                    if(sort.equals("desc"))
                        return accountRepository.getAccountsByGameIdAndPriceBetweenOrderByPriceDesc(id, from, to);
                    else
                        return accountRepository.getAccountsByGameIdAndPriceBetweenOrderByPrice(id, from, to);
                }



            }
            else {
                if(sort==null)
                    return accountRepository.findAll();
                else  if(sort.equals("desc"))
                    return accountRepository.getAccountsByGameIdOrderByPriceDesc(id);
                else
                    return accountRepository.getAccountsByGameIdOrderByPrice(id);
            }





    }

    public boolean addAccount(Account account) {
        accountRepository.save(account);
        return true;
    }

    public Game getGameById(long id) {
        return gameRepository.getGameById(id);
    }


//    private GameAccountStore gameAccountsStore = new GameAccountStore();
//
//    public List<Account> getAllAccounts(long id){
//        return gameAccountsStore.getAllById(Account.class, id);
//    }
//
//    public List<Game> getAllgames(){
//        return gameAccountsStore.getAll(Game.class);
//    }
//
//    public String getGameNameById(long id) {return  gameAccountsStore.getGameNameById(id);}
//
//    public <T> T getById(long id, Class<T> clazz){
//        return gameAccountsStore.getById(id, clazz); //так или расписывать в сервисе все методы??
//    }
//
//    public List<Account> getBagItemsForUser(long id) {
//        List<Long> ids = gameAccountsStore.getPurchaseIdForUser(id);
//        List<Account> res = new ArrayList<>();
//        for(Long i: ids) {
//            res.add(gameAccountsStore.getById(i, Account.class));
//        }
//
//        return res;
//    }
}
