package sk.posam.fsa.moneymate.domain;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private Long id;
    private String email;
    private UserRole role;
    private String firstName;

    public User(String email, UserRole role, String firstName) {
        this.email = email;
        this.role = role;
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(email, user.email) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, email, role);
    }


}
