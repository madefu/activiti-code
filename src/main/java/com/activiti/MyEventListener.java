package com.activiti;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;


public class MyEventListener implements ActivitiEventListener {

	@Override
	public void onEvent(ActivitiEvent event) {
		switch (event.getType()) {

		case JOB_EXECUTION_SUCCESS:
			System.out.println("A job well done!");
			break;

		case JOB_EXECUTION_FAILURE:
			System.out.println("A job has failed...");
			break;

		default:
			System.out.println("Event received: " + event.getType());
		}
	}

	@Override
	public boolean isFailOnException() {
		// onEvent方法中的逻辑并不重要，日志失败异常可以被忽略……
		return false;
	}
}
