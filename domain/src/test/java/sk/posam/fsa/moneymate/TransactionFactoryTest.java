package sk.posam.fsa.moneymate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.posam.fsa.moneymate.domain.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionFactoryTest {

    Long accountId;
    Category category;
    BigDecimal amount;
    String description;
    Long categoryId;

    @BeforeEach
    void setUp() {
        accountId = 1L;
        amount = BigDecimal.ONE;
        description = "Description";
        categoryId = 1L;
    }


    @Test
    void categoryAssignedAfterCreationIfProvided() {
        category = new Category("Category", new User());
        Transaction transaction = TransactionFactory.createIncome(accountId, category, amount, description, TransactionType.INCOME);
        assertSame(transaction.getCategory(), category);
    }

    @Test
    void crashIfAccountIsNull() {
        assertThrows(NullPointerException.class,
                () -> TransactionFactory.createIncome(null, category, amount, description, TransactionType.INCOME));
    }

    @Test
    void crashIfAmountIsNull() {
        assertThrows(NullPointerException.class,
                () -> TransactionFactory.createIncome(accountId, category, null, description, TransactionType.INCOME));
    }

    @Test
    void crashIfTypeIsNull() {
        assertThrows(NullPointerException.class,
                () -> TransactionFactory.createIncome(accountId, category, amount, description, null));
    }


}