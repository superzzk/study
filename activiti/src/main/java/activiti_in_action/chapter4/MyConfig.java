package activiti_in_action.chapter4;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class MyConfig {
    public static void main(String[] args) {
        //Creates the Process Engine using a memory-based h2 embedded database.
        ProcessEngineConfiguration cfg = new TestConfiguration()
                .setJdbcUrl("jdbc:h2:tcp://localhost/~/test2")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();


        //根据bpmn文件部署流程
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("activiti_in_action/chapter1/first.bpmn20.xml").deploy();
        //启动流程定义，返回流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("chapter1_first");
        String processId = pi.getId();
        System.out.println("流程创建成功，当前流程实例ID："+processId);

        Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        System.out.println("第一次执行前，任务名称："+task.getName());
        taskService.complete(task.getId());

        task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        System.out.println("第二次执行前，任务名称："+task.getName());
        taskService.complete(task.getId());

        task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        if(null == task)
            System.out.println("task为null，任务执行完毕："+task);
    }
}
