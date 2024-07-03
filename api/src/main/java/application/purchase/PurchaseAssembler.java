package application.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.pricing.Price;
import application.product.Amount;
import application.product.MeasurementUnit;
import application.product.Product;

import java.math.BigDecimal;
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
            ProductOrder productOrder = new ProductOrder();

            productOrder.setPurchase(purchase);
            productOrder.setId(dtoOrder.getId());

            Amount amount = new Amount();
            amount.setId(dtoOrder.getAmount().getId());
            amount.setMeasurementUnit(MeasurementUnit.valueOf(dtoOrder.getAmount().getUnit().toString()));
            amount.setAmount(dtoOrder.getAmount().getValue());
            productOrder.setAmount(amount);

            Product product = new Product();
            product.setId(dtoOrder.getProduct().getId());
            product.setName(dtoOrder.getProduct().getName());

            Price price = new Price();
            price.setId(dtoOrder.getProduct().getPrice().getId());
            price.setPrice(BigDecimal.valueOf(dtoOrder.getProduct().getPrice().getValue()));
            price.setCurrency(Currency.getInstance(dtoOrder.getProduct().getPrice().getCurrency().toString()));
            product.setPrice(price);
            productOrder.setProduct(product);

            productOrders.add(productOrder);
        }

        purchase.setId(purchase.getId());
        purchase.setOrders(productOrders);

        return purchase;
    }
}
