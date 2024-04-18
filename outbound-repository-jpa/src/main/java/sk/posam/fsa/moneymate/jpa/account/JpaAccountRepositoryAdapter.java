package sk.posam.fsa.moneymate.jpa.account;

import org.springframework.stereotype.Repository;
import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.AccountType;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.repository.AccountRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class JpaAccountRepositoryAdapter implements AccountRepository {

    private final AccountSpringDataRepository accountSpringDataRepository;

    public JpaAccountRepositoryAdapter(AccountSpringDataRepository accountSpringDataRepository) {
        this.accountSpringDataRepository = accountSpringDataRepository;
    }

    @Override
    public void create(Account account) {
        accountSpringDataRepository.save(account);
    }

    @Override
    public void update(Account account) {
        accountSpringDataRepository.save(account);
    }

    @Override
    public void delete(Long id) {
        accountSpringDataRepository.deleteById(id);
    }

    @Override
    public Optional<Account> findById(Long accountId) {
        return accountSpringDataRepository.findById(accountId);
    }

    @Override
    public Collection<Account> findByUser(User user) {
        return accountSpringDataRepository.findByCreatedByOrderByIdDesc(user);
    }

    @Override
    public Collection<Account> findByType(AccountType type) {
        return accountSpringDataRepository.findByType(type);
    }

    @Override
    public Collection<Account> findByCurrencyCode(String currencyCode) {
        return accountSpringDataRepository.findByCurrencyCode(currencyCode);
    }

    @Override
    public Optional<Object> findByName(String name) {
        return accountSpringDataRepository.findByName(name);
    }
}
