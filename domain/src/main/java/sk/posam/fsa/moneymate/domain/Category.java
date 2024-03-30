package sk.posam.fsa.moneymate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Category {

    private Long id;
    private String name;
    private String description;
    private User assignedUser;

    public Category(String name, User user) {
        this.name = Objects.requireNonNull(name, "Category name cannot be null");
        this.assignedUser = Objects.requireNonNull(user, "User cannot be null");

    }
}
