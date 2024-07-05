package application.controllers;

import application.pricing.Price;
import application.princing.PriceDto;
import application.product.Amount;
import application.product.AmountDto;
import application.product.MeasurementUnit;
import application.product.MeasurementUnitDto;
import application.product.Product;
import application.product.ProductDto;
import application.purchase.ProductOrder;
import application.purchase.ProductOrderDto;
import application.purchase.Purchase;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class ProductOrderAssembler {

    public ProductOrderDto createDto(ProductOrder productOrder) {
        ProductOrderDto productOrderDto = new ProductOrderDto();

        productOrderDto.setId(productOrder.getId());
        productOrderDto.setTotal(productOrder.calcTotal());

        AmountDto amountDto = new AmountDto();
        amountDto.setId(productOrder.getAmount().getId());
        amountDto.setUnit(MeasurementUnitDto.valueOf(productOrder.getAmount().getMeasurementUnit().toString()));
        amountDto.setValue(productOrder.getAmount().getAmount());
        productOrderDto.setAmount(amountDto);

        ProductDto productDto = new ProductDto();
        productDto.setId(productOrder.getProduct().getId());
        productDto.setName(productOrder.getProduct().getName());

        PriceDto priceDto = new PriceDto();
        priceDto.setId(productOrder.getProduct().getPrice().getId());
        priceDto.setValue(productOrder.getProduct().getPrice().getPrice());
        priceDto.setCurrency(Currency.getInstance(productOrder.getProduct().getPrice().getCurrency().toString()));
        productDto.setPrice(priceDto);
        productOrderDto.setProduct(productDto);

        return productOrderDto;
    }

    ProductOrder createModel(ProductOrderDto productOrderDto) {
        ProductOrder productOrder = new ProductOrder();

        productOrder.setId(productOrderDto.getId());

        Amount amount = new Amount();
        amount.setId(productOrderDto.getAmount().getId());
        amount.setMeasurementUnit(MeasurementUnit.valueOf(productOrderDto.getAmount().getUnit().toString()));
        amount.setAmount(productOrderDto.getAmount().getValue());
        productOrder.setAmount(amount);

        Price price = new Price();
        price.setId(productOrderDto.getProduct().getPrice().getId());
        price.setPrice(productOrderDto.getProduct().getPrice().getValue());
        price.setCurrency(Currency.getInstance(productOrderDto.getProduct().getPrice().getCurrency().toString()));

        Product product = new Product();
        product.setId(productOrderDto.getProduct().getId());
        product.setName(productOrderDto.getProduct().getName());

        product.setPrice(price);
        productOrder.setProduct(product);

        return productOrder;
    }

}
