package utils;

import application.princing.PricingCurrencyDto;
import application.product.MeasurementUnitDto;
import application.purchase.ProductOrderDto;

import java.math.BigDecimal;

public class ProductOrderFactory {

    public ProductOrderDto bananaOrder() {
        return ProductOrderDto.builder()
                .product("Banana", new BigDecimal("2.99"), PricingCurrencyDto.BRL)
                .amount(new BigDecimal("1.5"), MeasurementUnitDto.KILOGRAM)
                .get();
    }
}
