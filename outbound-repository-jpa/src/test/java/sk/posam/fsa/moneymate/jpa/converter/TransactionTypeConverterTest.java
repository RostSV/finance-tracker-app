package sk.posam.fsa.moneymate.jpa.converter;

import org.junit.jupiter.api.Test;
import sk.posam.fsa.moneymate.domain.TransactionType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTypeConverterTest {

    private final TransactionTypeConverter converter = new TransactionTypeConverter();

    @Test
    void convertToDatabaseColumn_withNullTransactionType_returnsNull() {
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    void convertToDatabaseColumn_withValidTransactionType_returnsTransactionTypeName() {
        assertEquals("INCOME", converter.convertToDatabaseColumn(TransactionType.INCOME));
    }

    @Test
    void convertToEntityAttribute_withNullType_returnsNull() {
        assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    void convertToEntityAttribute_withValidType_returnsTransactionType() {
        assertEquals(TransactionType.EXPENSE, converter.convertToEntityAttribute("EXPENSE"));
    }

    @Test
    void convertToEntityAttribute_withInvalidType_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("INVALID"));
    }
}