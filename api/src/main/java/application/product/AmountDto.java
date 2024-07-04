package application.product;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmountDto amountDto = (AmountDto) o;
        return Objects.equals(id, amountDto.id) && unit == amountDto.unit && Objects.equals(value, amountDto.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, unit, value);
    }
}
