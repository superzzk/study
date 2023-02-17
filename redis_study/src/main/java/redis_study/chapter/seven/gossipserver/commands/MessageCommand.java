package redis_study.chapter.seven.gossipserver.commands;

import redis_study.chapter.seven.gossipserver.CheckResult;
import redis_study.chapter.seven.gossipserver.commandhandlers.MessageCommandHandler;
import redis_study.chapter.seven.gossipserver.util.commandparser.MapListToken;
import redis_study.chapter.seven.gossipserver.util.commandparser.StringToken;
import redis_study.chapter.seven.gossipserver.util.commandparser.Token;
import redis_study.chapter.seven.gossipserver.util.commandparser.Validator;

import java.util.List;

public class MessageCommand extends AbstractCommand {
    Validator validator = new Validator();
    public MessageCommand() {
        validator.configureTemplate().add((new StringToken("msg"))).
                add(new StringToken()).add(new StringToken("where"))
                .add(new MapListToken());
    }
    @Override
    public CheckResult execute(CommandTokens commandTokens) {
        CheckResult checkResult = new CheckResult();
        validator.setInput(commandTokens);
        checkResult = validator.validate();
        if (checkResult.getResult()) {
            List<Token> tokenList = validator.getAllTokens();
            checkResult = new MessageCommandHandler(this.getName()).process(tokenList);
        }
        return checkResult;
    }
}