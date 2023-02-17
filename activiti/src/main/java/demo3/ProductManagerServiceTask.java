package demo3;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.logging.Logger;
/**
 * execute方法的参数DelegateExecution execution可以在流程中各个结点之间传递流程变量。
 *
 * <serviceTask id="servicetask2" name="产品经理同意" activiti:class="com.easyway.workflow.activiti.gateway.ProductManagerServiceTask"></serviceTask>

 * 产品经理审批过程
 */
public class ProductManagerServiceTask implements JavaDelegate {

    private final Logger log = Logger.getLogger(ProductManagerServiceTask.class.getName());

    public void execute(DelegateExecution execution)  {
        //Thread.sleep(10000);
        log.info("variavles=" + execution.getVariables());
        execution.setVariable("产品经理", "请假天数大约3天，同意请假。");
        log.info("产品经理,请假天数大约3天，同意请假。");
    }
}