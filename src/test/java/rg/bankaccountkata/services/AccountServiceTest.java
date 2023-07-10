package rg.bankaccountkata.services;

import org.bankaccountkata.entity.Account;
import org.bankaccountkata.entity.Transaction;
import org.bankaccountkata.enums.TransactionType;
import org.bankaccountkata.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;


public class AccountServiceTest {
    AccountService accountService = AccountService.getInstance();
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeAll
    public static void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void shouldUpdateAccountBalanceWhenDeposit() {
        //given a client account
        double amount = 10.0;
        Account account = new Account(0.0);

        //when user want to make a deposit to his account
        accountService.deposit(account, amount);

        //then user's account should have new balance
        assertEquals(account.getBalance(), amount);
        assertEquals(account.getTransactions().size(), 1);
        Transaction transaction = account.getTransactions().get(0);
        assertAll("transaction",
                () -> assertEquals(transaction.getBalance(), account.getBalance()),
                () -> assertEquals(transaction.getTransactionType(), TransactionType.DEPOSIT),
                () -> assertEquals(transaction.getAmount(), amount),
                () -> assertEquals(transaction.getDate().getDayOfMonth(), LocalDateTime.now().getDayOfMonth()));
    }

    @Test
    public void shouldUpdateAccountBalanceWhenWithdrawal() {
        //given a client account
        double amount = 5.0;
        Account account = new Account(20.0);

        //when user want to make a withdrawal from his account
        accountService.withdrawal(account, amount);

        //then user's account should have new balance
        Assertions.assertEquals(account.getBalance(), 20.0 - 5.0);
        assertEquals(account.getTransactions().size(), 1);
        Transaction transaction = account.getTransactions().get(0);
        assertAll("transaction",
                () -> assertEquals(transaction.getBalance(), account.getBalance()),
                () -> assertEquals(transaction.getTransactionType(), TransactionType.WITHDRAWAL),
                () -> assertEquals(transaction.getAmount(), amount),
                () -> assertEquals(transaction.getDate().getDayOfMonth(), LocalDateTime.now().getDayOfMonth()));

    }

    @Test
    public void shouldPrintStatement() {
        //given a client account
        Account account = new Account(0.0);
        accountService.deposit(account, 10.0);
        accountService.withdrawal(account, 3.0);

        //when client want to see the history of his operations
        accountService.printStatement(account);

        //then the system print all his transactions
        assertEquals("Operation   Date    Amount  Balance\n" +
                "DEPOSIT " + formatter.format(account.getTransactions().get(0).getDate()) + " 10.0 10.0\n" +
                "WITHDRAWAL " + formatter.format(account.getTransactions().get(1).getDate()) + " 3.0 7.0\n", outputStreamCaptor.toString());
    }
}
