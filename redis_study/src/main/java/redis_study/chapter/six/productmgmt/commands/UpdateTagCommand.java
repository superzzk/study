package redis_study.chapter.six.productmgmt.commands;

import redis_study.chapter.six.AnalyticsDBManager;
import redis_study.chapter.six.Argument;
import redis_study.chapter.six.Commands;
import redis_study.chapter.six.ProductDBManager;

/**
 * productApp?command=updatetag&args=sessionid=<sessionID of the user>:
 * productname=<product name which the user is browsing or has bought>:action=<browse or buy>
 * <p>
 * This command is called when a user is browsing a product or
 * purchasing a product. The idea behind this command is that when a user is
 * browsing a product or purchasing a product, it is gaining popularity, and so
 * proportionally, the popularity of the product should increase among other
 * products in the same tag. So in short, it helps calculate the most popular
 * product in its category (tags)
 */
public class UpdateTagCommand extends Commands {
    public UpdateTagCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + " Entering the execute function");
        String sessionid = this.getArgument().getValue("sessionid");
        String productname = this.getArgument().getValue("productname");
        String details = this.getArgument().getValue("details");
        String actionType = this.getArgument().getValue("action");

        switch (actionType.toLowerCase()) {
            case "browse":
                if (productname != null & ProductDBManager.singleton.keyExist(productname)) {
                    AnalyticsDBManager.singleton.updateRatingInTag(productname, 1);
                    AnalyticsDBManager.singleton.updateProductVisit(sessionid, productname);
                }
                break;
            case "buy":
                System.out.println("Buying the products in the shopping cart !!");
                String[] products = details.split(",");
                for (String product : products) {
                    if (product != null & !product.trim().equals("")) {
                        AnalyticsDBManager.singleton.updateRatingInTag(product, 10);
                        AnalyticsDBManager.singleton.updateProductPurchase(sessionid, product);
                    }
                }
                break;
            default:
                System.out.println("The URL cannot be acted uppon ");
                break;
        }
        return "";
    }
}