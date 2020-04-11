package com.netcrackerTask.backend.business.service.ServiceImpl;

import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Game;
import com.netcrackerTask.backend.business.entity.Purchase;
import com.netcrackerTask.backend.business.persistence.AccountRepository;
import com.netcrackerTask.backend.business.persistence.GameRepository;
import com.netcrackerTask.backend.business.persistence.PurchaseRepository;
import com.netcrackerTask.backend.business.service.Interfaces.IStoreService;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService implements IStoreService {


    PurchaseRepository purchaseRepository;

    AccountRepository accountRepository;

    GameRepository gameRepository;

    MailService mailService;

    StandardPBEStringEncryptor standardPBEStringEncryptor;

    @Autowired
    public StoreService(final PurchaseRepository purchaseRepository, final AccountRepository accountRepository,
                        final GameRepository gameRepository, final MailService mailService,
                        final StandardPBEStringEncryptor standardPBEStringEncryptor){
        this.purchaseRepository = purchaseRepository;
        this. accountRepository = accountRepository;
        this.gameRepository = gameRepository;
        this.mailService = mailService;
        this.standardPBEStringEncryptor = standardPBEStringEncryptor;
    }




    public List<Account> getOrdersForUser(long id) {
        return getAccountsByPurchase(purchaseRepository.getPurchaseByUserIdAndStatusEquals(id, "CONFIRMED"));
    }

    public List<Account> getBagItemsForUser(long id) {
        return getAccountsByPurchase(purchaseRepository.getPurchaseByUserIdAndStatusEquals(id, "ADDED_TO_BASKET"));
    }




    public Iterable<Account> getAccountsByGameID(Long id, String from, String to, String sort, Pageable page){
            if(from!=null && to!=null){
                if(sort==null)
                    return accountRepository.getAccountsByGameIdAndStatusAndPriceBetween(id, "FREE", Integer.parseInt(from), Integer.parseInt(to), page);
                else {
                    if(sort.equals("desc"))
                        return accountRepository.getAccountsByGameIdAndStatusAndPriceBetweenOrderByPriceDesc(id, "FREE",  Integer.parseInt(from), Integer.parseInt(to), page);
                    else
                        return accountRepository.getAccountsByGameIdAndStatusAndPriceBetweenOrderByPrice(id, "FREE", Integer.parseInt(from), Integer.parseInt(to), page);
                }
            }
            else {
                if(sort==null)
                    return accountRepository.getAccountsByGameIdAndStatus(id,"FREE", page);
                else if(sort.equals("desc"))
                    return accountRepository.getAccountsByGameIdAndStatusOrderByPriceDesc(id, "FREE", page);
                else
                    return accountRepository.getAccountsByGameIdAndStatusOrderByPrice(id, "FREE", page);
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

    private void writePurchaseLog(Purchase purchase){

    }

    public void removePurchase(Long accountId) {
        purchaseRepository.delete(purchaseRepository.getPurchaseByGameAccountId(accountId));
    }

    public void removeAccount(Long accountId){
        accountRepository.deleteById(accountId);
    }
}
