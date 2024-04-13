package sk.posam.fsa.moneymate.jpa.account;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.AccountType;
import sk.posam.fsa.moneymate.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface AccountSpringDataRepository extends JpaRepository<Account, Long>{
    Collection<Account> findByCreatedBy(User user);

    Collection<Account> findByType(AccountType type);

    Collection<Account> findByCurrencyCode(String currencyCode);

    Optional<Object> findByName(String name);
}
