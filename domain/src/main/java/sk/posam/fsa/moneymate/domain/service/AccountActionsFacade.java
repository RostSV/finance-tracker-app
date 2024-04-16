package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.AccountType;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountActionsFacade {

    //Accounts
    void createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Long id);
    void updateAccountBalance(Long accountId, BigDecimal amount);

    List<Account> findAccountsByUser(User user);
    List<Account> findAccountsByType(AccountType type);
    List<Account> findAccountsByCurrencyCode(String currencyCode);

    //Transactions
    void createTransaction(Long accountId, Transaction transaction);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(Long id);


    List<Transaction> findTransactionsByAccount(Long accountId, Integer limit);


    List<Transaction> findTransactionsByCategory(Category category);

    List<Transaction>  findTransactionsByUser(User user, Integer limit);

    Account findAccountById(Long accountId);
}