package application.purchase;

import application.product.AmountDto;
import application.product.ProductDto;

public class OrderDto {

    private String id;
    private ProductDto product;
    private AmountDto amount;

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
}
