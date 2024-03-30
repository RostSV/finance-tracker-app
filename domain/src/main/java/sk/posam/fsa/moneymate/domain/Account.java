package sk.posam.fsa.moneymate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Account {

    private Long id;
    private String name;
    private String description;
    private BigDecimal balance;
    private AccountType type;
    private Currency currency;
    private User createdBy;


}
