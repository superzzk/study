package redis_study.chapter.seven.gossipserver.commandhandlers;

import redis_study.chapter.seven.gossipserver.CheckResult;
import redis_study.chapter.seven.gossipserver.util.JedisUtil;
import redis_study.chapter.seven.gossipserver.util.commandparser.Token;

import java.util.List;

public class StatusCommandHandler extends AbstractCommandHandler {
    public StatusCommandHandler(String nodename) {
        super(nodename);
    }

    @Override
    public CheckResult process(List<Token> tokenList) {
        CheckResult checkResult = new CheckResult();
        JedisUtil jedisUtil = new JedisUtil();
        if (this.getNodename().equals("master")) {
            List<String> registerednames = jedisUtil.getAllNodesFromRegistrationHolder();
            checkResult.setTrue().appendReason("The following nodes are registered ");
            checkResult.appendReason(registerednames.toString());
            List<String> activenodenames = jedisUtil.getAllNodesFromActivatedHolder();
            checkResult.setTrue().appendReason("The following nodes are activated ");
            checkResult.appendReason(activenodenames.toString());
            List<String> passivenodenames = jedisUtil.getAllNodesFromPassivatedHolder();
            checkResult.setTrue().appendReason("The following nodes are passivated ");
            checkResult.appendReason(passivenodenames.toString());
            List<String> inconsistentState = jedisUtil.getAllNodesInInconsistentState();
            checkResult.setTrue().appendReason("The following nodes are not in consitent state ");
            checkResult.appendReason(inconsistentState.toString());
        } else {
            checkResult = jedisUtil.getStatus(this.getNodename());
        }
        return checkResult;
    }
}