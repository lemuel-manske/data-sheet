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

    private BigDecimal priceValue;

    public Price() { }

    public Price(Currency currency, BigDecimal price) {
        this.currency = currency;
        this.priceValue = price;
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
        return priceValue;
    }

    public void setPrice(BigDecimal value) {
        this.priceValue = value;
    }
}
