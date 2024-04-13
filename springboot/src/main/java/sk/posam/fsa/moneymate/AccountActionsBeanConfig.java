package sk.posam.fsa.moneymate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.posam.fsa.moneymate.domain.repository.AccountRepository;
import sk.posam.fsa.moneymate.domain.repository.CategoryRepository;
import sk.posam.fsa.moneymate.domain.repository.CurrencyRepository;
import sk.posam.fsa.moneymate.domain.repository.TransactionRepository;
import sk.posam.fsa.moneymate.domain.service.AccountActionsService;

@Configuration
public class AccountActionsBeanConfig {

    @Bean
    public AccountActionsService accountActionsService(TransactionRepository transactionRepository,
                                                       AccountRepository accountRepository,
                                                       CurrencyRepository currencyRepository,
                                                       CategoryRepository categoryRepository) {
        return new AccountActionsService(transactionRepository, accountRepository, currencyRepository, categoryRepository);
    }
}
