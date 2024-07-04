package application.princing;

import java.math.BigDecimal;
import java.util.Objects;

public class PriceDto {

    private String id;
    private BigDecimal value;
    private PricingCurrencyDto currency;

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

    public PricingCurrencyDto getCurrency() {
        return currency;
    }

    public void setCurrency(PricingCurrencyDto currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        PriceDto priceDto = (PriceDto) o;
        return id.equals(priceDto.id)
                && value.equals(priceDto.value)
                && currency.equals(priceDto.currency);
    }

    @Override
    public String toString() {
        return "PriceDto{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", currency=" + currency +
                '}';
    }
}
