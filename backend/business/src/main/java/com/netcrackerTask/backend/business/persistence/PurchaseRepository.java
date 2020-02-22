package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    Purchase getPurchaseByUserId(Long userId);

    @Query(value = "select * from purchase p where p.user_id=?1 and p.status=0", nativeQuery = true) //p.status = 0(покупка не подтверждега)
    Purchase getBagItemsForUser(Long userId);
}
