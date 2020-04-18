package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
   /**
    * Gets page of accounts by game id and special status
    * @param id game id
    * @param status accounts status
    * @param pageable page size and amount of accounts in it
    * @return page of accounts
    * */
   Page<Account> getAccountsByGameIdAndStatus(Long id, String status, Pageable pageable);

   /**
    * Gets page of accounts by game id and special status order by price decrease
    * @param id game id
    * @param status accounts status
    * @param pageable page size and amount of accounts in it
    * @return page of accounts
    * */
   Page<Account> getAccountsByGameIdAndStatusOrderByPriceDesc(Long id, String status, Pageable pageable);

   /**
    * Gets page of accounts by game id and special status order by price increase
    * @param id game id
    * @param status accounts status
    * @param pageable page size and amount of accounts in it
    * @return page of accounts
    * */
   Page<Account> getAccountsByGameIdAndStatusOrderByPrice(Long id, String status, Pageable pageable);

   /**
    * Gets page of accounts by game id and special status and which price is between some borders
    * @param id game id
    * @param status accounts status
    * @param from minimum accounts price
    * @param to maximun accounts price
    * @param pageable page size and amount of accounts in it
    * @return page of accounts
    * */
   Page<Account> getAccountsByGameIdAndStatusAndPriceBetween(Long id, String status, Integer from, Integer to, Pageable pageable);

   /**
    * Gets page of accounts by game id and special status and which price is between some borders order by price increase
    * @param id game id
    * @param status accounts status
    * @param from minimum accounts price
    * @param to maximun accounts price
    * @param pageable page size and amount of accounts in it
    * @return page of accounts
    * */
   Page<Account> getAccountsByGameIdAndStatusAndPriceBetweenOrderByPrice(Long id, String status, Integer from, Integer to, Pageable pageable);

   /**
    * Gets page of accounts by game id and special status order by price decrease
    * @param id game id
    * @param status accounts status
    * @param pageable page size and amount of accounts in it
    * @return page of accounts
    * */
   Page<Account> getAccountsByGameIdAndStatusAndPriceBetweenOrderByPriceDesc(Long id, String status, Integer from, Integer to, Pageable pageable);

   /**
    * Gets page of accounts by id
    * @param id game id
    * @return account
    * */
   Account getAccountById(Long id);

}
