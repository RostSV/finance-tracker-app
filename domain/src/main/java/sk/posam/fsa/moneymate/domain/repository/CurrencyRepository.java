package sk.posam.fsa.moneymate.domain.repository;

import sk.posam.fsa.moneymate.domain.Currency;

import java.util.Collection;
import java.util.Optional;

public interface CurrencyRepository {

    void create(Currency currency);

    Collection<Currency> findAll();

    Currency findByCode(String code);

    Optional<Currency> findById(Long id);
}
