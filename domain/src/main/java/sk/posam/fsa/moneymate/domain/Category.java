package sk.posam.fsa.moneymate.domain;

import java.util.Objects;

public class Category {

    private Long id;
    private String name;
    private String description;
    private User assignedUser;

    //Empty constructor for JPA
    public Category() {
    }

    public Category(String name, String description, User user) {
        this.name = Objects.requireNonNull(name, "Category name cannot be null");
        this.description = description;
        this.assignedUser = Objects.requireNonNull(user, "User cannot be null in category");

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", assignedUser=" + assignedUser +
                '}';
    }
}
