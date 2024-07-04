package application.controllers;

import application.princing.PriceDto;
import application.princing.PricingCurrencyDto;
import application.product.AmountDto;
import application.product.MeasurementUnitDto;
import application.product.ProductDto;
import application.purchase.ProductOrder;
import application.purchase.ProductOrderDto;
import org.springframework.stereotype.Component;

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
        priceDto.setCurrency(PricingCurrencyDto.valueOf(productOrder.getProduct().getPrice().getCurrency().toString()));
        productDto.setPrice(priceDto);
        productOrderDto.setProduct(productDto);

        return productOrderDto;
    }

    ProductOrder createOrder(ProductOrderDto productOrderDto) {
        return new ProductOrder();
    }

}
