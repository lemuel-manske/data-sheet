package princing;

public class PriceDto {

    private String id;
    private float value;
    private PricingCurrencyDto currency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public PricingCurrencyDto getCurrency() {
        return currency;
    }

    public void setCurrency(PricingCurrencyDto currency) {
        this.currency = currency;
    }
}
