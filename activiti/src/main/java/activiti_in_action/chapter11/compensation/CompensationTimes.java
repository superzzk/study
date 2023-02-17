package activiti_in_action.chapter11.compensation;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

/**
 * 测试补偿次数
 * @author yangenxiong
 *
 */
public class CompensationTimes {

	public static void main(String[] args) {
		// 创建流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		// 得到流程存储服务组件
		RepositoryService repositoryService = engine.getRepositoryService();
		// 得到运行时服务组件
		RuntimeService runtimeService = engine.getRuntimeService();
		TaskService taskService = engine.getTaskService();
		// 部署流程文件
		repositoryService.createDeployment()
				.addClasspathResource("activiti_in_action/chapter11/CompensationTimes.bpmn").deploy();
		runtimeService.startProcessInstanceByKey("ctProcess");
	}

}
