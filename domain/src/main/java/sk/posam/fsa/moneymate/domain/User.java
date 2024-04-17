package sk.posam.fsa.moneymate.domain;

import java.util.Objects;

public class User {

    private Long id;
    private String email;
    private UserRole role;
    private String name;

    //Empty constructor for JPA
    public User() {
    }

    public User(String email, UserRole role, String name) {
        this.email = Objects.requireNonNull(email, "User email cannot be null");
        this.role = Objects.requireNonNull(role, "User role cannot be null");
        this.name = name;
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

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, role);
    }


}
