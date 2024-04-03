package sk.posam.fsa.moneymate.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.UserRole;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceAlreadyExistsException;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceNotFoundException;
import sk.posam.fsa.moneymate.domain.repository.CategoryRepository;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;


    @InjectMocks
    private CategoryService categoryService;

    private User user;
    private Category category;

    @BeforeEach
    public void setUp() {
        user = new User("example@gmail.com", UserRole.USER, "Jack");
        category = new Category("Category", null, user);
        category.setId(1L);
    }

    @Test
    public void CategoryService_CreateCategory_CategoryCreated() {
        categoryService.create(category);
        Mockito.verify(categoryRepository, Mockito.times(1)).create(category);

    }

    @Test
    public void CategoryService_CreateCategoryWithInvalidName_CategoryNotCreated() {
        category = new Category("", null, user);
        assertThrows(IllegalArgumentException.class, () -> categoryService.create(category));
        Mockito.verify(categoryRepository, Mockito.times(0)).create(category);

    }

    @Test
    public void CategoryService_CreateCategoryWithNoUser_CategoryNotCreated() {
        category.setAssignedUser(null);
        assertThrows(NullPointerException.class, () -> categoryService.create(category));
        Mockito.verify(categoryRepository, Mockito.times(0)).create(category);

    }

    @Test
    public void CategoryService_CreateNullCategory_CategoryNotCreated() {
        assertThrows(NullPointerException.class, () -> categoryService.create(null));
        Mockito.verify(categoryRepository, Mockito.times(0)).create(category);

    }

    @Test
    public void CategoryService_CategoryIfAlreadyExist_CategoryNotCreated() {
        category = new Category("Category", null, user);
        when(categoryRepository.exists(category)).thenReturn(true);

        assertThrows(InstanceAlreadyExistsException.class, () -> categoryService.create(category));
        verify(categoryRepository, Mockito.times(1)).exists(category);
        verify(categoryRepository, Mockito.times(0)).create(category);

    }

    @Test
    public void CategoryService_DeleteCategoryWhenExist_CategoryDeleted() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        categoryService.delete(1L);
        verify(categoryRepository, Mockito.times(1)).delete(1L);
    }

    @Test
    public void CategoryService_DeleteCategoryNotExist_CategoryNotDeleted() {
        when(categoryRepository.findById(1L)).thenThrow(new InstanceNotFoundException("Category with id 1 not found"));
        assertThrows(InstanceNotFoundException.class, () -> categoryService.delete(1L));
        verify(categoryRepository, Mockito.times(1)).findById(1L);
        verify(categoryRepository, Mockito.times(0)).delete(1L);
    }

    @Test
    public void CategoryService_UpdateCategory_CategoryUpdated() {
        category.setId(1L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        category.setName("New Category");
        categoryService.update(category);
        verify(categoryRepository, Mockito.times(1)).update(category);
    }

    @Test
    public void CategoryService_UpdateCategoryWhenNull_CategoryNotUpdated() {
        assertThrows(NullPointerException.class, () -> categoryService.update(null));
        verify(categoryRepository, Mockito.times(0)).update(category);
    }

    @Test
    public void CategoryService_UpdateCategoryWhenNameIsNull_CategoryNotUpdated() {
        category.setName(null);
        assertThrows(NullPointerException.class, () -> categoryService.update(category));
        verify(categoryRepository, Mockito.times(0)).update(category);
    }

    @Test
    public void CategoryService_UpdateCategoryWhenNameIsEmpty_CategoryNotUpdated() {
        category.setName("");
        assertThrows(IllegalArgumentException.class, () -> categoryService.update(category));
        verify(categoryRepository, Mockito.times(0)).update(category);
    }

    @Test
    public void CategoryService_UpdateCategoryWhenIdIsEmpty_CategoryNotUpdated() {
        category.setId(null);
        assertThrows(IllegalArgumentException.class, () -> categoryService.update(category));
        verify(categoryRepository, Mockito.times(0)).update(category);
    }

    @Test
    public void CategoryService_UpdateCategoryWhenNotExist_CategoryNotUpdated() {
        when(categoryRepository.findById(1L)).thenThrow(new InstanceNotFoundException("Category with id 1 not found"));
        assertThrows(InstanceNotFoundException.class, () -> categoryService.update(category));
        verify(categoryRepository, Mockito.times(1)).findById(1L);
        verify(categoryRepository, Mockito.times(0)).update(category);
    }

}