package com.jm.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.XMLStreamException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jm.entity.ActivitiModel;
import com.jm.entity.Employee;
import com.jm.entity.HistoryModel;
import com.jm.entity.PageInfo;
import com.jm.entity.ProcessModel;
import com.jm.entity.TaskModel;
import com.jm.entity.application.Assets;
import com.jm.entity.application.Car;
import com.jm.entity.application.Card;
import com.jm.entity.application.Chapter;
import com.jm.entity.application.Correction;
import com.jm.entity.application.Data;
import com.jm.entity.application.Excess;
import com.jm.entity.application.Gift;
import com.jm.entity.application.Leave;
import com.jm.entity.application.Office;
import com.jm.entity.application.OverTime;
import com.jm.entity.application.Printing;
import com.jm.entity.application.Quit;
import com.jm.entity.application.Recruit;
import com.jm.entity.application.TableData;
import com.jm.entity.application.Travle;
import com.jm.service.ActivitiService;
import com.jm.utils.MoneyUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping(value = "activiti")
@Scope(value = "prototype")
@Controller
public class ActivitiController {

	@Lazy
	@Autowired
	private ActivitiService activitiService;
	
	@ResponseBody
	@RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String create(ActivitiModel activitiModel)
	{
		return activitiService.create(activitiModel);
	}

