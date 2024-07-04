package utils;

import application.product.MeasurementUnitDto;
import application.purchase.ProductOrderDto;

import java.math.BigDecimal;
import java.util.Currency;

public class ProductOrderFactory {

    public ProductOrderDto bananaOrder() {
        return ProductOrderDto.builder()
                .product("Banana", new BigDecimal("2.99"), Currency.getInstance("BRL"))
                .amount(new BigDecimal("1.50"), MeasurementUnitDto.KILOGRAM)
                .get();
    }
}
