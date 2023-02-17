package redis_study.chapter.seven.gossipserver.util.commandparser;

import java.util.Map;

/**
 * @author zhangzhongkun
 * @since 2019-07-29 19:24
 **/
public class MapListToken extends Token{
    public Map<String, String> getValueAsMap() {
        return null;
    }

    public String getValueAsSantizedString() {
        return null;
    }

    public MapListToken removeElement(String command) {
        return null;
    }

    public boolean containsKey(String command) {
        return true;
    }

    public String getNValue(String command) {
        return null;
    }
}
