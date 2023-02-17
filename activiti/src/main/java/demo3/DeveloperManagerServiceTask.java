package demo3;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.logging.Logger;
/**
 * execute方法的参数DelegateExecution execution可以在流程中各个结点之间传递流程变量。
 *通过基于Activiti 5.6的<parallelGateway>、<serviceTask>、<receiveTask>、<userTask>元素。
 *
 *自动执行
 <serviceTask>元素，可以实现自动活动，语法如下所示：
 <serviceTask id="serviceTaskId" name="serviceTaskName"
 activiti:class="org.shirdrn.workflow.activiti.gateway.ServiceTaskClass"/>
 其中，activiti:class属性为该结点对应的处理类，该类要求实现org.activiti.engine.delegate.JavaDelegate接口
 *
 * <serviceTask id="servicetask1" name="项目经理同意" activiti:class="com.easyway.workflow.activiti.gateway.DeveloperManagerServiceTask"></serviceTask>

 * 项目经理审批过程
 */
public class DeveloperManagerServiceTask implements JavaDelegate {

    private final Logger log = Logger.getLogger(DeveloperManagerServiceTask.class.getName());

    public void execute(DelegateExecution execution)  {
        //Thread.sleep(10000);
        log.info("variavles=" + execution.getVariables());
        execution.setVariable("项目经理", "请假天数大约3天，同意请假。");
        log.info("项目经理，请假天数大约3天，同意请假。.");
    }
}