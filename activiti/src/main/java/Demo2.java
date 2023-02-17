import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Demo2 {
    RepositoryService repositoryService;
    RuntimeService runtimeService;
    TaskService taskService;
    HistoryService historyService;
    ProcessEngineConfiguration processEngineConfiguration;

    private void init(){
        //Creates the Process Engine using a memory-based h2 embedded database.
        processEngineConfiguration = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        //Displays the Process Engine configuration and Activiti version.
        String pName = processEngine.getName();
        String ver = ProcessEngine.VERSION;
        System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");

        repositoryService = processEngine.getRepositoryService();
        runtimeService = processEngine.getRuntimeService();
        taskService = processEngine.getTaskService();
        historyService = processEngine.getHistoryService();
    }

    public static void main(String[] args) {
        Demo2 demo = new Demo2();
        demo.init();
    }

    /**
     * 启动流程
     */
    public void startProcesses(String bizId) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("demo5", bizId);//流程图id，业务表id
        System.out.println("流程启动成功，流程id:"+pi.getId());
    }

    /**
     * 描述: 根据用户id查询待办任务列表
     */
    public List<Task> findTasksByUserId(String userId) {
        List<Task> resultTask = taskService.createTaskQuery().processDefinitionKey("demo5").taskCandidateOrAssigned(userId).list();
        return resultTask;
    }
    /**
     *
     * 描述:任务审批 	（通过/拒接）
     */
    public void completeTask(String taskId,String userId,String result) {
        //获取流程实例
        taskService.claim(taskId, userId);

        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put("sign", "true");
        taskService.complete(taskId, vars);
    }

}
