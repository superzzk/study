package activiti_in_action.chapter1;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @program: activiti
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-04-02 16:48
 **/
public class SimpleProcessTest {
    @Test
    public void startBookOrder() {
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration()
                .buildProcessEngine();
        RuntimeService runtimeService =
                processEngine.getRuntimeService();
        RepositoryService repositoryService =
                processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource(
                        "activiti_in_action/bookorder.simple.bpmn20.xml")
                .deploy();
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey(
                        "simplebookorder");
        assertNotNull(processInstance.getId());
        System.out.println("id:" + processInstance.getId() + ", " +
                processInstance.getProcessDefinitionId());
    }
}
