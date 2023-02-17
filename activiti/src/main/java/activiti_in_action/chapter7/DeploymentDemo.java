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

public class DeploymentDemo {
    public static void main(String[] args) {
        test2();
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
        builder.addBpmnModel("MyCodeProcess", createProcessModel())
                .name("MyCodeDeploy").deploy();
    }
    private static void test2() {
        //创建流程引擎
        ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti_in_action/chapter5/activiti.cfg.xml");
        ProcessEngine engine = config.buildProcessEngine();

        //得到流程存储服务对象
        RepositoryService repositoryService = engine.getRepositoryService();
        //创建 DeploymentBuilder 实例
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.name("crazyit").tenantId("tenantId").key("myKey").category("myCategory");
        builder.deploy();
    }

    private static BpmnModel createProcessModel() {
        //创建 BPMN 模型对象
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        model.addProcess(process);
        process.setId("myProcess");
        process.setName("My Process");
        //开始事件
        StartEvent startEvent = new StartEvent();
        startEvent.setId("startEvent");
        process.addFlowElement(startEvent);
        //用户任务
        UserTask userTask = new UserTask();
        userTask.setName("User Task");
        userTask.setId("userTask");
        process.addFlowElement(userTask);
        //结束事件
        EndEvent endEvent = new EndEvent();
        endEvent.setId("endEvent");
        process.addFlowElement(endEvent);
        //添加流程顺序
        process.addFlowElement(new SequenceFlow("startEvent", "userTask"));
        process.addFlowElement(new SequenceFlow(" userTask", "endEvent"));
        return model;
    }
}
