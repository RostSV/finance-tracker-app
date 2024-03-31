package sk.posam.fsa.moneymate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.fsa.moneymate.domain.service.UserFacade;
import sk.posam.fsa.moneymate.mapper.UserMapper;
import sk.posam.fsa.moneymate.rest.api.UsersApi;
import sk.posam.fsa.moneymate.rest.dto.UserDto;

import java.util.List;

@RestController
public class UserRestController implements UsersApi {

    private final UserFacade userService;
    private final UserMapper userMapper;

    public UserRestController(UserFacade userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<Void> createUser(UserDto userDto) {
        userService.create(userMapper.toUserEntity(userDto));
        return ResponseEntity.ok().build();
    }


    //TODO: Implement the method so only admin could list all users
    @Override
    public ResponseEntity<List<UserDto>> listUsers() {
        return null;
    }
}
