package activiti_in_action.chapter11;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class GenOrderDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) {
		System.out.println("系统完成生成订单");
	}

}
