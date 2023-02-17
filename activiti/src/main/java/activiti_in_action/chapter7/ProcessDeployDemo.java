package activiti_in_action.chapter7;


import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;

public class ProcessDeployDemo {
    public static void main(String[] args) {
        test1();
    }
    private static void test1() {
        //创建流程引擎
        ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti_in_action/chapter5/activiti.cfg.xml");
        ProcessEngine engine = config.buildProcessEngine();

        //得到流程存储服务对象
        RepositoryService repositoryService = engine.getRepositoryService();
        //创建 DeploymentBuilder 实例
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.name("demo").addClasspathResource("demo.bpmn").deploy();
    }
}
