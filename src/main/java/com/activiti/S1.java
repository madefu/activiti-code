package com.activiti;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

public class S1 {

	public static void main(String[] args){
		
		ProcessEngine processEngine = ProcessEngineStarter.getProcessEngine();
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		RepositoryService repositoryService = processEngine.getRepositoryService();
		
		TaskService taskService = processEngine.getTaskService();
		ManagementService managementService = processEngine.getManagementService();
		IdentityService identityService = processEngine.getIdentityService();
		HistoryService historyService = processEngine.getHistoryService();
		FormService formService = processEngine.getFormService();
	}
}
