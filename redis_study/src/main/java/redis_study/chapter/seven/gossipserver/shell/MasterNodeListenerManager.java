package redis_study.chapter.seven.gossipserver.shell;

import redis_study.chapter.seven.gossipserver.MasterNode;
import redis_study.chapter.seven.gossipserver.commands.AbstractCommand;
import redis_study.chapter.seven.gossipserver.commands.StartMasterCommand;
import redis_study.chapter.seven.gossipserver.commands.StopMasterCommand;

public class MasterNodeListenerManager implements NodeMessageListenerManager {
    private MasterEventMessageListener masterEventMessageSubscriber;
    private Thread privateEventThread;
    private MasterNode masternode;
    public MasterNodeListenerManager(MasterNode masterNode) {
        this.masternode = masterNode;
        masterEventMessageSubscriber = new MasterEventMessageListener(masternode);
    }
    @Override
    public void start() {
        System.out.println(" start the master node manager .. ");
        privateEventThread = new Thread(masterEventMessageSubscriber);
        privateEventThread.start();
    }
    @Override
    public void stop() {
        System.out.println(" stop the master node manager .. ");
        privateEventThread.interrupt();
        masterEventMessageSubscriber.unsubscribe();
    }
    @Override
    public void passCommand(AbstractCommand command) {
        if (command instanceof StartMasterCommand) {
            this.start();
        } else if (command instanceof StopMasterCommand) {
            this.stop();
        }
    }
}