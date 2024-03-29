package sk.posam.fsa.moneymate.jpa;

import org.springframework.stereotype.Repository;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.repository.UserRepository;

@Repository
public class JpaUserRepositoryAdapter implements UserRepository {

    private final UserSpringDataRepository userSpringDataRepository;

    public JpaUserRepositoryAdapter(UserSpringDataRepository userSpringDataRepository) {
        this.userSpringDataRepository = userSpringDataRepository;
    }

    @Override
    public void create(User user) {
        userSpringDataRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userSpringDataRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {

        return userSpringDataRepository.findByEmail(email).orElse(null);
    }
}
