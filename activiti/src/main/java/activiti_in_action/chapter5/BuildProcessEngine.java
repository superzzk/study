package activiti_in_action.chapter5;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngineInfo;
import org.activiti.engine.ProcessEngines;

import java.net.URL;
import java.util.Map;

public class BuildProcessEngine {
    public static void main(String[] args) {
        test2();

    }
    public static void test1() {
        ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti_in_action/chapter5/activiti.cfg.xml");
        ProcessEngine engine = config.buildProcessEngine();
    }

    public static void test2() {
        ClassLoader cl = BuildProcessEngine.class.getClassLoader();
        URL url = cl.getResource("activiti_in_action/chapter5/activiti.cfg.xml");
        ProcessEngineInfo info =  ProcessEngines.retry(url.toString());
        Map<String, ProcessEngine> engines = ProcessEngines.getProcessEngines();
        System.out.println("engines size:"+engines.size());
    }
}
