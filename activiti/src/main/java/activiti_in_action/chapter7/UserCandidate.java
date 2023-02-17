package activiti_in_action.chapter7;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.UUID;

public class UserCandidate {
    public static void main(String[] args) {
        test1();
    }
    private static void test1() {
        //创建流程引擎
        ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti_in_action/chapter5/activiti.cfg.xml");
        ProcessEngine engine = config.buildProcessEngine();

        //得到流程存储服务对象
        RepositoryService repositoryService = engine.getRepositoryService();
        IdentityService identityService = engine.getIdentityService();

        //创建 DeploymentBuilder 实例
        DeploymentBuilder builder = repositoryService.createDeployment();
        Deployment dep = builder.name("demo3").addClasspathResource("activiti_in_action/chapter7/candidateUser.bpmn").deploy();

        ProcessDefinition def = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

        createUser(identityService, "user1" ,"user1","Young","yangenxiong@163.com","abc");
        repositoryService.addCandidateStarterUser(def.getId(), "user1");
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
