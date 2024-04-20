package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.UserRole;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceAlreadyExistsException;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceNotFoundException;
import sk.posam.fsa.moneymate.domain.repository.UserRepository;

import java.util.List;

public class UserService implements UserFacade {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new InstanceAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }
        if (user.getRole() == null || user.getEmail() == null)
            throw new IllegalArgumentException("User must have email and role");

        userRepository.create(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new InstanceNotFoundException("User with id: " + id + " is not present."));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll()
                .stream()
                .toList();
    }

    @Override
    public void updateUser(User userEntity) {

        userRepository.findById(userEntity.getId())
                .orElseThrow(() -> new InstanceNotFoundException("User with id: " + userEntity.getId() + " is not present."));

        validateUser(userEntity);
        userRepository.update(userEntity);
    }

    private void validateUser(User user) {
        if (user.getName() == null)
            throw new IllegalArgumentException("Name parameter for user cant be null");

        if (user.getName().isEmpty())
            throw new IllegalArgumentException("Name parameter for user cant be empty");

        if (user.getEmail() == null)
            throw new IllegalArgumentException("Email parameter for user cant be null");

        //TODO maybe refactor
        //will throw IllegalArgumentException if Role is not present
        UserRole.valueOf(user.getRole().toString());
    }

}




