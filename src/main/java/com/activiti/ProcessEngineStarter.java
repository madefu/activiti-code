package com.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;

public class ProcessEngineStarter {

	/**
	 * N不同的创建实例方法
	 * 
	 * 	ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
		ProcessEngineConfiguration.createProcessEngineConfigurationFromResource(String resource);
		ProcessEngineConfiguration.createProcessEngineConfigurationFromResource(String resource, String beanName);
		ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(InputStream inputStream);
		ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(InputStream inputStream, String beanName);
	 * 
	 */
	
	
	public static void main(String[] args) {

		startUsingFile();
	}
	
	static{
		startUsingFile();
	}
	
	private static ProcessEngine pe;
	
	public static ProcessEngine getProcessEngine(){
		if(pe==null){
			startUsingFile();
		}
		return pe;
	}
	
	private static void startUsingFile(){
		ProcessEngineConfiguration cfg =ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("my-activiti.cfg.xml");
		ProcessEngine processEngine=cfg.buildProcessEngine();
		String pName = processEngine.getName();
		String ver = ProcessEngine.VERSION;
		System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");
		pe=processEngine;
	}
	
	@SuppressWarnings("unused")
	private static void startSimple(){
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
				.setJdbcUrl("jdbc:mysql://localhost:3306/db_activiti?useUnicode=true&characterEncoding=UTF-8")
				.setJdbcUsername("root").setJdbcPassword("sa").setJdbcDriver("com.mysql.jdbc.Driver")
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		ProcessEngine processEngine = cfg.buildProcessEngine();
		String pName = processEngine.getName();
		String ver = ProcessEngine.VERSION;
		System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");
		pe=processEngine;
	}

}
