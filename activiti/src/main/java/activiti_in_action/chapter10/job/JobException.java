package activiti_in_action.chapter10.job;

import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 工作执行异常
 * 
 * @author yangenxiong
 * 
 */
public class JobException {

	public static void main(String[] args) throws Exception {
		// 创建流程引擎
		ProcessEngineImpl engine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();

		RepositoryService repositoryService = engine.getRepositoryService();
		RuntimeService runtimeService = engine.getRuntimeService();
		ManagementService managementService = engine.getManagementService();

		repositoryService.createDeployment().addClasspathResource("activiti_in_action/chapter10/jobException.bpmn").deploy();

		ProcessInstance pi = runtimeService.startProcessInstanceByKey("timer-start-event");

		Job job = managementService.createJobQuery().singleResult();
		managementService.setJobRetries(job.getId(),1);
		try{
			managementService.executeJob(job.getId());
		}catch (Exception e){

		}

		long deadCount = managementService.createDeadLetterJobQuery().count();
		System.out.println("无法执行的工作数量："+deadCount);
	}

}
