package com.netcrackerTask.backend.business.persistence;

import java.util.List;
import com.netcrackerTask.backend.business.entity.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    /**
     * Gets list of purchase by user id and special status
     * @param userId user id
     * @param status purchase status
     * @return list of purchases
     * */
    List<Purchase> getPurchaseByUserIdAndStatusEquals(Long userId, String status);

    /**
     * Gets purchase by user id
     * @param accountId account id
     * @return purchase
     * */
    Purchase getPurchaseByGameAccountId(Long accountId);

}
