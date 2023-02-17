package activiti_in_action.chapter1;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookOrderTest {
    @Test
    public void startBookOrder() {
//        ProcessEngine processEngine = ProcessEngineConfiguration
//                .createStandaloneProcessEngineConfiguration()
//                .buildProcessEngine();
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:tcp://localhost/~/test")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = cfg.buildProcessEngine();


        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        IdentityService identityService = processEngine.getIdentityService();
        TaskService taskService = processEngine.getTaskService();
        repositoryService.createDeployment()
                .addClasspathResource("activiti_in_action/bookorder.bpmn20.xml")
                .deploy();


        Map<String, Object> variableMap = new HashMap<String, Object>();
        variableMap.put("isbn", "123456");
        identityService.setAuthenticatedUserId("kermit");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("bookorder", variableMap);
        assertNotNull(processInstance.getId());

        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser("kermit").list();
        assertEquals(1, taskList.size());

//        System.out.println("found task " + taskList.get(0).getName());
//        taskService.complete(taskList.get(0).getId());
    }
}
