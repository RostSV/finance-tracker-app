package sk.posam.fsa.moneymate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.UserRole;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceNotFoundException;
import sk.posam.fsa.moneymate.domain.service.CategoryService;
import sk.posam.fsa.moneymate.exception.GlobalRestExceptionHandler;
import sk.posam.fsa.moneymate.mapper.CategoryMapper;
import sk.posam.fsa.moneymate.rest.dto.CategoryDto;
import sk.posam.fsa.moneymate.rest.dto.UserDto;
import sk.posam.fsa.moneymate.rest.dto.UserRoleDto;
import sk.posam.fsa.moneymate.security.CurrentUserDetailService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryRestControllerTest {


    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private CurrentUserDetailService currentUserDetailService;

    @InjectMocks
    private CategoryRestController categoryRestController;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;

    private UserDto userDto;
    private CategoryDto categoryDto;
    private User user;
    private Category category;

    @BeforeEach
    void setUp() {

        userDto = new UserDto("email@example.com", UserRoleDto.USER);
        categoryDto = new CategoryDto("Category", userDto);
        user = new User("email@example.com", UserRole.USER, "Mike");
        category = new Category("Category", null, user);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryRestController)
                .setControllerAdvice(new GlobalRestExceptionHandler())
                .build();
    }
    

    @Test
    void CategoryController_CreateCategory_CategoryCreated() throws Exception {

        when(categoryMapper.toEntity(any(CategoryDto.class))).thenReturn(category);
        when(currentUserDetailService.getFullCurrentUser()).thenReturn(user);
        mockMvc.perform(post("/api/v1/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isOk());
        verify(categoryService, times(1)).create(category);
    }

    @Test
    void CategoryController_UpdateCategory_CategoryUpdated() throws Exception {
        mockMvc.perform(put("/api/v1/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk());
    }

    @Test
    void CategoryController_DeleteCategory_CategoryDeleted() throws Exception {

        mockMvc.perform(delete("/api/v1/categories/1"))
                .andExpect(status().isNoContent());
        verify(categoryService, times(1)).delete(1L);
    }

    @Test
    void CategoryController_CreateCategoryWithInvalidName_CategoryNotCreated() throws Exception {
        categoryDto.setName("");
        mockMvc.perform(post("/api/v1/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void CategoryController_CreateNullCategory_CategoryNotCreated() throws Exception {
        mockMvc.perform(post("/api/v1/categories")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void CategoryController_CreateCategoryWithNullUser_CategoryNotCreated() throws Exception {
        categoryDto.setAssignedUser(null);
        mockMvc.perform(post("/api/v1/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void CategoryController_CreateCategoryWithNullUserEmail_CategoryNotCreated() throws Exception {
        userDto.setEmail(null);
        categoryDto.setAssignedUser(userDto);
        mockMvc.perform(post("/api/v1/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void CategoryController_CreateCategoryWithNullUserRole_CategoryNotCreated() throws Exception {
        userDto.setRole(null);
        categoryDto.setAssignedUser(userDto);
        mockMvc.perform(post("/api/v1/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void CategoryController_CreateCategoryWithNullUserFirstName_CategoryNotCreated() throws Exception {
        userDto.setFirstName(null);
        categoryDto.setAssignedUser(userDto);
        mockMvc.perform(post("/api/v1/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void CategoryController_CreateCategoryWithNullCategoryName_CategoryNotCreated() throws Exception {
        categoryDto.setName(null);
        mockMvc.perform(post("/api/v1/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void CategoryController_DeleteCategoryWhenNotExist_CategoryNotDeleted() throws Exception {
        doThrow(new InstanceNotFoundException("Category with id 1 not found")).when(categoryService).delete(1L);
        mockMvc.perform(delete("/api/v1/categories/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void CategoryController_UpdateCategoryWhenNotExist_CategoryNotUpdated() throws Exception {
        categoryDto.setId(1L);
        category = categoryMapper.toEntity(categoryDto);
        doThrow(new InstanceNotFoundException("Category not found")).when(categoryService).update(category);
        mockMvc.perform(put("/api/v1/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isNotFound());
    }

}