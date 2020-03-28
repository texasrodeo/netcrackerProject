package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
   List<Account> getAccountsByGameIdAndStatus(Long id, String status);

   List<Account> getAccountsByGameIdAndStatusOrderByPriceDesc(Long id, String status);

   List<Account> getAccountsByGameIdAndStatusOrderByPrice(Long id, String status);

   List<Account> getAccountsByGameIdAndStatusAndPriceBetween(Long id, String status, Integer from, Integer to);

   List<Account> getAccountsByGameIdAndStatusAndPriceBetweenOrderByPrice(Long id, String status, Integer from, Integer to);

   List<Account> getAccountsByGameIdAndStatusAndPriceBetweenOrderByPriceDesc(Long id, String status, Integer from, Integer to);

   Account getAccountById(Long id);

}
