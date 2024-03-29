package sk.posam.fsa.moneymate.jpa;

import org.springframework.stereotype.Repository;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.repository.CategoryRepository;

import java.util.Collection;

@Repository
public class JpaCategoryRepositoryAdapter implements CategoryRepository {

    private final CategorySpringDataRepository categorySpringDataRepository;

    public JpaCategoryRepositoryAdapter(CategorySpringDataRepository categorySpringDataRepository) {
        this.categorySpringDataRepository = categorySpringDataRepository;
    }

    @Override
    public void create(Category category) {
        categorySpringDataRepository.save(category);
    }


    @Override
    public void delete(Long id) {
        categorySpringDataRepository.deleteById(id);
    }

    @Override
    public Collection<Category> findAll() {
        return categorySpringDataRepository.findAll();
    }
}
