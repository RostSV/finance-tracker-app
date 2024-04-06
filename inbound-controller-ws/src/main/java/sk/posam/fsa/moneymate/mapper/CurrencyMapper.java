package sk.posam.fsa.moneymate.mapper;

import org.springframework.stereotype.Component;
import sk.posam.fsa.moneymate.domain.Category;
import sk.posam.fsa.moneymate.domain.Currency;
import sk.posam.fsa.moneymate.rest.dto.CategoryDto;
import sk.posam.fsa.moneymate.rest.dto.CurrencyDto;

@Component
public class CurrencyMapper {
    public Currency toEntity(CurrencyDto currencyDto) {

        if (currencyDto == null)
            return null;

        Currency entity = new Currency();
        entity.setId(currencyDto.getId());
        entity.setCode(currencyDto.getCode());
        entity.setName(currencyDto.getName());
        entity.setSign(currencyDto.getSign());
        return entity;
    }

    public CurrencyDto toDto(Currency currency) {

        if (currency == null)
            return null;

        CurrencyDto dto = new CurrencyDto();

        dto.setId(currency.getId());
        dto.setName(currency.getName());
        dto.setCode(currency.getCode());
        dto.setSign(currency.getSign());

        return dto;

    }
}
