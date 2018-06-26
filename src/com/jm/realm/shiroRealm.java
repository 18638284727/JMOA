package com.jm.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.jm.entity.Employee;
import com.jm.entity.Role;
import com.jm.service.EmployeeService;
import com.jm.service.ShiroService;

/**
 * authenticationRealm: 用来处理登录认证，授权问题
 * AuthorizingRealm 继承 CachingRealm 类, CachingRealm 实现了 CacheManagerAware 接口,所以当前 realm 具有缓存功能
 */
public class shiroRealm extends AuthorizingRealm {

	@Lazy
	@Autowired
	private ShiroService shiroService;
	
	@Lazy
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 继承 AuthorizingRealm 抽象类，实现 doGetAuthenticationInfo 方法，用来处理认证问题
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		Employee employee = shiroService.login(username);
		if (null == employee.getUsername()) {
			throw new UnknownAccountException("用户不存在！！！");
		} else if ("锁定".equals(employee.getState())) {
			throw new LockedAccountException("用户被锁定！！！");
		} else if ("禁用".equals(employee.getState())) {
			throw new AuthenticationException("用户异常！！！");
		}
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("employee", employeeService.getEmployeeByEid(employee.geteId())); // 放入session已备不时之需
		Object principal = employee.getUsername(); // 认证信息
		Object credentials = employee.getPassword(); // 密码
		String realmName = getName(); // 当前realm对象name
		ByteSource credentialsSalt = ByteSource.Util.bytes(employee.getUsername().toString()); // 盐值
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return simpleAuthenticationInfo;
	}

	/**
	 * 继承 AuthorizingRealm 抽象类，实现 doGetAuthorizationInfo 方法，用来处理授权问题
	 * principal : 数据量过大的情况下会导致rememberMe不起作用
	 */
	@SuppressWarnings("unused")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		Object principal = principalCollection.getPrimaryPrincipal(); // 返回的就是 doGetAuthenticationInfo 方法中的 principal(认证信息)
		Employee employee = (Employee) SecurityUtils.getSubject().getSession().getAttribute("employee");
		Set<String> roles = new HashSet<String>(); // 职员角色的集合(数据库查询或认证信息已经带有的角色集合)
		List<String> permissions = new ArrayList<String>(); // 职员角色所拥有的操作集合(数据库查询)
		List<Role> roleList = new ArrayList<Role>();
		if(null != employee){
			Integer eId = employee.geteId();
			roleList = shiroService.getRoleByEid(eId); // 返回角色的集合
		}
		for (Role role : roleList) {
			roles.add(role.getrName()); // 添加角色
			List<String> operations = shiroService.getOperationByRid(role.getrId()); // 根据角色查询操作
			for (String string : operations) {
				permissions.add(string);
			}
		}
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles); // 添加角色
		simpleAuthorizationInfo.addStringPermissions(permissions); // 添加权限
		return simpleAuthorizationInfo;
	}
	
	/**
	    shiro 标签:
	    <shiro:guest></shiro:guest> 游客访问
		 
		<shiro:user></shiro:user> 用户已经通过认证\记住我 登录后显示响应的内容
		 
		<shiro:authenticted></shiro:authenticted> 用户身份验证通过，即 Subjec.login 登录成功 不是记住我登录的
		 
		<shiro:notAuthenticated></shiro:notAuthenticated> 用户未进行身份验证，即没有调用Subject.login进行登录,包括"记住我"也属于未进行身份验证
		 
		<shiro:principal property = "username"/> 显示用户身份信息，默认调用Subjec.getPrincipal()获取，即Primary Principal
		 
		<shiro:hashRole name = "admin"></shiro:hashRole> 如果当前Subject有角色将显示body体内的内容
		 
		<shiro:hasAnyRoles name = "admin,user"></shiro:hasAnyRoles> 如果Subject有任意一个角色(或的关系)将显示body体里的内容
		 
		<shiro:lacksRole name = "admin"></shiro:lacksRole> 如果当前 Subjec没有角色将显示body体内的内容
		 
		<shiro:hashPermission name = "user:create"></shiro:hashPermission> 如果当前Subject有权限将显示body体内容
		 
		<shiro:lacksPermission name = "org:create"></shiro:lacksPermission> 如果当前Subject没有权限将显示body体内容
		
		shiro 注解:
		@RequiresAuthenthentication:表示当前Subject已经通过login进行身份验证;即 Subjec.isAuthenticated()返回 true
 
		@RequiresUser:表示当前Subject已经身份验证或者通过记住我登录的
		 
		@RequiresGuest:表示当前Subject没有身份验证或者通过记住我登录过，即是游客身份
		 
		@RequiresRoles(value = {"admin","user"},logical = Logical.AND):表示当前Subject需要角色admin和user
		 
		@RequiresPermissions(value = {"user:delete","user:b"},logical = Logical.OR):表示当前Subject需要权限user:delete或者user:b
	 */

	/**
	 * Git：
	 * 1. $ git add 'file' 把文件添加到缓存区
	 * 2. $ git cimmit -m 'file' 把文件提交到仓库
	 * 3. $ git config --global user.name 'Github上注册的账号'
	 * 4. $ git config --global user.email 'Github上注册的邮箱'
	 * 5. $ git config --list 查看配置
	 * 6. $ git init 初始化(生成一个 .git 的隐藏文件)
	 */
	
}
