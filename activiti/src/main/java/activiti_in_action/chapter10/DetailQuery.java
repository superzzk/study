package activiti_in_action.chapter10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * 历史数据明细查询
 * @author yangenxiong
 *
 */
public class DetailQuery {

	public static void main(String[] args) {
		// 创建流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		// 得到流程存储服务实例
		RepositoryService repositoryService = engine.getRepositoryService();
		// 得到运行时服务组件
		RuntimeService runtimeService = engine.getRuntimeService();
		// 得到历史服务组件
		HistoryService historyService = engine.getHistoryService();
		// 得到任务组件
		TaskService taskService = engine.getTaskService();
		// 部署流程文件
		repositoryService.createDeployment()
			.addClasspathResource("activiti_in_action/chapter10/DetailQuery.bpmn").deploy();
		// 初始化参数
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("days", 10);
		//  开始流程
		ProcessInstance pi1 = runtimeService.startProcessInstanceByKey("testProcess", vars);
		ProcessInstance pi2 = runtimeService.startProcessInstanceByKey("testProcess", vars);
		// 完成第一个流程 
		Execution exe = runtimeService.createExecutionQuery()
				.processInstanceId(pi1.getId()).singleResult();
		Task task = taskService.createTaskQuery().executionId(exe.getId()).singleResult();
		taskService.complete(task.getId(), vars);
		exe = runtimeService.createExecutionQuery().processInstanceId(pi1.getId()).singleResult();


		//runtimeService.signal(exe.getId());


		// 为第二个流程的第一个任务设置参数
		exe = runtimeService.createExecutionQuery().processInstanceId(pi2.getId()).singleResult();
		task = taskService.createTaskQuery().executionId(exe.getId()).singleResult();
		taskService.setVariableLocal(task.getId(), "name", "crazyit");
		// 查询历史行为
		HistoricActivityInstance act = historyService.createHistoricActivityInstanceQuery()
				.activityName("First Task").finished().singleResult();
		List<HistoricDetail> datas = historyService.createHistoricDetailQuery()
				.activityInstanceId(act.getId()).list();
		System.out.println("使用activityInstanceId方法查询：" + datas.size());//结果1
		datas = historyService.createHistoricDetailQuery().excludeTaskDetails().list();
		System.out.println("使用excludeTaskDetails方法查询：" + datas.size());//结果3
		datas = historyService.createHistoricDetailQuery().formProperties().list();
		System.out.println("使用formProperties方法查询：" + datas.size());//结果0
		datas = historyService.createHistoricDetailQuery().processInstanceId(pi1.getId()).list();
		System.out.println("使用processInstanceId方法查询：" + datas.size());//结果2
		datas = historyService.createHistoricDetailQuery().taskId(task.getId()).list();
		System.out.println("使用taskId方法查询：" + datas.size());//结果1
		datas = historyService.createHistoricDetailQuery().variableUpdates().list();
		System.out.println("使用variableUpdates方法查询：" + datas.size());//结果4
	}

}
