package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    Purchase getPurchaseByUserId(Long userId);

    //Purchase getPurchaseByUserIdAndStatus
}
