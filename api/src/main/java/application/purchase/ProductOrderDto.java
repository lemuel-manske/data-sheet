package application.purchase;

import application.princing.PriceDto;
import application.product.AmountDto;
import application.product.MeasurementUnitDto;
import application.product.ProductDto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class ProductOrderDto {

    private String id;
    private ProductDto product;
    private AmountDto amount;
    private BigDecimal total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public AmountDto getAmount() {
        return amount;
    }

    public void setAmount(AmountDto amount) {
        this.amount = amount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrderDto that = (ProductOrderDto) o;
        return Objects.equals(id, that.id) && Objects.equals(product, that.product) && Objects.equals(amount, that.amount) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, amount, total);
    }

    public static ProductOrderDtoBuilder builder() {
        return new ProductOrderDtoBuilder();
    }

    public static final class ProductOrderDtoBuilder {

        private final ProductOrderDto productOrderDto;

        public ProductOrderDtoBuilder() {
            productOrderDto = new ProductOrderDto();
        }

        public ProductOrderDtoBuilder product(String productName, BigDecimal productPrice, Currency currency) {
            PriceDto priceDto = new PriceDto();
            priceDto.setCurrency(currency);
            priceDto.setValue(productPrice);

            ProductDto productDto = new ProductDto();
            productDto.setName(productName);
            productDto.setPrice(priceDto);

            productOrderDto.setProduct(productDto);

            return this;
        }

        public ProductOrderDtoBuilder amount(BigDecimal amount, MeasurementUnitDto measurementUnit) {
            AmountDto amountDto = new AmountDto();
            amountDto.setValue(amount);
            amountDto.setUnit(measurementUnit);

            productOrderDto.setAmount(amountDto);

            return this;
        }

        public ProductOrderDto get() {
            return productOrderDto;
        }

    }
}
