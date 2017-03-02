package com.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProcessEngineStarter {

	/**
	 * N种不同的启动方式
	 * 
	 * ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
	 * ProcessEngineConfiguration.createProcessEngineConfigurationFromResource(String
	 * resource);
	 * ProcessEngineConfiguration.createProcessEngineConfigurationFromResource(String
	 * resource, String beanName);
	 * ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(InputStream
	 * inputStream);
	 * ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(InputStream
	 * inputStream, String beanName);
	 * 
	 */

	public static void main(String[] args) {
		ProcessEngine processEngine = startUsingSpringFile();
		String pName = processEngine.getName();
		String ver = ProcessEngine.VERSION;
		System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");
		pe = processEngine;
		// startUsingFile();
	}

	static {

		// startUsingFile();
	}

	private static ProcessEngine pe;

	public static ProcessEngine getProcessEngine() {
		if (pe == null) {
//			startUsingFile();
			startUsingSpringFile();
		}
		return pe;
	}

	@SuppressWarnings("resource")
	private static ProcessEngine startUsingSpringFile() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("all.xml");

		ProcessEngine processEngine = (ProcessEngine) ac.getBean("processEngine");
		pe=processEngine;
		return processEngine;

	}

	private static void startUsingFile() {
		// conf-activiti.xml
		// ProcessEngineConfiguration cfg
		// =ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("my-activiti.cfg.xml");
		ProcessEngineConfiguration cfg = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("all.xml");

		ProcessEngine processEngine = cfg.buildProcessEngine();
		String pName = processEngine.getName();
		String ver = ProcessEngine.VERSION;
		System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");
		pe = processEngine;
	}

	@SuppressWarnings("unused")
	private static void startSimple() {
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
				.setJdbcUrl("jdbc:mysql://localhost:3306/db_activiti?useUnicode=true&characterEncoding=UTF-8")
				.setJdbcUsername("root").setJdbcPassword("sa").setJdbcDriver("com.mysql.jdbc.Driver")
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		ProcessEngine processEngine = cfg.buildProcessEngine();
		String pName = processEngine.getName();
		String ver = ProcessEngine.VERSION;
		System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");
		pe = processEngine;
	}

}
