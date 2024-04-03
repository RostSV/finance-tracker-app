package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceAlreadyExistsException;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceNotFoundException;
import sk.posam.fsa.moneymate.domain.repository.CategoryRepository;

import java.util.List;
import java.util.Objects;

public class CategoryService implements CategoryFacade {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(Category category) {
        // Validate category
        Objects.requireNonNull(category, "Category cannot be null");
        Objects.requireNonNull(category.getName(), "Category name cannot be null");

        if (category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        if (category.getAssignedUser() == null) {
            throw new NullPointerException("Category must have assigned user/ user not exist");
        }
        // Check if category already exists
        if (categoryRepository.exists(category)) {
            throw new InstanceAlreadyExistsException(
                    "Category with name " + category.getName() +
                            " and description " + category.getDescription() + " already exists");
        }

        categoryRepository.create(category);
    }

    @Override
    public void delete(Long id) {
        //check if category exists
        categoryRepository.findById(id)
                .orElseThrow(() -> new InstanceNotFoundException("Category with id " + id + " not found"));

        categoryRepository.delete(id);
    }

    @Override
    public void update(Category category) {
        // Validate category
        Objects.requireNonNull(category, "Category cannot be null");
        Objects.requireNonNull(category.getName(), "Category name cannot be null");

        if (category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        if (category.getId() == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }

        // Check if category already exists
        Category existingCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new InstanceNotFoundException
                        ("Category with id " + category.getId() + " not found"));

        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());

        categoryRepository.update(existingCategory);
    }

    @Override
    public List<Category> findAllByUser(User user) {
        return categoryRepository.findAllByUser(user)
                .stream()
                .toList();
    }
}
