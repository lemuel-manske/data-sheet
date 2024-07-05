package application.purchase;

import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component
public class DefaultMoneyRoundingPolicy implements MoneyRoundingPolicy {

    @Override
    public int getScale() {
        return 2;
    }

    @Override
    public RoundingMode getRoundingMode() {
        return RoundingMode.HALF_UP;
    }
}
