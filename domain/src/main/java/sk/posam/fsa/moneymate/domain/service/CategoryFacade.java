package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.User;

import java.util.List;

public interface CategoryFacade {

    void create(Category category);

    void delete(Long id);

    void update(Category category);

    List<Category> findAllByUser(User user);
}
