package redis_study.chapter.seven.gossipserver.util.commandparser;

import redis_study.chapter.seven.gossipserver.CheckResult;
import redis_study.chapter.seven.gossipserver.commands.CommandTokens;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zhangzhongkun
 * @since 2019-07-29 19:21
 **/
public class Validator {
    public Validator configureTemplate() {
        return this;
    }

    public Validator add(Token set) {
        return this;
    }

    public List<Token> getAllTokens() {
        return null;
    }

    public CheckResult validate() {
        return null;
    }

    public void setInput(CommandTokens commandTokens) {
    }

    public void setInput(String readmessage) {
    }

    public Token getToken(int i) {
        return null;
    }
}
