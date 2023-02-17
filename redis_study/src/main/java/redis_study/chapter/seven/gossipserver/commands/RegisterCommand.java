package redis_study.chapter.seven.gossipserver.commands;

import redis_study.chapter.seven.gossipserver.CheckResult;
import redis_study.chapter.seven.gossipserver.commandhandlers.RegisterCommandHandler;
import redis_study.chapter.seven.gossipserver.util.commandparser.StringToken;
import redis_study.chapter.seven.gossipserver.util.commandparser.Token;
import redis_study.chapter.seven.gossipserver.util.commandparser.Validator;

import java.io.File;
import java.util.List;

public class RegisterCommand extends AbstractCommand {
    private Validator validator = new Validator();
    public RegisterCommand() {
        validator.configureTemplate().add((new StringToken("register")));
    }
    @Override
    public CheckResult execute(CommandTokens commandTokens) {
        validator.setInput(commandTokens);
        CheckResult checkResult = validator.validate();
        if (checkResult.getResult()) {
            List<Token> tokenList = validator.getAllTokens();
            checkResult = new RegisterCommandHandler(this.getName()).process(tokenList);
        }
        if(checkResult.getResult()){
            String path = System.getProperty("user.home") + "\\archive\\";
            File file = new File(path);
            if (!file.exists()) {
                if (file.mkdir()) {
                    checkResult.appendReason("Archive folder created!");
                } else {
                    checkResult.appendReason("Archive folder exists!");
                }
            }
        }
        return checkResult;
    }
}