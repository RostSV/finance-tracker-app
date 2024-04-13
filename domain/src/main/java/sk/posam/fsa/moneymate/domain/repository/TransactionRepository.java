package sk.posam.fsa.moneymate.domain.repository;

import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.transaction.Transaction;

import java.util.Collection;

/**
 * TransactionRepository is an interface that defines the contract for transaction-related database operations.
 * Implementations of this interface are expected to provide the functionality for these operations.
 */
public interface TransactionRepository {

    /**
     * Creates a new transaction in the database.
     *
     * @param transaction The transaction object to be created. This object should have all the necessary information
     *                    required to create a new transaction in the database.
     */
    void create(Transaction transaction);

    /**
     * Updates an existing transaction in the database.
     *
     * @param transaction The transaction object to be updated. This object should have all the updated information
     *                    required to update the transaction in the database.
     */
    void update(Transaction transaction);

    /**
     * Deletes a transaction from the database by its ID.
     *
     * @param id The ID of the transaction to be deleted.
     */
    void delete(Long id);


    Collection<Transaction> findAllByAccount(Account account);


    Collection<Transaction> findBAllByCategory(Category category);
}