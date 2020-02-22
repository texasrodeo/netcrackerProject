package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
   List<Account> getAccountsByGameId(Long id);

   List<Account> getAccountsByGameIdOrderByPriceDesc(long id);

   List<Account> getAccountsByGameIdOrderByPrice(long id);

   List<Account> getAccountsByGameIdAndPriceBetween(long id, int from, int to);

   List<Account> getAccountsByGameIdAndPriceBetweenOrderByPrice(long id, int from, int to);

   List<Account> getAccountsByGameIdAndPriceBetweenOrderByPriceDesc(long id, int from, int to);

}
