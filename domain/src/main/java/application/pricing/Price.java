package application.pricing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Currency;

@Entity
public class Price {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Currency currency;
    private BigDecimal price;

    public Price() { }

    public Price(PricingCurrency currency, BigDecimal price) {
        this.currency = Currency.getInstance(currency.toString());
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal value) {
        this.price = value;
    }
}
