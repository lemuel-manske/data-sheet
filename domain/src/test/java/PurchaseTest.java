import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pricing.Bank;
import product.Amount;
import purchase.DifferenceType;
import purchase.Order;
import purchase.Purchase;
import purchase.PurchaseDifference;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchaseTest {

    private static Store store;

    @BeforeAll
    static void createStore() {
        store = new Store();
    }

    @Test
    void calculateTotal() {
        Order bananaOrder = new Order(store.banana(), Amount.kilogram(3));
        Order riceOrder = new Order(store.rice(), Amount.kilogram(1));
        Order beansOrder = new Order(store.beans(), Amount.kilogram(2));

        Purchase purchase = new Purchase();
        purchase.add(bananaOrder, riceOrder, beansOrder);

        assertEquals(new BigDecimal("28.94"), purchase.calcTotal());
    }

    @Test
    void findDifferenceBetweenPurchases() {
        Purchase purchase = new Purchase();
        purchase.add(new Order(store.banana(), Amount.kilogram(3)));
        purchase.add(new Order(store.apple(), Amount.kilogram(1)));

        Purchase otherPurchase = new Purchase();
        otherPurchase.add(new Order(store.banana(Bank.brl("2.99")), Amount.kilogram(1)));
        otherPurchase.add(new Order(store.apple(Bank.brl("5.99")), Amount.kilogram(2)));

        PurchaseDifference diff = new PurchaseDifference();
        diff.add("Banana", new BigDecimal("-1.00"), DifferenceType.DECREASE);
        diff.add("Apple", new BigDecimal("2.00"), DifferenceType.INCREASE);

        assertEquals(diff, purchase.differenceFrom(otherPurchase));
    }

}
