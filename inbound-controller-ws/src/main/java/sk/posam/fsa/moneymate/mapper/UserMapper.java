package sk.posam.fsa.moneymate.mapper;

import org.springframework.stereotype.Component;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.UserRole;
import sk.posam.fsa.moneymate.rest.dto.UserDto;
import sk.posam.fsa.moneymate.rest.dto.UserRoleDto;

@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        if (user == null)
            return null;

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setRole(UserRoleDto.valueOf(user.getRole().name()));
        return userDto;
    }

    public User toUserEntity(UserDto userDto) {
        if (userDto == null)
            return null;

        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        user.setRole(UserRole.valueOf(userDto.getRole().name()));
        return user;
    }
}
