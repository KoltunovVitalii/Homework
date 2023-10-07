package org.koltunov;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {
    private String username;
    private String password;
    private double balance;
    private List<String> transactionHistory;
    private List<String> auditHistory;
    private Set<String> transactionIds;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
        this.auditHistory = new ArrayList<>();
        this.transactionIds = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public List<String> getAuditHistory() {
        return auditHistory;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public boolean debit(double amount, String transactionId) {
        if (balance - amount >= 0 && !transactionIds.contains(transactionId)) {
            balance -= amount;
            transactionHistory.add("Дебет/снятие: " + amount + " руб.");
            transactionIds.add(transactionId);
            return true;
        }
        return false;
    }

    public boolean credit(double amount, String transactionId) {
        if (!transactionIds.contains(transactionId) && amount >= 0) {
            balance += amount;
            transactionHistory.add("Кредит/пополнение: " + amount + " руб.");
            transactionIds.add(transactionId);
            return true;
        }
        return false;
    }

    public void audit(String action) {
        auditHistory.add("Аудит: " + action);
    }
}
