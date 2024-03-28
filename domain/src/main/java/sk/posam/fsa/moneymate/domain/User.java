package sk.posam.fsa.moneymate.domain;
import lombok.*;

import java.util.Objects;
@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
//    private Categories categories;


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof User user) {
            return Objects.equals(getId(), user.getId())
                    && Objects.equals(getName(), user.getName())
                    && Objects.equals(getEmail(), user.getEmail());
        }
        return false;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", categories=" + categories +
//                '}';
//    }
}
