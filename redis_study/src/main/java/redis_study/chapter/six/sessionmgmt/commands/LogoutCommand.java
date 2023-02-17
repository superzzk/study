package redis_study.chapter.six.sessionmgmt.commands;

import redis_study.chapter.six.Argument;
import redis_study.chapter.six.Commands;
import redis_study.chapter.six.UserDBManager;

public class LogoutCommand extends Commands {
    public LogoutCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String sessionid = this.getArgument().getValue("sessionid");
        if (UserDBManager.singleton.expireSession(sessionid)) {
            return "logout was clean";
        } else {
            return "logout was not clean";
        }
    }
}