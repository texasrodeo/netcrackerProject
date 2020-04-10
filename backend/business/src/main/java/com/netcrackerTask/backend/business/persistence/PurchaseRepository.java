package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {


    List<Purchase> getPurchaseByUserIdAndStatusEquals(Long userId, String status);

    Purchase getPurchaseByGameAccountId(Long accountId);

}
