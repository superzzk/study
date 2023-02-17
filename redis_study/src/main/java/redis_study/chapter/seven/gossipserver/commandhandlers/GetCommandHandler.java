package redis_study.chapter.seven.gossipserver.commandhandlers;

import redis_study.chapter.seven.gossipserver.CheckResult;
import redis_study.chapter.seven.gossipserver.util.ConstUtil;
import redis_study.chapter.seven.gossipserver.util.JedisUtil;
import redis_study.chapter.seven.gossipserver.util.commandparser.StringListToken;
import redis_study.chapter.seven.gossipserver.util.commandparser.Token;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("Duplicates")
public class GetCommandHandler extends AbstractCommandHandler {
    public GetCommandHandler(String nodename) {
        super(nodename);
    }
    public CheckResult process(List<Token> tokenList) {
        CheckResult checkResult = new CheckResult();
        JedisUtil jedisUtil = new JedisUtil();
        List<Boolean> result = jedisUtil
                .doesExist(this.getNodename(), Arrays
                        .asList(ConstUtil.registerationHolder,
                                ConstUtil.activationHolder,
                                ConstUtil.passivationHolder, ConstUtil.
                                        shutdownHolder));
        if ((result.get(0) == true) && (result.get(1) == true)
                && (result.get(2) == false)&& (result.get(3) == false)) {
            StringListToken stringList = (StringListToken) tokenList.get(1);
            checkResult = jedisUtil.getValuesFromNode(this.getNodename(), stringList.getValueAsList());
        } else {
            checkResult
                    .setFalse("Activation Validation :")
                    .appendReason(
                            ConstUtil.registerationHolder + " = "
                                    + ((Boolean) result.get(0)))
                    .appendReason(
                            ConstUtil.activationHolder + " = "
                                    + ((Boolean) result.get(1)))
                    .appendReason(
                            ConstUtil.passivationHolder + " = "
                                    + ((Boolean) result.get(2)));
        }
        return checkResult;
    }
}