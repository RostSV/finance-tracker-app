package sk.posam.fsa.moneymate.domain.transaction;

import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.Currency;
import sk.posam.fsa.moneymate.domain.exceptions.InvalidTransactionArgsException;
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
                                    Account assignedTo) {

        validateTransactionArgs(amount, category, currency, assignedTo);
        return new Transaction(amount, description, category, TransactionType.INCOME, currency, assignedTo);
    }

    public Transaction createExpense(BigDecimal amount,
                                     String description,
                                     Category category,
                                     Currency currency,
                                     Account assignedTo) {

        validateTransactionArgs(amount, category, currency, assignedTo);
        return new Transaction(amount, description, category, TransactionType.EXPENSE, currency, assignedTo);
    }

    private void validateTransactionArgs(BigDecimal amount,
                                         Category category,
                                         Currency currency,
                                         Account assignedTo) {


        if (amount == null || amount.doubleValue() <= 0) {
            throw new InvalidTransactionArgsException("Amount cannot be null or below zero");
        }
        if (category == null) {
            throw new InvalidTransactionArgsException("Category cannot be null");
        }
        if (currency == null) {
            throw new InvalidTransactionArgsException("Currency cannot be null or empty");
        }
        if (assignedTo == null) {
            throw new InvalidTransactionArgsException("Account cannot be null");
        }
        if (accountRepository.findById(assignedTo.getId()) == null) {
            throw new InvalidTransactionArgsException("Account not found");
        }
    }

}
