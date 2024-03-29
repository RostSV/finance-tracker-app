package sk.posam.fsa.moneymate.jpa;

import org.springframework.stereotype.Repository;
import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.AccountType;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.repository.AccountRepository;

import java.util.Collection;

@Repository
public class JpaAccountRepositoryAdapter implements AccountRepository {
    @Override
    public void create(Account account) {

    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Account findById(Long accountId) {
        return null;
    }

    @Override
    public Collection<Account> findByUser(User user) {
        return null;
    }

    @Override
    public Collection<Account> findByType(AccountType type) {
        return null;
    }

    @Override
    public Collection<Account> findByCurrencyCode(String currencyCode) {
        return null;
    }
}
