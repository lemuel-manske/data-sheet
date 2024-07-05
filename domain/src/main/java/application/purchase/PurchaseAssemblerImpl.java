package application.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseAssemblerImpl implements PurchaseAssembler {

    private final ProductOrderAssemblerImpl productOrderAssembler;
    private final CalculatePurchaseTotalService calculatePurchaseTotalService;

    @Autowired
    public PurchaseAssemblerImpl(ProductOrderAssemblerImpl productOrderAssembler,
                                 CalculatePurchaseTotalService calculatePurchaseTotalService) {
        this.productOrderAssembler = productOrderAssembler;
        this.calculatePurchaseTotalService = calculatePurchaseTotalService;
    }

    @Override
    public PurchaseDto createDto(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setId(purchase.getId());
        purchaseDto.setTotal(calculatePurchaseTotalService.execute(purchase));

        for (ProductOrder productOrder : purchase.getOrders())
            purchaseDto.add(productOrderAssembler.createDto(productOrder));

        return purchaseDto;
    }

    @Override
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
