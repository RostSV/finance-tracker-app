package sk.posam.fsa.moneymate.domain.transaction;

import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.Currency;

import java.math.BigDecimal;
import java.time.Instant;


public class Transaction {

    private Long id;
    private Instant createdOn;
    private BigDecimal amount;
    private String description;
    private Category category;
    private TransactionType type;
    private Currency currency;
    private Account assignedTo;

    //Empty constructor for JPA
    public Transaction() {
    }

    //Constructor for creating new transaction via factory method
    protected Transaction(BigDecimal amount,
                          String description,
                          Category category,
                          TransactionType type,
                          Currency currency,
                          Account assignedTo) {
        this.createdOn = Instant.now();
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.type = type;
        this.currency = currency;
        this.assignedTo = assignedTo;
    }

    public Long getId() {
        return id;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public TransactionType getType() {
        return  type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Account getAssignedTo() {
        return assignedTo;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setAssignedTo(Account assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }
}
