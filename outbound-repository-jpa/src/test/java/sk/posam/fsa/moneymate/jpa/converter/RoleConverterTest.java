package sk.posam.fsa.moneymate.jpa.converter;

import org.junit.jupiter.api.Test;
import sk.posam.fsa.moneymate.domain.UserRole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoleConverterTest {

    private final RoleConverter converter = new RoleConverter();

    @Test
    void convertToDatabaseColumn_withNullUserRole_returnsNull() {
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    void convertToDatabaseColumn_withValidUserRole_returnsUserRoleName() {
        assertEquals("ADMIN", converter.convertToDatabaseColumn(UserRole.ADMIN));
    }

    @Test
    void convertToEntityAttribute_withNullRole_returnsNull() {
        assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    void convertToEntityAttribute_withValidRole_returnsUserRole() {
        assertEquals(UserRole.USER, converter.convertToEntityAttribute("USER"));
    }

    @Test
    void convertToEntityAttribute_withInvalidRole_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("INVALID_ROLE"));
    }
}