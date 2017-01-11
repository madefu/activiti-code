package activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.activiti.ProcessEngineStarter;

@RunWith(org.junit.runners.JUnit4.class)
public class VerySimpleLeaveProcessTest {

	
	
    @Test
    public void testStartProcess() throws Exception {
        // �����������棬ʹ���ڴ����ݿ�
        ProcessEngine processEngine = ProcessEngineStarter.getProcessEngine();

        // �������̶����ļ�
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String processFileName = "bpmn/sayhelloleave.bpmn";
        repositoryService.createDeployment().addClasspathResource(processFileName)
                .deploy();

        // ��֤�Ѳ������̶���
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery().singleResult();
        assertEquals("leavesayhello", processDefinition.getKey());

        // �������̲���������ʵ��
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("leavesayhello");
        assertNotNull(processInstance);
        System.out.println("pid=" + processInstance.getId() + ", pdid="
                + processInstance.getProcessDefinitionId());
    }
}