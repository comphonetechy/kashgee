
// Transaction.java
package com.example.kashgee.model;

import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private int amount;
    private String receiverName;
    private LocalDateTime timestamp;
    private String type; // "SEND" or "RECEIVE"
    
    public Transaction() {
        this.timestamp = LocalDateTime.now();
    }
    
    public Transaction(int amount, String receiverName, String type) {
        this.amount = amount;
        this.receiverName = receiverName;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    @Override
    public String toString() {
        return type + " ₱" + amount + " to " + receiverName + " at " + timestamp;
    }
}

// ============================================