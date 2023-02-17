package jacksonStudy.inheritance.no_arg_constructors;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Car extends Vehicle {
    private int seatingCapacity;
    private double topSpeed;

    @JsonCreator
    public Car(
            @JsonProperty("make") String make,
            @JsonProperty("model") String model,
            @JsonProperty("seating") int seatingCapacity,
            @JsonProperty("topSpeed") double topSpeed) {
        super(make, model);
        this.seatingCapacity = seatingCapacity;
        this.topSpeed = topSpeed;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(double topSpeed) {
        this.topSpeed = topSpeed;
    }
}