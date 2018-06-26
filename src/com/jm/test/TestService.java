package com.jm.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jm.entity.ActivitiModel;
import com.jm.entity.ByteArray;
import com.jm.entity.Company;
import com.jm.entity.Department;
import com.jm.entity.Employee;
import com.jm.entity.Jurisdiction;
import com.jm.entity.NoticeBulletin;
import com.jm.entity.ProcessModel;
import com.jm.service.ActivitiService;
import com.jm.service.CompanyService;
import com.jm.service.DepartmentService;
import com.jm.service.EmployeeService;
import com.jm.service.JurisdictionService;
import com.jm.service.NoticeBulletinService;
import com.jm.service.RoleService;
import com.jm.service.ShiroService;
import com.jm.utils.CipherEncryption;
import com.jm.utils.IsInDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class TestService {

	@Autowired
	private JurisdictionService jurisdictionService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ShiroService shiroService;
	
	@Autowired
	private NoticeBulletinService noticeBulletinService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ActivitiService activitiService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private CompanyService companyService;
	
	@Test
	public void testInsertFollow()
	{
		Integer insertFollow = activitiService.insertFollow("aaaa", "aaa");
		
		System.out.println(insertFollow);
	}
	
	@Test
	public void testSelectExecutionIdByTaskId()
	{
		String selectExecutionIdByTaskId = activitiService.selectExecutionIdByTaskId("952538");
		String selectHisExecutionIdByTaskId = activitiService.selectHisExecutionIdByTaskId("952538");
		activitiService.deleteVariables(selectExecutionIdByTaskId);
		activitiService.deleteHisVariables(selectHisExecutionIdByTaskId);
	}
	
	@Test
	public void testUpdateAssignee()
	{
		String updateAssignee = activitiService.updateAssignee("340025", "aaa");
		System.out.println(updateAssignee);
	}
	
	@Test
	public void testCollection()
	{
		List<Company> allCompany = companyService.getAllCompany();
		System.out.println(allCompany);
	}
	
	@Test
	public void test1()
	{
		Map<String, Object> maps = new HashMap<>();
		maps.put("key1", "value1");
		maps.put("key2", "value2");
		maps.put("key3", "value3");
		maps.put("key4", "value4");
		maps.put("key5", "value5");
		Set<String> set = maps.keySet();
		Iterator<String> iterator = set.iterator();
		synchronized (iterator) {
			while(iterator.hasNext()){
				String string = iterator.next();
				if(string.equals("key3")){
					maps.remove("key3");
				}
			}
		}
		for (Entry<String, Object> entry : maps.entrySet()) {
			System.out.println(entry.getKey() + "----------" + entry.getValue());
		}
	}
	
	public static void main(String[] args) throws ParseException {
	
		boolean date = IsInDate.isDate("2018-04-05", "2018-02-01", "2018-03-31");
		System.out.println(date);
		
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		List<Integer> subList = list.subList(0, 2); // 起始索引标识从哪里开始截取到结束索引(但不包含结束索引)
		System.out.println(subList);
	}
	
	 /**
	 * 该方法主要是将数据进行开平方根，并且进行求和
	 */
	public void test3(int i)
	{
		System.out.println(i);
	}
	
	@Test
	public void test2()
	{
		int m = 100;
		for(int i = 2; i < Math.sqrt(m); i++)
		{
			for(int j = 2 * i; j < m; j+=i)
			{
				test3(j);
			}
		}
	}
	
	@Test
	public void test(){
	    int m = 100;                //TODO:测试的速度为一百条数据
	    int start = 2;              //TODO:定义开始变量为2
	    int n = (int) Math.sqrt(m);//TODO:将该平方根单拎出来使用，使数据不需要每次都调用，求该平方根
	    int lines = 1;              //TODO:定义lines的初始值是1
	    int b = start * 2;          //TODO:定义b的初始值是start的2倍
		
	    while(lines <= (n-2)){      //TODO:定义循环条件，n-2>=1,也就是8>=1,满足条件进入循环，
	        System.out.println(b);	//TODO:打印出每次的结果
	        if((b+lines+1) >= m ){	//TODO:如果满足if条件,即：(b+lines+1)的值>=100,执行if逻辑代码，lines增加1，直到lines>8,跳出循环
	            lines += 1;			
	            b = 2 * (lines+1);	
	        }						
	        else {					
	            b += (lines + 1);	//TODO:否则b的值依次增加2，
	        }
	    }
	}

	
	@Test
	public void testDynamicSQL(){
//		Integer countProcess = activitiService.countProcess("请假申请");
//		System.out.println(countProcess);
		
		PageHelper.startPage(1, 2);
		List<Task> list = taskService.createTaskQuery().taskAssignee("admin").list();
		PageInfo<Task> pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getPageNum());
		System.out.println(pageInfo.getPages());
		System.out.println(pageInfo.getPageSize());
		System.out.println(pageInfo.getTotal());
	}
	
	@Test
	public void testGetByteArrayByDeploymentId()
	{
		List<ByteArray> list = activitiService.getByteArrayByDeploymentId("107501");
		for (ByteArray byteArray : list) {
			System.out.println(byteArray);
		}
	}
	
	@Test
	public void testGetResourceStream() throws Exception
	{
		byte[] bytes = new byte[1024];
		InputStream inputStream = new ByteArrayInputStream(bytes);
		OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\JMOA\\WebContent\\activiti\\images\\aaa.process.png"));
		int len = 0;
		byte[] bytes2 = new byte[1024];
		while((len = inputStream.read(bytes2))!=-1){
			outputStream.write(bytes2, 0, len);
		}
		outputStream.flush();
		inputStream.close();
		outputStream.close();
	}
	
	@Test
	public void testProcessList()
	{
		com.jm.entity.PageInfo<ProcessModel> processList = activitiService.processList(1, 15, "请假申请");
		System.out.println(processList);
	}
	
	@Test
	public void testModelList()
	{
		com.jm.entity.PageInfo<ActivitiModel> pageInfo = activitiService.modelList(1, 15);
		System.out.println(pageInfo);
	}
	
	@Test
	public void testGetDepartmentByDid()
	{
		Department department = departmentService.getDepartmentByDid(1);
		System.out.println(department);
	}
	
	@Test
	public void testGetEmployeeByEid()
	{
		Employee employeeByEid = employeeService.getEmployeeByEid(1);
		System.out.println(employeeByEid);
	}
	
	@Test
	public void testGetOperationByRid()
	{
		List<String> operations = shiroService.getOperationByRid(1);
		for (String string : operations) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testUUID()
	{
		String string = UUID.randomUUID().toString().substring(0, 6);
		System.out.println(string);
	}
	
	@Test
	public void testEntry()
	{
		Employee employee = new Employee();
		employee.seteId(00004);
		employee.setUsername("aaa");
		employee.setPassword("bbb");
		employeeService.entry(employee);
	}
	
	@Test
	public void testCipherEncryption()
	{
		Object string = CipherEncryption.MD5("lisi", "lisi");
		System.out.println(string);
	}
	
	@Test
	public void testGetRoleByJid()
	{
		List<String> roles = roleService.getRoleByJid(1);
		for (String str : roles) {
			System.out.println(str);
		}
	}
	
	@Test
	public void testGetChildrenNode()
	{
		List<Jurisdiction> childrenNode = jurisdictionService.getChildrenNode(1);
		for (Jurisdiction jurisdiction : childrenNode) {
			System.out.println(jurisdiction);
		}
	}
	
	@Test
	public void testGetParentNode()
	{
		List<Jurisdiction> parentNode = jurisdictionService.getParentNode();
		for (Jurisdiction jurisdiction : parentNode) {
			System.out.println(jurisdiction);
		}
	}
	
	@Test
	public void testGetAllJurisdiction()
	{
		List<Jurisdiction> list = jurisdictionService.getAll();
		for (Jurisdiction jurisdiction : list) {
			System.out.println(jurisdiction);
		}
	}
}
