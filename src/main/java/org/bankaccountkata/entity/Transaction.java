package org.bankaccountkata.entity;

import org.bankaccountkata.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    private TransactionType transactionType;
    private LocalDateTime date;

    private double amount;

    private double balance;

    public Transaction(TransactionType transactionType, double amount, double balance) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
        this.date = LocalDateTime.now();
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
