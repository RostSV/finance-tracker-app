package sk.posam.fsa.moneymate.jpa.currency;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.posam.fsa.moneymate.domain.Currency;

public interface CurrencySpringDataRepository extends JpaRepository<Currency, Long> {

    Currency findByCode(String code);
}
