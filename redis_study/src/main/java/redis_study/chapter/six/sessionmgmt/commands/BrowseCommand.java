package redis_study.chapter.six.sessionmgmt.commands;

import redis_study.chapter.six.AnalyticsDBManager;
import redis_study.chapter.six.Argument;
import redis_study.chapter.six.Commands;
import redis_study.chapter.six.ProductDBManager;

/**
 * userApp?command=browse&args=sessionid=26913441:browse=Redisbook-1
 * <p>
 * This command will show the product's data from the system.
 */
public class BrowseCommand extends Commands {
    public BrowseCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String productname = this.getArgument().getValue("browse");
        if (ProductDBManager.singleton.keyExist(productname)) {
            AnalyticsDBManager.singleton.updateBrowsingHistory(this.getArgument().getValue("sessionid"), productname);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("You are browsing the following product = " + productname + "\n");
            stringBuffer.append(ProductDBManager.singleton.getProductInfo(productname));
            return stringBuffer.toString();
        } else {
            return "Error: The product you are trying to browse does not exist i.e. " + productname;
        }
    }
}