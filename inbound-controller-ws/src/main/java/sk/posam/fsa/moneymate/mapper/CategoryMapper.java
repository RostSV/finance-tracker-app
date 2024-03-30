package sk.posam.fsa.moneymate.mapper;

import org.springframework.stereotype.Component;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.rest.dto.CategoryDto;

@Component
public class CategoryMapper {

    private final UserMapper userMapper;

    public CategoryMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Category toEntity(CategoryDto categoryDto) {

        if (categoryDto == null) {
            return null;
        }

        Category entity = new Category();
        entity.setId(categoryDto.getId());
        entity.setName(categoryDto.getName());
        entity.setDescription(categoryDto.getDescription());
        entity.setAssignedUser(userMapper.toUserEntity(categoryDto.getAssignedUser()));
        return entity;
    }

    public CategoryDto toDto(Category category) {

        if (category == null) {
            return null;
        }

        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setAssignedUser(userMapper.toUserDto(category.getAssignedUser()));
        return dto;
    }
}
