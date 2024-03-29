package activiti_in_action.chapter10.job;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;

/**
 * 定时边界事件产生的工作
 * @author yangenxiong
 *
 */
public class TimerTransition {

	public static void main(String[] args) {
		// 创建流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		// 得到流程存储服务实例
		RepositoryService repositoryService = engine.getRepositoryService();
		// 得到运行时服务组件
		RuntimeService runtimeService = engine.getRuntimeService();
		// 部署流程文件
		repositoryService
				.createDeployment()
				.addClasspathResource("bpmn/timer-transition.bpmn")
				.deploy();
		// 开始流程
		runtimeService.startProcessInstanceByKey("timer-transition");
		// 查询工作数量
		System.out.println("工作数量：" + engine.getManagementService().createJobQuery().count());	
	}

}
