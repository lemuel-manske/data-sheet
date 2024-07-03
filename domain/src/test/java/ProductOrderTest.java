import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import application.product.Amount;
import application.purchase.ProductOrder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductOrderTest {

    private static Store store;

    @BeforeAll
    static void createStore() {
        store = new Store();
    }

    @Test
    void calculateTotal() {
        ProductOrder bananaProductOrder = new ProductOrder(store.banana(), Amount.kilogram(1.50f));

        BigDecimal expectedTotal = new BigDecimal("2.99");

        BigDecimal actualTotal = bananaProductOrder.calcTotal();

        assertEquals(expectedTotal, actualTotal);
    }

}