package com.netcrackerTask.backend.business.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Order class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    /**
     * Order summary purchase price.
     */
    private double price;

    /**
     * Order currency (RUB).
     */
    private String currency;

    /**
     * Order method (PayPal).
     */
    private String method;

    /**
     * Order intent (sale).
     */
    private String intent;

    /**
     * Order description.
     */
    private String description;

    /**
     * Order list of account id.
     */
    private List<Long> accountsId;

    /**
     *
     * @param accountId accounts to set
     */
    public void setAccountId(Long accountId) {
        if (this.accountsId == null){
            this.accountsId = new ArrayList<>();
        }
        this.accountsId.add(accountId);
    }
}
