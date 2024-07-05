package application.purchase;

import java.math.RoundingMode;

public interface ScaleStrategy {

    int getScale();

    RoundingMode getRoundingMode();
}
