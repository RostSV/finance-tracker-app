package sk.posam.fsa.moneymate.jpa.converter;

import jakarta.persistence.AttributeConverter;
import sk.posam.fsa.moneymate.domain.UserRole;

public class RoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {

        if (userRole == null) {
            return null;
        }
        return userRole.name();
    }

    @Override
    public UserRole convertToEntityAttribute(String role) {

        if (role == null) {
            return null;
        }

        try {
            return UserRole.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid value of UserRole: " + role);
        }
    }
}
