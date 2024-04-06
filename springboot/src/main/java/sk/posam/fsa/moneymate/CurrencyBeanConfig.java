package sk.posam.fsa.moneymate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.posam.fsa.moneymate.domain.repository.CurrencyRepository;
import sk.posam.fsa.moneymate.domain.service.CurrencyFacade;
import sk.posam.fsa.moneymate.domain.service.CurrencyService;

@Configuration
public class CurrencyBeanConfig {
    @Bean
    public CurrencyFacade currencyFacade(CurrencyRepository currencyRepository) {
        return new CurrencyService(currencyRepository);

    }


}
