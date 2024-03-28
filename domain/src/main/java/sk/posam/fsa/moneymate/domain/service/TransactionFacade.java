package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.Transaction;
import java.util.List;

/**
 * TransactionFacade is an interface that defines the contract for transaction-related operations.
 * Implementations of this interface are expected to provide the functionality for these operations.
 */
public interface TransactionFacade {

    /**
     * Creates a new transaction.
     *
     * @param transaction The transaction object to be created. This object should have all the necessary information
     *                    required to create a new transaction.
     */
    void create(Transaction transaction);

    /**
     * Updates an existing transaction.
     *
     * @param transaction The transaction object to be updated. This object should have all the updated information
     *                    required to update the transaction.
     */
    void update(Transaction transaction);

    /**
     * Deletes a transaction by its ID.
     *
     * @param id The ID of the transaction to be deleted.
     */
    void delete(Long id);

    /**
     * Finds transactions by their associated account ID.
     *
     * @param accountId The ID of the account whose transactions are to be found.
     * @return A list of Transaction objects associated with the given account ID.
     */
    List<Transaction> findByAccount(Long accountId);

    /**
     * Finds transactions by their associated category ID.
     *
     * @param categoryId The ID of the category whose transactions are to be found.
     * @return A list of Transaction objects associated with the given category ID.
     */
    List<Transaction> findByCategory(Long categoryId);
}