package activiti_in_action.chapter6;


import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

import java.util.List;
import java.util.UUID;

public class UserDemo {
    public static void main(String[] args) {
        testAddUser();

    }

    private static void testAddUser() {
        ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti_in_action/chapter5/activiti.cfg.xml");
        ProcessEngine engine = config.buildProcessEngine();
        IdentityService identityService = engine.getIdentityService();

        String id = UUID.randomUUID().toString();
        User user = identityService.newUser(id);
        user.setFirstName("Angus");
        user.setLastName("Young");
        user.setPassword("abc");
        user.setEmail("yangenxiong@163.com");
        identityService.saveUser(user);

        List<User> users = identityService.createUserQuery().list();
        for(User data: users){
            System.out.println("Group ID: " + data.getId() + ", Name:"+ data.getFirstName()+data.getLastName() );
        }
    }
}
