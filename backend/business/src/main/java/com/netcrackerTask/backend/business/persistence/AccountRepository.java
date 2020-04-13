/*
 * Copyright (c)
 */

package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
    Page<Account> getAccountsByGameIdAndStatus(Long id,
                                               String status,
                                               Pageable pageable);

    Page<Account> getAccountsByGameIdAndStatusOrderByPriceDesc(Long id,
                                                               String status,
                                                               Pageable pageable);

    Page<Account> getAccountsByGameIdAndStatusOrderByPrice(Long id,
                                                           String status,
                                                           Pageable pageable);

    Page<Account> getAccountsByGameIdAndStatusAndPriceBetween(Long id,
                                                              String status,
                                                              Integer from,
                                                              Integer to,
                                                              Pageable pageable);

    Page<Account> getAccountsByGameIdAndStatusAndPriceBetweenOrderByPrice(Long id,
                                                                          String status,
                                                                          Integer from,
                                                                          Integer to,
                                                                          Pageable pageable);

    Page<Account> getAccountsByGameIdAndStatusAndPriceBetweenOrderByPriceDesc(Long id,
                                                                              String status,
                                                                              Integer from,
                                                                              Integer to,
                                                                              Pageable pageable);

    Account getAccountById(Long id);

}
