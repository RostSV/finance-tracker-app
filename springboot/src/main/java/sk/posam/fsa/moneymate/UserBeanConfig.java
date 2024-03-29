package sk.posam.fsa.moneymate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.posam.fsa.moneymate.domain.repository.UserRepository;
import sk.posam.fsa.moneymate.domain.service.UserFacade;
import sk.posam.fsa.moneymate.domain.service.UserService;

@Configuration
public class UserBeanConfig {

    @Bean
    public UserFacade userFacade(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
