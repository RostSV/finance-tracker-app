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
import sk.posam.fsa.moneymate.domain.transaction.TransactionFactory;
import sk.posam.fsa.moneymate.domain.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class AccountActionsService implements AccountActionsFacade {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CurrencyRepository currencyRepository;
    private final CategoryRepository categoryRepository;

    private final TransactionFactory transactionFactory;

    public AccountActionsService(TransactionRepository transactionRepository,
                                 AccountRepository accountRepository,
                                 CurrencyRepository currencyRepository,
                                 CategoryRepository categoryRepository,
                                 TransactionFactory transactionFactory) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.currencyRepository = currencyRepository;
        this.categoryRepository = categoryRepository;
        this.transactionFactory = transactionFactory;
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
    public void createTransaction(Long accountId, Transaction transaction) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account with id " + accountId + " not found"));

        validateTransaction(transaction);


        //Transaction created by Transaction factory
        Transaction _transaction = null;

        if(transaction.getType() == TransactionType.INCOME){
            _transaction = transactionFactory.createIncome(
                    transaction.getAmount(),
                    transaction.getDescription(),
                    transaction.getCategory(),
                    transaction.getCurrency(),
                    account);

            //(else if) in case we will add more types of transactions in the future
        } else if(transaction.getType() == TransactionType.EXPENSE){
            _transaction = transactionFactory.createExpense(
                    transaction.getAmount(),
                    transaction.getDescription(),
                    transaction.getCategory(),
                    transaction.getCurrency(),
                    account);

        }

        this.updateAccountBalance(accountId, account.getBalance()
                .add(transaction.getType() == TransactionType.INCOME ? transaction.getAmount() : transaction.getAmount().negate()));



        transactionRepository.create(_transaction);
    }


    @Override
    public void updateTransaction(Transaction transaction) {

    }

    @Override
    public void deleteTransaction(Long id) {

    }

    @Override
    public List<Transaction> findTransactionsByAccount(Long accountId, Integer limitDays) {

        if(limitDays == null || limitDays < 0)
            throw new IllegalArgumentException("Limit days cannot be null or negative");

        LocalDate startDate = LocalDate.now().minusDays(limitDays);

        return transactionRepository.findAllByAccount(accountId, startDate)
                .stream()
                .toList();
    }

    @Override
    public List<Transaction> findTransactionsByCategory(Category category) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionsByUser(User user, Integer limitDays) {

        if(limitDays == null || limitDays < 0)
            throw new IllegalArgumentException("Limit days cannot be null or negative");

        LocalDate startDate = LocalDate.now().minusDays(limitDays);

        return transactionRepository.findByUser(user.getId(), startDate)
                .stream()
                .toList();
    }

    @Override
    public Account findAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new InstanceNotFoundException("Account with id " + accountId + " not found"));
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


            if(transaction.getType() != TransactionType.EXPENSE && transaction.getType() != TransactionType.INCOME){
                Logger logger = Logger.getLogger(AccountActionsService.class.getName());
                logger.info(transaction.getType().name());
                throw new IllegalArgumentException("Type of transaction is not valid. Valid types are: " + Arrays.toString(TransactionType.values()));
            }



        if(currencyRepository.findById(transaction.getCurrency().getId()).isEmpty()){
            throw new IllegalArgumentException("Currency with id " + transaction.getCurrency().getId() + " is not available in the system");
        }

        if(currencyRepository.findByCode(transaction.getCurrency().getCode()) == null){
            throw new IllegalArgumentException("Currency with code " + transaction.getCurrency().getCode() + " is not available in the system");
        }

        if(transaction.getAssignedTo() == null){
            throw new IllegalArgumentException("Account assigned to transaction cannot be null");
        }

        if(accountRepository.findById(transaction.getAssignedTo().getId()).isEmpty()){
            throw new IllegalArgumentException("Account with id " + transaction.getAssignedTo().getId() + " is not available in the system");
        }



    }
}
