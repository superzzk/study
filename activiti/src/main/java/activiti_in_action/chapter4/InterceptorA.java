package activiti_in_action.chapter4;


import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.activiti.engine.impl.interceptor.CommandInterceptor;

public class InterceptorA implements CommandInterceptor {
    private CommandInterceptor next;

    public <T> T execute(CommandConfig commandConfig, Command<T> command) {
        System.out.println("This is interceptor A:"+ command.getClass().getName());
        return getNext().execute(commandConfig, command);
    }

    public CommandInterceptor getNext() {
        return this.next;
    }

    public void setNext(CommandInterceptor commandInterceptor) {
        this.next = commandInterceptor;
    }
}
