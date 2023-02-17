package redis_study.chapter.six.sessionmgmt.commands;

import redis_study.chapter.six.Argument;
import redis_study.chapter.six.Commands;
import redis_study.chapter.six.UserDBManager;

import java.util.Map;

/**
 * This command will show user's data from the system.
 */
public class MyDataCommand extends Commands {
    public MyDataCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String sessionid = this.getArgument().getValue("sessionid");
        String name = UserDBManager.singleton.getUserName(sessionid);
        Map<String, String> map = UserDBManager.singleton.getRegistrationMap(name);
        return map.toString();
    }
}