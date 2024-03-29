package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.repository.CategoryRepository;

import java.util.Collection;
import java.util.Objects;

public class CategoryService implements CategoryFacade {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(Category category) {
        Objects.requireNonNull(category, "Category cannot be null");
        Objects.requireNonNull(category.getName(), "Category name cannot be null");
        if (category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        categoryRepository.create(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }
}
