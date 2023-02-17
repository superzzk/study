package redis_study.chapter.seven.gossipserver.commands;

import redis_study.chapter.seven.gossipserver.CheckResult;
import redis_study.chapter.seven.gossipserver.commandhandlers.GetCommandHandler;
import redis_study.chapter.seven.gossipserver.util.commandparser.StringListToken;
import redis_study.chapter.seven.gossipserver.util.commandparser.StringToken;
import redis_study.chapter.seven.gossipserver.util.commandparser.Token;
import redis_study.chapter.seven.gossipserver.util.commandparser.Validator;

import java.util.List;

public class GetCommand extends AbstractCommand {
    Validator validator = new Validator();
    public GetCommand() {
        validator.configureTemplate().add((new StringToken("get"))).
                add(new StringListToken());
    }
    @Override
    public CheckResult execute(CommandTokens commandTokens) {
        validator.setInput(commandTokens);
        CheckResult checkResult = validator.validate();
        if (checkResult.getResult()) {
            List<Token> tokenList = validator.getAllTokens();
            checkResult = new GetCommandHandler(this.getName()).process(tokenList);
        }
        return checkResult;
    }
}