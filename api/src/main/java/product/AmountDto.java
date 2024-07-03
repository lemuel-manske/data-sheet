package product;

import java.math.BigDecimal;

public class AmountDto {

    private String id;
    private MeasurementUnitDto unit;
    private BigDecimal value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MeasurementUnitDto getUnit() {
        return unit;
    }

    public void setUnit(MeasurementUnitDto unit) {
        this.unit = unit;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
