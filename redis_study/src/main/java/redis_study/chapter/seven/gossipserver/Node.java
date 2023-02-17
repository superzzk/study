package redis_study.chapter.seven.gossipserver;

public interface Node {
    CheckResult process(String commands);

    String getNodename();
}
