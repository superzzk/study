import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class AutomatedDataDelegate implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        Date now = new Date();
        //Shows setting a process variable. In this case, the variable autoWelcomeTime with the current time.
        execution.setVariable("autoWelcomeTime", now);
        System.out.println("Faux call to backend for ["
                + execution.getVariable("fullName") + "]");
    }

}