	@ResponseBody
	@RequestMapping(value = "modelList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageInfo<ActivitiModel> modelList(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo)
	{
		return activitiService.modelList(pageNo, 15);
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteModel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public void deleteModel(@RequestParam(value = "id") String id)
	{
		activitiService.deleteModel(id);
	} 
	
	@ResponseBody
	@RequestMapping(value = "deployModel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String deployModel(@RequestParam(value = "id") String id)
	{
		return activitiService.deploy(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "processList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageInfo<ProcessModel> processList(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, 
			@RequestParam(value = "name", required = false) String name)
	{
		if("".equals(name) || null == name){
			name = null;
		}
		return activitiService.processList(pageNo, 5, name);
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteProcess", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public void deleteProcess(@RequestParam(value = "deploymentId") String deploymentId)
	{
		activitiService.deleteProcess(deploymentId);
	}
	
	//页面分发
	@RequestMapping(value = "pageJump", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String pageJump(ProcessModel processModel, HttpSession session)
	{
		String viewName = "";
		if(processModel != null){
			session.setAttribute("processModel", processModel);
			if(processModel.getName().equals("请假申请")){
				viewName = "leave.jsp";
			}else if(processModel.getName().equals("加班申请")){
				viewName = "overtime.jsp";
			}else if(processModel.getName().equals("礼品申请")){
				viewName = "gift.jsp";
			}else if(processModel.getName().equals("招聘申请")){
				viewName = "recruit.jsp";
			}else if(processModel.getName().equals("用车申请")){
				viewName = "car.jsp";
			}else if(processModel.getName().equals("出差申请")){
				viewName = "travel.jsp";
			}else if(processModel.getName().equals("名片印刷申请")){
				viewName = "card.jsp";
			}else if(processModel.getName().equals("固定资产借出申请")){
				viewName = "assets.jsp";
			}else if(processModel.getName().equals("办公用品申请")){
				viewName = "office.jsp";
			}else if(processModel.getName().equals("超额补贴申请")){
				viewName = "excess.jsp";
			}else if(processModel.getName().equals("离职申请")){
				viewName = "quit.jsp";
			}else if(processModel.getName().equals("用章申请")){
				viewName = "chapter.jsp";
			}else if(processModel.getName().equals("印刷品印刷申请")){
				viewName = "printing.jsp";
			}else if(processModel.getName().equals("员工转正申请")){
				viewName = "correction.jsp";
			}else if(processModel.getName().equals("人事异动申请")){
				viewName = "changeOfPersonal.jsp";
			}else if(processModel.getName().equals("投标保证金申请")){
				viewName = "margin.jsp";
			}else if(processModel.getName().equals("特殊发货申请")){
				viewName = "special.jsp";
			}else if(processModel.getName().equals("业务招待申请")){
				viewName = "business.jsp";
			}else if(processModel.getName().equals("报销管理")){
				viewName = "reimbursement.jsp";
			}else if(processModel.getName().equals("付款管理")){
				viewName = "payment.jsp";
			}else if(processModel.getName().equals("发票管理")){
				viewName = "invoice.jsp";
			}else if(processModel.getName().equals("回款管理")){
				viewName = "returnMoney.jsp";
			}else if(processModel.getName().equals("借款管理")){
				viewName = "lendMoney.jsp";
			}else if(processModel.getName().equals("开户管理")){
				viewName = "account.jsp";
			}else if(processModel.getName().equals("信息费管理")){
				viewName = "informationFee.jsp";
			}
		}
		return "redirect:/views/" + viewName;
	}
	
	@ResponseBody
	@RequestMapping(value = "leaveStartProcess", method = RequestMethod.POST)
	public String leaveStartProcess(HttpSession session, Leave leave, 
			@RequestParam(value = "file", required = false) MultipartFile multipartFile) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		if(multipartFile.getSize() > 0){
			String realPath = session.getServletContext().getRealPath("/activiti/upload/" + multipartFile.getOriginalFilename());
			leave.setEnclosure(multipartFile.getOriginalFilename());
			leave.setApplicant(employee.geteName());
			OutputStream os = null;
			InputStream is = null;
			try {
				os = new FileOutputStream(realPath);
				is = multipartFile.getInputStream();
				byte[] bytes = new byte[1024];
				int len = 0;
				while((len = is.read(bytes)) != -1){
					os.write(bytes, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			} finally {
				try {
					os.flush();
					os.close();
					is.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		Map<String, Object> varables = new LinkedHashMap<String, Object>();
		varables.put("applicant", leave.getApplicant()); // 申请人
		varables.put("applyCompany", employee.getDept().getCompany().getcName()); // 申请人公司
		varables.put("applyDept", employee.getDept().getdName()); // 申请人部门
		varables.put("applyManager", employee.geteManager()); // 申请人职务
		varables.put("applyTime", leave.getApply()); // 申请时间
		varables.put("startTime", leave.getStartTime1() + " " + leave.getStartTime2()); // 开始时间
		varables.put("endTime", leave.getEndTime1() + " " + leave.getEndTime2()); // 结束时间
		varables.put("numberOfDays", leave.getNumberOfDays()); // 请假时长
		varables.put("leaveType", leave.getInlineRadioOptions1()); // 请假类型
		varables.put("emergency", leave.getInlineRadioOptions2()); // 紧急程度
		varables.put("enclosure", leave.getEnclosure()); // 附件
		varables.put("causeContent", leave.getCauseContent()); // 原因
		varables.put("state", leave.getState());
		varables.put("company", employee.getDept().getCompany().getcName()); // company
		varables.put("dept", employee.getDept().getdName()); // dept
		varables.put("opinion", ""); // 意见
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "/findTask", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageInfo<TaskModel> findTask(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "state") String state)
	{
		return activitiService.findTask(pageNo, 12, state);
	}
	
	@ResponseBody
	@RequestMapping(value = "searchTaskByCondition", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageInfo<TaskModel> searchTaskByCondition(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "state") String state, @RequestParam(value = "startTime") String startTime,
			@RequestParam(value = "endTime") String endTime, @RequestParam(value = "eName", required = false) String eName,
			@RequestParam(value = "company", required = false) String company, @RequestParam(value = "dept", required = false) String dept)
	{
		try {
			return activitiService.searchTaskByCondition(pageNo, 12, state, startTime, endTime, eName, company, dept);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/completeTask", method = RequestMethod.POST)
	public String completeTask(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "text") String text, 
			@RequestParam(value = "opinion", required = false) String opinion) throws XMLStreamException
	{
		return activitiService.completeTask(taskId, text, opinion);
	}
	
	@ResponseBody
	@RequestMapping(value = "/completeTask2", method = RequestMethod.POST)
	public String completeTask2(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "text") String text, 
			@RequestParam(value = "opinion", required = false) String opinion, @RequestParam(value = "tableData1") String tableData1) throws XMLStreamException
	{
		return activitiService.completeTask2(taskId, text, opinion, tableData1);
	}
	
	@ResponseBody
	@RequestMapping(value = "/completeTask3", method = RequestMethod.POST)
	public String completeTask3(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "text") String text, 
			@RequestParam(value = "opinion", required = false) String opinion, Correction correction) throws XMLStreamException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		return activitiService.completeTask3(taskId, text, opinion, correction);
	}
	
	@ResponseBody
	@RequestMapping(value = "/completeTaskExpression", method = RequestMethod.POST)
	public String completeTaskExpression(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "text") String text, 
			@RequestParam(value = "condition", required = false) String condition,
			@RequestParam(value = "opinion", required = false) String opinion)
	{
		try {
			return activitiService.completeTaskExpression(taskId, text, condition, opinion);
		} catch (XMLStreamException e) {
			return "fail";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
	public String deleteTask(@RequestParam(value = "taskId") String taskId)
	{
		return activitiService.deleteTask(taskId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateTask", method = RequestMethod.POST)
	public String updateTask(@RequestParam(value = "taskId") String taskId, HttpSession session, Leave leave, 
			@RequestParam(value = "file", required = false) MultipartFile multipartFile)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		leave.setApplicant(employee.geteName());
		if(multipartFile.getSize() > 0){
			leave.setEnclosure(multipartFile.getOriginalFilename());
			String realPath = session.getServletContext().getRealPath("/activiti/upload/" + multipartFile.getOriginalFilename());
			leave.setEnclosure(multipartFile.getOriginalFilename());
			leave.setApplicant(employee.geteName());
			OutputStream os = null;
			InputStream is = null;
			try {
				os = new FileOutputStream(realPath);
				is = multipartFile.getInputStream();
				byte[] bytes = new byte[1024];
				int len = 0;
				while((len = is.read(bytes)) != -1){
					os.write(bytes, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			} finally {
				try {
					os.flush();
					os.close();
					is.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return activitiService.updateTask(taskId, leave);
	}
	
	@ResponseBody
	@RequestMapping(value = "/submitTask", method = RequestMethod.POST)
	public String submitTask(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "follow") String follow)
	{
		return activitiService.submitTask(taskId, follow);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getProdefIdByName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public List<String> getProdefIdByName(@RequestParam(value = "name") String name)
	{
		return activitiService.getProdefIdByName(name);
	}
	
	@ResponseBody
	@RequestMapping(value = "/hisTask", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageInfo<HistoryModel> hisTask2(HttpSession session, 
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "processId") String processDefinedId)
	{
		return activitiService.hisTask(((Employee) session.getAttribute("employee")).geteName(), pageNo, processDefinedId, 8);
	}
	
	@ResponseBody
	@RequestMapping(value = "/hisTaskByCondition", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageInfo<HistoryModel> hisTaskByCondition(HttpSession session, 
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "processId") String processDefinedId,
			@RequestParam(value = "stime") String stime, @RequestParam(value = "etime") String etime)
	{
		return activitiService.hisTaskByCondition(((Employee) session.getAttribute("employee")).geteName(), pageNo, processDefinedId, 8, stime, etime);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getProcessModel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public List<ProcessModel> getProcessModel()
	{
		return activitiService.getProcessModel();
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteHisTask", method = RequestMethod.POST)
	public String deleteHisTask(@RequestParam(value = "hisTaskId") String hisTaskId, @RequestParam(value = "message") String message)
	{
		return activitiService.deleteHisTask(hisTaskId, message);
	}
	
	@RequestMapping(value = "hisImage1")
	public void hisImage1(HttpServletResponse response, String processInstanceId)
	{
		try {
			activitiService.hisImage1(response, processInstanceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "hisImage2")
	public void hisImage2(HttpServletResponse response, String processInstanceId)
	{
		try {
			activitiService.hisImage2(response, processInstanceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "getProcdefName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public List<String> getProcdefName()
	{
		return activitiService.getProcdefName();
	}

	@ResponseBody
	@RequestMapping(value = "overTimeStartProcess", method = RequestMethod.POST)
	public String overTimeStartProcess(OverTime overTime, HttpSession session) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new LinkedHashMap<String, Object>();
		varables.put("applicant", employee.geteName()); // 申请人
		varables.put("applyCompany", employee.getDept().getCompany().getcName()); // 申请人公司
		varables.put("applyDept", employee.getDept().getdName()); // 申请人部门
		varables.put("applyManager", employee.geteManager()); // 申请人职务
		varables.put("applyTime", overTime.getApply()); // 申请时间
		varables.put("startTime", overTime.getStartTime1() + " " + overTime.getStartTime2()); // 开始时间
		varables.put("endTime", overTime.getEndTime1() + " " + overTime.getEndTime2()); // 结束时间
		varables.put("numberOfDays", overTime.getNumberOfDays()); // 请假时长
		varables.put("emergency", overTime.getInlineRadioOptions2()); // 紧急程度
		varables.put("causeContent", overTime.getCauseContent()); // 原因
		varables.put("state", overTime.getState());
		varables.put("company", employee.getDept().getCompany().getcName()); // company
		varables.put("dept", employee.getDept().getdName()); // dept
		varables.put("opinion", ""); // 意见
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "updateTask2", method = RequestMethod.POST)
	public String updateTask2(@RequestParam(value = "taskId") String taskId, OverTime overTime, HttpSession session)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		overTime.setApplicant(employee.geteName());
		return activitiService.updateTask2(taskId, overTime);
	}
	
	@ResponseBody
	@RequestMapping(value = "cardStarProcess", method = RequestMethod.POST)
	public String cardStarProcess(Card card, HttpSession session) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new LinkedHashMap<String, Object>();
		varables.put("applyTime", card.getApply());
		varables.put("inlineRadioOptions2", card.getInlineRadioOptions2());
		varables.put("name", card.getName());
		varables.put("company", card.getCompany());
		varables.put("dept", card.getDept()); 
		varables.put("manager", card.getManager());
		varables.put("phone", card.getPhone());
		varables.put("tel", card.getTel());
		varables.put("fax", card.getFax());
		varables.put("email", card.getEmail());
		varables.put("state", card.getState());
		varables.put("applicant", employee.geteName());
		varables.put("opinion", card.getOpinion());
		varables.put("applyCompany", employee.getDept().getCompany().getcName());
		varables.put("applyDept", employee.getDept().getdName());
		varables.put("applyManager", employee.geteManager());
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "updateTask3", method = RequestMethod.POST)
	public String updateTask3(@RequestParam(value = "taskId") String taskId, Card card, HttpSession session)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		card.setApplicant(employee.geteName());
		return activitiService.updateTask3(taskId, card, employee);
	}
	
	@ResponseBody
	@RequestMapping(value = "assetsStartProcess", method = RequestMethod.POST)
	public String assetsStartProcess(Assets assets, HttpSession session) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new LinkedHashMap<String, Object>();
		varables.put("applyTime", assets.getApply());
		varables.put("inlineRadioOptions2", assets.getInlineRadioOptions2());
		varables.put("aname", assets.getAname());
		varables.put("cause", assets.getCause());
		varables.put("state", assets.getState());
		varables.put("applicant", employee.geteName());
		varables.put("opinion", assets.getOpinion());
		varables.put("applyCompany", employee.getDept().getCompany().getcName());
		varables.put("applyDept", employee.getDept().getdName());
		varables.put("applyManager", employee.geteManager());
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "updateTask4", method = RequestMethod.POST)
	public String updateTask4(@RequestParam(value = "taskId") String taskId, Assets assets, HttpSession session)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		assets.setApplicant(employee.geteName());
		return activitiService.updateTask4(taskId, assets, employee);
	}
	
	@ResponseBody
	@RequestMapping(value = "updateAssignee", method = RequestMethod.POST)
	public String updateAssignee(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "assignee") String assignee)
	{
		return activitiService.updateAssignee(taskId, assignee);
	}
	
	@ResponseBody
	@RequestMapping(value = "officeStartProcess", method = RequestMethod.POST)
	public String officeStartProcess(@RequestParam(value = "tableData", required = false) String tableData,
			@RequestParam(value = "apply", required = false) String apply,
			@RequestParam(value = "inlineRadioOptions2", required = false) String inlineRadioOptions2,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "opinion", required = false) String opinion,
			HttpSession session) throws XMLStreamException
	{
		List<TableData> lists = new ArrayList<TableData>();
		JSONArray fromObject = JSONArray.fromObject(tableData);
		for (Object object : fromObject) {
			JSONObject jsonObject = JSONObject.fromObject(object);
			TableData data = new TableData();
			data.setNum(jsonObject.getInt("Num"));
			data.setName(jsonObject.getString("name"));
			data.setNums(jsonObject.getInt("nums"));
			data.setSpecifications(jsonObject.getString("specifications"));
			data.setPrices(jsonObject.getDouble("prices"));
			data.setPurpose(jsonObject.getString("purpose"));
			data.setRequiredDate(jsonObject.getString("requiredDate"));
			data.setUser(jsonObject.getString("user"));
			data.setRemarks(jsonObject.getString("remarks"));
			lists.add(data);
		}
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new HashMap<String, Object>();
		varables.put("applyTime", apply);
		varables.put("inlineRadioOptions2", inlineRadioOptions2);
		varables.put("state", state);
		varables.put("applicant", employee.geteName());
		varables.put("opinion", opinion);
		varables.put("applyCompany", employee.getDept().getCompany().getcName());
		varables.put("applyDept", employee.getDept().getdName());
		varables.put("applyManager", employee.geteManager());
		varables.put("tableData", lists);
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "sendData", method = RequestMethod.POST)
	public String sendData(@RequestParam(value = "tableDate") String tableDate, HttpSession session)
	{
		session.setAttribute("tableDate", tableDate);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "travelStartProcess", method = RequestMethod.POST)
	public String travelStartProcess(Travle travle, HttpSession session) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new HashMap<String, Object>();
		varables.put("applyTime", travle.getApply());
		varables.put("startTime", travle.getStartTime1() + " " + travle.getStartTime2());
		varables.put("endTime", travle.getEndTime1() + " " + travle.getEndTime2());
		varables.put("numberOfDays", travle.getNumberOfDays());
		varables.put("inlineRadioOptions1", travle.getInlineRadioOptions1());
		varables.put("inlineRadioOptions2", travle.getInlineRadioOptions2());
		varables.put("money", travle.getMoney());
		varables.put("causeContent", travle.getCauseContent());
		varables.put("project", travle.getProject());
		varables.put("customer", travle.getCustomer());
		varables.put("contract", travle.getContract());
		varables.put("tableData", travle.getTableData());
		varables.put("tableData1", travle.getTableData1());
		varables.put("tableData2", travle.getTableData2());
		varables.put("tableData3", travle.getTableData3());
		varables.put("applicant", employee.geteName());
		varables.put("state", travle.getState());
		varables.put("opinion", travle.getOpinion());
		varables.put("applyCompany", employee.getDept().getCompany().getcName());
		varables.put("applyDept", employee.getDept().getdName());
		varables.put("applyManager", employee.geteManager());
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "updateTask5", method = RequestMethod.POST)
	public String updateTask5(@RequestParam(value= "taskId") String taskId, Travle travle, HttpSession session)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		return activitiService.updateTask5(taskId, travle, employee);
	}
	
	@ResponseBody
	@RequestMapping(value = "sendData2", method = RequestMethod.POST)
	public String sendData2(Travle travel, HttpSession session, 
				@RequestParam(value = "processInstanceId") String processInstanceId,
				@RequestParam(value = "id") String id,
				@RequestParam(value = "applyCompany") String applyCompany,
				@RequestParam(value = "applyDept") String applyDept,
				@RequestParam(value = "applyManager") String applyManager)
	{
		session.setAttribute("travel", travel);
		session.setAttribute("processInstanceId", processInstanceId);
		session.setAttribute("id", id);
		session.setAttribute("applyCompany", applyCompany);
		session.setAttribute("applyDept", applyDept);
		session.setAttribute("applyManager", applyManager);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "excessStartProces", method = RequestMethod.POST)
	public String excessStartProces(Excess excess, HttpSession session) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new HashMap<String, Object>();
		varables.put("applyTime", excess.getApply());
		varables.put("inlineRadioOptions2", excess.getInlineRadioOptions2());
		varables.put("region", excess.getRegion());
		varables.put("monthlyTask", excess.getMonthlyTask());
		varables.put("monthlyReturn", excess.getMonthlyReturn());
		varables.put("completionRetio", excess.getCompletionRetio());
		varables.put("subsidy", excess.getSubsidy());
		varables.put("daysOfTravel", excess.getDaysOfTravel());
		varables.put("subsidizedAmouny", excess.getSubsidizedAmouny());
		varables.put("applicant", employee.geteName());
		varables.put("state", excess.getState());
		varables.put("opinion", excess.getOpinion());
		varables.put("applyCompany", employee.getDept().getCompany().getcName());
		varables.put("applyDept", employee.getDept().getdName());
		varables.put("applyManager", employee.geteManager());
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "updateTask6", method = RequestMethod.POST)
	public String updateTask6(@RequestParam(value = "taskId") String taskId, Excess excess, HttpSession session)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		return activitiService.updateTask6(taskId, excess, employee);
	}
	
	@ResponseBody
	@RequestMapping(value = "/carStartProcess", method = RequestMethod.POST)
	public String carStartProcess(HttpSession session, Car car) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new LinkedHashMap<String, Object>();
		varables.put("applicant", car.getApplicant()); 
		varables.put("applyTime", car.getApply()); 
		varables.put("inlineRadioOptions2", car.getInlineRadioOptions2()); 
		varables.put("startTime", car.getStartTime1() + " " + car.getStartTime2()); 
		varables.put("endTime", car.getEndTime1() + " " + car.getEndTime2()); 
		varables.put("numberOfDays", car.getNumberOfDays()); 
		varables.put("inlineRadioOptions1", car.getInlineRadioOptions1()); 
		varables.put("Cf", car.getCf());
		varables.put("Md", car.getMd());
		varables.put("Num", car.getNum());
		varables.put("causeContent", car.getCauseContent()); 
		varables.put("state", car.getState());
		varables.put("opinion", car.getOpinion());
		varables.put("applyCompany", employee.getDept().getCompany().getcName());
		varables.put("applyDept", employee.getDept().getdName());
		varables.put("applyManager", employee.geteManager());
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "updateTask7", method = RequestMethod.POST)
	public String updateTask7(@RequestParam(value = "taskId") String taskId, Car car, HttpSession session)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		return activitiService.updateTask7(taskId, car, employee);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recruitStartProcess", method = RequestMethod.POST)
	public String recruitStartProcess(HttpSession session, Recruit recruit) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new LinkedHashMap<String, Object>();
		varables.put("applyTime", recruit.getApply());
		varables.put("inlineRadioOptions1", recruit.getInlineRadioOptions1());
		varables.put("inlineRadioOptions2", recruit.getInlineRadioOptions2());
		varables.put("rdept", recruit.getRdept());
		varables.put("rjob", recruit.getRjob());
		varables.put("rcount", recruit.getRcount());
		varables.put("organization", recruit.getOrganization());
		varables.put("numberOfPeople", recruit.getNumberOfPeople());
		varables.put("rdate", recruit.getRdate());
		varables.put("gender", recruit.getGender());
		varables.put("age", recruit.getAge());
		varables.put("marriage", recruit.getMarriage());
		varables.put("education", recruit.getEducation());
		varables.put("major", recruit.getMajor());
		varables.put("experience", recruit.getExperience());
		varables.put("explain", recruit.getExplain());
		varables.put("duty", recruit.getDuty());
		varables.put("applicant", employee.geteName());
		varables.put("state", recruit.getState());
		varables.put("opinion", recruit.getOpinion());
		varables.put("applyCompany", employee.getDept().getCompany().getcName());
		varables.put("applyDept", employee.getDept().getdName());
		varables.put("applyManager", employee.geteManager());
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "sendData3", method = RequestMethod.POST)
	public String sendData3(Recruit recruit, HttpSession session, 
			@RequestParam(value = "processInstanceId") String processInstanceId,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "applyCompany") String applyCompany,
			@RequestParam(value = "applyDept") String applyDept,
			@RequestParam(value = "applyManager") String applyManager)
	{
		session.setAttribute("recruit", recruit);
		session.setAttribute("processInstanceId", processInstanceId);
		session.setAttribute("id", id);
		session.setAttribute("applyCompany", applyCompany);
		session.setAttribute("applyDept", applyDept);
		session.setAttribute("applyManager", applyManager);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "updateTask8", method = RequestMethod.POST)
	public String updateTask8(Recruit recruit, HttpSession session,
			@RequestParam(value = "taskId") String taskId)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		return activitiService.updateTask8(taskId, recruit, employee);
	}

	@ResponseBody
	@RequestMapping(value = "/giftStartProcess", method = RequestMethod.POST)
	public String giftStartProcess(HttpSession session, Gift gift) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new LinkedHashMap<String, Object>();
		varables.put("applyTime", gift.getApply());
		varables.put("inlineRadioOptions2", gift.getInlineRadioOptions2());
		varables.put("customer", gift.getCustomer());
		varables.put("manager", gift.getManager());
		varables.put("phone", gift.getPhone());
		varables.put("money", gift.getMoney());
		varables.put("causeContent", gift.getCauseContent());
		varables.put("tableData", gift.getTableData());
		varables.put("applicant", employee.geteName());
		varables.put("state", gift.getState());
		varables.put("opinion", gift.getOpinion());
		varables.put("applyCompany", employee.getDept().getCompany().getcName());
		varables.put("applyDept", employee.getDept().getdName());
		varables.put("applyManager", employee.geteManager());
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateTask9", method = RequestMethod.POST)
	public String updateTask9(Gift gift, HttpSession session,
			@RequestParam(value = "taskId") String taskId)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		return activitiService.updateTask9(taskId, gift, employee);
	}
	
	@ResponseBody
	@RequestMapping(value = "/sendData4", method = RequestMethod.POST)
	public String sendData4(Gift gift, HttpSession session, 
			@RequestParam(value = "processInstanceId") String processInstanceId,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "applyCompany") String applyCompany,
			@RequestParam(value = "applyDept") String applyDept,
			@RequestParam(value = "applyManager") String applyManager)
	{
		session.setAttribute("gift", gift);
		session.setAttribute("processInstanceId", processInstanceId);
		session.setAttribute("id", id);
		session.setAttribute("applyCompany", applyCompany);
		session.setAttribute("applyDept", applyDept);
		session.setAttribute("applyManager", applyManager);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "quitStartProcess", method = RequestMethod.POST)
	public String quitStartProcess(HttpSession session, Quit quit) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new LinkedHashMap<String, Object>();
		varables.put("applyTime", quit.getApply());
		varables.put("inlineRadioOptions1", quit.getInlineRadioOptions1());
		varables.put("inlineRadioOptions2", quit.getInlineRadioOptions2());
		varables.put("hiredate", quit.getHiredate());
		varables.put("quitdate", quit.getQuitdate());
		varables.put("reason", quit.getReason());
		varables.put("explain", quit.getExplain());
		varables.put("tableData", quit.getTableData());
		varables.put("tableData1", quit.getTableData1());
		varables.put("applicant", employee.geteName());
		varables.put("state", quit.getState());
		varables.put("opinion", quit.getOpinion());
		varables.put("applyCompany", employee.getDept().getCompany().getcName());
		varables.put("applyDept", employee.getDept().getdName());
		varables.put("applyManager", employee.geteManager());
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "updateTask10", method = RequestMethod.POST)
	public String updateTask10(Quit quit, HttpSession session,
			@RequestParam(value = "taskId") String taskId)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		return activitiService.updateTask10(taskId, quit, employee);
	}
	
	@ResponseBody
	@RequestMapping(value = "sendData5", method = RequestMethod.POST)
	public String sendData5(Quit quit, HttpSession session, 
			@RequestParam(value = "processInstanceId") String processInstanceId,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "applyCompany") String applyCompany,
			@RequestParam(value = "applyDept") String applyDept,
			@RequestParam(value = "applyManager") String applyManager)
	{
		session.setAttribute("quit", quit);
		session.setAttribute("processInstanceId", processInstanceId);
		session.setAttribute("id", id);
		session.setAttribute("applyCompany", applyCompany);
		session.setAttribute("applyDept", applyDept);
		session.setAttribute("applyManager", applyManager);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "chapterStartProcess", method = RequestMethod.POST)
	public String chapterStartProcess(HttpSession session, Chapter chapter) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new LinkedHashMap<String, Object>();
		varables.put("applyTime", chapter.getApply());
		varables.put("inlineRadioOptions1", chapter.getInlineRadioOptions1());
		varables.put("inlineRadioOptions2", chapter.getInlineRadioOptions2());
		varables.put("startTime1", chapter.getStartTime1());
		varables.put("endTime1", chapter.getEndTime1());
		varables.put("days", chapter.getDays());
		varables.put("tableData", chapter.getTableData());
		varables.put("applicant", employee.geteName());
		varables.put("state", chapter.getState());
		varables.put("opinion", chapter.getOpinion());
		varables.put("applyCompany", employee.getDept().getCompany().getcName());
		varables.put("applyDept", employee.getDept().getdName());
		varables.put("applyManager", employee.geteManager());
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "updateTask11", method = RequestMethod.POST)
	public String updateTask11(HttpSession session, Chapter chapter,
			@RequestParam(value= "taskId") String taskId)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		return activitiService.updateTask11(taskId, chapter, employee);
	}
	
	@ResponseBody
	@RequestMapping(value = "sendData6", method = RequestMethod.POST)
	public String sendData6(Chapter chapter, HttpSession session, 
			@RequestParam(value = "processInstanceId") String processInstanceId,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "applyCompany") String applyCompany,
			@RequestParam(value = "applyDept") String applyDept,
			@RequestParam(value = "applyManager") String applyManager)
	{
		session.setAttribute("chapter", chapter);
		session.setAttribute("processInstanceId", processInstanceId);
		session.setAttribute("id", id);
		session.setAttribute("applyCompany", applyCompany);
		session.setAttribute("applyDept", applyDept);
		session.setAttribute("applyManager", applyManager);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "printingStartProcess", method = RequestMethod.POST)
	public String printingStartProcess(HttpSession session, Printing printing) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new LinkedHashMap<String, Object>();
		varables.put("applyTime", printing.getApply());
		varables.put("inlineRadioOptions2", printing.getInlineRadioOptions2());
		varables.put("remark", printing.getRemark());
		varables.put("tableData", printing.getTableData());
		varables.put("tableData1", printing.getTableData1());
		varables.put("applicant", employee.geteName());
		varables.put("state", printing.getState());
		varables.put("opinion", printing.getOpinion());
		varables.put("applyCompany", employee.getDept().getCompany().getcName());
		varables.put("applyDept", employee.getDept().getdName());
		varables.put("applyManager", employee.geteManager());
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value= "updateTask12", method = RequestMethod.POST)
	public String updateTask12(@RequestParam(value = "taskId") String taskId, HttpSession session, Printing printing)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		return activitiService.updateTask12(taskId, printing, employee);
	}
	
	@ResponseBody
	@RequestMapping(value= "sendData7", method = RequestMethod.POST)
	public String sendData7(Printing printing, HttpSession session, 
			@RequestParam(value = "processInstanceId") String processInstanceId,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "applyCompany") String applyCompany,
			@RequestParam(value = "applyDept") String applyDept,
			@RequestParam(value = "applyManager") String applyManager)
	{
		session.setAttribute("printing", printing);
		session.setAttribute("processInstanceId", processInstanceId);
		session.setAttribute("id", id);
		session.setAttribute("applyCompany", applyCompany);
		session.setAttribute("applyDept", applyDept);
		session.setAttribute("applyManager", applyManager);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "correctionStratProcess", method = RequestMethod.POST)
	public String correctionStratProcess(HttpSession session, Correction correction) throws XMLStreamException
	{
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new LinkedHashMap<String, Object>();
		varables.put("applyTime", correction.getApply());
		varables.put("inlineRadioOptions1", correction.getInlineRadioOptions1());
		varables.put("inlineRadioOptions2", correction.getInlineRadioOptions2());
		varables.put("startTime1", correction.getStartTime1());
		varables.put("endTime1", correction.getEndTime1());
		varables.put("trialPost", correction.getTrialPost());
		varables.put("probationSalary", correction.getProbationSalary());
		varables.put("expectationSalary", correction.getExpectationSalary());
		varables.put("selfEvaluation", correction.getSelfEvaluation());
		varables.put("tableData", correction.getTableData());
		varables.put("tableData1", correction.getTableData1());
		
		varables.put("inlineRadioOptions3", correction.getInlineRadioOptions3());
		varables.put("inlineRadioOptions4", correction.getInlineRadioOptions4());
		varables.put("post", correction.getPost());
		varables.put("standard", correction.getStandard());
		varables.put("executionDate", correction.getExecutionDate());
		varables.put("deptOpinion", correction.getDeptOpinion());
		
		varables.put("applicant", employee.geteName());
		varables.put("state", correction.getState());
		varables.put("opinion", correction.getOpinion());
		varables.put("applyCompany", employee.getDept().getCompany().getcName());
		varables.put("applyDept", employee.getDept().getdName());
		varables.put("applyManager", employee.geteManager());
		return activitiService.startProcess(processModel, varables, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "updateTask13", method = RequestMethod.POST)
	public String updateTask13(@RequestParam(value = "taskId") String taskId, HttpSession session, Correction correction)
	{
		Employee employee = (Employee) session.getAttribute("employee");
		return activitiService.updateTask13(taskId, correction, employee);
	}
	
	@ResponseBody
	@RequestMapping(value = "sendData8", method = RequestMethod.POST)
	public String sendData8(Correction correction, HttpSession session, 
			@RequestParam(value = "processInstanceId") String processInstanceId,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "applyCompany") String applyCompany,
			@RequestParam(value = "applyDept") String applyDept,
			@RequestParam(value = "applyManager") String applyManager)
	{
		session.setAttribute("correction", correction);
		session.setAttribute("processInstanceId", processInstanceId);
		session.setAttribute("id", id);
		session.setAttribute("applyCompany", applyCompany);
		session.setAttribute("applyDept", applyDept);
		session.setAttribute("applyManager", applyManager);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "startProcess", method = RequestMethod.POST)
	public String startProcess(Data data, HttpSession session, @RequestParam(value = "follow") String follow, 
					@RequestParam(value = "fileName") MultipartFile[] multipartFiles) throws XMLStreamException
	{ 
		String fileName = "";
		for (MultipartFile multipartFile : multipartFiles) {
			if(multipartFile.getSize() > 0){
				String realPath = session.getServletContext().getRealPath("/activiti/upload/" + multipartFile.getOriginalFilename());
				OutputStream os = null;
				InputStream is = null;
				try {
					os = new FileOutputStream(realPath);
					is = multipartFile.getInputStream();
					byte[] bytes = new byte[1024];
					int len = 0;
					while((len = is.read(bytes)) != -1){
						os.write(bytes, 0, len);
					}
					fileName = fileName + multipartFile.getOriginalFilename() + ",";
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						os.flush();
						os.close();
						is.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		}
		if(!fileName.equals("")){
			data.setReserve12(fileName.substring(0, fileName.length() - 1));
		}
		ProcessModel processModel = (ProcessModel) session.getAttribute("processModel");
		if(null == processModel){
			String procdefId = (String) session.getAttribute("procdefId");
			String procdefName = (String) session.getAttribute("procdefName");
			processModel = new ProcessModel();
			processModel.setId(procdefId);
			processModel.setName(procdefName);
		}
		Employee employee = (Employee) session.getAttribute("employee");
		Map<String, Object> varables = new LinkedHashMap<String, Object>();
		try {
			Field[] fields = data.getClass().getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method method = data.getClass().getMethod("get" + fieldName);
				if(-1 < fieldName.indexOf("Reserve")){
					String value = (String) method.invoke(data);
					varables.put(field.getName(), value);
				}
			}
			
			varables.put("inLineOptions1", data.getInLineOptions1());
			varables.put("inLineOptions2", data.getInLineOptions2());
			varables.put("inLineOptions3", data.getInLineOptions3());
			varables.put("inLineOptions4", data.getInLineOptions4());
			
			varables.put("tableData1", data.getTableData1());
			varables.put("tableData2", data.getTableData2());
			varables.put("tableData3", data.getTableData3());
			varables.put("tableData4", data.getTableData4());
			
			varables.put("applyTime", data.getApply());
			varables.put("applicant", employee.geteName());
			varables.put("state", data.getState());
			varables.put("opinion", data.getOpinion());
			varables.put("opinions", data.getOpinions());
			varables.put("condition", data.getCondition());
			varables.put("applyCompany", employee.getDept().getCompany().getcName());
			varables.put("applyDept", employee.getDept().getdName());
			varables.put("applyManager", employee.geteManager());
			return activitiService.startProcess(processModel, varables, follow);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "updateAllTask", method = RequestMethod.POST)
	public String updateAllTask(@RequestParam(value = "taskId") String taskId, HttpSession session, Data data,
			@RequestParam(value = "follow") String follow)
	{
		if(follow.equals("关注")){
			activitiService.deleteFollow(taskId);
		}else{
			activitiService.insertFollow(taskId, ((Employee) session.getAttribute("employee")).geteName());
		}
		Employee employee = (Employee) session.getAttribute("employee");
		return activitiService.updateAllTask(taskId, data, employee);
	}
	
	@ResponseBody
	@RequestMapping(value = "sendAllData", method = RequestMethod.POST)
	public String sendAllData(Data data, HttpSession session, @RequestParam(value = "id") String taskId,
			@RequestParam(value = "processInstanceId") String processInstanceId,
			@RequestParam(value = "applyCompany") String applyCompany,
			@RequestParam(value = "applyDept") String applyDept,
			@RequestParam(value = "applyManager") String applyManager,
			@RequestParam(value = "nextPerson") String nextPerson)
	{
		session.setAttribute("data", data);
		session.setAttribute("processInstanceId", processInstanceId);
		session.setAttribute("taskId", taskId);
		session.setAttribute("applyCompany", applyCompany);
		session.setAttribute("applyDept", applyDept);
		session.setAttribute("applyManager", applyManager);
		session.setAttribute("nextPerson", nextPerson);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "completeAllTask", method = RequestMethod.POST)
	public String completeAllTask(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "text") String text, Data data,
			@RequestParam(value = "follow") String follow, HttpSession session) throws Exception
	{
		if(follow.equals("已关注")){
			activitiService.insertFollow(taskId, ((Employee) session.getAttribute("employee")).geteName());
		}else if(follow.equals("关注")){
			activitiService.deleteFollow(taskId);
		}
		return activitiService.completeAllTask(taskId, text, data);
	}
	
	@ResponseBody
	@RequestMapping(value = "sendHistoryAllData", method = RequestMethod.POST)
	public String sendHistoryAllData(Data data, HistoryModel historyModel, HttpSession session, 
			@RequestParam(value = "applyCompany") String applyCompany,
			@RequestParam(value = "applyDept") String applyDept,
			@RequestParam(value = "applyManager") String applyManager,
			@RequestParam(value = "procdefId") String procdefId,
			@RequestParam(value = "name") String name)
	{
		session.setAttribute("data", data);
		session.setAttribute("historyModel", historyModel);
		session.setAttribute("applyCompany", applyCompany);
		session.setAttribute("applyDept", applyDept);
		session.setAttribute("applyManager", applyManager);
		session.setAttribute("procdefId", procdefId);
		session.setAttribute("procdefName", name);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "multiple", method = RequestMethod.POST)
	public String multiple(@RequestParam(value = "fileName") MultipartFile[] files, HttpSession session) throws IOException
	{
		if(files != null && files.length > 0){
			for (MultipartFile multipartFile : files) {
				if(multipartFile != null && multipartFile.getSize() > 0){
					String realPath = session.getServletContext().getRealPath("/activiti/upload/" + multipartFile.getOriginalFilename());
					OutputStream os = null;
					InputStream is = null;
					try {
						os = new FileOutputStream(realPath);
						is = multipartFile.getInputStream();
						byte[] bytes = new byte[1024];
						int len = 0;
						while((len = is.read(bytes)) != -1){
							os.write(bytes, 0, len);
						}
					} catch (Exception e) {
						e.printStackTrace();
						return "fail";
					} finally {
						try {
							os.flush();
							os.close();
							is.close();
						} catch (Exception e2) {
							e2.printStackTrace();
							return "fail";
						}
					}
				}
			}
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value ="checkMoney", method = RequestMethod.POST)
	public String checkMoney(@RequestParam(value = "lowerMoney") String lowerMoney, @RequestParam(value = "upperMoney") String upperMoney) throws Exception
	{
		String digitUppercase = MoneyUtils.digitUppercase(lowerMoney);
		if(digitUppercase.equals(upperMoney)){
			return "true";
		}else{
			return "false";
		}
	}

	@ResponseBody
	@RequestMapping(value = "checkFollowTask", method = RequestMethod.POST)
	public Integer checkFollowTask(String taskId)
	{
		return activitiService.checkFollowTask(taskId);
	}
}