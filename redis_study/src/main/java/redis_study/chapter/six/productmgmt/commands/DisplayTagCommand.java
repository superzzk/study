package redis_study.chapter.six.productmgmt.commands;

import redis_study.chapter.six.Commands;
import redis_study.chapter.six.Argument;
import redis_study.chapter.six.ProductDBManager;

/**
 * productApp?command=displaytag&args=tagname=nosql
 *
 * This program will display books on the basis of hits for a book
 */
public class DisplayTagCommand extends Commands {
    public DisplayTagCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        String tagName = this.getArgument().getValue("tagname");
        String details = ProductDBManager.singleton.getTagValues(tagName);
        return details;
    }
}