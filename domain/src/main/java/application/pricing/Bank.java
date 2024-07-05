package application.pricing;

import java.math.BigDecimal;
import java.util.Currency;

public class Bank {

    public static Price brl(String price) {
        return new Price(Currency.getInstance("BRL"), new BigDecimal(price));
    }
}