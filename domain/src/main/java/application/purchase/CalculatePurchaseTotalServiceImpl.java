package application.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatePurchaseTotalServiceImpl implements CalculatePurchaseTotalService {

    private final MoneyRoundingPolicy moneyRoundingPolicy;

    @Autowired
    public CalculatePurchaseTotalServiceImpl(MoneyRoundingPolicy moneyRoundingPolicy) {
        this.moneyRoundingPolicy = moneyRoundingPolicy;
    }

    @Override
    public BigDecimal execute(Purchase purchase) {
        return purchase.calcTotal(moneyRoundingPolicy);
    }
}
