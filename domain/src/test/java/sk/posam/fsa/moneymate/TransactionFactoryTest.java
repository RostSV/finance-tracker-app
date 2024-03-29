package sk.posam.fsa.moneymate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sk.posam.fsa.moneymate.domain.*;
import sk.posam.fsa.moneymate.domain.repository.AccountRepository;
import sk.posam.fsa.moneymate.domain.transaction.InvalidTransactionArgsException;
import sk.posam.fsa.moneymate.domain.transaction.Transaction;
import sk.posam.fsa.moneymate.domain.transaction.TransactionFactory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class TransactionFactoryTest {

    @Mock
    AccountRepository accountRepository;

    TransactionFactory transactionFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionFactory = new TransactionFactory(accountRepository);
    }

    @Test
    void createIncome_withValidArgs_returnsIncomeTransaction() throws InvalidTransactionArgsException {
        when(accountRepository.findById(1L)).thenReturn(new Account());
        Transaction transaction = transactionFactory.createIncome(BigDecimal.ONE, "Description", new Category("Shopping", new User()), new Currency(), 1L);
        assertEquals(TransactionType.INCOME, transaction.getType());
    }

    @Test
    void createIncome_withInvalidArgs_throwsException() {
        assertThrows(InvalidTransactionArgsException.class, () -> transactionFactory.createIncome(null, "Description", new Category("Shopping", new User()), new Currency(), 1L));
    }

    @Test
    void createExpense_withValidArgs_returnsExpenseTransaction() throws InvalidTransactionArgsException {
        when(accountRepository.findById(1L)).thenReturn(new Account());
        Transaction transaction = transactionFactory.createExpense(BigDecimal.ONE, "Description", new Category("Shopping", new User()), new Currency(), 1L);
        assertEquals(TransactionType.EXPENSE, transaction.getType());
    }

    @Test
    void createExpense_withInvalidArgs_throwsException() {
        assertThrows(InvalidTransactionArgsException.class, () -> transactionFactory.createExpense(null, "Description", new Category("Shopping", new User()), new Currency(), 1L));
    }
}