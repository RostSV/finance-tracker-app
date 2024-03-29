package sk.posam.fsa.moneymate.jpa.converter;

import jakarta.persistence.AttributeConverter;
import sk.posam.fsa.moneymate.domain.AccountType;

public class AccountTypeConverter implements AttributeConverter<AccountType, String> {
    @Override
    public String convertToDatabaseColumn(AccountType accountType) {
        if (accountType == null) {
            return null;
        }

        return accountType.toString();
    }

    @Override
    public AccountType convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }

        try {
            return AccountType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid value for enum AccountType: " + type);
        }
    }
}
