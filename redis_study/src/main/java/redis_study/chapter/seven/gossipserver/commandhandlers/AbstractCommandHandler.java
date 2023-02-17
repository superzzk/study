package redis_study.chapter.seven.gossipserver.commandhandlers;

import redis_study.chapter.seven.gossipserver.CheckResult;
import redis_study.chapter.seven.gossipserver.util.commandparser.Token;

import java.util.List;

/**
 * @author zhangzhongkun
 * @since 2019-07-29 19:29
 **/
public abstract class AbstractCommandHandler {
    private String nodeName;
    public AbstractCommandHandler(String nodename) {
    }

    public abstract CheckResult process(List<Token> tokenList);

    protected String getNodename(){
        return nodeName;
    }
}
