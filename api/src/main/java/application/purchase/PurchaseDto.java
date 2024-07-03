package application.purchase;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseDto {

    private String id;
    private List<ProductOrderDto> orders;
    private BigDecimal total;

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
}
