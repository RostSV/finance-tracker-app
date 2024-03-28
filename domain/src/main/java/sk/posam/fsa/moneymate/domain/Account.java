package sk.posam.fsa.moneymate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Account {

    private Long id;
    private String name;
    private String description;
    private AccountType type;
    private User createdBy;
    private BigDecimal balance;
    private Currency currency;

    public Account(String name,
                       String description,
                       AccountType type,
                       User createdBy,
                       BigDecimal balance,
                       Currency currency) {
        this.balance = balance;
        this.currency = currency;
        this.description = description;
        this.name = Objects.requireNonNull(name, "Name of the Account cannot be null");
        this.type = Objects.requireNonNull(type, "Type of the Account cannot be null");
        this.createdBy = Objects.requireNonNull(createdBy, "Owner of the Account cannot be null");
    }

}
