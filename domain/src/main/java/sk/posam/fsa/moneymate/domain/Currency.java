package sk.posam.fsa.moneymate.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Currency {

    private Long id;
    private String name;
    private String code;
    private String symbol;

    //TODO add builder
    public Currency(String code, String symbol) {
        this.code = Objects.requireNonNull(code, "Currency code cannot be null");
        this.symbol = symbol;
    }

    public Currency(String code, String symbol,String name) {
        this.name = Objects.requireNonNull(name, "Currency name cannot be null");
        this.code = Objects.requireNonNull(code, "Currency code cannot be null");
        this.symbol = symbol;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(name, currency.name) && Objects.equals(code, currency.code) && Objects.equals(symbol, currency.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, symbol);
    }
}
