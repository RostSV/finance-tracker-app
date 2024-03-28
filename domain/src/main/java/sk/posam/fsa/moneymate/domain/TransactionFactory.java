package sk.posam.fsa.moneymate.domain;

import java.math.BigDecimal;

public class TransactionFactory {

    public static Transaction createIncome(Long accountId, Category category, BigDecimal amount, String description, TransactionType type) {
        return new Transaction(accountId, category, amount, description, type);
    }
}
