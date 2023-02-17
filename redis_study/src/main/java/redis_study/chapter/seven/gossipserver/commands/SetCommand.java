package redis_study.chapter.seven.gossipserver.commands;

import redis_study.chapter.seven.gossipserver.CheckResult;
import redis_study.chapter.seven.gossipserver.commandhandlers.SetCommandHandler;
import redis_study.chapter.seven.gossipserver.util.commandparser.MapListToken;
import redis_study.chapter.seven.gossipserver.util.commandparser.StringToken;
import redis_study.chapter.seven.gossipserver.util.commandparser.Token;
import redis_study.chapter.seven.gossipserver.util.commandparser.Validator;

import java.util.List;

public class SetCommand extends AbstractCommand {
    Validator validator = new Validator();
    public SetCommand() {
        validator.configureTemplate().add((new StringToken("set"))).add(new MapListToken());
    }
    @Override
    public CheckResult execute(CommandTokens commandTokens) {
        CheckResult checkResult = new CheckResult();
        validator.setInput(commandTokens);
        checkResult = validator.validate();
        if (checkResult.getResult()) {
            List<Token> tokenList = validator.getAllTokens();
            checkResult = new SetCommandHandler(this.getName()).process(tokenList);
        }
        return checkResult;
    }
}