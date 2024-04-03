package sk.posam.fsa.moneymate.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.UserRole;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceAlreadyExistsException;
import sk.posam.fsa.moneymate.domain.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("@example.com", UserRole.USER, "Jack");
    }

    @Test
    public void UserService_CreateUser_UserCreated() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        userService.create(user);
        verify(userRepository, times(1)).create(user);
    }

    @Test
    public void UserService_CreateUserWithExistingEmail_UserNotCreated() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        assertThrows(InstanceAlreadyExistsException.class, () -> userService.create(user));
        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userRepository, times(0)).create(user);
    }

    @Test
    public void UserService_CreateUserWithNoEmail_UserNotCreated() {
        user.setEmail(null);
        assertThrows(IllegalArgumentException.class, () -> userService.create(user));
        verify(userRepository, times(0)).create(user);
    }

    @Test
    public void UserService_CreateUserWithNoRole_UserNotCreated() {
        user.setRole(null);
        assertThrows(IllegalArgumentException.class, () -> userService.create(user));
        verify(userRepository, times(0)).create(user);
    }

    @Test
    public void UserService_FindById_ReturnsCorrectUser() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(user);
        User foundUser = userService.findById(id);
        assertEquals(user, foundUser);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void UserService_FindByEmail_ReturnsCorrectUser() {
        String email = "@example.com";
        when(userRepository.findByEmail(email)).thenReturn(user);
        User foundUser = userService.findByEmail(email);
        assertEquals(user, foundUser);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void UserService_FindAll_ReturnsAllUsers() {
        List<User> users = List.of(user);
        when(userRepository.findAll()).thenReturn(users);
        List<User> foundUsers = userService.findAll();
        assertEquals(users, foundUsers);
        verify(userRepository, times(1)).findAll();
    }

}