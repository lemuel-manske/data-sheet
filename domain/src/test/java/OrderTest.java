import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import product.Amount;
import purchase.Order;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    private static Store store;

    @BeforeAll
    static void createStore() {
        store = new Store();
    }

    @Test
    void calculateTotal() {
        Order bananaOrder = new Order(store.banana(), Amount.kilogram(1.50f));

        BigDecimal expectedTotal = new BigDecimal("2.99");

        BigDecimal actualTotal = bananaOrder.calcTotal();

        assertEquals(expectedTotal, actualTotal);
    }

}