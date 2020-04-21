package com.netcrackerTask.backend.business.service.interfaces;

import java.util.List;
import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Game;
import com.netcrackerTask.backend.business.entity.Purchase;
import org.springframework.data.domain.Pageable;

public interface IStoreService {

    /**
     * Returns all game stores
     *
     * @return list of games tores
     * */
    List<Game> findAllGames();

    /**
     * Returns accounts that user has already bought
     * @param id user id
     * @return list of accounts
     * */
    List<Account> getOrdersForUser(long id);

    /**
     * Returns accounts that user has aput in bag
     * @param id user id
     * @return list of accouts
     * */
    List<Account> getBagItemsForUser(long id);

    /**
     * Returns accounts in game store with sort
     * @param id user game id
     * @param from minimum price
     * @param to maximum price
     *  @param sort type of sort
     * @param page number of page and amount od accounts on it
     * @return list of accouts
     * */
    Iterable<Account> getAccountsByGameID(Long id, String from, String to, String sort, Pageable page);

    /**
     * Adds account
     * @param account account
     * */
    void addAccount(Account account);

    /**
     * Returns game by its id
     * @param id game id
     * @return game
     * */
    Game getGameById(Long id);

    /**
     * Returns account by its id
     * @param id account id
     * @return account
     * */
    Account getAccountById(Long id);

    /**
     * Adds purchase to database
     * @param accountId account id
     * @param userId id of user who had made this purchase
     * @return boolean status of operation
     * */
    Boolean addPurchase(Long accountId, Long userId);

    /**
     * Returns accounts that are is user purchases
     * @param purchases list od user purchases
     * @return list of accounts
     * */
    List<Account> getAccountsByPurchase(List<Purchase> purchases);

    /**
     * Returns summary price of bought accounts
     * @param accounts list od accounts
     * @return price
     * */
    int getPriceSum(List<Account> accounts);

    /**
     * Sells account to user
     * @param accountsId list of sold accounts id
     * @param name user name
     * @param email user email
     * @return list of accouts
     * */
    void sellAccounts(List<Long> accountsId, String name, String email);

    /**
     * Removes purchase from database
     * @param accountId account in purchase id
     * */
    void removePurchase(Long accountId);

    /**
     * Removes account from database
     * @param accountId account id
     * */
    void removeAccount(Long accountId);

    /**
     * Removes all accounts from user bag
     * @param itemsId list of accounts ids in bag
     * */
    void clearBag(List<Long> itemsId);
}
