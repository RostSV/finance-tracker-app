package sk.posam.fsa.moneymate.jpa.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.transaction.Transaction;

import java.util.Collection;

public interface TransactionSpringDataRepository extends JpaRepository<Transaction, Long> {
    Collection<Transaction> findByAssignedTo(Account account);

    Collection<Transaction> findAllByCategory(Category category);

    @Query(value = "SELECT t.* FROM transaction t JOIN account a ON t.account_id = a.id " +
            "WHERE a.user_id = :userId", nativeQuery = true)
    Page<Transaction> findAllByUser(Long userId, Pageable pageable);

    @Query(value = "SELECT * FROM transaction where account_id = :id ORDER BY transaction.date desc", nativeQuery = true)
    Page<Transaction> findAllByAccountId(Long id, Pageable pageable);


}
