package jacksonStudy.inheritance.conversion;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 15:53
 **/
public abstract class Vehicle {
    private String make;
    private String model;

    protected Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public Vehicle() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
