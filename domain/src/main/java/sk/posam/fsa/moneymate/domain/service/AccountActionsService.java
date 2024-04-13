package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.AccountType;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceAlreadyExistsException;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceNotFoundException;
import sk.posam.fsa.moneymate.domain.repository.AccountRepository;
import sk.posam.fsa.moneymate.domain.repository.CategoryRepository;
import sk.posam.fsa.moneymate.domain.repository.CurrencyRepository;
import sk.posam.fsa.moneymate.domain.repository.TransactionRepository;
import sk.posam.fsa.moneymate.domain.transaction.Transaction;
import sk.posam.fsa.moneymate.domain.transaction.TransactionType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AccountActionsService implements AccountActionsFacade {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CurrencyRepository currencyRepository;
    private final CategoryRepository categoryRepository;

    public AccountActionsService(TransactionRepository transactionRepository,
                                 AccountRepository accountRepository,
                                 CurrencyRepository currencyRepository,
                                 CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.currencyRepository = currencyRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void createAccount(Account account) {

        if(accountRepository.findByName(account.getName()).isPresent()){
            throw new InstanceAlreadyExistsException("Account with name " + account.getName() + " already exists in the system");
        }

        validateAccount(account);


        accountRepository.create(account);
    }

    @Override
    public void updateAccount(Account account) {

        accountRepository.findById(account.getId())
                .orElseThrow(() -> new InstanceNotFoundException("Account with id " + account.getId() + " not found"));

        validateAccount(account);

        accountRepository.update(account);

    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.findById(id)
                .orElseThrow(() -> new InstanceNotFoundException("Account with id " + id + " not found"));

        accountRepository.delete(id);
    }

    @Override
    public void updateAccountBalance(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new InstanceNotFoundException("Account with id " + accountId + " not found"));

        if(amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Amount cannot be negative");

        account.setBalance(amount);

        accountRepository.update(account);
    }

    @Override
    public List<Account> findAccountsByUser(User user) {
        return accountRepository.findByUser(user)
                .stream()
                .toList();
    }

    @Override
    public List<Account> findAccountsByType(AccountType type) {
        return accountRepository.findByType(type)
                .stream()
                .toList();
    }

    @Override
    public List<Account> findAccountsByCurrencyCode(String currencyCode) {
        return accountRepository.findByCurrencyCode(currencyCode)
                .stream()
                .toList();
    }

    @Override
    public void createTransaction(Transaction transaction) {

    }

    @Override
    public void updateTransaction(Transaction transaction) {

    }

    @Override
    public void deleteTransaction(Long id) {

    }

    @Override
    public List<Transaction> findTransactionsByAccount(Account account) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionsByCategory(Category category) {
        return null;
    }

    //TODO refactor to separate class
    private void validateAccount(Account account) {
        Objects.requireNonNull(account, "Account cannot be null");

        if(account.getName().isEmpty()){
            throw new IllegalArgumentException("Name of account cannot be empty");
        }

        if(account.getBalance() == null || account.getBalance().compareTo(BigDecimal.ZERO) < 0){
            account.setBalance(BigDecimal.ZERO);
        }

        boolean isValidEnum = Arrays.stream(AccountType.values())
                .anyMatch(e -> e.name().equals(account.getType().name()));

        if (!isValidEnum) {
            throw new IllegalArgumentException("Type of account is not valid. Valid types are: " + Arrays.toString(AccountType.values()));
        }

        if(account.getCurrency() == null){
            throw new IllegalArgumentException("Currency of account cannot be null");
        }

        if(account.getCurrency().getId() == null){
            throw new IllegalArgumentException("Currency of account cannot be null");
        }

        if(account.getCurrency().getCode().isEmpty()){
            throw new IllegalArgumentException("Currency of account cannot be empty");
        }

        if(currencyRepository.findByCode(account.getCurrency().getCode()) == null){
            throw new IllegalArgumentException("Currency with code " + account.getCurrency().getCode() + " is not available in the system");
        }

        if(account.getCreatedBy() == null){
            throw new IllegalArgumentException("Creator of account cannot be null");
        }
    }

    private void validateTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }

        if (transaction.getAmount() == null || transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount of transaction cannot be null or negative");
        }

        if (transaction.getCategory() == null || transaction.getCategory().getName().isEmpty()) {
            throw new IllegalArgumentException("Category of transaction cannot be null");
        }

        if(categoryRepository.findById(transaction.getCategory().getId()).isEmpty()) {
            throw new IllegalArgumentException("Category with name " + transaction.getCategory().getName() + " is not available in the system");
        }

        for(TransactionType type : TransactionType.values()){
            if(type.name().equals(transaction.getType().name())){
                return;
            }
            throw new IllegalArgumentException("Type of transaction is not valid. Valid types are: " + Arrays.toString(TransactionType.values()));
        }

        if(currencyRepository.findByCode(transaction.getCurrency().getCode()) == null){
            throw new IllegalArgumentException("Currency with code " + transaction.getCurrency().getCode() + " is not available in the system");
        }

        if(accountRepository.findById(transaction.getAssignedTo().getId()).isEmpty()){
            throw new IllegalArgumentException("Account with id " + transaction.getAssignedTo().getId() + " is not available in the system");
        }



    }
}
