package activiti_in_action.chapter8;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;

public class NewTask {

	public static void main(String[] args) {
		// 创建流程引擎
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti_in_action/chapter5/activiti.cfg.xml");
		ProcessEngine engine = config.buildProcessEngine();
		//获取任务服务组件
		TaskService taskService = engine.getTaskService();
		//保存第一个Task，不设置ID
		Task task1 = taskService.newTask();
		taskService.saveTask(task1);
		//保存第二个Task，设置ID
		Task task2 = taskService.newTask("审核任务");
		taskService.saveTask(task2);
	}

}
