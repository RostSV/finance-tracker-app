package sk.posam.fsa.moneymate.jpa.converter;

import org.junit.jupiter.api.Test;
import sk.posam.fsa.moneymate.domain.AccountType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTypeConverterTest {

    private final AccountTypeConverter converter = new AccountTypeConverter();

    @Test
    void convertToDatabaseColumn_withNullAccountType_returnsNull() {
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    void convertToDatabaseColumn_withValidAccountType_returnsAccountTypeName() {
        assertEquals("SAVINGS", converter.convertToDatabaseColumn(AccountType.SAVINGS));
    }

    @Test
    void convertToEntityAttribute_withNullString_returnsNull() {
        assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    void convertToEntityAttribute_withValidString_returnsAccountType() {
        assertEquals(AccountType.PERSONAL, converter.convertToEntityAttribute("PERSONAL"));
    }

    @Test
    void convertToEntityAttribute_withInvalidString_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("INVALID_TYPE"));
    }
}