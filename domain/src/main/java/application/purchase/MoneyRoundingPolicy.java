package application.purchase;

import java.math.RoundingMode;

public interface MoneyRoundingPolicy {

    int getScale();

    RoundingMode getRoundingMode();
}
