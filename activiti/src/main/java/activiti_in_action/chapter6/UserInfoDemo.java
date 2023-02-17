package activiti_in_action.chapter6;


import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.identity.User;

import java.util.List;
import java.util.UUID;

public class UserInfoDemo {
    public static void main(String[] args) {
        testAddUserInfo();

    }

    private static void testAddUserInfo() {
        ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti_in_action/chapter5/activiti.cfg.xml");
        ProcessEngine engine = config.buildProcessEngine();
        IdentityService identityService = engine.getIdentityService();

        String id = UUID.randomUUID().toString();
        createUser(identityService, id ,"Angus2","Young","yangenxiong@163.com","abc");

        identityService.setUserInfo(id, "age","30");
        identityService.setUserInfo(id, "weight","60KG");

        String val = identityService.getUserInfo(id, "age");
        System.out.println("userInfo age: "+val);
        identityService.deleteUserInfo(id, "age");

        List<User> users = identityService.createUserQuery().list();
        for(User data: users){
            System.out.println("Group ID: " + data.getId() + ", Name:"+ data.getFirstName()+data.getLastName() );
        }
    }

    private static void createUser(IdentityService identityService, String id, String firstName, String lastName, String email, String passwd){
        User user = identityService.newUser(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(passwd);
        user.setEmail(email);
        identityService.saveUser(user);
    }
}
