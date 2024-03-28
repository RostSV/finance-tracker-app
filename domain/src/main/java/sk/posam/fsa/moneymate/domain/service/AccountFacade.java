package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.AccountType;
import sk.posam.fsa.moneymate.domain.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountFacade {

    void create(Account account);
    void update(Account account);
    void delete(Long id);

    List<Account> findByUser(User user);
    List<Account> findByType(AccountType type);
    List<Account> findByCurrencyCode(String currencyCode);
    void updateBalance(Long accountId, BigDecimal amount);
}
