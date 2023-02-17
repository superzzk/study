package jacksonStudy.enums.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Distance3 {
    @JsonProperty("distance-in-km")
    KILOMETER("km", 1000),
    @JsonProperty("distance-in-miles")
    MILE("miles", 1609.34),
    METER("meters", 1),
    INCH("inches", 0.0254),
    CENTIMETER("cm", 0.01),
    MILLIMETER("mm", 0.001);

    private String unit;
    private final double meters;

    private Distance3(String unit, double meters) {
        this.unit = unit;
        this.meters = meters;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getMeters() {
        return meters;
    }
}