package redis_study.chapter.seven.gossipserver;

/**
 * @author zhangzhongkun
 * @since 2019-07-29 19:14
 **/
public class CheckResult {
    public boolean getResult() {
        return true;
    }

    public String getReason() {
        return "reason";
    }

    public String getValue() {
        return "value";
    }

    public CheckResult setFalse(String s) {
        return this;
    }

    public CheckResult appendReason(String s){
        return this;
    }

    public CheckResult setTrue() {
        return this;
    }
}
