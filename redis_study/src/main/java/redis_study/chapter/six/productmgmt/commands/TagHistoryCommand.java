package redis_study.chapter.six.productmgmt.commands;

import redis_study.chapter.six.Commands;
import redis_study.chapter.six.AnalyticsDBManager;
import redis_study.chapter.six.Argument;

/**
 * This command can be executed if we want to see the tag history
 * of a product. The ranking of the product is based on the points accumulated
 * by individual products belonging to the tag.
 */
public class TagHistoryCommand extends Commands {
    public TagHistoryCommand(Argument argument) {
        super(argument);
    }
    @Override
    public String execute() {
        String tagname = this.getArgument().getValue("tagname");
        String tagHistory = AnalyticsDBManager.singleton.getTagHistory(tagname);
        return tagHistory;
    }
}