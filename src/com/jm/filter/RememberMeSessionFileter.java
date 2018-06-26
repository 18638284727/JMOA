package com.jm.filter;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.jm.entity.Employee;
import com.jm.entity.Jurisdiction;
import com.jm.service.EmployeeService;
import com.jm.service.JurisdictionService;
import com.jm.service.ShiroService;

/**
 * 添加shiro拦截规则filter:
 * 1. doGetAuthenticationInfo 处理用户认证的时候会返回一个 SimpleAuthenticationInfo 对象,
 * 这个对象封装了一个 Object 类型的 Principal 返回,而 Principal 表示的是用户认证后存储的用户信息(用户名/用户实体)
 * 2. 使用 RememberMe 功能时会返回一个 RememberMe Cookie, RememberMe Cookie 里面是加密的Principle,
 * 所以如果需要用户信息,则需要从这个Principle找出对应的用户信息(用户名/实体/数据库查询)放到Session里面
 * 3. 在对应的 RememberMe 功能可以访问的页面，添加该filter,当访问这些页面的时候,会自动调用该filter
 * 重新给Session添加数据
 */
public class RememberMeSessionFileter extends AccessControlFilter{
	
	@Lazy
	@Autowired
	private ShiroService shiroService;
	
	@Lazy
	@Autowired
	private JurisdictionService jurisdictionService;
	
	@Lazy
	@Autowired
	private EmployeeService employeeService;
	
	private Cache<String, Deque<Serializable>> cache;
	
	public void setCache(CacheManager cacheManager) {
		this.cache = cacheManager.getCache("shiro-activeSessionCache"); // 操作缓存
	}
	
	//拦截时触发,来自 AdviceFilter
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if(subject.isRemembered()){
			Object principal = subject.getPrincipal();
			Session session = subject.getSession();
			Employee employee = shiroService.login(principal.toString());
			session.setAttribute("employee", employeeService.getEmployeeByEid(employee.geteId())); // 添加用户数据
			List<Jurisdiction> nodes = jurisdictionService.getParentNode();
			session.setAttribute("nodes", nodes); // 添加菜单
		}
		return true;
    }
	
	//允许访问时
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return false;
	}

	//访问拒绝时
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		String username = (String) subject.getPrincipal();
		Deque<Serializable> deque = cache.get(username);
		if(deque == null){
			// 操作缓存
			deque = new LinkedList<Serializable>();  
			cache.put(username, deque);
		}
		return true;
	}

}
