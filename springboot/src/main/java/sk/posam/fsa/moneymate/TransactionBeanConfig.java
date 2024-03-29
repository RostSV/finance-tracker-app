package sk.posam.fsa.moneymate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.posam.fsa.moneymate.domain.repository.AccountRepository;
import sk.posam.fsa.moneymate.domain.transaction.TransactionFactory;

@Configuration
public class TransactionBeanConfig {

    @Bean
    public TransactionFactory transactionFactory(AccountRepository accountRepository) {
        return new TransactionFactory(accountRepository);
    }
}
