package sk.posam.fsa.moneymate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long accountId;
    private Category category;
    private BigDecimal amount;
    private String description;
    private TransactionType type;
    private Currency currency;


    protected Transaction(Long accountId,
                          Category category,
                          BigDecimal amount,
                          String description,
                          TransactionType type){
        this.accountId = Objects.requireNonNull(accountId, "Account ID cannot be null");
        this.category = Objects.requireNonNull(category, "Category ID cannot be null");
        this.description = description;
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null");
        this.type = Objects.requireNonNull(type, "Type cannot be null");
        this.date = LocalDateTime.now();
    }

    public String getFormatDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return date.format(formatter);
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
