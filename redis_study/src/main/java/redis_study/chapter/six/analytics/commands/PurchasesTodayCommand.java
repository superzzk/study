package redis_study.chapter.six.analytics.commands;

import redis_study.chapter.six.Commands;
import redis_study.chapter.six.Argument;
import redis_study.chapter.six.ProductDBManager;

/**
 * This command can be executed if we want to check how many
 * unique users purchased the given product. The data structure implementing
 * this use case is a Bitmap.
 */
public class PurchasesTodayCommand extends Commands {
    public PurchasesTodayCommand(Argument argument) {
        super(argument);
    }
    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String productName = this.getArgument().getValue("productname");
        Integer purchaseCount = ProductDBManager.singleton.getPurchaseToday(productName);
        System.out.println(this.getClass().getSimpleName() + ": " + "Printing the result for execute function");
        System.out.println("Result = " + "Total Unique Customers are:" + purchaseCount.toString());
        return "Total Unique Customers are: " + purchaseCount.toString();
    }
}