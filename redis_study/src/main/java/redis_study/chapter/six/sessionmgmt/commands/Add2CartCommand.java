package redis_study.chapter.six.sessionmgmt.commands;

import redis_study.chapter.six.Argument;
import redis_study.chapter.six.Commands;
import redis_study.chapter.six.ShoppingCartDBManager;
import redis_study.chapter.six.UserDBManager;

import java.util.HashMap;
import java.util.Map;

/**
 * /userApp?command=add2cart&args=sessionid=26913441:product=Redisbook-1@2,Redisbook-4@1
 *
 * This command will put products and their quantities into the
 * shopping cart.
 */
public class Add2CartCommand extends Commands {
    public Add2CartCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String result = "did not update the shopping cart";
        String sessionid = this.getArgument().getValue("sessionid");
        String product = this.getArgument().getValue("product");
        String[] productList = product.split(",");

        Map<String, String> productQtyMap = new HashMap<>();
        for (String _product : productList) {
            String[] nameQty = _product.split("@");
            productQtyMap.put(nameQty[0], nameQty[1]);
        }
        if (UserDBManager.singleton.doesSessionExist(sessionid)) {
            result = ShoppingCartDBManager.singleton.addToShoppingCart(sessionid, productQtyMap);
        }
        return "Result : " + result;
    }
}