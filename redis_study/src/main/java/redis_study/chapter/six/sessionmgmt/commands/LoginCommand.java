package redis_study.chapter.six.sessionmgmt.commands;

import java.util.HashMap;
import java.util.Map;

import redis_study.chapter.six.Commands;
import redis_study.chapter.six.AnalyticsDBManager;
import redis_study.chapter.six.Argument;
import redis_study.chapter.six.ProductDBManager;
import redis_study.chapter.six.UserDBManager;

/**
 * userApp?command=login&args=name=vinoo:password=******
 *
 * This command will log in the user into the system.
 */
public class LoginCommand extends Commands {
    public LoginCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String name = this.getArgument().getValue("name");
        String password = this.getArgument().getValue("password");

        if (UserDBManager.singleton.doesUserExist(name)) {
            if (UserDBManager.singleton.getUserPassword(name).equals(password)
                    & UserDBManager.singleton.getUserSessionId(name).equals("null")) {
                String sessionID = ProductDBManager.getRandomSessionID();
                UserDBManager.singleton.login(sessionID, name);

                //更新userdata中当前的session
                Map<String, String> map = new HashMap<>();
                map.put("sessionID", sessionID);
                UserDBManager.singleton.setRegistrationMap(name, map);
                System.out.println("login map : " + map);
                AnalyticsDBManager.singleton.registerInSessionTracker(sessionID);
                return "Login successful \n" + name + " \n use the following session id : " + sessionID;
            } else if (UserDBManager.singleton.getUserPassword(name).equals(password)
                    & !UserDBManager.singleton.getUserSessionId(name).equals("null")) {
                return " Login failed ...u r already logged in \n please logout to login again \n or try relogin command ";
            } else {
                return " Login failed ...invalid password ";
            }
        } else {
            return " please register before executing command for login ";
        }
    }
}