package org.bankaccountkata.entity;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;

    private List<Transaction> transactions;

    public Account(double balance){
        this.balance=balance;
        transactions=new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

}
