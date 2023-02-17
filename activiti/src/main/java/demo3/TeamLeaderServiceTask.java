package demo3;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

/**
 * 项目组长批准请假
 *    模拟当开发人员请假小于3天，组长比准
 *
 *
 *     <serviceTask id="servicetask3" name="项目组长同意" activiti:class="com.easyway.workflow.activiti.gateway.TeamLeaderServiceTask"></serviceTask>

 */
public class TeamLeaderServiceTask implements JavaDelegate {

    private final Logger log = Logger.getLogger(TeamLeaderServiceTask.class.getName());

    public void execute(DelegateExecution execution)  {
        //Thread.sleep(10000);
        log.info("variavles=" + execution.getVariables());
        execution.setVariable("项目组长", "请假天数小于3天，同意请假。");
        log.info("项目组长,请假天数小于3天，同意请假。");
    }
}