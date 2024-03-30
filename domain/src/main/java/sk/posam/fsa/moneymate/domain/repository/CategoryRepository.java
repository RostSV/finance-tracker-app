package sk.posam.fsa.moneymate.domain.repository;

import sk.posam.fsa.moneymate.domain.Category;

import java.util.Collection;
import java.util.Optional;

public interface CategoryRepository {
    void create(Category category);

    Collection<Category> findAll();

    Optional<Category> findById(Long id);

    void update(Category category);

    void delete(Long id);

    boolean exists(String name, String description);
}
