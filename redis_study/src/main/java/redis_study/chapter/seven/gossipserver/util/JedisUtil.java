package redis_study.chapter.seven.gossipserver.util;

import redis_study.chapter.seven.gossipserver.CheckResult;

import java.util.List;
import java.util.Map;

/**
 * @author zhangzhongkun
 * @since 2019-07-29 19:41
 **/
public class JedisUtil {
    public List<Boolean> doesExist(Object nodename, List<String> asList) {
        return null;
    }

    public CheckResult setValuesInNode(Object nodename, Map<String, String> valueAsMap) {
        return null;
    }

    public CheckResult registerNode(Object nodename) {
        return null;
    }

    public CheckResult activateNode(Object nodename) {
        return null;
    }

    public CheckResult getValuesFromNode(String nodename, List<String> valueAsList) {
        return null;
    }

    public CheckResult deleteValuesFromNode(String nodename, List<String> valueAsList) {
        return null;
    }

    public List<String> getAllNodesFromRegistrationHolder() {
        return null;
    }

    public List<String> getAllNodesFromActivatedHolder() {
        return null;
    }

    public List<String> getAllNodesFromPassivatedHolder() {
        return null;
    }

    public List<String> getAllNodesInInconsistentState() {
        return null;
    }

    public CheckResult getStatus(String nodename) {
        return null;
    }

    public CheckResult passivateNode(String nodename) {
        return null;
    }

    public CheckResult publish(String value, Map<String, String> valueAsMap) {
        return null;
    }
}
