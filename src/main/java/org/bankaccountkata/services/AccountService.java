package org.bankaccountkata.services;

import org.bankaccountkata.entity.Account;
import org.bankaccountkata.entity.Transaction;
import org.bankaccountkata.enums.TransactionType;

import java.time.format.DateTimeFormatter;

public class AccountService {

    private static AccountService INSTANCE;

    public static AccountService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountService();
        }

        return INSTANCE;
    }

    public void deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, account.getBalance());
        account.addTransaction(transaction);
    }

    public void withdrawal(Account account, double amount) {
        account.setBalance(account.getBalance() - amount);
        Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, amount, account.getBalance());
        account.addTransaction(transaction);
    }

    public void printStatement(Account account){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Operation   Date    Amount  Balance");
        for(Transaction transaction: account.getTransactions()){
            System.out.println(transaction.getTransactionType()+" "+formatter.format(transaction.getDate())+" "+transaction.getAmount()+" "+transaction.getBalance());
        }
    }
}
