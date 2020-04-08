package com.netcrackerTask.backend.business.service;

import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Game;
import com.netcrackerTask.backend.business.entity.Purchase;
import com.netcrackerTask.backend.business.persistence.AccountRepository;
import com.netcrackerTask.backend.business.persistence.GameRepository;
import com.netcrackerTask.backend.business.persistence.PurchaseRepository;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.acl.LastOwnerException;
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

    @Autowired
    MailService mailService;

    @Autowired
    StandardPBEStringEncryptor standardPBEStringEncryptor;




    public List<Account> getOrdersForUser(long id) {
        return getAccountsByPurchase(purchaseRepository.getPurchaseByUserIdAndStatusEquals(id, "CONFIRMED"));
    }

    public List<Account> getBagItemsForUser(long id) {
        return getAccountsByPurchase(purchaseRepository.getPurchaseByUserIdAndStatusEquals(id, "ADDED_TO_BASKET"));
    }

    public Iterable<Game> findAllgames() {
        return gameRepository.findAll();
    }

    public Iterable<Account> getAccountsByGameID(Long id, String from, String to, String sort){
            if(from!=null && to!=null){
                if(sort==null)
                    return accountRepository.getAccountsByGameIdAndStatusAndPriceBetween(id, "FREE", Integer.parseInt(from), Integer.parseInt(to));
                else {
                    if(sort.equals("desc"))
                        return accountRepository.getAccountsByGameIdAndStatusAndPriceBetweenOrderByPriceDesc(id, "FREE",  Integer.parseInt(from), Integer.parseInt(to));
                    else
                        return accountRepository.getAccountsByGameIdAndStatusAndPriceBetweenOrderByPrice(id, "FREE", Integer.parseInt(from), Integer.parseInt(to));
                }
            }
            else {
                if(sort==null)
                    return accountRepository.getAccountsByGameIdAndStatus(id,"FREE");
                else  if(sort.equals("desc"))
                    return accountRepository.getAccountsByGameIdAndStatusOrderByPriceDesc(id, "FREE");
                else
                    return accountRepository.getAccountsByGameIdAndStatusOrderByPrice(id, "FREE");
            }
    }

    public void addAccount(Account account) {
        account.setPassword(standardPBEStringEncryptor.encrypt(account.getPassword()));
        accountRepository.save(account);
    }

    public Game getGameById(Long id) {
        return gameRepository.getGameById(id);
    }

    public Account getAccountById(Long id){
        return accountRepository.getAccountById(id);
    }

    public Boolean addPurchase(Long accountId, Long userId) {
        java.util.Date date=new java.util.Date();

       // java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());

        Purchase purchase = new Purchase(sqlTime, "ADDED_TO_BASKET", userId, accountId);

        Account account = accountRepository.getAccountById(accountId);
        if(!account.getStatus().equals("RESERVED")){
            account.setStatus("RESERVED");

            accountRepository.save(account);
            purchaseRepository.save(purchase);
            return true;
        }
        return false;
    }

    public List<Account> getAccountsByPurchase(List<Purchase> purchases) {
        List<Account> accountsInBag = new ArrayList<>();
        for(Purchase p: purchases){
            if(accountRepository.findById(p.getGameAccountId()).isPresent()){
                accountsInBag.add(accountRepository.findById(p.getGameAccountId()).get());
            }
        }
        return accountsInBag;
    }

    public int getPriceSum(List<Account> accounts){
        int sum = 0;
        for(Account account:accounts){
            sum+=account.getPrice();
        }
        return sum;
    }


    public void sellAccounts(List<Long> accountsId, String name, String email) {
        StringBuilder sb = new StringBuilder("Здравствуйте, ").append(name).append(".")
                .append("\n").append("Ваши покупки: ").append("\n");
        for(Long id: accountsId){
            Account account = accountRepository.getAccountById(id);
            sb.append("Описание: ").append(account.getDescription()).append("\n");
            sb.append("Логин: ").append(account.getLogin()).append("\n");
            sb.append("Пароль: ").append(standardPBEStringEncryptor.decrypt(account.getPassword())).append("\n").append("\n");
            account.setStatus("SOLD");
            accountRepository.save(account);
            confirmPurchase(account.getId());
        }
        sb.append("С уважением, команда сайта.");
        mailService.send(email, "Покупка аккаунта", sb.toString());
    }

    private void confirmPurchase(Long accountId){
        Purchase purchase = purchaseRepository.getPurchaseByGameAccountId(accountId);
        purchase.setStatus("CONFIRMED");
        purchaseRepository.save(purchase);
    }

    public void removePurchase(Long accountId) {
        purchaseRepository.delete(purchaseRepository.getPurchaseByGameAccountId(accountId));
    }
}
