package redis_study.chapter.seven.gossipserver.commands;

import redis_study.chapter.seven.gossipserver.CheckResult;
import redis_study.chapter.seven.gossipserver.commandhandlers.DeleteCommandHandler;
import redis_study.chapter.seven.gossipserver.util.commandparser.StringListToken;
import redis_study.chapter.seven.gossipserver.util.commandparser.StringToken;
import redis_study.chapter.seven.gossipserver.util.commandparser.Token;
import redis_study.chapter.seven.gossipserver.util.commandparser.Validator;

import java.util.List;

public class DeleteCommand extends AbstractCommand {
    Validator validator = new Validator();
    public DeleteCommand() {
        validator.configureTemplate().add((new StringToken("del"))).
                add(new StringListToken());
    }
    @Override
    public CheckResult execute(CommandTokens commandTokens) {
        validator.setInput(commandTokens);
        CheckResult checkResult = validator.validate();
        if (checkResult.getResult()) {
            List<Token> tokenList = validator.getAllTokens();
            checkResult = new DeleteCommandHandler(this.getName()).process(tokenList);
        }
        return checkResult;
    }
}