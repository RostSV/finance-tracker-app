package sk.posam.fsa.moneymate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Category {

    private long id;
    private String name;
    private String description;

    public Category(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }


}
