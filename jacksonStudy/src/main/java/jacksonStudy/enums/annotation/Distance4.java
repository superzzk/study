package jacksonStudy.enums.annotation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Distance4 {
    KILOMETER("km", 1000),
    MILE("miles", 1609.34),
    METER("meters", 1),
    INCH("inches", 0.0254),
    CENTIMETER("cm", 0.01),
    MILLIMETER("mm", 0.001);

    private String unit;
    private final double meters;

    @JsonCreator
    public static Distance4 forValues(@JsonProperty("unit") String unit,
                                     @JsonProperty("meters") double meters) {
        for (Distance4 distance : Distance4.values()) {
            if (distance.unit.equals(unit) && Double.compare(distance.meters, meters) == 0) {
                return distance;
            }
        }

        return null;
    }

    private Distance4(String unit, double meters) {
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

    @Override
    public String toString() {
        return "Distance4{" +
                "unit='" + unit + '\'' +
                ", meters=" + meters +
                '}';
    }
}