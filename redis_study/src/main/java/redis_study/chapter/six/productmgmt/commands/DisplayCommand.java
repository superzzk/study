package redis_study.chapter.six.productmgmt.commands;

import redis_study.chapter.six.Commands;
import redis_study.chapter.six.Argument;
import redis_study.chapter.six.ProductDBManager;

public class DisplayCommand extends Commands {
    public DisplayCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        String display = ProductDBManager.singleton.
                getProductInfo(this.getArgument().getValue("name"));
        return display;
    }
}