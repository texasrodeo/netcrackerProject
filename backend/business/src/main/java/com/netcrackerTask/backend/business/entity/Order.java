package com.netcrackerTask.backend.business.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private double price;

    private String currency;

    private String method;

    private String intent;

    private String description;

    private List<Long> accountsId;

    public void setAccountId(Long accountId) {
        if (this.accountsId == null){
            this.accountsId = new ArrayList<Long>();
        }
        this.accountsId.add(accountId);
    }
}
