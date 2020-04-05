package com.netcrackerTask.backend.business.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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



    public void setAccountId(Long accountId){
        if(accountsId == null)
            accountsId = new ArrayList<Long>();
        this.accountsId.add(accountId);
    }


}
