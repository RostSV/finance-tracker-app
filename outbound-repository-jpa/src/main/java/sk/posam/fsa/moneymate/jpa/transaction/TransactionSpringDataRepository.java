package sk.posam.fsa.moneymate.jpa.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.transaction.Transaction;

import java.util.Collection;

public interface TransactionSpringDataRepository extends JpaRepository<Transaction, Long> {
    Collection<Transaction> findByAssignedTo(Account account);

    Collection<Transaction> findAllByCategory(Category category);
}
