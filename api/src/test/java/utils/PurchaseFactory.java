package utils;

import application.product.MeasurementUnitDto;
import application.purchase.ProductOrderDto;
import application.purchase.PurchaseDto;

import java.math.BigDecimal;
import java.util.Currency;

public class PurchaseFactory {

    public PurchaseDto emptyPurchase() {
        return new PurchaseDto();
    }

    public PurchaseDto bananaPurchase() {
        PurchaseDto purchaseDto = new PurchaseDto();

        ProductOrderDto bananaOrder = ProductOrderDto.builder()
                .product("Banana", new BigDecimal("2.99"), Currency.getInstance("BRL"))
                .amount(new BigDecimal("1.50"), MeasurementUnitDto.KILOGRAM)
                .get();

        purchaseDto.addOrders(bananaOrder);

        return purchaseDto;
    }
}
