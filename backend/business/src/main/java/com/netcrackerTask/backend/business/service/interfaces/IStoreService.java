package com.netcrackerTask.backend.business.service.interfaces;

import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Game;
import com.netcrackerTask.backend.business.entity.Purchase;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IStoreService {
    List<Account> getOrdersForUser(long id);

    List<Account> getBagItemsForUser(long id);

    Iterable<Account> getAccountsByGameID(Long id, String from, String to, String sort, Pageable page);

    void addAccount(Account account);

    Game getGameById(Long id);

    Account getAccountById(Long id);

    Boolean addPurchase(Long accountId, Long userId);

    List<Account> getAccountsByPurchase(List<Purchase> purchases);

    int getPriceSum(List<Account> accounts);

    void sellAccounts(List<Long> accountsId, String name, String email);



    void removePurchase(Long accountId);

    void removeAccount(Long accountId);

}
