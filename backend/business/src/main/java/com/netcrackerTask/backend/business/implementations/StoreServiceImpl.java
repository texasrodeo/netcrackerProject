package com.netcrackerTask.backend.business.implementations;

import java.util.ArrayList;
import java.util.List;
import com.netcrackerTask.backend.business.entity.Account;
import com.netcrackerTask.backend.business.entity.Game;
import com.netcrackerTask.backend.business.entity.Purchase;
import com.netcrackerTask.backend.business.persistence.AccountRepository;
import com.netcrackerTask.backend.business.persistence.GameRepository;
import com.netcrackerTask.backend.business.persistence.PurchaseRepository;
import com.netcrackerTask.backend.business.service.interfaces.IStoreService;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class StoreServiceImpl implements IStoreService {

    private static final String FREE_STATUS = "FREE";

    private static final String SOLD_STATUS = "SOLD";

    private static final String CONFIRMED_STATUS = "CONFIRMED";

    private static final String ADDED_TO_BASKET_STATUS = "ADDED_TO_BASKET";

    private static final String RESERVED_STATUS = "RESERVED";

    private static final String DESC_SORT = "desc";

    PurchaseRepository purchaseRepository;

    AccountRepository accountRepository;

    GameRepository gameRepository;

    MailServiceImpl mailService;

    LogServiceImpl logService;

    StandardPBEStringEncryptor standardPBEStringEncryptor;

    @Autowired
    public StoreServiceImpl(final PurchaseRepository purchaseRepository, final AccountRepository accountRepository,
                            final GameRepository gameRepository, final MailServiceImpl mailService,
                            final StandardPBEStringEncryptor standardPBEStringEncryptor, final LogServiceImpl logService) {
        this.purchaseRepository = purchaseRepository;
        this.accountRepository = accountRepository;
        this.gameRepository = gameRepository;
        this.mailService = mailService;
        this.standardPBEStringEncryptor = standardPBEStringEncryptor;
        this.logService = logService;
    }


    public List<Account> getOrdersForUser(long id) {
        return getAccountsByPurchase(purchaseRepository.getPurchaseByUserIdAndStatusEquals(id, CONFIRMED_STATUS));
    }

    public List<Account> getBagItemsForUser(long id) {
        return getAccountsByPurchase(purchaseRepository.getPurchaseByUserIdAndStatusEquals(id, ADDED_TO_BASKET_STATUS));
    }


    public Iterable<Account> getAccountsByGameID(Long id, String from, String to, String sort, Pageable page) {
        if (from != null && to != null) {
            if (sort == null)
                return accountRepository.getAccountsByGameIdAndStatusAndPriceBetween(id, FREE_STATUS, Integer.parseInt(from), Integer.parseInt(to), page);
            else {
                if (sort.equals(DESC_SORT))
                    return accountRepository.getAccountsByGameIdAndStatusAndPriceBetweenOrderByPriceDesc(id, FREE_STATUS, Integer.parseInt(from), Integer.parseInt(to), page);
                else
                    return accountRepository.getAccountsByGameIdAndStatusAndPriceBetweenOrderByPrice(id, FREE_STATUS, Integer.parseInt(from), Integer.parseInt(to), page);
            }
        } else {
            if (sort == null)
                return accountRepository.getAccountsByGameIdAndStatus(id, FREE_STATUS, page);
            else if (sort.equals(DESC_SORT))
                return accountRepository.getAccountsByGameIdAndStatusOrderByPriceDesc(id, FREE_STATUS, page);
            else
                return accountRepository.getAccountsByGameIdAndStatusOrderByPrice(id, FREE_STATUS, page);
        }
    }

    public void addAccount(Account account) {
        account.setPassword(standardPBEStringEncryptor.encrypt(account.getPassword()));
        accountRepository.save(account);
    }

    public Game getGameById(Long id) {
        return gameRepository.getGameById(id);
    }

    public Account getAccountById(Long id) {
        return accountRepository.getAccountById(id);
    }

    public Boolean addPurchase(Long accountId, Long userId) {
        java.util.Date date = new java.util.Date();
        java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());

        Purchase purchase = new Purchase(sqlTime, ADDED_TO_BASKET_STATUS, userId, accountId);

        Account account = accountRepository.getAccountById(accountId);
        if (!account.getStatus().equals(RESERVED_STATUS)) {
            account.setStatus(RESERVED_STATUS);

            accountRepository.save(account);
            purchaseRepository.save(purchase);
            return true;
        }
        return false;
    }

    public List<Account> getAccountsByPurchase(List<Purchase> purchases) {
        List<Account> accountsInBag = new ArrayList<>();
        for (Purchase p : purchases) {
            if (accountRepository.findById(p.getGameAccountId()).isPresent()) {
                accountsInBag.add(accountRepository.findById(p.getGameAccountId()).get());
            }
        }
        return accountsInBag;
    }

    public int getPriceSum(List<Account> accounts) {
        int sum = 0;
        for (Account account : accounts) {
            sum += account.getPrice();
        }
        return sum;
    }

    public void sellAccounts(List<Long> accountsId, String name, String email) {
        StringBuilder sb = new StringBuilder("Здравствуйте, ").append(name).append(".\\n\"")
            .append("Ваши покупки: ").append('\n');
        for (Long id : accountsId) {
            Account account = accountRepository.getAccountById(id);
            sb.append("Описание: ").append(account.getDescription()).append('\n');
            sb.append("Логин: ").append(account.getLogin()).append('\n');
            sb.append("Пароль: ").append(standardPBEStringEncryptor.decrypt(account.getPassword())).append("\n").append("\n");
            sellAccount(account);
        }
        sb.append("С уважением, команда сайта.");
        mailService.send(email, "Покупка аккаунта", sb.toString());
    }

    private void sellAccount(Account account){
        account.setStatus(SOLD_STATUS);
        accountRepository.save(account);
        confirmPurchase(account.getId());
    }

    private void confirmPurchase(Long accountId) {
        Purchase purchase = purchaseRepository.getPurchaseByGameAccountId(accountId);
        purchase.setStatus("CONFIRMED");
        StringBuilder json = new StringBuilder("{\"purchase\":\"").append(purchase.toString()).append("\"}");
        System.out.println(json.toString());
        logService.writeLog(json.toString(), "Purchase");
        purchaseRepository.save(purchase);
    }


    public void removePurchase(Long accountId) {
        purchaseRepository.delete(purchaseRepository.getPurchaseByGameAccountId(accountId));
    }

    public void removeAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
