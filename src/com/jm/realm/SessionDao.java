package com.jm.realm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jm.entity.Employee;
import com.jm.utils.SerializableUtiles;

/**
 * 针对同一个浏览器
 */
public class SessionDao extends EnterpriseCacheSessionDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		// 1. 首次访问，创建session，数据对象为null
		Employee employee = (Employee) session.getAttribute("employee");
		// 2. 序列化加密后添加到数据库
		String sql = "INSERT IGNORE INTO SESSIONS VALUES(?, ?, ?, ?)";
		jdbcTemplate.update(sql, sessionId, SerializableUtiles.serialize(employee), new Date(), new Date());
		// 3. 返回该记录的session编号
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		String sql = "SELECT SESSION_NAME FROM SESSIONS WHERE SESSION_ID = ?";
		List<String> sessions = jdbcTemplate.queryForList(sql, String.class, sessionId);
		if(sessions.size() == 0){
			return null;
		}
		Employee employee = (Employee) SerializableUtiles.deSerialize(sessions.get(0));
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		Session session = new HttpServletSession(request.getSession(), "0:0:0:0:0:0:0:1");
		session.setAttribute("employee", employee);
		return session; 
	}

	@Override
	protected void doUpdate(Session session) {
		Employee employee = (Employee) session.getAttribute("employee");
		String sql = "UPDATE SESSIONS SET SESSION_NAME = ?, SESSION_END_DATE = ? WHERE SESSION_ID = ?";
		String serialize = SerializableUtiles.serialize(employee);
		jdbcTemplate.update(sql, serialize, new Date(), session.getId());
	}

	@Override
	protected void doDelete(Session session) {
		String sql = "DELETE FROM SESSIONS WHERE SESSION_ID = ?";
		jdbcTemplate.update(sql, session.getId());
	}

}
