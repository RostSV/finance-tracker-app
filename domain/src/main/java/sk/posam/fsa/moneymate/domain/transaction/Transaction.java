package sk.posam.fsa.moneymate.domain.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.Currency;
import sk.posam.fsa.moneymate.domain.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
    private Long accountId;

    public Transaction(BigDecimal amount,
                       String description,
                       Category category,
                       TransactionType type,
                       Currency currency,
                       Long accountId) {
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.type = type;
        this.currency = currency;
        this.accountId = accountId;
    }

    public String getFormatDate() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id)
                && Objects.equals(date, that.date)
                && Objects.equals(accountId, that.accountId) && Objects.equals(category, that.category)
                && Objects.equals(amount, that.amount) && Objects.equals(description, that.description)
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, accountId, category, amount, description, type);
    }
}
