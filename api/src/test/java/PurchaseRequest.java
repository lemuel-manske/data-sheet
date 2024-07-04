import application.princing.PriceDto;
import application.princing.PricingCurrencyDto;
import application.product.AmountDto;
import application.product.MeasurementUnitDto;
import application.product.ProductDto;
import application.purchase.ProductOrderDto;
import application.purchase.PurchaseDto;

import java.math.BigDecimal;

public class PurchaseRequest {

    private final PurchaseDto purchaseDto = new PurchaseDto();

    public void addOrder(String productName) {
        addOrder(productName, null, null, null, null);

    }

    public void addOrder(String productName, String price, String currency) {
        addOrder(productName, price, currency, null, null);
    }

    public void addOrder(String productName, String price, String currency, String amount, String unit) {
        PriceDto priceDto = new PriceDto();
        priceDto.setCurrency(PricingCurrencyDto.valueOf(currency));
        priceDto.setValue(new BigDecimal(price));

        ProductDto productDto = new ProductDto();
        productDto.setName(productName);
        productDto.setPrice(priceDto);

        AmountDto amountDto = new AmountDto();
        amountDto.setValue(new BigDecimal(amount));
        amountDto.setUnit(MeasurementUnitDto.valueOf(unit));

        ProductOrderDto validProductOrder = new ProductOrderDto();
        validProductOrder.setProduct(productDto);
        validProductOrder.setAmount(amountDto);

        purchaseDto.addOrders(validProductOrder);
    }

    public PurchaseDto getRequest() {
        return purchaseDto;
    }
}
