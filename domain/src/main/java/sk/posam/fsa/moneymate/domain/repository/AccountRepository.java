package sk.posam.fsa.moneymate.domain.repository;

import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.AccountType;
import sk.posam.fsa.moneymate.domain.User;

import java.util.Collection;

public interface AccountRepository {


    void create(Account account);

    void update(Account account);

    void delete(Long id);

    Account findById(Long accountId);

    Collection<Account> findByUser(User user);

    Collection<Account> findByType(AccountType type);

    Collection<Account> findByCurrencyCode(String currencyCode);
}
