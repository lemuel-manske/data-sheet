package application.purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Difference {

    private final List<Variation> listOfVariations = new ArrayList<>();

    public void calcDifference(ProductOrder anProductOrder,
                               ProductOrder anotherProductOrder,
                               MoneyRoundingPolicy policy) {
        BigDecimal subtraction = subtractOrders(anProductOrder, anotherProductOrder)
                .setScale(policy.getScale(), policy.getRoundingMode());
        Type type = calcDifferenceType(subtraction);

        add(anProductOrder.getProduct().getName(), subtraction, type);
    }

    public void add(String productName, BigDecimal value, Type type) {
        listOfVariations.add(new Variation(productName, value, type));
    }

    private BigDecimal subtractOrders(ProductOrder anProductOrder,
                                      ProductOrder anotherProductOrder) {
        BigDecimal anOrderPrice = anProductOrder.getProduct().getPrice().getPrice();
        BigDecimal subtrahendOrderPrice = anotherProductOrder.getProduct().getPrice().getPrice();

        return anOrderPrice.subtract(subtrahendOrderPrice);
    }

    private Type calcDifferenceType(BigDecimal productPriceDifference) {
        return productPriceDifference.floatValue() < 0
                ? Type.DECREASE
                : Type.INCREASE;
    }

    public List<Variation> getListOfVariations() {
        return listOfVariations;
    }

    @Override
    public boolean equals(Object o) {
        Difference that = (Difference) o;
        return listOfVariations.equals(that.listOfVariations);
    }

    public enum Type { INCREASE, DECREASE }

    public record Variation(String productName, BigDecimal value, Type type) {}
}