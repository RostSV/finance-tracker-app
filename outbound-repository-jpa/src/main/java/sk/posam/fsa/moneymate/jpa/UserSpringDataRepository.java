package sk.posam.fsa.moneymate.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.posam.fsa.moneymate.domain.User;

import java.util.Optional;

public interface UserSpringDataRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
