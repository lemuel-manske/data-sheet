package application.product;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Amount {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private BigDecimal amountValue;

    @Enumerated(EnumType.STRING)
    private MeasurementUnit measurementUnit;

    public Amount() { }

    public Amount(MeasurementUnit measurementUnit, BigDecimal amount) {
        this.measurementUnit = measurementUnit;
        this.amountValue = amount;
    }

    public static Amount kilogram(float amount) {
        return new Amount(MeasurementUnit.KILOGRAM, BigDecimal.valueOf(amount));
    }

    public static Amount unit(float amount) {
        return new Amount(MeasurementUnit.UNIT, BigDecimal.valueOf(amount));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amountValue;
    }

    public void setAmount(BigDecimal value) {
        this.amountValue = value;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
}
