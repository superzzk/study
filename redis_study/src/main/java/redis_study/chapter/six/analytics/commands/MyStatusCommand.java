package redis_study.chapter.six.analytics.commands;

import redis_study.chapter.six.AnalyticsDBManager;
import redis_study.chapter.six.Argument;
import redis_study.chapter.six.Commands;
import redis_study.chapter.six.UserDBManager;

import java.util.Iterator;
import java.util.Set;

public class MyStatusCommand extends Commands {
    public MyStatusCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String sessionID = this.getArgument().getValue("sessionid");
        if (UserDBManager.singleton.doesSessionExist(sessionID)) {
            Set<String> browsingHistory = AnalyticsDBManager.singleton.getBrowsingHistory(sessionID);
            StringBuffer buffer = new StringBuffer();
            buffer.append(" View your browsing history where the one on top is the least visited product");
            buffer.append("\n and the product at the bottom is the most frequented product ");
            buffer.append("\n");
            Iterator<String> iterator = browsingHistory.iterator();
            int i = 1;
            while (iterator.hasNext()) {
                buffer.append("[" + i + "] " + iterator.next() + "\n");
                i++;
            }
            System.out.println(this.getClass().getSimpleName() + ": " +
                    "Printing the result for execute function");
            System.out.println("Result = " + buffer.toString());
            return buffer.toString();
        } else {
            return "history is not available";
        }
    }
}