package sk.posam.fsa.moneymate.jpa.category;

import org.springframework.stereotype.Repository;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.repository.CategoryRepository;

import java.util.Collection;
import java.util.Optional;

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

    @Override
    public Optional<Category> findById(Long id) {
        return categorySpringDataRepository.findById(id);
    }

    @Override
    public void update(Category category) {
        categorySpringDataRepository.save(category);
    }

    @Override
    public boolean exists(Category category) {
        return categorySpringDataRepository
                .existsByNameAndDescriptionAndAssignedUser
                        (category.getName(), category.getDescription(), category.getAssignedUser());
    }


}
