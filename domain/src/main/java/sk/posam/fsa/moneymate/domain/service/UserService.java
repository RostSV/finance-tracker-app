package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.repository.UserRepository;

public class UserService implements UserFacade {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.create(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
