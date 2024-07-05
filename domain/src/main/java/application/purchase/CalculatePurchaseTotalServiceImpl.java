package application.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatePurchaseTotalServiceImpl implements CalculatePurchaseTotalService {

    private final ScaleStrategy scaleStrategy;

    @Autowired
    public CalculatePurchaseTotalServiceImpl(ScaleStrategy scaleStrategy) {
        this.scaleStrategy = scaleStrategy;
    }

    @Override
    public BigDecimal execute(Purchase purchase) {
        return purchase.calcTotal(scaleStrategy);
    }
}
