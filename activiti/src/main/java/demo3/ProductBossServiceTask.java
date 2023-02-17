package demo3;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

/**
 *  execute方法的参数DelegateExecution execution可以在流程中各个结点之间传递流程变量。
 *
 * <serviceTask id="servicetask4" name="项目总监同意" activiti:class="com.easyway.workflow.activiti.gateway.ProductBossServiceTask"></serviceTask>
 *
 * 项目总监审批过程
 */
public class ProductBossServiceTask implements JavaDelegate {

    private final Logger log = Logger.getLogger(ProductBossServiceTask.class.getName());

    public void execute(DelegateExecution execution)  {
        //Thread.sleep(10000);
        log.info("variavles=" + execution.getVariables());
        execution.setVariable("项目总监", "请假天数大约3天，同意请假。");
        log.info("项目总监,请假天数大约3天，同意请假。");
    }
}