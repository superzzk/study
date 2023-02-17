package redis_study.chapter.seven.gossipserver.shell;

import redis_study.chapter.seven.gossipserver.commands.AbstractCommand;

public interface NodeMessageListenerManager {
    void start();
    void stop();
    void passCommand(AbstractCommand command);
}
