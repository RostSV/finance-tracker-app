package sk.posam.fsa.moneymate.domain.repository;

import sk.posam.fsa.moneymate.domain.Currency;

import java.util.Collection;

public interface CurrencyRepository {

    void create(Currency currency);

    Collection<Currency> findAll();

    Currency findByCode(String code);
}
