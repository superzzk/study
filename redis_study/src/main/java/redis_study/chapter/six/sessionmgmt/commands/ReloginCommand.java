package redis_study.chapter.six.sessionmgmt.commands;

import redis_study.chapter.six.Argument;
import redis_study.chapter.six.Commands;
import redis_study.chapter.six.UserDBManager;

/**
 *  This command will again check the user and password of the
 * user and return back the associated session ID of the user. The idea is to have
 * session which can exist many shopping and browsing sessions of a user
 */
public class ReloginCommand extends Commands {
    public ReloginCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String name = this.getArgument().getValue("name");
        String password = this.getArgument().getValue("password");
        if (UserDBManager.singleton.doesUserExist(name)) {
            if (UserDBManager.singleton.getUserPassword(name).equals(password)) {
                String sessionID = UserDBManager.singleton.getUserSessionId(name);
                return "ReLogin successful \n" + name + " \n use the following session id : " + sessionID;
            } else {
                return " ReLogin failed ...invalid password ";
            }
        } else {
            return " please register before executing command for login ";
        }
    }
}