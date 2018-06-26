package com.jm.listener;


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;

/**
 * activiti:全局监听器
 */
@SuppressWarnings("serial")
@Component("executionListenerImpl")
public class ExecutionListenerImpl implements ExecutionListener{

	/**
	 * FixedValue 是该接口 Expression 的实现类，
	 * 实现监听器的时候，注入监听器中的 Expression 类型的属性值
	 */
	private FixedValue message;
	
	public FixedValue getMessage() {
		return message;
	}

	public void setMessage(FixedValue message) {
		this.message = message;
	}

	@Override
	public void notify(DelegateExecution delegateExecution) throws Exception {
	}

}
