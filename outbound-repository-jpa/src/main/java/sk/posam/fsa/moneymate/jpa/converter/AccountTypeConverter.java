package sk.posam.fsa.moneymate.jpa.converter;

import jakarta.persistence.AttributeConverter;
import sk.posam.fsa.moneymate.domain.AccountType;

public class AccountTypeConverter implements AttributeConverter<AccountType, String> {
    @Override
    public String convertToDatabaseColumn(AccountType accountType) {
        return null;
    }

    @Override
    public AccountType convertToEntityAttribute(String s) {
        return null;
    }
}
