package demo3;

import junit.framework.TestCase;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.test.ActivitiTestCase;
/**
 * 主要是在测试之前做一些初始化工作，主要包括流程引擎实例的构建，及其流程提供的基本服务。
 * 目的：让开发者熟悉工作流使用过程使用的几个步骤
 * 1.加载相关的工作流全局配置文件activiti.cfg.xml配置文件信息
 * 2.获取工作流相关的服务(RepositoryService，RuntimeService，
 *    TaskService，HistoryService，FormService，ManagementService，
 *    IdentityService等)
 * 2.加载工作流文件*.bpmn20.xml信息
 *
 * 3.部署工作流
 *    部署工作流由多种方式，在以后会相继讲解
 *
 */
public abstract class AbstractTest extends TestCase {

    protected ProcessEngine processEngine;
    protected String deploymentId;
    protected RepositoryService repositoryService;
    protected RuntimeService runtimeService;
    protected TaskService taskService;
    protected FormService formService;
    protected HistoryService historyService;
    protected IdentityService identityService;
    protected ManagementService managementService;


    /**
     * 测试用例开始初始化工作
     * 1.创建相关的工作流程对象ProcessEngine
     * 2.创建相关的服务
     * 3.
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //由于ProcessEngine为线程安全性对象，整个项目可以共用一个
        if(processEngine==null) {
            //此处使用此种方法调用的activiti的配置文件为 classpath路径下的activiti.cfg.xml
            //采用的H2的数据库
            //processEngine = ProcessEngines.getDefaultProcessEngine();
            ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                    .setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
                    .setJdbcUsername("sa")
                    .setJdbcPassword("")
                    .setJdbcDriver("org.h2.Driver")
                    .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
            processEngine = cfg.buildProcessEngine();
        }
        //获取工作流的各种服务信息
        repositoryService = processEngine.getRepositoryService();
        runtimeService = processEngine.getRuntimeService();
        taskService = processEngine.getTaskService();
        formService = processEngine.getFormService();
        historyService = processEngine.getHistoryService();
        identityService = processEngine.getIdentityService();
        managementService = processEngine.getManagementService();
        //调用扩展的初始化工作
        initialize();

    }

    /**
     * test销毁方法
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        destroy();
    }

    /**
     * 便于子类的工作的初始化的扩展工作
     */
    protected abstract void initialize() throws Exception;
    /**
     * 便于子类的工作的销毁的扩展工作
     */
    protected abstract void destroy() throws Exception;
}