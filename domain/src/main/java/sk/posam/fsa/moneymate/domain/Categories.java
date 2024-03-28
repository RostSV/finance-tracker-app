package sk.posam.fsa.moneymate.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Categories {


    private Collection<Category> categories;

    public Categories() {
        this.categories = new ArrayList<>();
    }

    public void add(Category category) {
        if(category == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        if(categories.contains(category)) {
            throw new IllegalArgumentException("Category already exists");
        }

        categories.add(category);
    }

    public void remove(long categoryId) {

        if(categories.stream().noneMatch(category -> category.getId() == categoryId)) {
            throw new IllegalArgumentException("Category does not exist");
        }

        categories.removeIf(category -> category.getId() == categoryId);
    }

    public Collection<Category> getCategories() {
        return Collections.unmodifiableCollection(categories);
    }

}
