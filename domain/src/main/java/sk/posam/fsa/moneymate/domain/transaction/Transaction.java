package sk.posam.fsa.moneymate.domain.transaction;

import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private Long id;
    private LocalDateTime date;
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
        this.date = LocalDateTime.now();
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

    public LocalDateTime getDate() {
        return date;
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
        return (TransactionType)type;
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

    public void setDate(LocalDateTime date) {
        this.date = date;
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
}
