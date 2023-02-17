package redis_study.chapter.seven.gossipserver.commands;

import redis_study.chapter.seven.gossipserver.CheckResult;
import redis_study.chapter.seven.gossipserver.commandhandlers.ActivateCommandHandler;
import redis_study.chapter.seven.gossipserver.util.commandparser.StringToken;
import redis_study.chapter.seven.gossipserver.util.commandparser.Token;
import redis_study.chapter.seven.gossipserver.util.commandparser.Validator;

import java.util.List;

public class ActivateCommand extends AbstractCommand {
    private Validator validator = new Validator();
    public ActivateCommand() {
        validator.configureTemplate().add((new StringToken("activate")));
    }

    @Override
    public CheckResult execute(CommandTokens commandTokens) {
        CheckResult checkResult = new CheckResult();
        validator.setInput(commandTokens);
        checkResult = validator.validate();
        if (checkResult.getResult()) {
            List<Token> tokenList = validator.getAllTokens();
            checkResult = new ActivateCommandHandler(this.getName()).
                    process(tokenList);
        }
        return checkResult;
    }
}