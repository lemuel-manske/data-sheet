package application.purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDifference {

    private final List<Variation> listOfVariations = new ArrayList<>();

    public void calcDifference(ProductOrder anProductOrder, ProductOrder anotherProductOrder) {
        BigDecimal subtraction = subtractOrders(anProductOrder, anotherProductOrder);
        DifferenceType type = calcDifferenceType(subtraction);

        add(anProductOrder.getProduct().getName(), subtraction, type);
    }

    public void add(String productName, BigDecimal value, DifferenceType type) {
        listOfVariations.add(new Variation(productName, value, type));
    }

    private BigDecimal subtractOrders(ProductOrder anProductOrder, ProductOrder anotherProductOrder) {
        BigDecimal anOrderPrice = anProductOrder.getProduct().getPrice().getPrice();
        BigDecimal subtrahendOrderPrice = anotherProductOrder.getProduct().getPrice().getPrice();

        return anOrderPrice.subtract(subtrahendOrderPrice).setScale(2, RoundingMode.HALF_UP);
    }

    private DifferenceType calcDifferenceType(BigDecimal productPriceDifference) {
        return productPriceDifference.floatValue() < 0
                ? DifferenceType.DECREASE
                : DifferenceType.INCREASE;
    }

    public List<Variation> getListOfVariations() {
        return listOfVariations;
    }

    @Override
    public boolean equals(Object o) {
        PurchaseDifference that = (PurchaseDifference) o;
        return listOfVariations.equals(that.listOfVariations);
    }

    public record Variation(String productName, BigDecimal value, DifferenceType type) {}
}