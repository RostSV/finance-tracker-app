package sk.posam.fsa.moneymate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.UserRole;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceAlreadyExistsException;
import sk.posam.fsa.moneymate.domain.service.UserService;
import sk.posam.fsa.moneymate.exception.GlobalRestExceptionHandler;
import sk.posam.fsa.moneymate.mapper.UserMapper;
import sk.posam.fsa.moneymate.rest.dto.UserDto;
import sk.posam.fsa.moneymate.rest.dto.UserRoleDto;
import sk.posam.fsa.moneymate.security.CurrentUserDetailService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private CurrentUserDetailService currentUserDetailService;

    @InjectMocks
    private UserRestController userRestController;

    private ObjectMapper objectMapper;

    MockMvc mockMvc;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("user@gmail.com", UserRole.USER, "Mike");
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController)
                .setControllerAdvice(new GlobalRestExceptionHandler())
                .build();
    }

    @Test
    public void UserRestController_CreateUser_UserCreated() throws Exception {
        UserDto userDto = new UserDto("user@gmail.com", UserRoleDto.USER);
        userDto.setName("Mike");
        when(userMapper.toUserEntity(userDto)).thenReturn(user);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());

        verify(userService, times(1)).create(user);
    }


    @Test
    public void UserRestController_ListUsers_ReturnsIsForbidden() throws Exception {
        when(currentUserDetailService.isAdmin()).thenReturn(false);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isForbidden());

        verify(currentUserDetailService, times(1)).isAdmin();
        verify(userService, times(0)).findAll();
    }

    @Test
    public void UserRestController_ListUsers_ReturnList() throws Exception {

        List<User> users = List.of(user);

        when(currentUserDetailService.isAdmin()).thenReturn(true);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(currentUserDetailService, times(1)).isAdmin();
    }

    @Test
    public void UserRestController_CreateUserIfExists_ReturnIsConflict() throws Exception {
        doThrow(new InstanceAlreadyExistsException())
                .when(userService).create(any(User.class));
        when(userMapper.toUserEntity(any(UserDto.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isConflict());
    }

    @Test
    public void UserRestController_CreateUserIfEmailIsNull_ReturnBadRequest() throws Exception {
        user.setEmail(null);
        when(userMapper.toUserEntity(any(UserDto.class))).thenReturn(user);
        doThrow(new IllegalArgumentException())
                .when(userService).create(any(User.class));

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }


}