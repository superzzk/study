package example2;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

public class WordCountTopology {

    public static void main(String[] args) {
        //第一步，定义TopologyBuilder对象，用于构建拓扑
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        //第二步，设置spout和bolt
        topologyBuilder.setSpout("RandomSentenceSpout", new RandomSentenceSpout());
        topologyBuilder.setBolt("SplitSentenceBolt", new SplitSentenceBolt()).shuffleGrouping("RandomSentenceSpout");
        topologyBuilder.setBolt("WordCountBolt", new WordCountBolt()).shuffleGrouping("SplitSentenceBolt");
        topologyBuilder.setBolt("PrintBolt", new PrintBolt()).shuffleGrouping("WordCountBolt");

        //第三步，构建Topology对象
        StormTopology topology = topologyBuilder.createTopology();
        Config config = new Config();

        //第四步，提交拓扑到集群，这里先提交到本地的模拟环境中进行测试
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("WordCountTopology", config, topology);

        //提交到集群
/*        try {
            StormSubmitter.submitTopology("WordCountTopology", config, topology);
        } catch (AlreadyAliveException e) {
            e.printStackTrace();
        } catch (InvalidTopologyException e) {
            e.printStackTrace();
        } catch (AuthorizationException e) {
            e.printStackTrace();
        }*/
    }

}