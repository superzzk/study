package redis_study.chapter.six.sessionmgmt.commands;

import redis_study.chapter.six.Argument;
import redis_study.chapter.six.Commands;
import redis_study.chapter.six.ShoppingCartDBManager;
import redis_study.chapter.six.UserDBManager;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class EditCartCommand extends Commands {
    public EditCartCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String result = "did not edit the shopping cart";
        String sessionID = this.getArgument().getValue("sessionid");
        String product = this.getArgument().getValue("product");
        String[] productList = product.split(",");
        Map<String, String> productQtyMap = new HashMap<>();
        for (String _product : productList) {
            String[] nameQty = _product.split("@");
            productQtyMap.put(nameQty[0], nameQty[1]);
        }
        if (UserDBManager.singleton.doesSessionExist(sessionID)) {
            result = ShoppingCartDBManager.singleton.editMyCart(sessionID, productQtyMap);
        }
        return "result : " + result;
    }
}