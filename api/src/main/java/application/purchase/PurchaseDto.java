package application.purchase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDto {

    private String id;
    private List<ProductOrderDto> orders = new ArrayList<>();
    private BigDecimal total;

    public void addOrders(ProductOrderDto productOrderDto) {
        orders.add(productOrderDto);
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
        PurchaseDto that = (PurchaseDto) o;
        return id.equals(that.id)
                && orders.equals(that.orders)
                && total.equals(that.total);
    }
}
