package sk.posam.fsa.moneymate.jpa.converter;

import jakarta.persistence.AttributeConverter;
import sk.posam.fsa.moneymate.domain.TransactionType;

public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {
    @Override
    public String convertToDatabaseColumn(TransactionType transactionType) {
        return null;
    }

    @Override
    public TransactionType convertToEntityAttribute(String s) {
        return null;
    }
}
