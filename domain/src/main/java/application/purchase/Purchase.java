package application.purchase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Purchase {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrder> productOrders = new ArrayList<>();

    public Purchase() { }

    public Purchase(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

    public void add(ProductOrder... productOrders) {
        this.productOrders.addAll(Arrays.asList(productOrders));
    }

    public BigDecimal calcTotal(ScaleStrategy scaleStrategy) {
        BigDecimal total = BigDecimal.ZERO;

        for (ProductOrder productOrder : productOrders)
            total = total.add(productOrder.calcTotal(scaleStrategy));

        return total.setScale(scaleStrategy.getScale(), scaleStrategy.getRoundingMode());
    }

    public Difference differenceFrom(Purchase otherPurchase) {
        Difference diff = new Difference();

        for (ProductOrder otherProductOrder : otherPurchase.productOrders)
            for (ProductOrder productOrder : productOrders)
                if (otherProductOrder.getProduct().getName().equals(productOrder.getProduct().getName()))
                    diff.calcDifference(productOrder, otherProductOrder);

        return diff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrders(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

    public List<ProductOrder> getOrders() {
        return productOrders;
    }
}
