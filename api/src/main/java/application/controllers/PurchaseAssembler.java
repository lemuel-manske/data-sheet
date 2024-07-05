package application.controllers;

import application.purchase.CalculatePurchaseTotalService;
import application.purchase.ProductOrder;
import application.purchase.ProductOrderDto;
import application.purchase.Purchase;
import application.purchase.PurchaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseAssembler {

    private final ProductOrderAssembler productOrderAssembler;
    private final CalculatePurchaseTotalService calculatePurchaseTotalService;

    @Autowired
    public PurchaseAssembler(ProductOrderAssembler productOrderAssembler,
                             CalculatePurchaseTotalService calculatePurchaseTotalService) {
        this.productOrderAssembler = productOrderAssembler;
        this.calculatePurchaseTotalService = calculatePurchaseTotalService;
    }

    public PurchaseDto createDto(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setId(purchase.getId());
        purchaseDto.setTotal(calculatePurchaseTotalService.execute(purchase));

        for (ProductOrder productOrder : purchase.getOrders())
            purchaseDto.add(productOrderAssembler.createDto(productOrder));

        return purchaseDto;
    }

    public Purchase createModel(PurchaseDto purchaseDto) {
        Purchase purchase = new Purchase();
        purchase.setId(purchase.getId());

        for (ProductOrderDto dtoOrder : purchaseDto.getOrders()) {
            ProductOrder productOrder = productOrderAssembler.createModel(dtoOrder);
            productOrder.setPurchase(purchase);
            purchase.add(productOrder);
        }

        return purchase;
    }
}
