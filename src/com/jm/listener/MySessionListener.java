package com.jm.listener;

import java.util.Date;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class MySessionListener implements SessionListener{

	@Override
	public void onStart(Session session) {
		 System.out.println("会话创建：" + session.getId() + "------" + session.getLastAccessTime());  
	}

	@Override
	public void onStop(Session session) {
		System.out.println("会话过期：" + session.getId() + "------" + new Date());  
	}

	@Override
	public void onExpiration(Session session) {
		 System.out.println("会话停止：" + session.getId() + "------" + session.getLastAccessTime());
	}
}
