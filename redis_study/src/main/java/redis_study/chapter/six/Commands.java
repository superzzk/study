package redis_study.chapter.six;


public abstract class Commands {
    private Argument argument;
    public Commands(Argument argument) {
        this.argument = argument;
    }
    public abstract String execute();
    public Argument getArgument() {
        return argument;
    }
}