package jacksonStudy.enums.annotation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Distance2 {
    KILOMETER("km", 1000),
    MILE("miles", 1609.34),
    METER("meters", 1),
    INCH("inches", 0.0254),
    CENTIMETER("cm", 0.01),
    MILLIMETER("mm", 0.001);

    private String unit;
    private final double meters;

    private Distance2(String unit, double meters) {
        this.unit = unit;
        this.meters = meters;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @JsonValue
    public double getMeters() {
        return meters;
    }
}