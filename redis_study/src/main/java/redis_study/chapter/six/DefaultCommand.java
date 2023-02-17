package redis_study.chapter.six;

public class DefaultCommand extends Commands {
    public DefaultCommand(Argument argument) {
        super(argument);
    }
    @Override
    public String execute() {
        return "Command Not Recognized !!";
    }
}