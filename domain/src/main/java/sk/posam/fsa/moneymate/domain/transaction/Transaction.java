package sk.posam.fsa.moneymate.domain.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    private Long id;
    private LocalDateTime date;
    private BigDecimal amount;
    private String description;
    private Category category;
    private TransactionType type;
    private Currency currency;
    private Account assignedTo;

    public Transaction(BigDecimal amount,
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


}
