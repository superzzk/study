package redis_study.chapter.six.productmgmt.commands;

import java.util.Map;

import redis_study.chapter.six.Commands;
import redis_study.chapter.six.Argument;
import redis_study.chapter.six.ProductDBManager;

/**
 * 增加产品信息
 * productApp?command=commission&args=name=Redisbook-1:cost=10:catagory=book:author=vinoo:
 * tags=Redis@5,NoSql@3,database@2,technology@1
 *
 * The tags are associated with the weights that will come into play whenever
 * recommendation engine kicks in
 */
public class CommissionProductCommand extends Commands {
    public CommissionProductCommand(Argument argument) {
        super(argument);
    }

    @Override
    public String execute() {
        System.out.println(this.getClass().getSimpleName() + ": " + "Entering the execute function");
        Map<String, String> productAttributes = this.getArgument().getAttributes();
        boolean commisioning_result = ProductDBManager.singleton.commisionProduct(productAttributes);
        boolean tagging_result = ProductDBManager.singleton.enterTagEntries(productAttributes.get("name"),
                productAttributes.get("tags"));
        if (commisioning_result & tagging_result) {
            return "commisioning successful";
        } else {
            return "commisioning not successful";
        }
    }
}