package application.purchase;

import application.product.Amount;
import application.product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class ProductOrder {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(optional = false)
    private Purchase purchase;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    private Amount amount;

    public ProductOrder() { }

    public ProductOrder(Product product, Amount amount) {
        this.product = product;
        this.amount = amount;
    }

    public BigDecimal calcTotal(ScaleStrategy scaleStrategy) {
        BigDecimal priceTimesAmount = product.getPrice().getPrice().multiply(amount.getAmount());

        return priceTimesAmount.setScale(scaleStrategy.getScale(), scaleStrategy.getRoundingMode());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
