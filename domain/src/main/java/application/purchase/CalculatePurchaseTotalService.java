package application.purchase;

import java.math.BigDecimal;

public interface CalculatePurchaseTotalService {

    BigDecimal execute(Purchase purchase);
}
