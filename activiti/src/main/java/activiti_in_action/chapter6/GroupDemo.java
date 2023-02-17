package activiti_in_action.chapter6;


import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.identity.Group;

import java.util.List;
import java.util.UUID;

public class GroupDemo {
    public static void main(String[] args) {
        testListGroup();

    }
    public static void testAddGroup() {
        ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti_in_action/chapter5/activiti.cfg.xml");
        ProcessEngine engine = config.buildProcessEngine();
        IdentityService identityService = engine.getIdentityService();
        String genId = UUID.randomUUID().toString();
        Group group = identityService.newGroup(genId);
        group.setName("经理组");
        group.setType("Manager");
        //group.setId(null);
        identityService.saveGroup(group);
        Group data = identityService.createGroupQuery().groupId(genId).singleResult();
        System.out.println("Group ID: " + data.getId() + ", Name:"+ data.getName() );
    }
    public static void testListGroup() {
        ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti_in_action/chapter5/activiti.cfg.xml");
        ProcessEngine engine = config.buildProcessEngine();
        IdentityService identityService = engine.getIdentityService();

        List<Group> datas = identityService.createGroupQuery().list();
        for(Group data: datas){
            System.out.println("Group ID: " + data.getId() + ", Name:"+ data.getName() );
        }
    }

    private static  void createGroup(IdentityService identityService, String id, String name, String type){
        Group group = identityService.newGroup(id);
        group.setName(name);
        group.setType(type);
        //group.setId(null);
        identityService.saveGroup(group);
    }
}

