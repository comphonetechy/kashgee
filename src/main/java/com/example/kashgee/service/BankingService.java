
// BankingService.java
package com.example.kashgee.service;

import org.springframework.stereotype.Service;

import com.example.kashgee.model.Account;
import com.example.kashgee.model.Transaction;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankingService {
    
    // For now using in-memory storage
    // Later you can replace with database
    private Account currentAccount;
    private List<Transaction> transactions;
    
    public BankingService() {
        this.currentAccount = new Account("Main Account", 10000000);
        this.transactions = new ArrayList<>();
    }
    
    public Account getAccount() {
        return currentAccount;
    }
    
    public int getBalance() {
        return currentAccount.getBalance();
    }
    
    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactions); // Return copy
    }
    
    public boolean sendMoney(int amount, String receiverName) {
        // Validation
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        
        if (receiverName == null || receiverName.trim().isEmpty()) {
            throw new IllegalArgumentException("Receiver name is required");
        }
        
        if (currentAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        
        // Process transaction
        int newBalance = currentAccount.getBalance() - amount;
        currentAccount.setBalance(newBalance);
        
        // Log transaction
        Transaction transaction = new Transaction(amount, receiverName, "SEND");
        transactions.add(transaction);
        
        return true;
    }
    
    public void depositMoney(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        
        int newBalance = currentAccount.getBalance() + amount;
        currentAccount.setBalance(newBalance);
        
        Transaction transaction = new Transaction(amount, "Self", "DEPOSIT");
        transactions.add(transaction);
    }
}