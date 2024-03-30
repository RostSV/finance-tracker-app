package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceAlreadyExistsException;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceNotFoundException;

import java.util.List;

public interface CategoryFacade {

    void create(Category category) throws InstanceAlreadyExistsException;


    void delete(Long id) throws InstanceNotFoundException;

    void update(Category category);

    List<Category> findAll();


}
