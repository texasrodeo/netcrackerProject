/*
 * Copyright (c)
 */

package com.netcrackerTask.backend.business.persistence;

import java.util.List;
import com.netcrackerTask.backend.business.entity.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    List<Purchase> getPurchaseByUserIdAndStatusEquals(Long userId, String status);

    Purchase getPurchaseByGameAccountId(Long accountId);

}
