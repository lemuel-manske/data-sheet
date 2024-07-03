package application.purchase;

import application.princing.PricingCurrencyDto;
import application.product.AmountDto;
import application.product.MeasurementUnitDto;
import org.springframework.stereotype.Component;

import application.pricing.Price;
import application.princing.PriceDto;
import application.product.Amount;
import application.product.MeasurementUnit;
import application.product.Product;
import application.product.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Component
public class PurchaseAssembler {

    public PurchaseDto createDto(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();

        List<OrderDto> orders = new ArrayList<>();

        for (Order order : purchase.getOrders()) {
            OrderDto dtoOrder = new OrderDto();

            dtoOrder.setId(order.getId());
            dtoOrder.setTotal(order.calcTotal());

            AmountDto amountDto = new AmountDto();
            amountDto.setId(order.getAmount().getId());
            amountDto.setUnit(MeasurementUnitDto.valueOf(order.getAmount().getMeasurementUnit().toString()));
            amountDto.setValue(order.getAmount().getAmount());
            dtoOrder.setAmount(amountDto);

            ProductDto productDto = new ProductDto();
            productDto.setId(order.getProduct().getId());
            productDto.setName(order.getProduct().getName());

            PriceDto priceDto = new PriceDto();
            priceDto.setId(order.getProduct().getPrice().getId());
            priceDto.setValue(order.getProduct().getPrice().getPrice().floatValue());
            priceDto.setCurrency(PricingCurrencyDto.valueOf(order.getProduct().getPrice().getCurrency().toString()));
            productDto.setPrice(priceDto);
            dtoOrder.setProduct(productDto);

            orders.add(dtoOrder);
        }

        purchaseDto.setId(purchase.getId());
        purchaseDto.setTotal(purchase.calcTotal());
        purchaseDto.setOrders(orders);

        return purchaseDto;
    }

    public Purchase createModel(PurchaseDto purchaseDto) {
        Purchase purchase = new Purchase();

        List<Order> orders = new ArrayList<>();

        for (OrderDto dtoOrder : purchaseDto.getOrders()) {
            Order order = new Order();

            order.setPurchase(purchase);
            order.setId(dtoOrder.getId());

            Amount amount = new Amount();
            amount.setId(dtoOrder.getAmount().getId());
            amount.setMeasurementUnit(MeasurementUnit.valueOf(dtoOrder.getAmount().getUnit().toString()));
            amount.setAmount(dtoOrder.getAmount().getValue());
            order.setAmount(amount);

            Product product = new Product();
            product.setId(dtoOrder.getProduct().getId());
            product.setName(dtoOrder.getProduct().getName());

            Price price = new Price();
            price.setId(dtoOrder.getProduct().getPrice().getId());
            price.setPrice(BigDecimal.valueOf(dtoOrder.getProduct().getPrice().getValue()));
            price.setCurrency(Currency.getInstance(dtoOrder.getProduct().getPrice().getCurrency().toString()));
            product.setPrice(price);
            order.setProduct(product);

            orders.add(order);
        }

        purchase.setId(purchase.getId());
        purchase.setOrders(orders);

        return purchase;
    }
}
