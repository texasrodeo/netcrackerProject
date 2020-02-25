package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
   List<Account> getAccountsByGameId(Long id);

   List<Account> getAccountsByGameIdOrderByPriceDesc(Long id);

   List<Account> getAccountsByGameIdOrderByPrice(Long id);

   List<Account> getAccountsByGameIdAndPriceBetween(Long id, Integer from, Integer to);

   List<Account> getAccountsByGameIdAndPriceBetweenOrderByPrice(Long id, Integer from, Integer to);

   List<Account> getAccountsByGameIdAndPriceBetweenOrderByPriceDesc(Long id, Integer from, Integer to);

   Account getAccountsById(Long id);

}
