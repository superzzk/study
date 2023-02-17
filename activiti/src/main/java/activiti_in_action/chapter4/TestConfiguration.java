package activiti_in_action.chapter4;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.CommandInterceptor;

import java.util.ArrayList;

/**
 * @program: activiti
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-04-08 16:08
 **/
public class TestConfiguration extends ProcessEngineConfigurationImpl {
    public CommandInterceptor createTransactionInterceptor() {
        //不实现事务拦截器
        return null;
    }

    public void initCommandInterceptors(){
        customPreCommandInterceptors = new ArrayList<CommandInterceptor>();
        customPreCommandInterceptors.add(new InterceptorA());
        customPreCommandInterceptors.add(new InterceptorB());

        super.initCommandInterceptors();
    }

}
