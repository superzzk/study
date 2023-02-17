package redis_study.chapter.six.sessionmgmt.commands;

import redis_study.chapter.six.AnalyticsDBManager;
import redis_study.chapter.six.Argument;
import redis_study.chapter.six.Commands;

import java.util.List;


public class MyPurchaseHistoryCommand extends Commands {
    public MyPurchaseHistoryCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        StringBuffer report = new StringBuffer();
        String sessionid = this.getArgument().getValue("sessionid");
        List<String> purchasehistory = AnalyticsDBManager.singleton.getMyPurchaseHistory(sessionid);
        report.append("Your purchase history is as follows : \n");
        int i = 0;
        for (String purchase : purchasehistory) {
            report.append("[" + i + "] You purchased " + purchase);
            report.append("\n");
            i++;
        }
        return report.toString();
    }
}