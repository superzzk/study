package activiti_in_action.chapter8;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * 参数类型
 * @author yangenxiong
 *
 */
public class SerilizableVariableType {

	public static void main(String[] args)  {
		//获取流程引擎实例
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti_in_action/chapter5/activiti.cfg.xml");
		ProcessEngine engine = config.buildProcessEngine();

		// 获取任务服务组件
		TaskService taskService = engine.getTaskService();
		//保存第一个Task
		Task task1 = taskService.newTask("task1");
		task1.setName("出差申请");
		taskService.saveTask(task1);
		//设置序列化参数
		taskService.setVariable(task1.getId(), "arg0", new TestVO("crazyit"));
	}

}
