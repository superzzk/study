package jacksonStudy.inheritance.global_default_typing;

import java.util.List;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 16:01
 **/
public class Fleet {
    private List<Vehicle> vehicles;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
