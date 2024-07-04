package application.princing;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class PriceDto {

    private String id;
    private BigDecimal value;
    private Currency currency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDto priceDto = (PriceDto) o;
        return Objects.equals(id, priceDto.id) && Objects.equals(value, priceDto.value) && Objects.equals(currency, priceDto.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, currency);
    }
}
