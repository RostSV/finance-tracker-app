package sk.posam.fsa.moneymate.jpa.currency;

import org.springframework.stereotype.Repository;
import sk.posam.fsa.moneymate.domain.Currency;
import sk.posam.fsa.moneymate.domain.repository.CurrencyRepository;

import java.util.Collection;

@Repository
public class JpaCurrencyRepositoryAdapter implements CurrencyRepository {

    private final CurrencySpringDataRepository currencySpringDataRepository;

    public JpaCurrencyRepositoryAdapter(CurrencySpringDataRepository currencySpringDataRepository) {
        this.currencySpringDataRepository = currencySpringDataRepository;
    }

    @Override
    public void create(Currency currency) {
        currencySpringDataRepository.save(currency);
    }

    @Override
    public Collection<Currency> findAll() {
        return currencySpringDataRepository.findAll();
    }

    @Override
    public Currency findByCode(String code) {
        return currencySpringDataRepository.findByCode(code);
    }
}
