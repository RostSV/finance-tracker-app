package sk.posam.fsa.moneymate.jpa.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategorySpringDataRepository extends JpaRepository<Category, Long> {
    boolean existsByNameAndDescriptionAndAssignedUser(String name, String description, User assignedUser);

    List<Category> findAllByAssignedUser(User assignedUser);
}
