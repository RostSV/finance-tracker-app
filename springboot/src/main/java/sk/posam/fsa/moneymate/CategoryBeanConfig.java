package sk.posam.fsa.moneymate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.posam.fsa.moneymate.domain.repository.CategoryRepository;
import sk.posam.fsa.moneymate.domain.service.CategoryFacade;
import sk.posam.fsa.moneymate.domain.service.CategoryService;

@Configuration
public class CategoryBeanConfig {
    @Bean
    public CategoryFacade categoryFacade(CategoryRepository categoryRepository) {
        return new CategoryService(categoryRepository);

    }
}
