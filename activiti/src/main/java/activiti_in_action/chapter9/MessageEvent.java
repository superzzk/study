package activiti_in_action.chapter9;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;

/**
 * 使用 messageEventReceived 方法
 * @author yangenxiong
 *
 */
public class MessageEvent {

	public static void main(String[] args) {
		// 创建流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		// 得到流程存储服务实例
		RepositoryService repositoryService = engine.getRepositoryService();
		// 得到运行时服务组件
		RuntimeService runtimeService = engine.getRuntimeService();
		// 部署流程描述文件
		repositoryService.createDeployment().name("MessageEvent")
				.addClasspathResource("activiti_in_action/chapter9/MessageEvent.bpmn").deploy();
		// 开始流流程
		runtimeService.startProcessInstanceByKey("testProcess");
		// 查询流执行流
		List<Execution> exes = runtimeService.createExecutionQuery().processDefinitionKey("testProcess").list();
		System.out.println("遇到消息事件节点，执行流数量：" + exes.size());
		// 根据事件名称查询当前事件所在的执行流
		Execution exe = runtimeService.createExecutionQuery().activityId("messageintermediatecatchevent1").singleResult();
		// 触发消息事件
		runtimeService.messageEventReceived("testMsg", exe.getId(), null);
		// 查询执行流
		exes = runtimeService.createExecutionQuery().processDefinitionKey("testProcess").list();
		System.out.println("触发消息事件节点后，执行流数量：" + exes.size());
	}

}
