package sk.posam.fsa.moneymate.domain;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
public class User {

    private Long id;
    private String email;
    private UserRole role;
    private String firstName;

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
