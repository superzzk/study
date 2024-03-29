package example;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LearningStormSpout extends BaseRichSpout {
    private static final long serialVersionUID = 1L;
    private SpoutOutputCollector spoutOutputCollector;
    private static final Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "google");
        map.put(1, "facebook");
        map.put(2, "twitter");
        map.put(3, "youtube");
        map.put(4, "linkedin");
    }

    public void open(Map conf, TopologyContext context,
                     SpoutOutputCollector spoutOutputCollector) {
// Open the spout
        this.spoutOutputCollector = spoutOutputCollector;
    }

    public void nextTuple() {
// Storm cluster repeatedly calls this method to emit a continuous stream of tuples.
        final Random rand = new Random();
// generate the random number from 0 to 4.
        int randomNumber = rand.nextInt(5);
        spoutOutputCollector.emit(new Values(map.get(randomNumber)));
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // emit the tuple with field "site"
        declarer.declare(new Fields("site"));
    }
}