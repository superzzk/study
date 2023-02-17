package redis_study.chapter.five.high_read_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the main class
 * ° This class coordinates the flow for USECASE-1 and USECASE-2
 * ° This class is responsible for creating threads for USECASE-1 and USECASE-2
 */
public class MasterSlaveLoadTest {
    private List<Thread> threadList = new ArrayList<Thread>();

    public static void main(String[] args) throws InterruptedException {
        MasterSlaveLoadTest test = new MasterSlaveLoadTest();
        test.setup();
        //make it sleep so that the master finishes writing the
        // values in the datastore otherwise reads will have either null values or old values.
        Thread.sleep(40000);

        test.readFromMasterNode();
        test.readFromSlaveNodes();
    }

    private void setup() {
        Thread pumpData = new Thread(new PumpData());
        pumpData.start();
    }

    private void readFromMasterNode() {
        long starttime = System.currentTimeMillis();
        for (int number = 1; number < 11; number++) {
            Thread thread = new Thread(new FetchData(number, starttime, "localhost", 6379));
            threadList.add(thread);
        }
        for (int number = 0; number < 10; number++) {
            Thread thread = threadList.get(number);
            thread.start();
        }
    }

    private void readFromSlaveNodes() {
        long starttime0 = System.currentTimeMillis();
        for (int number = 1; number < 6; number++) {
            Thread thread = new Thread(new FetchData(number, starttime0, "localhost", 6381));
            threadList.add(thread);
        }
        long starttime1 = System.currentTimeMillis();
        for (int number = 6; number < 11; number++) {
            Thread thread = new Thread(new FetchData(number, starttime1, "localhost", 6380));
            threadList.add(thread);
        }
        for (int number = 0; number < 10; number++) {
            Thread thread = threadList.get(number);
            thread.start();
        }
    }
}