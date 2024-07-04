package application.purchase;

import application.product.AmountDto;
import application.product.ProductDto;

import java.math.BigDecimal;

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
        ProductOrderDto that = (ProductOrderDto) o;
        return id.equals(that.id)
                && product.equals(that.product)
                && amount.equals(that.amount)
                && total.equals(that.total);
    }
}
