package sk.posam.fsa.moneymate.domain.transaction;

import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.Currency;
import sk.posam.fsa.moneymate.domain.TransactionType;
import sk.posam.fsa.moneymate.domain.repository.AccountRepository;

import java.math.BigDecimal;

public class TransactionFactory {

    private final AccountRepository accountRepository;

    public TransactionFactory(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Transaction createIncome(BigDecimal amount,
                                    String description,
                                    Category category,
                                    Currency currency,
                                    Long accountId)
            throws InvalidTransactionArgsException {

        validateTransactionArgs(amount, description, category, currency, accountId);
        return new Transaction(amount, description, category, TransactionType.INCOME, currency, accountId);
    }

    public Transaction createExpense(BigDecimal amount,
                                     String description,
                                     Category category,
                                     Currency currency,
                                     Long accountId)
            throws InvalidTransactionArgsException {

        validateTransactionArgs(amount, description, category, currency, accountId);
        return new Transaction(amount, description, category, TransactionType.EXPENSE, currency, accountId);
    }

    private void validateTransactionArgs(BigDecimal amount,
                                         String description,
                                         Category category,
                                         Currency currency,
                                         Long accountId) throws InvalidTransactionArgsException {


        if (amount == null || amount.doubleValue() <= 0) {
            throw new InvalidTransactionArgsException("Amount cannot be null or below zero");
        }
        if (description == null) {
            throw new InvalidTransactionArgsException("Description cannot be null or empty");
        }
        if (category == null) {
            throw new InvalidTransactionArgsException("Category cannot be null");
        }
        if (currency == null) {
            throw new InvalidTransactionArgsException("Currency cannot be null or empty");
        }
        if (accountId == null) {
            throw new InvalidTransactionArgsException("Account ID cannot be null");
        }
        if (accountRepository.findById(accountId) == null) {
            throw new InvalidTransactionArgsException("Account not found");
        }
    }

}
