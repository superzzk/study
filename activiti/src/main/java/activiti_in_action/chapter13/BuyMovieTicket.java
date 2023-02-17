package activiti_in_action.chapter13;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class BuyMovieTicket {

	public static void main(String[] args) {
		// 创建流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		// 得到流程存储服务组件
		RepositoryService repositoryService = engine.getRepositoryService();
		// 得到运行时服务组件
		RuntimeService runtimeService = engine.getRuntimeService();
		// 得到任务服务组件
		TaskService taskService = engine.getTaskService();
		// 部署流程文件
		repositoryService.createDeployment()
				.addClasspathResource("activiti_in_action/chapter13/BuyMovieTicket.bpmn")
				.deploy();
		runtimeService.startProcessInstanceByKey("process1");
		// 查询当前任务
		Task task = taskService.createTaskQuery().singleResult();
		System.out.println("当前任务：" +  task.getName());
		// 完成选场次的任务
		taskService.complete(task.getId());
	}

}
