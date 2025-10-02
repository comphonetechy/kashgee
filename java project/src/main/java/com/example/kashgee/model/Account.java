// Account.java
package com.example.kashgee.model;

public class Account {
    private Long id;
    private String accountName;
    private int balance;
    
    public Account() {
        this.balance = 10000000; // Initial balance
    }
    
    public Account(String accountName, int initialBalance) {
        this.accountName = accountName;
        this.balance = initialBalance;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }
    
    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }
}