package redis_study.chapter.seven.gossipserver.commands;

import redis_study.chapter.seven.gossipserver.CheckResult;
import redis_study.chapter.seven.gossipserver.util.commandparser.StringToken;
import redis_study.chapter.seven.gossipserver.util.commandparser.Validator;

public class StartMasterCommand extends AbstractCommand {
    private Validator validator = new Validator();
    public StartMasterCommand() {
        validator.configureTemplate().add((new StringToken("start")));
    }
    @Override
    public CheckResult execute(CommandTokens commandTokens) {
        CheckResult checkResult = new CheckResult();
        validator.setInput(commandTokens);
        return checkResult.setTrue().appendReason("master started..");
    }
}