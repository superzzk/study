package redis_study.chapter.seven.gossipserver.commands;

import redis_study.chapter.seven.gossipserver.CheckResult;

/**
 * @author zhangzhongkun
 * @since 2019-07-29 19:35
 **/
public abstract class AbstractCommand {
    private String name;
    public abstract CheckResult execute(CommandTokens commandTokens);

    protected String getName(){
        return "name";
    }

    public void setName(String nodename){
        this.name = nodename;
    }
}
