package demo3;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;

import java.util.List;


/**
 * Activiti 5.6支持流程活动自动执行与手工触发执行。
 * 其中，自动执行是指，在启动流程之前，准备流程所需要的控制流程进度的变量数据，
 * 启动流程之后，无需外部干预，就能够按照预定义的流程执行；
 * 过基于Activiti 5.6的<parallelGateway>、<serviceTask>、<receiveTask>、<userTask>元素
 *
 *
 * 自动执行
 *<serviceTask>元素，可以实现自动活动，语法如下所示：
 *<serviceTask id="serviceTaskId" name="serviceTaskName"
 *activiti:class="org.shirdrn.workflow.activiti.gateway.ServiceTaskClass"/>
 *
 * 其中，activiti:class属性为该结点对应的处理类，该类要求实现org.activiti.engine.delegate.JavaDelegate
 * 接口，该接口定义如下所示：
 *
 * <pre>
 * package org.activiti.engine.delegate;
 *   public interface JavaDelegate {
 *         void execute(DelegateExecution execution) throws Exception;
 *  }
 * </pre>
 * execute方法的参数DelegateExecution execution可以在流程中各个结点之间传递流程变量
 *
 * 工作流模拟某公司请假流程情景如下：
 *  1.开发人员请假流程，如果开发人员请假，如果请假天数小于3天，组长批准，人事批准即可请假。
 *  2.如果请假大约三天，需要项目经理或者产品经理批准，并且项目总监批准
 *
 *  模拟自动审批：只需要启动一个流程实例，它会自动执行到结束。这种情况下，你不需要关注流
 *  程的执行进度，而只需要把精力集中在每个结点的处理逻辑（通常是简单或者复杂的商业逻辑）上。
 */
public class AutomaticParallelGatewayTest extends AbstractTest {

    private String deploymentId;

    @Override
    protected void initialize() throws Exception {
        deploymentId = repositoryService.createDeployment()
                .addClasspathResource("demo3.bpmn")
                .deploy().getId();
    }

    @Override
    protected void destroy() throws Exception {
        repositoryService.deleteDeployment(deploymentId, true);
    }

    /**
     * 使用注解部署 工作流程
     */
    @Deployment
    public void testForkJoin() {
        //根据流程ID获取流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("AutoExecuteTask");
        System.out.println("pid:"+pi.getId()+", activitiId:"+pi.getActivityId()+", pdId:"+pi.getProcessDefinitionId());
        try {
            queryProcessState(pi.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //根据运行是服务检查是否执行的结束
        assertEquals(true, pi.isEnded());
    }

    public void testQueryProcessDefinition() throws Exception{
        List<ProcessDefinition> pdList = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc()
                .list();
        for(ProcessDefinition pd : pdList){
            System.out.println("id:" + pd.getId());
            System.out.println("name:" + pd.getName());
            System.out.println("key:" + pd.getKey());
            System.out.println("version:" + pd.getVersion());
            System.out.println("resouceName:" + pd.getDiagramResourceName());
        }
    }

    public void queryProcessState(String id) throws Exception{
        ProcessInstance pi = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(id)
                .singleResult();
        if(null != pi)
            System.out.println("current process at: "+pi.getActivityId());
        else
            System.out.println("process is end!!!");
    }
}