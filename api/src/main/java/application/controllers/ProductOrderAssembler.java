package application.controllers;

import application.pricing.Price;
import application.princing.PriceDto;
import application.product.Amount;
import application.product.AmountDto;
import application.product.MeasurementUnit;
import application.product.MeasurementUnitDto;
import application.product.Product;
import application.product.ProductDto;
import application.purchase.CalculatePurchaseTotalService;
import application.purchase.ProductOrder;
import application.purchase.ProductOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class ProductOrderAssembler {

    private final CalculatePurchaseTotalService calculatePurchaseTotalService;

    @Autowired
    public ProductOrderAssembler(CalculatePurchaseTotalService calculatePurchaseTotalService) {
        this.calculatePurchaseTotalService = calculatePurchaseTotalService;
    }

    public ProductOrderDto createDto(ProductOrder productOrder) {
        ProductOrderDto productOrderDto = new ProductOrderDto();

        productOrderDto.setId(productOrder.getId());

        productOrderDto.setTotal(calculatePurchaseTotalService.execute(productOrder.getPurchase()));

            PriceDto priceDto = new PriceDto();
            priceDto.setId(productOrder.getProduct().getPrice().getId());
            priceDto.setValue(productOrder.getProduct().getPrice().getPrice());
            priceDto.setCurrency(Currency.getInstance(productOrder.getProduct().getPrice().getCurrency().toString()));

            ProductDto productDto = new ProductDto();
            productDto.setId(productOrder.getProduct().getId());
            productDto.setName(productOrder.getProduct().getName());
            productDto.setPrice(priceDto);

        productOrderDto.setProduct(productDto);

            AmountDto amountDto = new AmountDto();
            amountDto.setId(productOrder.getAmount().getId());
            amountDto.setUnit(MeasurementUnitDto.valueOf(productOrder.getAmount().getMeasurementUnit().toString()));
            amountDto.setValue(productOrder.getAmount().getAmount());

        productOrderDto.setAmount(amountDto);

        return productOrderDto;
    }

    ProductOrder createModel(ProductOrderDto productOrderDto) {
        ProductOrder productOrder = new ProductOrder();

        productOrder.setId(productOrderDto.getId());

            Price price = new Price();
            price.setId(productOrderDto.getProduct().getPrice().getId());
            price.setPrice(productOrderDto.getProduct().getPrice().getValue());
            price.setCurrency(Currency.getInstance(productOrderDto.getProduct().getPrice().getCurrency().toString()));

            Product product = new Product();
            product.setId(productOrderDto.getProduct().getId());
            product.setName(productOrderDto.getProduct().getName());
            product.setPrice(price);

        productOrder.setProduct(product);

            Amount amount = new Amount();
            amount.setId(productOrderDto.getAmount().getId());
            amount.setMeasurementUnit(MeasurementUnit.valueOf(productOrderDto.getAmount().getUnit().toString()));
            amount.setAmount(productOrderDto.getAmount().getValue());

        productOrder.setAmount(amount);

        return productOrder;
    }

}
