package sk.posam.fsa.moneymate.jpa.converter;

import jakarta.persistence.AttributeConverter;
import sk.posam.fsa.moneymate.domain.TransactionType;

public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {
    @Override
    public String convertToDatabaseColumn(TransactionType transactionType) {
        if (transactionType == null) {
            return null;
        }
        return transactionType.name();
    }

    @Override
    public TransactionType convertToEntityAttribute(String type) {

        if (type == null) {
            return null;
        }
        try {
            return TransactionType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid value for enum TransactionType: " + type);
        }
    }
}
