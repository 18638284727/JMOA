package com.jm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jm.entity.Company;
import com.jm.entity.Department;
import com.jm.entity.Employee;
import com.jm.service.CompanyService;
import com.jm.service.DepartmentService;
import com.jm.service.EmployeeService;

@Controller
@RequestMapping(value = "company")
public class CompanyController {

	@Lazy
	@Autowired
	private CompanyService companyService;
	
	@Lazy
	@Autowired
	private EmployeeService employeeService;
	
	@Lazy
	@Autowired
	public DepartmentService departmentService;
	
	@ResponseBody
	@RequestMapping(value = "/getAllCompany", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public List<Company> getAllCompany()
	{
		return companyService.getAllCompany();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getDepartmentByCid", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public List<Department> getDepartmentByCid(@RequestParam(value = "cId") Integer cId)
	{
		return departmentService.getDepartmentByCid(cId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getEmployeeByDid", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public List<Employee> getEmployeeByDid(@RequestParam(value = "dId") Integer dId)
	{
		return employeeService.getEmployeeByDid(dId);
	}
}
