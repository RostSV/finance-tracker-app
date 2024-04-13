package sk.posam.fsa.moneymate.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private Long id;
    private String name;
    private String description;
    private BigDecimal balance;
    private AccountType type;
    private Currency currency;
    private User createdBy;

    //Empty constructor for JPA
    public Account() {
    }

    public Account(String name, String description, BigDecimal balance,
                   AccountType type, Currency currency, User createdBy) {
        this.name = Objects.requireNonNull(name, "Name of account cannot be null");
        this.description = description;
        this.balance = Objects.requireNonNull(balance, "Balance of account cannot be null");
        this.type = Objects.requireNonNull(type, "Type of account cannot be null");
        this.currency = Objects.requireNonNull(currency, "Currency of account cannot be null");
        this.createdBy = Objects.requireNonNull(createdBy, "Creator of account cannot be null");
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

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountType getType() {
        return type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public User getCreatedBy() {
        return createdBy;
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

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
