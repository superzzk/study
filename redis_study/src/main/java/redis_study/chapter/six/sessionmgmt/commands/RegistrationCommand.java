package redis_study.chapter.six.sessionmgmt.commands;

import redis_study.chapter.six.Commands;
import redis_study.chapter.six.Argument;
import redis_study.chapter.six.UserDBManager;

/**
 * This command will register the user into the system.
 */
public class RegistrationCommand extends Commands {
    public RegistrationCommand(Argument argument) {
        super(argument);
    }

    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String name = this.getArgument().getValue("name");
        if (!UserDBManager.singleton.doesUserExist(name)) {
            UserDBManager.singleton.createUser(this.getArgument().getAttributes());
        } else {
            return "user already registered in ";
        }
        return "successful registeration -> " + name;
    }
}