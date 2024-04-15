package sk.posam.fsa.moneymate.jpa.transaction;

import org.springframework.stereotype.Repository;
import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.repository.TransactionRepository;
import sk.posam.fsa.moneymate.domain.transaction.Transaction;

import java.util.Collection;

@Repository
public class JpaTransactionRepositoryAdapter implements TransactionRepository {

    private final TransactionSpringDataRepository transactionSpringDataRepository;

    public JpaTransactionRepositoryAdapter(TransactionSpringDataRepository transactionSpringDataRepository) {
        this.transactionSpringDataRepository = transactionSpringDataRepository;
    }

    @Override
    public void create(Transaction transaction) {
        transactionSpringDataRepository.save(transaction);
    }

    @Override
    public void update(Transaction transaction) {
        transactionSpringDataRepository.save(transaction);
    }

    @Override
    public void delete(Long id) {
        transactionSpringDataRepository.deleteById(id);
    }

    @Override
    public Collection<Transaction> findAllByAccount(Long accountId) {
        return transactionSpringDataRepository.findAllByAccountId(accountId);
    }

    @Override
    public Collection<Transaction> findBAllByCategory(Category category) {
        return transactionSpringDataRepository.findAllByCategory(category);
    }

    @Override
    public Collection<Transaction> findByUser(Long userId) {
        return transactionSpringDataRepository.findAllByUser(userId);
    }


}
