package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.Category;

import java.util.Collection;

public interface CategoryFacade {

    void create(Category category);


    void delete(Long id);

    Collection<Category> findAll();


}
