package redis_study.chapter.seven.gossipserver.shell;

import redis_study.chapter.seven.gossipserver.ClientNode;
import redis_study.chapter.seven.gossipserver.commands.AbstractCommand;
import redis_study.chapter.seven.gossipserver.commands.ActivateCommand;
import redis_study.chapter.seven.gossipserver.commands.PassivateCommand;

public class ClientNodeListenerManager implements NodeMessageListenerManager {
    private String nodename;
    private ClientEventMessageListener privateEventMessageSubscriber;
    private Thread commonEventThread;
    private Thread privateEventThread;
    public ClientNodeListenerManager(ClientNode clientNode) {
        this.nodename = clientNode.getNodename();
        privateEventMessageSubscriber = new ClientEventMessageListener(clientNode);
    }
    @Override
    public void start() {
        System.out.println(" start the client node manager .. ");
        privateEventThread = new Thread(privateEventMessageSubscriber);
        commonEventThread.start();
        privateEventThread.start();
    }
    @Override
    public void stop() {
        System.out.println(" stop the client node manager .. ");
        privateEventMessageSubscriber.unsubscribe();
        commonEventThread.interrupt();
        privateEventThread.interrupt();
    }
    @Override
    public void passCommand(AbstractCommand command) {
        if (command instanceof ActivateCommand
//                || command instanceof ReactivateCommand
//                || command instanceof ReConnectCommand
        ) {
            this.start();
        } else if (command instanceof PassivateCommand
//                || command instanceof KillNodeCommand
        ) {
            this.stop();
        }
    }
}