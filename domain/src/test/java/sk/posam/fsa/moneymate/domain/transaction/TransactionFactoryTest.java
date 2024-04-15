package sk.posam.fsa.moneymate.domain.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.Currency;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionFactoryTest {

    @Mock
    private AccountRepository accountRepository;

    private TransactionFactory transactionFactory;

    Category category = new Category("Salary", null, new User());
    Currency currency = new Currency("USD");
    Account account;

    @BeforeEach
    public void setUp() {
        account = new Account();
        MockitoAnnotations.openMocks(this);
        transactionFactory = new TransactionFactory(accountRepository);
    }

    @Test
    public void createIncomeProducesIncomeTransaction() {
        when(accountRepository.findById(any())).thenReturn(Optional.ofNullable(account));

        Transaction transaction = transactionFactory.createIncome(new BigDecimal("100.00"), "Salary", category, currency, account);

        assertNotNull(transaction);
        assertEquals(TransactionType.INCOME, transaction.getType());
    }

    @Test
    public void createExpenseProducesExpenseTransaction() {
        when(accountRepository.findById(any())).thenReturn(Optional.ofNullable(account));

        Transaction transaction = transactionFactory.createExpense(new BigDecimal("50.00"), "Groceries", category, currency, account);

        assertNotNull(transaction);
        assertEquals(TransactionType.EXPENSE, transaction.getType());
    }

    @Test
    public void createIncomeWithNullAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> transactionFactory.createIncome(null, "Salary", category, currency, account));
    }

    @Test
    public void createExpenseWithNullAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> transactionFactory.createExpense(null, "Groceries", category, currency, account));
    }

    @Test
    public void createIncomeWithInvalidAccountThrowsException() {
        when(accountRepository.findById(any())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> transactionFactory.createIncome(new BigDecimal("100.00"), "Salary", category, currency, null));
    }

    @Test
    public void createExpenseWithInvalidAccountShouldThrowException() {
        when(accountRepository.findById(any())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> transactionFactory.createExpense(new BigDecimal("50.00"), "Groceries", category, currency, null));
    }

    @Test
    public void createIncomeWithNegativeAmountShouldThrowException() {

        assertThrows(IllegalArgumentException.class, () -> transactionFactory.createIncome(new BigDecimal("-100.00"), "Salary", category, currency, account));
    }

    @Test
    public void createIncomeWithZeroAmountShouldThrowException() {

        assertThrows(IllegalArgumentException.class, () -> transactionFactory.createIncome(new BigDecimal("0.00"), "Salary", category, currency, account));
    }

    @Test
    public void createTransactionWithNullCategoryShouldThrowException() {

        assertThrows(IllegalArgumentException.class, () -> transactionFactory.createExpense(new BigDecimal("150.00"), "Groceries", null, currency, account));
    }

    @Test
    public void createTransactionWithNullCurrencyShouldThrowException() {

        assertThrows(IllegalArgumentException.class, () -> transactionFactory.createExpense(new BigDecimal("100.00"), "Groceries", category, null, account));
    }

    @Test
    public void createTransactionWithNullAccountShouldThrowException() {

        assertThrows(IllegalArgumentException.class, () -> transactionFactory.createExpense(new BigDecimal("100.00"), "Groceries", category, currency, null));
    }


}
