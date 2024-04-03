package sk.posam.fsa.moneymate.domain.repository;

import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface CategoryRepository {
    void create(Category category);

    Collection<Category> findAllByUser(User user);

    Optional<Category> findById(Long id);

    void update(Category category);

    void delete(Long id);

    boolean exists(Category category);
}
