package activiti_in_action.chapter6;


import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

import java.util.List;
import java.util.UUID;

public class MemberShipDemo {
    public static void main(String[] args) {
        testAddUserInfo();

    }

    private static void testAddUserInfo() {
        ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti_in_action/chapter5/activiti.cfg.xml");
        ProcessEngine engine = config.buildProcessEngine();
        IdentityService identityService = engine.getIdentityService();

        String uid = UUID.randomUUID().toString();
        createUser(identityService, uid ,"Angus2","Young","yangenxiong@163.com","abc");
        User user = identityService.createUserQuery().userId(uid).singleResult();

        String gid = UUID.randomUUID().toString();
        createGroup(identityService, gid, "经理组","manager");
        Group group = identityService.createGroupQuery().groupId(gid).singleResult();

        identityService.createMembership(user.getId(),group.getId());
    }

    private static void createUser(IdentityService identityService, String id, String firstName, String lastName, String email, String passwd){
        User user = identityService.newUser(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(passwd);
        user.setEmail(email);
        identityService.saveUser(user);
    }
    private static  void createGroup(IdentityService identityService, String id, String name, String type){
        Group group = identityService.newGroup(id);
        group.setName(name);
        group.setType(type);
        //group.setId(null);
        identityService.saveGroup(group);
    }
}
