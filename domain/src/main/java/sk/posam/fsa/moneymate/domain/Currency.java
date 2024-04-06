package sk.posam.fsa.moneymate.domain;

import java.util.Objects;

public class Currency {

    private Long id;
    private String code;
    private String name;
    private String sign;

    //Empty constructor for JPA
    public Currency() {
    }

    public Currency(String code) {
        this.code = Objects.requireNonNull(code, "Currency code cannot be null");
    }

    public Currency(String code, String name, String sign) {
        this.code = Objects.requireNonNull(code, "Currency code cannot be null");
        this.name = name;
        this.sign = sign;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getSign() {
        return sign;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(name, currency.name) && Objects.equals(code, currency.code) && Objects.equals(sign, currency.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, sign);
    }
}
