package example;


import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

public class LearningStormTopology {
    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
        // create an instance of TopologyBuilder class
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("LearningStormSpout", new LearningStormSpout(), 2);
        builder.setBolt("LearningStormBolt",
                new LearningStormBolt(), 4).shuffleGrouping("LearningStormSpout");
        Config conf = new Config();
        //conf.setDebug(true);

        // create an instance of LocalCluster class for executing topology in local mode.
        LocalCluster cluster = new LocalCluster();
        // LearningStormTopolgy is the name of submitted topology.
        cluster.submitTopology("LearningStormToplogy", conf, builder.createTopology());
        try {
            Thread.sleep(10000);
        } catch (Exception exception) {
            System.out.println("Thread interrupted exception : " + exception);
        }
        // kill the LearningStormTopology
        cluster.killTopology("LearningStormToplogy");
        // shutdown the storm test cluster
        cluster.shutdown();
    }
}