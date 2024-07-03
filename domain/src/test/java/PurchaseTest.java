import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import application.pricing.Bank;
import application.product.Amount;
import application.purchase.DifferenceType;
import application.purchase.ProductOrder;
import application.purchase.Purchase;
import application.purchase.PurchaseDifference;

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
        ProductOrder bananaProductOrder = new ProductOrder(store.banana(), Amount.kilogram(3));
        ProductOrder riceProductOrder = new ProductOrder(store.rice(), Amount.kilogram(1));
        ProductOrder beansProductOrder = new ProductOrder(store.beans(), Amount.kilogram(2));

        Purchase purchase = new Purchase();
        purchase.add(bananaProductOrder, riceProductOrder, beansProductOrder);

        assertEquals(new BigDecimal("28.94"), purchase.calcTotal());
    }

    @Test
    void findDifferenceBetweenPurchases() {
        Purchase purchase = new Purchase();
        purchase.add(new ProductOrder(store.banana(), Amount.kilogram(3)));
        purchase.add(new ProductOrder(store.apple(), Amount.kilogram(1)));

        Purchase otherPurchase = new Purchase();
        otherPurchase.add(new ProductOrder(store.banana(Bank.brl("2.99")), Amount.kilogram(1)));
        otherPurchase.add(new ProductOrder(store.apple(Bank.brl("5.99")), Amount.kilogram(2)));

        PurchaseDifference diff = new PurchaseDifference();
        diff.add("Banana", new BigDecimal("-1.00"), DifferenceType.DECREASE);
        diff.add("Apple", new BigDecimal("2.00"), DifferenceType.INCREASE);

        assertEquals(diff, purchase.differenceFrom(otherPurchase));
    }

}
