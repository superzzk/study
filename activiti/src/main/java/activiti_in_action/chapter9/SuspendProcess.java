package activiti_in_action.chapter9;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 中断流程
 * 
 * @author yangenxiong
 * 
 */
public class SuspendProcess {

	public static void main(String[] args) {
		// 创建流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		// 得到流程存储服务实例
		RepositoryService repositoryService = engine.getRepositoryService();
		// 得到运行时服务组件
		RuntimeService runtimeService = engine.getRuntimeService();
		// 部署流程描述文件
		repositoryService.createDeployment()
				.addClasspathResource("activiti_in_action/chapter9/SuspendProcess.bpmn").deploy();
		// 开始流流程
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("testProcess");
		// 中断流程
		runtimeService.suspendProcessInstanceById(pi.getId());

		ExecutionEntity exe = (ExecutionEntity) runtimeService.createExecutionQuery().activityId("usertask1")
				.processInstanceId(pi.getId()).singleResult();
		System.out.println("中断后执行流状态：" + exe.isSuspended());
		// 激活流程
		runtimeService.activateProcessInstanceById(pi.getId());
		exe = (ExecutionEntity) runtimeService.createExecutionQuery().activityId("usertask1")
				.processInstanceId(pi.getId()).singleResult();
		System.out.println("激活后执行流状态：" + exe.isSuspended());
	}

}
