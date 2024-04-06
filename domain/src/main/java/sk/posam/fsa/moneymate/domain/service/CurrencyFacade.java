package sk.posam.fsa.moneymate.domain.service;


import sk.posam.fsa.moneymate.domain.Currency;

import java.util.List;

public interface CurrencyFacade {

    void create(Currency currency);

    Currency findByCode(String code);

    List<Currency> findAll();
}
