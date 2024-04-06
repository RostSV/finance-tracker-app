package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.Currency;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceAlreadyExistsException;
import sk.posam.fsa.moneymate.domain.repository.CurrencyRepository;

import java.util.List;
import java.util.Objects;

public class CurrencyService implements CurrencyFacade {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void create(Currency currency) {

        Objects.requireNonNull(currency, "Currency cannot be null");
        Objects.requireNonNull(currency.getCode(), "Currency code cannot be null");

        if (currency.getCode().isEmpty())
            throw new IllegalArgumentException("Currency code cannot be empty");

        if (currencyRepository.findByCode(currency.getCode()) != null)
            throw new InstanceAlreadyExistsException("Currency with the same code already exist");

        currencyRepository.create(currency);
    }

    @Override
    public Currency findByCode(String code) {
        return currencyRepository.findByCode(code);
    }


    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll()
                .stream()
                .toList();
    }

}
