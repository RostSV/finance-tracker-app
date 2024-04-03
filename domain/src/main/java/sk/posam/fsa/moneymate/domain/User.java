package sk.posam.fsa.moneymate.domain;

import java.util.Objects;

public class User {

    private Long id;
    private String email;
    private UserRole role;
    private String firstName;

    //Empty constructor for JPA
    public User() {
    }

    public User(String email, UserRole role, String firstName) {
        this.email = Objects.requireNonNull(email, "User email cannot be null");
        this.role = Objects.requireNonNull(role, "User role cannot be null");
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
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
