package sk.posam.fsa.moneymate.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.posam.fsa.moneymate.domain.Category;

@Repository
public interface CategorySpringDataRepository extends JpaRepository<Category, Long> {
}
