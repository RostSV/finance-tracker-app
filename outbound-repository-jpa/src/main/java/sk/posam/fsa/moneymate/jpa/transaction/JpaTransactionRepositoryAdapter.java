package sk.posam.fsa.moneymate.jpa.transaction;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import sk.posam.fsa.moneymate.domain.Category;
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
    public Collection<Transaction> findBAllByCategory(Category category) {
        return transactionSpringDataRepository.findAllByCategory(category);
    }

    @Override
    public Collection<Transaction> findAllByAccount(Long accountId, Integer limit) {
        if(limit != null){
            Pageable pageable = PageRequest.of(0, limit);
            return transactionSpringDataRepository.findAllByAccountId(accountId, pageable).getContent();
        }
        return transactionSpringDataRepository.findAllByUser(accountId, Pageable.unpaged()).getContent();
    }
    @Override
    public Collection<Transaction> findByUser(Long userId, Integer limit) {
        if(limit != null){
            Pageable pageable = PageRequest.of(0, limit);
            return transactionSpringDataRepository.findAllByUser(userId, pageable).getContent();
        }

        return transactionSpringDataRepository.findAllByUser(userId, Pageable.unpaged()).getContent();
    }

}
