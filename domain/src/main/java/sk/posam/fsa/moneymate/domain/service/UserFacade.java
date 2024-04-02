package sk.posam.fsa.moneymate.domain.service;

import sk.posam.fsa.moneymate.domain.User;

import java.util.List;

/**
 * UserFacade is an interface that defines the contract for user-related operations.
 * Implementations of this interface are expected to provide the functionality for these operations.
 */
public interface UserFacade {

    /**
     * Creates a new user.
     *
     * @param user The user object to be created. This object should have all the necessary information
     *             required to create a new user.
     */
    void create(User user);

    /**
     * Finds a user by their ID.
     *
     * @param id The ID of the user to be found.
     * @return The User object if found, null otherwise.
     */
    User findById(Long id);

    /**
     * Finds a user by their email.
     *
     * @param email The email of the user to be found.
     * @return The User object if found, null otherwise.
     */
    User findByEmail(String email);

    List<User> findAll();
}