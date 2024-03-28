package sk.posam.fsa.moneymate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.fsa.moneymate.rest.api.CategoriesApi;
import sk.posam.fsa.moneymate.rest.dto.CategoryDto;

import java.util.Collections;
import java.util.List;
@RestController
public class CategoryRestController implements CategoriesApi {
    @Override
    public ResponseEntity<Void> createCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<CategoryDto>> listCategories() {
       return ResponseEntity.ok().body(Collections.emptyList());
    }



}
