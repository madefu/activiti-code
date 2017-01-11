package activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class SayHelloToLeaveTest {

    @Test
    public void testStartProcess() throws Exception {
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration()
                .buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        String bpmnFileName = "SayHelloToLeave.bpmn";
        repositoryService
                .createDeployment()
                .addInputStream(
                        "SayHelloToLeave.bpmn",
                        this.getClass().getClassLoader()
                                .getResourceAsStream(bpmnFileName)).deploy();

        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery().singleResult();
        assertEquals("SayHelloToLeave", processDefinition.getKey());

        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applyUser", "employee1");
        variables.put("days", 3);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                "SayHelloToLeave", variables);
        assertNotNull(processInstance);
        System.out.println("pid=" + processInstance.getId() + ", pdid="
                + processInstance.getProcessDefinitionId());

        TaskService taskService = processEngine.getTaskService();
        Task taskOfDeptLeader = taskService.createTaskQuery()
                .taskCandidateGroup("deptLeader").singleResult();
        assertNotNull(taskOfDeptLeader);
        assertEquals("¡Ïµº…Û≈˙", taskOfDeptLeader.getName());

        taskService.claim(taskOfDeptLeader.getId(), "leaderUser");
        variables = new HashMap<String, Object>();
        variables.put("approved", true);
        taskService.complete(taskOfDeptLeader.getId(), variables);

        taskOfDeptLeader = taskService.createTaskQuery()
                .taskCandidateGroup("deptLeader").singleResult();
        assertNull(taskOfDeptLeader);

        HistoryService historyService = processEngine.getHistoryService();
        long count = historyService.createHistoricProcessInstanceQuery().finished()
                .count();
        assertEquals(1, count);
    }
}