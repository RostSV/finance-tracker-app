package sk.posam.fsa.moneymate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.service.CategoryFacade;
import sk.posam.fsa.moneymate.mapper.CategoryMapper;
import sk.posam.fsa.moneymate.rest.api.CategoriesApi;
import sk.posam.fsa.moneymate.rest.dto.CategoryDto;
import sk.posam.fsa.moneymate.security.CurrentUserDetailService;

import java.util.Collections;
import java.util.List;

//RestController for Category version 1.0
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController implements CategoriesApi {

    private final CurrentUserDetailService currentUserDetailService;
    private final CategoryMapper categoryMapper;
    private final CategoryFacade categoryService;

    public CategoryRestController(CurrentUserDetailService currentUserDetailService, CategoryMapper categoryMapper, CategoryFacade categoryService) {
        this.currentUserDetailService = currentUserDetailService;
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<Void> createCategory(CategoryDto categoryDto) {

        Category category = categoryMapper.toEntity(categoryDto);
        category.setAssignedUser(currentUserDetailService.getFullCurrentUser());
        categoryService.create(category);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<Category> allCategories = categoryService
                .findAllByUser(currentUserDetailService.getFullCurrentUser());
        
        return allCategories != null ? ResponseEntity.ok()
                .body(allCategories.stream()
                        .map(categoryMapper::toDto)
                        .toList())
                : ResponseEntity.ok().body(Collections.emptyList());
    }

    @Override
    public ResponseEntity<Void> updateCategory(CategoryDto categoryDto) {
        categoryService.update(categoryMapper.toEntity(categoryDto));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteCategory(Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
