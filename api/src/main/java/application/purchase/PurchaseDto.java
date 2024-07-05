package application.purchase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PurchaseDto {

    private String id;
    private List<ProductOrderDto> orders = new ArrayList<>();
    private BigDecimal total;

    public void add(ProductOrderDto... orderDtos) {
        orders.addAll(List.of(orderDtos));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductOrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<ProductOrderDto> orders) {
        this.orders = orders;
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
        PurchaseDto that = (PurchaseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(orders, that.orders) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orders, total);
    }
}
