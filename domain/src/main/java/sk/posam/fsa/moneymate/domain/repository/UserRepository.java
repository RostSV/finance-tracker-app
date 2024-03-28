package sk.posam.fsa.moneymate.domain.repository;

import sk.posam.fsa.moneymate.domain.User;

/**
 * UserRepository is an interface that defines the contract for user-related database operations.
 * Implementations of this interface are expected to provide the functionality for these operations.
 */
public interface UserRepository {

    /**
     * Creates a new user in the database.
     *
     * @param user The user object to be created. This object should have all the necessary information
     *             required to create a new user in the database.
     */
    void create(User user);

    /**
     * Finds a user in the database by their ID.
     *
     * @param id The ID of the user to be found.
     * @return The User object if found in the database, null otherwise.
     */
    User findById(Long id);

    /**
     * Finds a user in the database by their email.
     *
     * @param email The email of the user to be found.
     * @return The User object if found in the database, null otherwise.
     */
    User findByEmail(String email);
}