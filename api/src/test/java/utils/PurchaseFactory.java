package utils;

import application.princing.PricingCurrencyDto;
import application.product.MeasurementUnitDto;
import application.purchase.ProductOrderDto;
import application.purchase.PurchaseDto;

import java.math.BigDecimal;

public class PurchaseFactory {

    public PurchaseDto bananaPurchase() {
        PurchaseDto purchaseDto = new PurchaseDto();

        ProductOrderDto bananaOrder = ProductOrderDto.builder()
                .product("Banana", new BigDecimal("2.99"), PricingCurrencyDto.BRL)
                .amount(new BigDecimal("1.5"), MeasurementUnitDto.KILOGRAM)
                .get();

        purchaseDto.addOrders(bananaOrder);

        return purchaseDto;
    }
}
