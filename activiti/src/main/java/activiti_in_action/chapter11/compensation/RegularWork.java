package activiti_in_action.chapter11.compensation;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class RegularWork implements JavaDelegate {
	int i = 0;
	public void execute(DelegateExecution execution)  {
		i++;
		System.out.println("处理第 " + i + " 次正常工作...");
	}
}
