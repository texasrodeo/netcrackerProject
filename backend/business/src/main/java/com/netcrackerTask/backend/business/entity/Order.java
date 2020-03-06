package com.netcrackerTask.backend.business.entity;


import java.util.ArrayList;
import java.util.List;

public class Order {


   private double price;

    private String currency;
    private String method;
    private String intent;
    private String description;

    private List<Long> accountsId;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(List<Long> accountsId) {
        this.accountsId = accountsId;
    }

    public void setAccountId(Long accountId){
        if(accountsId == null)
            accountsId = new ArrayList<Long>();
        this.accountsId.add(accountId);
    }

    public Order(double price, String currency, String method, String intent, String description) {
        this.price = price;
        this.currency = currency;
        this.method = method;
        this.intent = intent;
        this.description = description;
        this.accountsId = new ArrayList<>();
    }

    public Order() {
    }
}
