package redis_study.chapter.six.analytics.commands;

import redis_study.chapter.six.Commands;
import redis_study.chapter.six.AnalyticsDBManager;
import redis_study.chapter.six.Argument;

/**
 * productApp?command=visitstoday&args=productname=Redisbook-1
 *
 * This command can be executed if we want to check how many
 * unique users visited the product. The data structure implementing this use
 * case is a Bitmap.
 */
public class VisitTodayCommand extends Commands {
    public VisitTodayCommand(Argument argument) {
        super(argument);
    }
    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String productName = this.getArgument().getValue("productname");
        Integer visitCount = AnalyticsDBManager.singleton.getVisitToday(productName);
        System.out.println(this.getClass().getSimpleName() + ": " + "Printing the result for execute function");
        System.out.println("Result = " + "Total Unique Visitors are: " + visitCount.toString());
        return "Total Unique Visitors are: " + visitCount.toString();
    }
}