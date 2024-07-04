package application.controllers;

import application.pricing.Price;
import application.product.Amount;
import application.product.MeasurementUnit;
import application.product.Product;
import application.purchase.ProductOrder;
import application.purchase.ProductOrderDto;
import application.purchase.Purchase;
import application.purchase.PurchaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Component
public class PurchaseAssembler {

    private final ProductOrderAssembler productOrderAssembler;

    @Autowired
    public PurchaseAssembler(ProductOrderAssembler productOrderAssembler) {
        this.productOrderAssembler = productOrderAssembler;
    }

    public PurchaseDto createDto(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();

        purchaseDto.setId(purchase.getId());
        purchaseDto.setTotal(purchase.calcTotal());

        List<ProductOrderDto> orders = new ArrayList<>();

        for (ProductOrder productOrder : purchase.getOrders())
            orders.add(productOrderAssembler.createDto(productOrder));

        purchaseDto.setOrders(orders);

        return purchaseDto;
    }

    public Purchase createModel(PurchaseDto purchaseDto) {
        Purchase purchase = new Purchase();

        List<ProductOrder> productOrders = new ArrayList<>();

        for (ProductOrderDto dtoOrder : purchaseDto.getOrders()) {
            ProductOrder productOrder = productOrderAssembler.createOrder(dtoOrder);

            productOrder.setPurchase(purchase);

            productOrders.add(productOrder);
        }

        purchase.setId(purchase.getId());
        purchase.setOrders(productOrders);

        return purchase;
    }
}
