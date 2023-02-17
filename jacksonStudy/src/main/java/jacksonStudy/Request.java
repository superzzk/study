package jacksonStudy;

import java.util.Date;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 15:41
 **/
public class Request {
    private Car car;
    private Date datePurchased;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }
}
