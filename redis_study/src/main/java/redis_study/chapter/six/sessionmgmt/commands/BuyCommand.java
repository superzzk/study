package redis_study.chapter.six.sessionmgmt.commands;

import redis_study.chapter.six.Argument;
import redis_study.chapter.six.Commands;
import redis_study.chapter.six.ShoppingCartDBManager;

public class BuyCommand extends Commands {
    public BuyCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String sessionid = this.getArgument().getValue("sessionid");

        String shoppingdetails = ShoppingCartDBManager.singleton.buyItemsInTheShoppingCart(sessionid);
        return shoppingdetails;
    }
}