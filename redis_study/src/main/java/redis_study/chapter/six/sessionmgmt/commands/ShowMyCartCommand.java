package redis_study.chapter.six.sessionmgmt.commands;

import redis_study.chapter.six.Argument;
import redis_study.chapter.six.Commands;
import redis_study.chapter.six.ShoppingCartDBManager;

import java.util.Map;
import java.util.Set;

public class ShowMyCartCommand extends Commands {
    public ShowMyCartCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String sessionid = this.getArgument().getValue("sessionid");
        Map<String, String> productMap = ShoppingCartDBManager.singleton.myCartInfo(sessionid);

        StringBuffer stringBuffer = new StringBuffer();
        if (!productMap.isEmpty()) {
            stringBuffer.append("Your shopping cart contains the following : ");
            stringBuffer.append("\n");
            Set<String> set = productMap.keySet();
            int i = 1;
            for (String str : set) {
                stringBuffer.append("[" + i + "] product name = " + str +
                        " Qty = " + productMap.get(str) + "\n");
                i++;
            }
            return stringBuffer.toString();
        } else {
            return " your shopping cart is empty.";
        }
    }
}