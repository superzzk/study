package jacksonStudy.inheritance.ignoring_properties_from_supertype.mix_ins;

public class Sedan extends Car {
    public Sedan(String make, String model, int seatingCapacity, double topSpeed) {
        super(make, model, seatingCapacity, topSpeed);
    }

    public Sedan() {
    }
}
