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
        Category entity = new Category();
        entity.setId(categoryDto.getId());
        entity.setName(categoryDto.getName());
        entity.setDescription(categoryDto.getDescription());
        entity.setAssignedUser(userMapper.toUserEntity(categoryDto.getAssignedUser()));
        return entity;
    }
}
