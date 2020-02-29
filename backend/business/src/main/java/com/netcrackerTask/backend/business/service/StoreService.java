package com.netcrackerTask.backend.business.service;

import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Game;
import com.netcrackerTask.backend.business.entity.Purchase;
import com.netcrackerTask.backend.business.persistence.AccountRepository;
import com.netcrackerTask.backend.business.persistence.GameRepository;
import com.netcrackerTask.backend.business.persistence.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

    public List<Purchase> getBagItemsForUser(long id) {
        return purchaseRepository.getPurchaseByUserIdAndStatusEquals(id, 0);
    }

    public Iterable<Game> findAllgames() {
        return gameRepository.findAll();
    }

    public Iterable<Account> getAccountsByGameID(Long id, Integer from, Integer to, String sort){
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

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public Game getGameById(Long id) {
        return gameRepository.getGameById(id);
    }

    public Account getAccountById(Long id){
        return accountRepository.getAccountById(id);
    }

    public void addPurchase(Long accountId, Long userId) {
        java.util.Date date=new java.util.Date();

        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());

        Purchase purchase = new Purchase(sqlTime, "ADDED_TO_BASKET", userId, accountId);

        Account account = accountRepository.getAccountById(accountId);
        account.setStatus("RESERVED");

        accountRepository.save(account);
        purchaseRepository.save(purchase);
    }

    public List<Account> getAccountsInBag(List<Purchase> purchases) {
        List<Account> accountsInBag = new ArrayList<>();
        for(Purchase p: purchases){
            if(accountRepository.findById(p.getGameAccountId()).isPresent()){
                accountsInBag.add(accountRepository.findById(p.getGameAccountId()).get());
            }
        }
        return accountsInBag;
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
