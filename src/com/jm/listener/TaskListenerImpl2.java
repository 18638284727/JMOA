package com.jm.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component("taskListenerImpl2")
public class TaskListenerImpl2 implements TaskListener{

	@Override
	public void notify(DelegateTask delegateTask) {
		if(delegateTask.getEventName().equals("create")){
			delegateTask.setAssignee((String) delegateTask.getVariables().get("nextPerson"));
		}
	}

}
