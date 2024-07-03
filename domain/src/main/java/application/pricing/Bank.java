package application.pricing;

import java.math.BigDecimal;

public class Bank {

    public static Price brl(String price) {
        return new Price(PricingCurrency.BRL, new BigDecimal(price));
    }

    public static Price brl(BigDecimal price) {
        return new Price(PricingCurrency.BRL, price);
    }
}