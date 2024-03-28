package sk.posam.fsa.moneymate.jpa.converter;

import jakarta.persistence.AttributeConverter;
import sk.posam.fsa.moneymate.domain.Category;

public class CategoryConverter implements AttributeConverter<Category, String> {
    @Override
    public String convertToDatabaseColumn(Category category) {
        return null;
    }

    @Override
    public Category convertToEntityAttribute(String s) {
        return null;
    }
}
