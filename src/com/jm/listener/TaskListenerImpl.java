package com.jm.listener;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jm.entity.Employee;

/**
 * activiti:Task 模块监听器
 */
@SuppressWarnings("serial")
@Component("taskListenerImpl")
public class TaskListenerImpl implements TaskListener{

	/**
	 * 装配两个监听器，一个监听申请人，一个监听申请人的下一节点
	 * 作用：设置第一个任务的处理人以及下级处理节点的处理人信息
	 * message：必须和画流程图模型填写的字段一致，以作为传值用
	 */
	private Expression message;
	
	public Expression getMessage() {
		return message;
	}

	public void setMessage(Expression message) {
		this.message = message;
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String assignee = ((Employee) request.getSession().getAttribute("employee")).geteName();
		if(delegateTask.getEventName().equals("create")){
			delegateTask.setAssignee(assignee);
		}
	}
}
