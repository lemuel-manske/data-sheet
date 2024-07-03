package purchase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Purchase {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public Purchase() { }

    public Purchase(List<Order> orders) {
        this.orders = orders;
    }

    public void add(Order... orders) {
        this.orders.addAll(Arrays.asList(orders));
    }

    public BigDecimal calcTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (Order order : orders)
            total = total.add(order.calcTotal());

        return total.setScale(2, RoundingMode.HALF_UP);
    }

    public PurchaseDifference differenceFrom(Purchase otherPurchase) {
        PurchaseDifference diff = new PurchaseDifference();

        for (Order otherOrder : otherPurchase.orders)
            for (Order order : orders)
                if (otherOrder.getProduct().getName().equals(order.getProduct().getName()))
                    diff.calcDifference(order, otherOrder);

        return diff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
