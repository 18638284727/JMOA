package com.jm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jm.entity.Employee;
import com.jm.entity.Jurisdiction;
import com.jm.service.EmployeeService;
import com.jm.service.JurisdictionService;

@Controller
@RequestMapping(value = "employee")
public class EmployeeController {

	@Lazy
	@Autowired
	private EmployeeService employeeService;
	
	@Lazy
	@Autowired
	private JurisdictionService jurisdictionService;
	
	@ResponseBody
	@RequestMapping(value = "checkUsername", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Integer checkUsername(@RequestParam(value = "logname") String username)
	{
		return employeeService.checkUsername(username);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String login(@RequestParam(value = "logname") String username, @RequestParam(value = "password") String password, 
			HttpSession session, @RequestParam(value = "savesid", required = false, defaultValue = "false") String savesId)
	{
		Subject subject = SecurityUtils.getSubject();
		if(!subject.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			if(savesId.equals("true")){ // 记住我
				token.setRememberMe(true);
			}
			try {
				subject.login(token);
				List<Jurisdiction> nodes = jurisdictionService.getParentNode();
				session.setAttribute("nodes", nodes);
			} catch (UnknownAccountException uae){
				uae.printStackTrace();
			} catch (LockedAccountException lae){
				lae.printStackTrace();
			} catch (AuthenticationException ae) {
				ae.printStackTrace();
			}
		}
		return "redirect:/home.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value = "entry", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Integer entry(Employee employee)
	{
		return employeeService.entry(employee);
	}
	
	@ResponseBody
	@RequestMapping(value = "getEmployeeByEid", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Employee getEmployeeByEid(@RequestParam(value = "eId") Integer eId)
	{
		return employeeService.getEmployeeByEid(eId);
	}
}
