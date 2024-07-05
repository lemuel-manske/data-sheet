import application.pricing.Bank;
import application.product.Amount;
import application.purchase.DefaultMoneyRoundingPolicy;
import application.purchase.ProductOrder;
import application.purchase.Purchase;
import application.purchase.Difference;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchaseTest {

    @Test
    void calculateTotal() {
        ProductOrder bananaProductOrder = new ProductOrder(Store.banana(), Amount.kilogram(3));
        ProductOrder riceProductOrder = new ProductOrder(Store.rice(), Amount.kilogram(1));
        ProductOrder beansProductOrder = new ProductOrder(Store.beans(), Amount.kilogram(2));

        Purchase purchase = new Purchase();
        purchase.add(bananaProductOrder, riceProductOrder, beansProductOrder);

        assertEquals(new BigDecimal("28.94"), purchase.calcTotal(new DefaultMoneyRoundingPolicy()));
    }

    @Test
    void findDifferenceBetweenPurchases() {
        Purchase purchase = new Purchase();
        purchase.add(new ProductOrder(Store.banana(), Amount.kilogram(3)));
        purchase.add(new ProductOrder(Store.apple(), Amount.kilogram(1)));

        Purchase otherPurchase = new Purchase();
        otherPurchase.add(new ProductOrder(Store.banana(Bank.brl("2.99")), Amount.kilogram(1)));
        otherPurchase.add(new ProductOrder(Store.apple(Bank.brl("5.99")), Amount.kilogram(2)));

        Difference diff = new Difference();
        diff.add("Banana", new BigDecimal("-1.00"), Difference.Type.DECREASE);
        diff.add("Apple", new BigDecimal("2.00"), Difference.Type.INCREASE);

        assertEquals(diff, purchase.differenceFrom(otherPurchase));
    }

}
