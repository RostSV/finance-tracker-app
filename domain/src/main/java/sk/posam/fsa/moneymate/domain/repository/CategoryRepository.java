package sk.posam.fsa.moneymate.domain.repository;

import sk.posam.fsa.moneymate.domain.Category;

import java.util.Collection;

public interface CategoryRepository {
    void create(Category category);

    void delete(Long id);

    Collection<Category> findAll();
}
