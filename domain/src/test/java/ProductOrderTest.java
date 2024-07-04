import application.product.Amount;
import application.purchase.ProductOrder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductOrderTest {

    @Test
    void calculateTotal() {
        ProductOrder bananaProductOrder = new ProductOrder(Store.banana(), Amount.kilogram(1.50f));

        BigDecimal expectedTotal = new BigDecimal("2.99");

        BigDecimal actualTotal = bananaProductOrder.calcTotal();

        assertEquals(expectedTotal, actualTotal);
    }

}