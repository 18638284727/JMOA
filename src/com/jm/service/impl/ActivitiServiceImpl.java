package com.jm.service.impl;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.ParallelGateway;
import org.activiti.bpmn.model.UserTask;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jm.dao.ActivitiDao;
import com.jm.dao.EmployeeDao;
import com.jm.entity.ActivitiModel;
import com.jm.entity.ByteArray;
import com.jm.entity.Description;
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
import com.jm.entity.application.OverTime;
import com.jm.entity.application.Printing;
import com.jm.entity.application.Quit;
import com.jm.entity.application.Recruit;
import com.jm.entity.application.Travle;
import com.jm.service.ActivitiService;
import com.jm.utils.IsInDate;

/**
 * 模型Service接口实现类
 */
@Service
public class ActivitiServiceImpl implements ActivitiService {

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private FormService formService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ManagementService managementService;

	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private ActivitiDao activitiDao;

	@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public String create(ActivitiModel activitiModel) {
		try {
			ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
			RepositoryService repositoryService = processEngine.getRepositoryService();
			ObjectMapper objectMapper = new ObjectMapper();
			// 设置模型的相关信息，可以从前台获取：添加模型的实体类
			Model modelData = repositoryService.newModel();
			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, activitiModel.getName());
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, activitiModel.getVersion());
			String description = activitiModel.getDescription();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, StringUtils.defaultString(description));
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(activitiModel.getName());
			modelData.setKey(StringUtils.defaultString(activitiModel.getKey()));
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			/**
			 * com.fasterxml.jackson.databind.ObjectMapper:用于将JavaBean对象转换为JSON,XML对象,或者将JSON,XML转换为JavaBean对象
			 * StringUtils.defaultString(""); //该方法可以防止空输入字符串
			 */
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
			return modelData.getId();
		} catch (Exception e) {
			System.out.println("创建模型失败");
			return "false";
		}
	}

	@Transactional
	@Override
	public PageInfo<ActivitiModel> modelList(Integer pageNo, Integer pageSize) {
		PageInfo<ActivitiModel> pageInfo = new PageInfo<ActivitiModel>(); // 简单分页
		List<ActivitiModel> modelList = new ArrayList<ActivitiModel>(); // 数据集合
		Integer count = countModel(); // 总条数
		try {
			List<Model> modelList1 = repositoryService.createModelQuery().listPage((pageNo - 1) * pageSize, pageSize);
			if (modelList1 != null && modelList1.size() > 0) {
				for (Model model : modelList1) {
					ActivitiModel activitiModel = new ActivitiModel();
					activitiModel.setId(model.getId());
					activitiModel.setCreateTime(model.getCreateTime());
					Description description = new ObjectMapper().readValue(model.getMetaInfo(), Description.class);
					// 字符串取出来，使用ObjectMapper对象转换为Description对象并设置为ActivitiModel对象的一个属性
					activitiModel.setDescription(description.getDescription());
					activitiModel.setKey(model.getKey());
					activitiModel.setLastUpdateTime(model.getLastUpdateTime());
					activitiModel.setName(model.getName());
					activitiModel.setVersion(model.getVersion());
					modelList.add(activitiModel);
				}
			}
			if (count % pageSize == 0) {
				pageInfo.setPages(count / pageSize);
			} else {
				pageInfo.setPages(count / pageSize + 1);
			}
			pageInfo.setList(modelList);
			pageInfo.setTotal(count);
			pageInfo.setPageNum(pageNo);
			pageInfo.setPageSize(pageSize);
			List<Integer> nums = new ArrayList<Integer>();
			for (int i = 1; i <= pageInfo.getPages(); i++) {
				nums.add(i);
			}
			pageInfo.setPageNums(nums);
			return pageInfo;
		} catch (Exception e) {
			System.out.println("获取模型失败");
			return null;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Integer countModel() {
		return activitiDao.countModel();
	}

	@Transactional
	@Override
	public void deleteModel(String id) {
		repositoryService.deleteModel(id);
	}

	@Transactional
	@Override
	public String deploy(String modelId) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			Model modelData = repositoryService.getModel(modelId);
			ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
			byte[] bpmnBytes = null;
			BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
			//验证bpmnModel 是否是正确的bpmn xml文件
			ProcessValidatorFactory processValidatorFactory=new ProcessValidatorFactory();
			ProcessValidator defaultProcessValidator = processValidatorFactory.createDefaultProcessValidator();
			//验证失败信息的封装ValidationError
			List<ValidationError> validate = defaultProcessValidator.validate(model);
			System.out.println(validate.size() == 0 ? "模型正确" : "模型错误");
			bpmnBytes = new BpmnXMLConverter().convertToXML(model);
			String string = new String(bpmnBytes, "UTF-8");
			String processName = modelData.getName() + ".bpmn20.xml";
			//部署流程后，根据部署编号查询出对应的源文件及图片
			Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(string.getBytes())).deploy();
			if (deployment != null && deployment.getId() != null) {
				List<ByteArray> byteArrays = getByteArrayByDeploymentId(deployment.getId());
				for (ByteArray byteArray : byteArrays) {
					byte[] source = (byte[]) byteArray.getBytes();
					String name = byteArray.getName();
					InputStream is = new ByteArrayInputStream(source);
					OutputStream os = null;
					if(name.indexOf(".xml") > 0){
						os = new FileOutputStream(request.getSession().getServletContext().getRealPath("/activiti/xml/" + name));
					}else if(name.indexOf(".png") > 0){
						os = new FileOutputStream(request.getSession().getServletContext().getRealPath("/activiti/images/" + name));
					}
					System.out.println(request.getSession().getServletContext().getRealPath("/activiti/"));
					int len = 0;
					byte[] bytes = new byte[1024];
					while ((len = is.read(bytes)) != -1) {
						os.write(bytes, 0, len);
					}
					os.flush();
					os.close();
					is.close();
				}
				updateProcessName(modelId, deployment.getId());
			}
			return "true";
		} catch (Exception e) {
			// 需要根据异常向前台返回数据，用来判断流程图模型哪里出问题了，需要重新画
			// XMLException异常：标识线的sourceRef和targetRef属性出错
			e.printStackTrace();
			return "false";
		}
	}

	@Override
	public List<ByteArray> getByteArrayByDeploymentId(String deploymentId) {
		return activitiDao.getByteArrayByDeploymentId(deploymentId);
	}
	
	@Override
	public Integer updateProcessName(String modelId, String deploymentId) {
		return activitiDao.updateProcessName(modelId, deploymentId);
	}
	
	@Transactional(readOnly = true)
	@Override
	public PageInfo<ProcessModel> processList(Integer pageNo, Integer pageSize, String name) {
		PageInfo<ProcessModel> pageInfo = new PageInfo<ProcessModel>();
		List<ProcessModel> processList = new ArrayList<ProcessModel>();
		List<ProcessDefinition> processList1 = null;
		Integer count = null;
		if(null == name){
			processList1 = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().desc().listPage((pageNo - 1) * pageSize, pageSize);
			count = countProcess(null);
		}else{
			processList1 = repositoryService.createProcessDefinitionQuery().processDefinitionName(name).orderByProcessDefinitionVersion().desc().listPage((pageNo - 1) * pageSize, pageSize);
			count = countProcess(name);
		}
		for (ProcessDefinition pro : processList1) {
			ProcessModel processModel = new ProcessModel();
			processModel.setDeploymentId(pro.getDeploymentId());
			processModel.setId(pro.getId());
			processModel.setKey(pro.getKey());
			processModel.setResourceName(pro.getResourceName());
			processModel.setVersion(pro.getVersion());
			processModel.setName(pro.getName());
			processModel.setDiagramResourceName(pro.getDiagramResourceName());
			processList.add(processModel);
		}
		if (count % pageSize == 0) {
			pageInfo.setPages(count / pageSize);
		} else {
			pageInfo.setPages(count / pageSize + 1);
		}
		pageInfo.setList(processList);
		pageInfo.setTotal(count);
		pageInfo.setPageNum(pageNo);
		pageInfo.setPageSize(pageSize);
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 1; i <= pageInfo.getPages(); i++) {
			nums.add(i);
		}
		pageInfo.setPageNums(nums);
		return pageInfo;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Integer countProcess(String name) {
		return activitiDao.countProcess(name);
	}

	@Transactional
	@Override
	public void deleteProcess(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}

	@Transactional(readOnly = true)
	@Override
	public Iterator<FlowElement> findFlow(String processDefId) throws XMLStreamException {
		List<ProcessDefinition> lists = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefId).orderByProcessDefinitionVersion().desc().list();
		ProcessDefinition processDefinition = lists.get(0);
		processDefinition.getCategory();
		String resourceName = processDefinition.getResourceName();
		InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
		BpmnXMLConverter converter = new BpmnXMLConverter();
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(inputStream);
		BpmnModel bpmnModel = converter.convertToBpmnModel(reader);
		org.activiti.bpmn.model.Process process = bpmnModel.getMainProcess();
		Collection<FlowElement> elements = process.getFlowElements();
		Iterator<FlowElement> iterator = elements.iterator();
		return iterator;
	}

	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional
	@Override
	public String startProcess(ProcessModel processModel, Map<String, Object> varables, String follow) throws XMLStreamException {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		if (processModel != null) {
			String processDefId = processModel.getId();
			Iterator<FlowElement> iterator = this.findFlow(processDefId);
			int i = 1;
			while (iterator.hasNext()) {
				FlowElement flowElement = iterator.next();
				if (flowElement.getClass().getSimpleName().equals("UserTask") && i == 1) {
					UserTask userTask = (UserTask) flowElement;
					String assignee = userTask.getAssignee();
					if(assignee == null){
						userTask.setAssignee(employee.geteName());
						varables.put("message", "未提交");
					}
					i++;
				} else if (flowElement.getClass().getSimpleName().equals("UserTask") && i == 2) {
					UserTask userTask = (UserTask) flowElement;
					String assignee = userTask.getAssignee();
					if(assignee == null){
						Integer leader = employee.geteLeader();
						Employee employee2 = employeeDao.getEmployeeByEid(leader);
						varables.put("nextPerson", employee2.geteName()); // 下级处理人
					}else{
						varables.put("nextPerson", assignee);
					}
					break;
				// 并行网关表示UserTask的Assignee为固定，而不是动态设定，并行网关是否需要成对出现？
				}else if (flowElement.getClass().getSimpleName().equals("ParallelGateway") && i == 1){
					varables.put("message", "已处理");
					break;
				}else if (flowElement.getClass().getSimpleName().equals("ParallelGateway") && i == 2){
					break;
				}
			}
			runtimeService.startProcessInstanceById(processDefId, varables); // 根据流程定义的ID启动
			Task task = taskService.createTaskQuery().taskAssignee(employee.geteName()).orderByTaskCreateTime().desc().list().get(0);
			if(follow.equals("已关注")){
				insertFollow(task.getId(), employee.geteName());
			}
			String nodeName = task.getName(); // 节点名称
			String nodeOpinion = (String) varables.get("opinion"); // 节点意见
			if(null == nodeOpinion){
				nodeOpinion = "暂无意见";
			}
			String opinions = nodeName + "---" + nodeOpinion + ",";
			taskService.setVariable(task.getId(), "opinions", opinions);
			
			String state = (String) varables.get("state");
			if(state.equals("提交")){
				Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
				variables.put("message", "通过");
				taskService.complete(task.getId(), variables);
			}
			return "success";
		} else {
			return "fail";
		}
	}

	@Transactional(readOnly = true)
	@Override
	public PageInfo<TaskModel> findTask(Integer pageNo, Integer pageSize, String state) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		ProcessModel processModel = (ProcessModel) request.getSession().getAttribute("processModel");
		PageInfo<TaskModel> pageInfo = new PageInfo<TaskModel>();
		List<Task> lists = taskService.createTaskQuery().taskAssignee(employee.geteName()).processDefinitionId(processModel.getId()).list(); //获取当前的所有任务
		int i1 = 0;
		int i2 = 0;
		for (Task task : lists) {
			Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId()); // 变量获取为null
			if(state.equals("未处理")){
				if(!variables.get("message").equals("未提交")){
					i1++;
				}
			}else{
				if(variables.get("message").equals("未提交")){
					i2++;
				}
			}
		}
		if(state.equals("未处理")){
			if(i1 % pageSize == 0){
				pageInfo.setPages(i1 / pageSize);
			}else{
				pageInfo.setPages(i1 / pageSize + 1);
			}
			pageInfo.setTotal(i1);
		}else{
			if(i2 % pageSize == 0){
				pageInfo.setPages(i2 / pageSize);
			}else{
				pageInfo.setPages(i2 / pageSize + 1);
			}
			pageInfo.setTotal(i2);
		}
		List<TaskModel> taskModels = new ArrayList<TaskModel>();
		if(lists != null && lists.size() > 0){
			for (Task task : lists) {
				Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
				TaskModel taskModel = new TaskModel();
				if(state.equals("未处理")){
					if(!variables.get("message").equals("未提交")){
						taskModel.setApplicant((String) variables.get("applicant"));
						taskModel.setAssignee(task.getAssignee());// 处理人
						taskModel.setCreateTime(task.getCreateTime());// 创建时间
						taskModel.setId(task.getId());// 标识
						taskModel.setName(task.getName());// 当前节点的名称
						taskModel.setProcessInstanceId(task.getProcessInstanceId());// 流程实例Id
						taskModel.setProcessDefinitionId(task.getProcessDefinitionId());// 流程定义id
						taskModel.setTaskDefinitionKey(task.getTaskDefinitionKey());
						taskModel.setVariables(variables);
						taskModels.add(taskModel);
					}
				}else{
					if(variables.get("message").equals("未提交")){
						taskModel.setApplicant((String) variables.get("applicant"));
						taskModel.setAssignee(task.getAssignee());// 处理人
						taskModel.setCreateTime(task.getCreateTime());// 创建时间
						taskModel.setId(task.getId());// 标识
						taskModel.setName(task.getName());// 当前节点的名称
						taskModel.setProcessInstanceId(task.getProcessInstanceId());// 流程实例Id
						taskModel.setProcessDefinitionId(task.getProcessDefinitionId());// 流程定义id
						taskModel.setTaskDefinitionKey(task.getTaskDefinitionKey());
						taskModel.setVariables(variables);
						taskModels.add(taskModel);
					}
				}
			}
		}
		if(pageNo == 1){
			if(pageSize > taskModels.size()){
				pageInfo.setList(taskModels.subList((pageNo - 1) * pageSize, taskModels.size()));
			}else{
				pageInfo.setList(taskModels.subList((pageNo - 1) * pageSize, pageSize));
			}
		}else{
			if(pageSize > taskModels.size()){
				pageInfo.setList(taskModels.subList((pageNo - 1) * pageSize, taskModels.size() + 1));
			}else{
				pageInfo.setList(taskModels.subList((pageNo - 1) * pageSize, pageSize + 1));
			}
		}
		pageInfo.setPageNum(pageNo);
		pageInfo.setPageSize(pageSize);
		List<Integer> nums1 = new ArrayList<Integer>();
		for (int i = 1; i <= pageInfo.getPages(); i++) {
			nums1.add(i);
		}
		pageInfo.setPageNums(nums1);
		return pageInfo;
	}

	@Transactional(readOnly = true)
	@Override
	public PageInfo<TaskModel> searchTaskByCondition(Integer pageNo, Integer pageSize, String state, String startTime, String endTime, String eName, String company, String dept) throws ParseException
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		ProcessModel processModel = (ProcessModel) request.getSession().getAttribute("processModel");
		PageInfo<TaskModel> pageInfo = new PageInfo<TaskModel>();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(employee.geteName()).processDefinitionId(processModel.getId()).list();
		List<TaskModel> taskModels = new ArrayList<TaskModel>();
		if(tasks != null && tasks.size() > 0){
			for (Task task : tasks) {
				Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
				TaskModel taskModel = new TaskModel();
				if(state.equals("未处理")){
					if(!variables.get("message").equals("未提交")){
						if(!startTime.equals("开始时间...") || !endTime.equals("结束时间...")){
							String applyTime = (String) variables.get("applyTime");
							if(!IsInDate.isDate(applyTime, startTime, endTime)){
								continue;
							} 
						}else if(!eName.equals("")){ // 申请人
							if(!variables.get("applicant").equals(eName)){
								continue;
							}
						}else if(!company.equals("0")){ // 公司
							if(!variables.get("applyCompany").equals(company)){
								continue;
							}
						}else if(!dept.equals("0")){ // 部门
							if(!variables.get("applyDept").equals(dept)){
								continue;
							}
						}
						taskModel.setApplicant((String) variables.get("applicant"));
						taskModel.setAssignee(task.getAssignee());// 处理人
						taskModel.setCreateTime(task.getCreateTime());// 创建时间
						taskModel.setId(task.getId());// 标识
						taskModel.setName(task.getName());// 当前节点的名称
						taskModel.setProcessInstanceId(task.getProcessInstanceId());// 流程实例Id
						taskModel.setProcessDefinitionId(task.getProcessDefinitionId());// 流程定义id
						taskModel.setTaskDefinitionKey(task.getTaskDefinitionKey());
						taskModel.setVariables(variables);
						taskModels.add(taskModel);
					}
				}else if(state.equals("未提交")){
					if(variables.get("message").equals("未提交")){
						if(!startTime.equals("开始时间...") || !endTime.equals("结束时间...")){
							String applyTime = (String) variables.get("applyTime");
							if(!IsInDate.isDate(applyTime, startTime, endTime)){
								continue;
							} 
						}
						taskModel.setApplicant((String) variables.get("applicant"));
						taskModel.setAssignee(task.getAssignee());// 处理人
						taskModel.setCreateTime(task.getCreateTime());// 创建时间
						taskModel.setId(task.getId());// 标识
						taskModel.setName(task.getName());// 当前节点的名称
						taskModel.setProcessInstanceId(task.getProcessInstanceId());// 流程实例Id
						taskModel.setProcessDefinitionId(task.getProcessDefinitionId());// 流程定义id
						taskModel.setTaskDefinitionKey(task.getTaskDefinitionKey());
						taskModel.setVariables(variables);
						taskModels.add(taskModel);
					}
				}
			}
		}
		if(taskModels.size() % pageSize == 0){
			pageInfo.setPages(taskModels.size() / pageSize);
		}else{
			pageInfo.setPages(taskModels.size() / pageSize + 1);
		}
		if(pageNo == 1){
			if(pageSize > taskModels.size()){
				pageInfo.setList(taskModels.subList((pageNo - 1) * pageSize, taskModels.size()));
			}else{
				pageInfo.setList(taskModels.subList((pageNo - 1) * pageSize, pageSize));
			}
		}else{
			if(pageSize > taskModels.size()){
				pageInfo.setList(taskModels.subList((pageNo - 1) * pageSize, taskModels.size() + 1));
			}else{
				pageInfo.setList(taskModels.subList((pageNo - 1) * pageSize, pageSize + 1));
			}
		}
		pageInfo.setTotal(taskModels.size());
		pageInfo.setPageNum(pageNo);
		pageInfo.setPageSize(pageSize);
		List<Integer> nums1 = new ArrayList<Integer>();
		for (int i = 1; i <= pageInfo.getPages(); i++) {
			nums1.add(i);
		}
		pageInfo.setPageNums(nums1);
		return pageInfo;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Integer countTask(String assignee) {
		return activitiDao.countTask(assignee);
	}

	/**
	 * 查询当前任务的下级线名称集合
	 * @param taskId
	 * @return
	 */
	public List<String> findOutComeListByTaskId(String taskId) {
        List<String> list = new ArrayList<String>();
        Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
        String processDefinitionId = task.getProcessDefinitionId();
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) processEngine.getRepositoryService().getProcessDefinition(processDefinitionId);
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance pi = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ActivityImpl activityImpl = processDefinitionEntity.findActivity(pi.getActivityId());
        List<PvmTransition> pvmList = activityImpl.getOutgoingTransitions();
        if (pvmList != null && pvmList.size() > 0) {
            for (PvmTransition pvm : pvmList) {
                String name = (String) pvm.getProperty("name");
                if (name != null) {
                    list.add(name);
                }
            }
        }
        return list;
    }
	
	@SuppressWarnings("unused")
	@Transactional
	@Override
	public String completeTask(String taskId, String text, String opinion) throws XMLStreamException {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
		Set<String> keysSet = variables.keySet();
		Iterator<String> keySet = keysSet.iterator();
		String assignee = task.getAssignee();
		List<String> findOutComeListByTaskId = findOutComeListByTaskId(taskId);
		if(null != variables){
			while (keySet.hasNext()) {
				String key = keySet.next();
				if(assignee.equals(variables.get(key))){
					Iterator<FlowElement> iterator = this.findFlow(task.getProcessDefinitionId());
					while (iterator.hasNext()) {
						FlowElement flowElement = iterator.next();
						String classNames = flowElement.getClass().getSimpleName();
						if (classNames.equals("UserTask")){
							UserTask userTask = (UserTask) flowElement;
							String assginee11 = userTask.getAssignee();
							if(assignee.equals(assginee11)){
								FlowElement flowElement2 = iterator.next();
								String classNames1 = flowElement2.getClass().getSimpleName();
								if(classNames1.equals("EndEvent")){
									text = "完成";
								}
							}
						}
					}
				}
			}
			if(text.equals("同意")){
				variables.put("message", "通过");
			}else if(text.equals("退回")){
				variables.put("message", "驳回");
				variables.put("opinion", opinion);
			}
			taskService.complete(taskId, variables);
			return "success";
		}else{
			return "fail";
		}
	}

	@SuppressWarnings("unused")
	@Transactional
	@Override
	public String completeTask2(String taskId, String text, String opinion, String tableData1) throws XMLStreamException
	{
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
		Set<String> keysSet = variables.keySet();
		Iterator<String> keySet = keysSet.iterator();
		String assignee = task.getAssignee();
		List<String> findOutComeListByTaskId = findOutComeListByTaskId(taskId);
		if(null != variables){
			while (keySet.hasNext()) {
				String key = keySet.next();
				if(assignee.equals(variables.get(key))){
					Iterator<FlowElement> iterator = this.findFlow(task.getProcessDefinitionId());
					while (iterator.hasNext()) {
						FlowElement flowElement = iterator.next();
						String classNames = flowElement.getClass().getSimpleName();
						if (classNames.equals("UserTask")){
							UserTask userTask = (UserTask) flowElement;
							String assginee11 = userTask.getAssignee();
							if(assignee.equals(assginee11)){
								FlowElement flowElement2 = iterator.next();
								String classNames1 = flowElement2.getClass().getSimpleName();
								if(classNames1.equals("EndEvent")){
									text = "完成";
								}
							}
						}
					}
				}
			}
			if(text.equals("同意")){
				variables.put("message", "通过");
			}else if(text.equals("退回")){
				variables.put("message", "驳回");
				variables.put("opinion", opinion);
			}
			variables.put("tableData1", tableData1);
			taskService.complete(taskId, variables);
			return "success";
		}else{
			return "fail";
		}
	}
	
	@SuppressWarnings("unused")
	@Transactional
	@Override
	public String completeAllTask(String taskId, String text, Data data)
	{
		String temp = "";
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
			Set<String> keysSet = variables.keySet();
			Iterator<String> keySet = keysSet.iterator();
			String assignee = task.getAssignee();
			List<String> findOutComeListByTaskId = findOutComeListByTaskId(taskId);
			if(null != variables){
				while (keySet.hasNext()) {
					String key = keySet.next();
					if(!assignee.equals(variables.get(key))){
						Iterator<FlowElement> iterator = this.findFlow(task.getProcessDefinitionId());
						while (iterator.hasNext()) {
							FlowElement flowElement = iterator.next();
							String classNames = flowElement.getClass().getSimpleName();
							if (classNames.equals("UserTask")){
								UserTask userTask = (UserTask) flowElement;
								String assginee11 = userTask.getAssignee();
								if(assignee.equals(assginee11)){
									FlowElement flowElement2 = iterator.next();
									String classNames1 = flowElement2.getClass().getSimpleName();
									if(classNames1.equals("EndEvent") && text.equals("同意")){
										temp = "完成";
									}else{
										if(findOutComeListByTaskId.size() > 0){
											String string = findOutComeListByTaskId.get(0);
											if(string.equals("完成") && !text.equals("退回")){
												temp = "完成";
											}
										}
									}
								}
							}
						}
					}
				}
				if(text.equals("同意")){
					variables.put("message", "通过");
				}else if(text.equals("退回")){ 
					variables.put("message", "驳回");
				}
				if(temp.equals("完成")){
					variables.put("message", "完成");
				}
				Field[] fields = data.getClass().getDeclaredFields();
				for (Field field : fields) {
					String fieldName = field.getName();
					fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Method m = data.getClass().getMethod("get" + fieldName);
					String value = (String) m.invoke(data);
					if(null != value){
						variables.put(field.getName(), value);
						if(text.equals("退回") && field.getName().equals("condition")){ // 待处理
							variables.put("condition", 0);
						}
					}
				}
				String nodeName = task.getName(); // 节点名称
				String nodeOpinion = (String) variables.get("opinion"); // 节点意见
				String opinions1 = nodeName + "---" + nodeOpinion + ",";
				String opinions2 = (String) variables.get("opinions");
				opinions2 = opinions2 + opinions1;
				variables.put("opinions", opinions2);
				taskService.complete(taskId, variables);
				if(text.equals("退回")){
					List<Task> lists = taskService.createTaskQuery().taskAssignee(task.getAssignee()).orderByTaskCreateTime().desc().list();
					if(lists != null && lists.size() > 0){
						Task newTask = lists.get(0); // 获取自动提交的任务
						runtimeService.deleteProcessInstance(newTask.getProcessInstanceId(), "deleted"); //删除自动提交的任务
						historyService.deleteHistoricTaskInstance(newTask.getId()); // 删除历史自动提交的任务
					}
				}
				return "success";
			}else{
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@SuppressWarnings("unused")
	@Transactional
	@Override
	public String completeTask3(String taskId, String text, String opinion, Correction correction) throws XMLStreamException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
		Set<String> keysSet = variables.keySet();
		Iterator<String> keySet = keysSet.iterator();
		String assignee = task.getAssignee();
		List<String> findOutComeListByTaskId = findOutComeListByTaskId(taskId);
		if(null != variables){
			while (keySet.hasNext()) {
				String key = keySet.next();
				if(assignee.equals(variables.get(key))){
					Iterator<FlowElement> iterator = this.findFlow(task.getProcessDefinitionId());
					while (iterator.hasNext()) {
						FlowElement flowElement = iterator.next();
						String classNames = flowElement.getClass().getSimpleName();
						if (classNames.equals("UserTask")){
							UserTask userTask = (UserTask) flowElement;
							String assginee11 = userTask.getAssignee();
							if(assignee.equals(assginee11)){
								FlowElement flowElement2 = iterator.next();
								String classNames1 = flowElement2.getClass().getSimpleName();
								if(classNames1.equals("EndEvent")){
									text = "完成";
								}
							}
						}
					}
				}
			}
			if(text.equals("同意")){
				variables.put("message", "通过");
				Field[] fields = correction.getClass().getDeclaredFields();
				for (Field field : fields) {
					String name = field.getName();
					name = name.substring(0, 1).toUpperCase() + name.substring(1);
					Method m = correction.getClass().getMethod("get" + name);
					String value = (String) m.invoke(correction);
					if(null != value){
						variables.put(field.getName(), value);
					}
				}
			}else if(text.equals("退回")){
				variables.put("message", "驳回");
				variables.put("opinion", opinion);
			}
			
			taskService.complete(taskId, variables);
			return "success";
		}else{
			return "fail";
		}
	}
	
	@SuppressWarnings("unused")
	@Transactional
	@Override
	public String completeTaskExpression(String taskId, String text, String condition, String opinion) throws XMLStreamException
	{
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
		Set<String> keysSet = variables.keySet();
		Iterator<String> keySet = keysSet.iterator();
		String assignee = task.getAssignee();
		if(null != variables){
			while (keySet.hasNext()) {
				String key = keySet.next();
				if(assignee.equals(variables.get(key))){
					Iterator<FlowElement> iterator = this.findFlow(task.getProcessDefinitionId());
					while (iterator.hasNext()) {
						FlowElement flowElement = iterator.next();
						String classNames = flowElement.getClass().getSimpleName();
						if (classNames.equals("UserTask")){
							UserTask userTask = (UserTask) flowElement;
							String assginee11 = userTask.getAssignee();
							if(assignee.equals(assginee11)){
								FlowElement flowElement2 = iterator.next();
								String classNames1 = flowElement2.getClass().getSimpleName();
								if(classNames1.equals("EndEvent")){
									text = "完成";
								}
							}
						}
					}
				}
			}
			if(text.equals("同意")){
				if(!condition.equals("")){
					variables.put("money", condition);
				}
				variables.put("message", "通过");
			}else if(text.equals("退回")){
				variables.put("message", "驳回");
				variables.put("opinion", opinion);
			}else{
				variables.put("message", "完成");
			}
			taskService.complete(taskId, variables);
			return "success";
		}else{
			return "fail";
		}
	}
	
	@Transactional
	@Override
	public String deleteTask(String taskId) {
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			if(null != task.getProcessInstanceId()){
				runtimeService.deleteProcessInstance(task.getProcessInstanceId(), "deleted");
				return "success";
			}
			return "fail";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@Transactional
	@Override
	public String updateTask(String taskId, Leave leave){
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", leave.getApply());
			taskService.setVariable(task.getId(), "startTime", leave.getStartTime1() + " " + leave.getStartTime2());
			taskService.setVariable(task.getId(), "endTime", leave.getEndTime1() + " " + leave.getEndTime2());
			taskService.setVariable(task.getId(), "numberOfDays", leave.getNumberOfDays());
			taskService.setVariable(task.getId(), "inlineRadioOptions1", leave.getInlineRadioOptions1());
			taskService.setVariable(task.getId(), "inlineRadioOptions1", leave.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "enclosure", leave.getEnclosure());
			taskService.setVariable(task.getId(), "causeContent", leave.getCauseContent());
			taskService.setVariable(task.getId(), "message", leave.getMessage());
			taskService.setVariable(task.getId(), "applicant", leave.getApplicant());
			taskService.setVariable(task.getId(), "nextPerson", leave.getNextPerson());
			taskService.setVariable(task.getId(), "state", leave.getState());
			taskService.setVariable(task.getId(), "opinion", leave.getOpinion());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@Transactional
	@Override
	public synchronized String submitTask(String taskId, String follow)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
			Set<String> keysSet = variables.keySet();
			Iterator<String> keySet = keysSet.iterator();
			String assignee = task.getAssignee();
			while (keySet.hasNext()) {
				String key = keySet.next();
				if(assignee.equals(variables.get(key))){
					Iterator<FlowElement> iterator = this.findFlow(task.getProcessDefinitionId());
					int i = 1;
					while (iterator.hasNext()) {
						FlowElement flowElement = iterator.next();
						if (flowElement.getClass().getSimpleName().equals("UserTask") && i == 1) {
							UserTask userTask = (UserTask) flowElement;
							String assignee1 = userTask.getAssignee();
							if(assignee.equals(assignee1)){
							}
							i++;
						} else if (flowElement.getClass().getSimpleName().equals("UserTask") && i == 2) {
							UserTask userTask = (UserTask) flowElement;
							String assignee2 = userTask.getAssignee();
							if(assignee2 == null){
								variables.put("nextPerson", variables.get("nextPerson")); // 下级处理人
							}else{
								variables.put("nextPerson", assignee2);
							}
							break;
						}else if(flowElement.getClass().getSimpleName().equals("ParallelGateway") && i == 2){ // 网关处理
							i++;
						}
					}
				}
			}
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			if(follow.equals("关注")){
				deleteFollow(taskId);
			}else if(follow.equals("已关注")){
				insertFollow(taskId, employee.geteName());
			}
			variables.put("message", "通过");
			taskService.complete(taskId, variables);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@Override
	public void deleteFollow(String taskId) {
		activitiDao.deleteFollow(taskId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProcessModel> getProcessModel()
	{
		return activitiDao.getProcessModel();
	}
	
	@Transactional
	@Override
	public String deleteHisTask(String hisTaskId, String message)
	{
		try {
			if(null != hisTaskId){
				String hisexecutionId = selectHisExecutionIdByTaskId(hisTaskId);
				if(message.equals("未提交")){
					String executionId = selectExecutionIdByTaskId(hisTaskId);
					deleteVariables(executionId);
					deleteTaskInstance(hisTaskId);
				}
				deleteHisVariables(hisexecutionId);
				historyService.deleteHistoricTaskInstance(hisTaskId);
				return "success";
			}else{
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@Transactional(readOnly = true)
	@Override
	public String selectHisExecutionIdByTaskId(String hisTaskId)
	{
		return activitiDao.selectHisExecutionIdByTaskId(hisTaskId);
	}
	
	@Transactional(readOnly = true)
	@Override
	public String selectExecutionIdByTaskId(String hisTaskId)
	{
		return activitiDao.selectExecutionIdByTaskId(hisTaskId);
	}
	
	@Transactional
	@Override
	public void deleteVariables(String executionId)
	{
		activitiDao.deleteVariables(executionId);
	}
	
	@Transactional
	@Override
	public void deleteHisVariables(String executionId)
	{
		activitiDao.deleteHisVariables(executionId);
	}
	
	@Transactional
	@Override
	public void deleteTaskInstance(String taskId)
	{
		activitiDao.deleteTaskInstance(taskId);
	}
	
	@Transactional
	@Override
	public List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity, List<HistoricActivityInstance> historicActivityInstances) {
		List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
		for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
			ActivityImpl activityImpl = processDefinitionEntity.findActivity(historicActivityInstances.get(i).getActivityId());// 得到节点定义的详细信息
			List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
			ActivityImpl sameActivityImpl1 = processDefinitionEntity.findActivity(historicActivityInstances.get(i + 1).getActivityId());
			// 将后面第一个节点放在时间相同节点的集合里
			sameStartTimeNodes.add(sameActivityImpl1);
			for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
				HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);// 后续第一个节点
				HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);// 后续第二个节点
				if (activityImpl1.getStartTime().equals(activityImpl2.getStartTime())) {
					// 如果第一个节点和第二个节点开始时间相同保存
					ActivityImpl sameActivityImpl2 = processDefinitionEntity.findActivity(activityImpl2.getActivityId());
					sameStartTimeNodes.add(sameActivityImpl2);
				} else {
					// 有不相同跳出循环
					break;
				}
			}
			
			List<PvmTransition> pvmOutTransitions = activityImpl.getOutgoingTransitions();// 取出节点的所有出去的线
			List<PvmTransition> pvmInTransitions = activityImpl.getIncomingTransitions();
			for (PvmTransition pvmTransition : pvmOutTransitions) {
				// 对所有的线进行遍历
				ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
				if(pvmActivityImpl.getProperties().get("type").equals("parallelGateway")){
					highFlows.add(pvmTransition.getId());
				}
				// 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
				if (sameStartTimeNodes.contains(pvmActivityImpl)) {
					highFlows.add(pvmTransition.getId());
				}
			}
		}
		return highFlows;
	}
	
	@Transactional
	@Override
	public void hisImage1(HttpServletResponse response, String processInstanceId)throws Exception {
		// 获取历史流程实例 act_hi_procinst
		HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		// 获取流程图
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
		ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
		Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
		ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
		ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());
		//环节历史 act_gi_actinst
		List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
		// 高亮环节id集合
		List<String> highLightedActivitis = new ArrayList<String>();
		// 高亮线路id集合
		List<String> highLightedFlows = getHighLightedFlows(definitionEntity, highLightedActivitList);
		//查看历史任务的流程图
		for (HistoricActivityInstance tempActivity : highLightedActivitList) {
			String activityId = tempActivity.getActivityId();
			highLightedActivitis.add(activityId);
		}
		// 当前流程实例执行到哪个节点
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();// 执行实例
		highLightedActivitis.add(execution.getActivityId());
		// 中文显示的是口口口，设置字体就好了
		InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis, highLightedFlows, "宋体", "宋体", "宋体", null, 1.0);
		// 输出资源内容到相应对象
		byte[] b = new byte[1024];
		int len;
		while ((len = imageStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}
	
	@Transactional
	@Override
	public void hisImage2(HttpServletResponse response, String processInstanceId)throws Exception {
		// 获取历史流程实例 act_hi_procinst
		HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		// 获取流程图
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
		ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
		Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
		ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
		ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

		//环节历史 act_gi_actinst
		List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
		// 高亮环节id集合
		List<String> highLightedActivitis = new ArrayList<String>();
		// 高亮线路id集合
		List<String> highLightedFlows = getHighLightedFlows(definitionEntity, highLightedActivitList);
		//查看历史任务的流程图
		for (HistoricActivityInstance tempActivity : highLightedActivitList) {
			String activityId = tempActivity.getActivityId();
			highLightedActivitis.add(activityId);
		}
		// 当前流程实例执行到哪个节点
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();// 执行实例
		if(null != execution){
			highLightedActivitis.add(execution.getActivityId());
		}
		// 中文显示的是口口口，设置字体就好了
		InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis, highLightedFlows, "宋体", "宋体", "宋体", null, 1.0);
		// 输出资源内容到相应对象
		byte[] b = new byte[1024];
		int len;
		while ((len = imageStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<String> getProcdefName() {
		return activitiDao.getProcdefName();
	}
	
	@Transactional
	@Override
	public String updateTask2(String taskId, OverTime overTime)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", overTime.getApply());
			taskService.setVariable(task.getId(), "startTime", overTime.getStartTime1() + " " + overTime.getStartTime2());
			taskService.setVariable(task.getId(), "endTime", overTime.getEndTime1() + " " + overTime.getEndTime2());
			taskService.setVariable(task.getId(), "numberOfDays", overTime.getNumberOfDays());
			taskService.setVariable(task.getId(), "inlineRadioOptions1", overTime.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "causeContent", overTime.getCauseContent());
			taskService.setVariable(task.getId(), "message", overTime.getMessage());
			taskService.setVariable(task.getId(), "applicant", overTime.getApplicant());
			taskService.setVariable(task.getId(), "nextPerson", overTime.getNextPerson());
			taskService.setVariable(task.getId(), "state", overTime.getState());
			taskService.setVariable(task.getId(), "opinion", overTime.getOpinion());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@Override
	public List<String> getProdefIdByName(String name)
	{
		return activitiDao.getProdefIdByName(name);
	}
	
	@Override
	public PageInfo<HistoryModel> hisTask(String assignee, Integer pageNo, String processDefinedId, int pageSize) {
		List<HistoryModel> historyModels = new ArrayList<HistoryModel>();
		List<HistoricTaskInstance> hisTaskList1 = historyService.createHistoricTaskInstanceQuery().processDefinitionId(processDefinedId).taskAssignee(assignee).list();
		for (HistoricTaskInstance hisTask : hisTaskList1) 
		{
			HistoryModel historyModel = new HistoryModel();
			historyModel.setId(hisTask.getId());
			historyModel.setName(hisTask.getName());
			historyModel.setAssignee(hisTask.getAssignee());
			historyModel.setStartTime(hisTask.getStartTime());
			historyModel.setEndTime(hisTask.getEndTime());
			historyModel.setProcessInstanceId(hisTask.getProcessInstanceId());
			List<HistoricVariableInstance> hisList = historyService.createHistoricVariableInstanceQuery().processInstanceId(hisTask.getProcessInstanceId()).list();
			Map<String, Object> hisVariables = new HashMap<String, Object>();
			for(HistoricVariableInstance historicVariableInstance : hisList){
				hisVariables.put(historicVariableInstance.getVariableName(), historicVariableInstance.getValue());
			}
			historyModel.setHisVariable(hisVariables);
			historyModels.add(historyModel);
		}
		
//		PageInfo<TaskModel> findTask = findTask(1, Integer.MAX_VALUE, "未提交");
//		List<TaskModel> list = findTask.getList();
//		for (TaskModel taskModel : list) {
//			for(HistoryModel historyModel : historyModels){
//				String processDefinitionId = taskModel.getProcessDefinitionId();
//				List<HistoricTaskInstance> list2 = historyService.createHistoricTaskInstanceQuery().processDefinitionId(processDefinitionId).taskAssignee(assignee).unfinished().list();
//				for (HistoricTaskInstance hisTask2 : list2) {
//					HistoryModel historyModel2 = new HistoryModel();
//					historyModel2.setId(hisTask2.getId());
//					historyModel2.setName(hisTask2.getName());
//					historyModel2.setAssignee(hisTask2.getAssignee());
//					historyModel2.setStartTime(hisTask2.getStartTime());
//					historyModel2.setEndTime(hisTask2.getEndTime());
//					historyModel2.setProcessInstanceId(hisTask2.getProcessInstanceId());
//					List<HistoricVariableInstance> list3 = historyService.createHistoricVariableInstanceQuery().processInstanceId(hisTask2.getProcessInstanceId()).list();
//					Map<String, Object> hisVariables = new HashMap<String, Object>();
//					for(HistoricVariableInstance historicVariableInstance : list3){
//						hisVariables.put(historicVariableInstance.getVariableName(), historicVariableInstance.getValue());
//					}
//					historyModel2.setHisVariable(hisVariables);
//					historyModels.add(historyModel);
//				}
//			}
//		}
//		
		PageInfo<HistoryModel> pageInfo = new PageInfo<HistoryModel>();
		if (historyModels.size() % pageSize == 0) {
			pageInfo.setPages(historyModels.size() / pageSize);
		} else {
			pageInfo.setPages(historyModels.size() / pageSize + 1);
		}
		
		pageInfo.setList(historyModels.subList((pageNo - 1) * pageSize, (pageNo - 1) * pageSize + pageSize > historyModels.size() ? historyModels.size() : (pageNo - 1) * pageSize + pageSize));
		
		pageInfo.setTotal(historyModels.size());
		pageInfo.setPageNum(pageNo);
		pageInfo.setPageSize(pageSize);
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 1; i <= pageInfo.getPages(); i++) {
			nums.add(i);
		}
		pageInfo.setPageNums(nums);
		return pageInfo;
	}
	
	@Transactional(readOnly = true)
	@Override
	public PageInfo<HistoryModel> hisTaskByCondition(String assignee, Integer pageNo, String processDefinedId, int pageSize, String stime, String etime)
	{
		List<HistoryModel> historyModels = new ArrayList<HistoryModel>();
		List<HistoricTaskInstance> hisTaskList1 = historyService.createHistoricTaskInstanceQuery().processDefinitionId(processDefinedId).taskAssignee(assignee).list();
		for (HistoricTaskInstance hisTask : hisTaskList1) 
		{
			HistoryModel historyModel = new HistoryModel();
			historyModel.setId(hisTask.getId());
			historyModel.setName(hisTask.getName());
			historyModel.setAssignee(hisTask.getAssignee());
			historyModel.setStartTime(hisTask.getStartTime());
			historyModel.setEndTime(hisTask.getEndTime());
			historyModel.setProcessInstanceId(hisTask.getProcessInstanceId());
			List<HistoricVariableInstance> hisList = historyService.createHistoricVariableInstanceQuery().processInstanceId(hisTask.getProcessInstanceId()).list();
			Map<String, Object> hisVariables = new HashMap<String, Object>();
			for(HistoricVariableInstance historicVariableInstance : hisList){
				hisVariables.put(historicVariableInstance.getVariableName(), historicVariableInstance.getValue());
			}
			String date = (String) hisVariables.get("applyTime");
			try {
				boolean bool = IsInDate.isDate(date, stime, etime);
				if(!bool){
					continue;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			historyModel.setHisVariable(hisVariables);
			historyModels.add(historyModel);
		}
		
		PageInfo<HistoryModel> pageInfo = new PageInfo<HistoryModel>();
		if (historyModels.size() % pageSize == 0) {
			pageInfo.setPages(historyModels.size() / pageSize);
		} else {
			pageInfo.setPages(historyModels.size() / pageSize + 1);
		}
		
		pageInfo.setList(historyModels.subList((pageNo - 1) * pageSize, (pageNo - 1) * pageSize + pageSize > historyModels.size() ? historyModels.size() : (pageNo - 1) * pageSize + pageSize));
		
		pageInfo.setTotal(historyModels.size());
		pageInfo.setPageNum(pageNo);
		pageInfo.setPageSize(pageSize);
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 1; i <= pageInfo.getPages(); i++) {
			nums.add(i);
		}
		pageInfo.setPageNums(nums);
		return pageInfo;
	}
	
	@Transactional
	@Override
	public String updateTask3(String taskId, Card card, Employee employee)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", card.getApply());
			taskService.setVariable(task.getId(), "inlineRadioOptions2", card.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "name", card.getName());
			taskService.setVariable(task.getId(), "company", card.getCompany());
			taskService.setVariable(task.getId(), "dept", card.getDept());
			taskService.setVariable(task.getId(), "manager", card.getManager());
			taskService.setVariable(task.getId(), "phone", card.getPhone());
			taskService.setVariable(task.getId(), "tel", card.getTel());
			taskService.setVariable(task.getId(), "fax", card.getFax());
			taskService.setVariable(task.getId(), "email", card.getEmail());
			taskService.setVariable(task.getId(), "message", card.getMessage());
			taskService.setVariable(task.getId(), "applicant", card.getApplicant());
			taskService.setVariable(task.getId(), "nextPerson", card.getNextPerson());
			taskService.setVariable(task.getId(), "state", card.getState());
			taskService.setVariable(task.getId(), "opinion", card.getOpinion());
			taskService.setVariable(task.getId(), "applyCompany", employee.getDept().getCompany().getcName());
			taskService.setVariable(task.getId(), "applyDept", employee.getDept().getdName());
			taskService.setVariable(task.getId(), "applyManager", employee.geteManager());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@Override
	@Transactional
	public String updateTask4(String taskId, Assets assets, Employee employee)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", assets.getApply());
			taskService.setVariable(task.getId(), "inlineRadioOptions2", assets.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "message", assets.getMessage());
			taskService.setVariable(task.getId(), "aname", assets.getAname());
			taskService.setVariable(task.getId(), "cause", assets.getCause());
			taskService.setVariable(task.getId(), "applicant", assets.getApplicant());
			taskService.setVariable(task.getId(), "nextPerson", assets.getNextPerson());
			taskService.setVariable(task.getId(), "state", assets.getState());
			taskService.setVariable(task.getId(), "opinion", assets.getOpinion());
			taskService.setVariable(task.getId(), "applyCompany", employee.getDept().getCompany().getcName());
			taskService.setVariable(task.getId(), "applyDept", employee.getDept().getdName());
			taskService.setVariable(task.getId(), "applyManager", employee.geteManager());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@Override
	public String updateAssignee(String taskId, String assignee) {
		try {
			activitiDao.updateAssignee(taskId, assignee);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@Transactional
	@Override
	public String updateTask5(String taskId, Travle travle, Employee employee)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", travle.getApply());
			taskService.setVariable(task.getId(), "startTime", travle.getStartTime1() + " " + travle.getStartTime2());
			taskService.setVariable(task.getId(), "endTime", travle.getEndTime1() + " " + travle.getEndTime2());
			taskService.setVariable(task.getId(), "numberOfDays", travle.getNumberOfDays());
			taskService.setVariable(task.getId(), "inlineRadioOptions1", travle.getInlineRadioOptions1());
			taskService.setVariable(task.getId(), "inlineRadioOptions2", travle.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "money", travle.getMoney());
			taskService.setVariable(task.getId(), "causeContent", travle.getCauseContent());
			taskService.setVariable(task.getId(), "project", travle.getProject());
			taskService.setVariable(task.getId(), "customer", travle.getCustomer());
			taskService.setVariable(task.getId(), "contract", travle.getContract());
			taskService.setVariable(task.getId(), "tableData", travle.getTableData());
			taskService.setVariable(task.getId(), "tableData1", travle.getTableData1());
			taskService.setVariable(task.getId(), "tableData2", travle.getTableData2());
			taskService.setVariable(task.getId(), "tableData3", travle.getTableData3());
			taskService.setVariable(task.getId(), "applicant", employee.geteName());
			taskService.setVariable(task.getId(), "nextPerson", travle.getNextPerson());
			taskService.setVariable(task.getId(), "state", travle.getState());
			taskService.setVariable(task.getId(), "opinion", travle.getOpinion());
			taskService.setVariable(task.getId(), "applyCompany", employee.getDept().getCompany().getcName());
			taskService.setVariable(task.getId(), "applyDept", employee.getDept().getdName());
			taskService.setVariable(task.getId(), "applyManager", employee.geteManager());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@Transactional
	@Override
	public String updateTask6(String taskId, Excess excess, Employee employee)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", excess.getApply());
			taskService.setVariable(task.getId(), "inlineRadioOptions2", excess.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "region", excess.getRegion());
			taskService.setVariable(task.getId(), "monthlyTask", excess.getMonthlyTask());
			taskService.setVariable(task.getId(), "monthlyReturn", excess.getMonthlyReturn());
			taskService.setVariable(task.getId(), "completionRetio", excess.getCompletionRetio());
			taskService.setVariable(task.getId(), "subsidy", excess.getSubsidy());
			taskService.setVariable(task.getId(), "daysOfTravel", excess.getDaysOfTravel());
			taskService.setVariable(task.getId(), "subsidizedAmouny", excess.getSubsidizedAmouny());
			taskService.setVariable(task.getId(), "applicant", employee.geteName());
			taskService.setVariable(task.getId(), "nextPerson", excess.getNextPerson());
			taskService.setVariable(task.getId(), "state", excess.getState());
			taskService.setVariable(task.getId(), "opinion", excess.getOpinion());
			taskService.setVariable(task.getId(), "applyCompany", employee.getDept().getCompany().getcName());
			taskService.setVariable(task.getId(), "applyDept", employee.getDept().getdName());
			taskService.setVariable(task.getId(), "applyManager", employee.geteManager());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@Transactional
	@Override
	public String updateTask7(String taskId, Car car, Employee employee)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", car.getApply());
			taskService.setVariable(task.getId(), "inlineRadioOptions2", car.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "startTime", car.getStartTime1() + " " + car.getStartTime2());
			taskService.setVariable(task.getId(), "endTime", car.getEndTime1() + " " + car.getEndTime2());
			taskService.setVariable(task.getId(), "numberOfDays", car.getNumberOfDays());
			taskService.setVariable(task.getId(), "inlineRadioOptions1", car.getInlineRadioOptions1());
			taskService.setVariable(task.getId(), "Cf", car.getCf());
			taskService.setVariable(task.getId(), "Md", car.getMd());
			taskService.setVariable(task.getId(), "Num", car.getNum());
			taskService.setVariable(task.getId(), "causeContent", car.getCauseContent());
			taskService.setVariable(task.getId(), "message", car.getMessage());
			taskService.setVariable(task.getId(), "applicant", employee.geteName());
			taskService.setVariable(task.getId(), "nextPerson", car.getNextPerson());
			taskService.setVariable(task.getId(), "state", car.getState());
			taskService.setVariable(task.getId(), "opinion", car.getOpinion());
			taskService.setVariable(task.getId(), "applyCompany", employee.getDept().getCompany().getcName());
			taskService.setVariable(task.getId(), "applyDept", employee.getDept().getdName());
			taskService.setVariable(task.getId(), "applyManager", employee.geteManager());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	
	@Transactional
	@Override
	public String updateTask8(String taskId, Recruit recruit, Employee employee)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", recruit.getApply());
			taskService.setVariable(task.getId(), "inlineRadioOptions2", recruit.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "inlineRadioOptions1", recruit.getInlineRadioOptions1());
			taskService.setVariable(task.getId(), "rdept", recruit.getRdept());
			taskService.setVariable(task.getId(), "rjob", recruit.getRjob());
			taskService.setVariable(task.getId(), "rcount", recruit.getRcount());
			taskService.setVariable(task.getId(), "organization", recruit.getOrganization());
			taskService.setVariable(task.getId(), "numberOfPeople", recruit.getNumberOfPeople());
			taskService.setVariable(task.getId(), "rdate", recruit.getRdate());
			taskService.setVariable(task.getId(), "gender", recruit.getGender());
			taskService.setVariable(task.getId(), "age", recruit.getAge());
			taskService.setVariable(task.getId(), "marriage", recruit.getMarriage());
			taskService.setVariable(task.getId(), "education", recruit.getEducation());
			taskService.setVariable(task.getId(), "major", recruit.getMajor());
			taskService.setVariable(task.getId(), "experience", recruit.getExperience());
			taskService.setVariable(task.getId(), "explain", recruit.getExplain());
			taskService.setVariable(task.getId(), "duty", recruit.getDuty());
			taskService.setVariable(task.getId(), "message", recruit.getMessage());
			taskService.setVariable(task.getId(), "applicant", employee.geteName());
			taskService.setVariable(task.getId(), "state", recruit.getState());
			taskService.setVariable(task.getId(), "opinion", recruit.getOpinion());
			taskService.setVariable(task.getId(), "applyCompany", employee.getDept().getCompany().getcName());
			taskService.setVariable(task.getId(), "applyDept", employee.getDept().getdName());
			taskService.setVariable(task.getId(), "applyManager", employee.geteManager());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@Transactional
	@Override
	public String updateTask9(String taskId, Gift gift, Employee employee)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", gift.getApply());
			taskService.setVariable(task.getId(), "inlineRadioOptions2", gift.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "customer", gift.getCustomer());
			taskService.setVariable(task.getId(), "manager", gift.getManager());
			taskService.setVariable(task.getId(), "phone", gift.getPhone());
			taskService.setVariable(task.getId(), "money", gift.getMoney());
			taskService.setVariable(task.getId(), "causeContent", gift.getCauseContent());
			taskService.setVariable(task.getId(), "tableData", gift.getTableData());
			taskService.setVariable(task.getId(), "message", gift.getMessage());
			taskService.setVariable(task.getId(), "applicant", employee.geteName());
			taskService.setVariable(task.getId(), "state", gift.getState());
			taskService.setVariable(task.getId(), "opinion", gift.getOpinion());
			taskService.setVariable(task.getId(), "applyCompany", employee.getDept().getCompany().getcName());
			taskService.setVariable(task.getId(), "applyDept", employee.getDept().getdName());
			taskService.setVariable(task.getId(), "applyManager", employee.geteManager());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@Transactional
	@Override
	public String updateTask10(String taskId, Quit quit, Employee employee)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", quit.getApply());
			taskService.setVariable(task.getId(), "inlineRadioOptions1", quit.getInlineRadioOptions1());
			taskService.setVariable(task.getId(), "inlineRadioOptions2", quit.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "hiredate", quit.getHiredate());
			taskService.setVariable(task.getId(), "quitdate", quit.getQuitdate());
			taskService.setVariable(task.getId(), "reason", quit.getReason());
			taskService.setVariable(task.getId(), "explain", quit.getExplain());
			taskService.setVariable(task.getId(), "tableData", quit.getTableData());
			taskService.setVariable(task.getId(), "tableData1", quit.getTableData1());
			taskService.setVariable(task.getId(), "message", quit.getMessage());
			taskService.setVariable(task.getId(), "applicant", employee.geteName());
			taskService.setVariable(task.getId(), "state", quit.getState());
			taskService.setVariable(task.getId(), "opinion", quit.getOpinion());
			taskService.setVariable(task.getId(), "applyCompany", employee.getDept().getCompany().getcName());
			taskService.setVariable(task.getId(), "applyDept", employee.getDept().getdName());
			taskService.setVariable(task.getId(), "applyManager", employee.geteManager());
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}
	
	@Transactional
	@Override
	public String updateTask11(String taskId, Chapter chapter, Employee employee)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", chapter.getApply());
			taskService.setVariable(task.getId(), "inlineRadioOptions1", chapter.getInlineRadioOptions1());
			taskService.setVariable(task.getId(), "inlineRadioOptions2", chapter.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "startTime1", chapter.getStartTime1());
			taskService.setVariable(task.getId(), "endTime1", chapter.getEndTime1());
			taskService.setVariable(task.getId(), "days", chapter.getDays());
			taskService.setVariable(task.getId(), "tableData", chapter.getTableData());
			taskService.setVariable(task.getId(), "message", chapter.getMessage());
			taskService.setVariable(task.getId(), "applicant", employee.geteName());
			taskService.setVariable(task.getId(), "state", chapter.getState());
			taskService.setVariable(task.getId(), "opinion", chapter.getOpinion());
			taskService.setVariable(task.getId(), "applyCompany", employee.getDept().getCompany().getcName());
			taskService.setVariable(task.getId(), "applyDept", employee.getDept().getdName());
			taskService.setVariable(task.getId(), "applyManager", employee.geteManager());
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}
	
	@Transactional
	@Override
	public String updateTask12(String taskId, Printing printing, Employee employee)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", printing.getApply());
			taskService.setVariable(task.getId(), "inlineRadioOptions2", printing.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "remark", printing.getRemark());
			taskService.setVariable(task.getId(), "tableData", printing.getTableData());
			taskService.setVariable(task.getId(), "tableData1", printing.getTableData1());
			taskService.setVariable(task.getId(), "message", printing.getMessage());
			taskService.setVariable(task.getId(), "applicant", employee.geteName());
			taskService.setVariable(task.getId(), "state", printing.getState());
			taskService.setVariable(task.getId(), "opinion", printing.getOpinion());
			taskService.setVariable(task.getId(), "applyCompany", employee.getDept().getCompany().getcName());
			taskService.setVariable(task.getId(), "applyDept", employee.getDept().getdName());
			taskService.setVariable(task.getId(), "applyManager", employee.geteManager());
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}
	
	@Transactional
	@Override
	public String updateTask13(String taskId, Correction correction, Employee employee)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", correction.getApply());
			taskService.setVariable(task.getId(), "inlineRadioOptions1", correction.getInlineRadioOptions1());
			taskService.setVariable(task.getId(), "inlineRadioOptions2", correction.getInlineRadioOptions2());
			taskService.setVariable(task.getId(), "startTime1", correction.getStartTime1());
			taskService.setVariable(task.getId(), "endTime1", correction.getEndTime1());
			taskService.setVariable(task.getId(), "trialPost", correction.getTrialPost());
			taskService.setVariable(task.getId(), "probationSalary", correction.getProbationSalary());
			taskService.setVariable(task.getId(), "expectationSalary", correction.getExpectationSalary());
			taskService.setVariable(task.getId(), "selfEvaluation", correction.getSelfEvaluation());
			taskService.setVariable(task.getId(), "tableData", correction.getTableData());
			taskService.setVariable(task.getId(), "tableData1", correction.getTableData1());
			
			taskService.setVariable(task.getId(), "inlineRadioOptions3", correction.getInlineRadioOptions3());
			taskService.setVariable(task.getId(), "inlineRadioOptions4", correction.getInlineRadioOptions4());
			taskService.setVariable(task.getId(), "post", correction.getPost());
			taskService.setVariable(task.getId(), "standard", correction.getStandard());
			taskService.setVariable(task.getId(), "executionDate", correction.getExecutionDate());
			taskService.setVariable(task.getId(), "deptOpinion", correction.getDeptOpinion());
			
			
			taskService.setVariable(task.getId(), "message", correction.getMessage());
			taskService.setVariable(task.getId(), "applicant", employee.geteName());
			taskService.setVariable(task.getId(), "state", correction.getState());
			taskService.setVariable(task.getId(), "opinion", correction.getOpinion());
			taskService.setVariable(task.getId(), "applyCompany", employee.getDept().getCompany().getcName());
			taskService.setVariable(task.getId(), "applyDept", employee.getDept().getdName());
			taskService.setVariable(task.getId(), "applyManager", employee.geteManager());
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}
	
	@Transactional
	@Override
	public String updateAllTask(String taskId, Data data, Employee employee)
	{
		try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			taskService.setVariable(task.getId(), "applyTime", data.getApply());
			
			taskService.setVariable(task.getId(), "inLineOptions1", data.getInLineOptions1());
			taskService.setVariable(task.getId(), "inLineOptions2", data.getInLineOptions2());
			taskService.setVariable(task.getId(), "inLineOptions3", data.getInLineOptions3());
			taskService.setVariable(task.getId(), "inLineOptions4", data.getInLineOptions4());
			
			Field[] fields = data.getClass().getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method method = data.getClass().getMethod("get" + fieldName);
				if(-1 < fieldName.indexOf("Reserve")){
					String value = (String) method.invoke(data);
					taskService.setVariable(task.getId(), field.getName(), value);
				}
			}
			
			taskService.setVariable(task.getId(), "tableData1", data.getTableData1());
			taskService.setVariable(task.getId(), "tableData2", data.getTableData2());
			taskService.setVariable(task.getId(), "tableData3", data.getTableData3());
			taskService.setVariable(task.getId(), "tableData4", data.getTableData4());
			
			taskService.setVariable(task.getId(), "message", data.getMessage());
			taskService.setVariable(task.getId(), "applicant", employee.geteName());
			taskService.setVariable(task.getId(), "state", data.getState());
			taskService.setVariable(task.getId(), "opinion", data.getOpinion());
			taskService.setVariable(task.getId(), "condition", data.getCondition());
			taskService.setVariable(task.getId(), "applyCompany", employee.getDept().getCompany().getcName());
			taskService.setVariable(task.getId(), "applyDept", employee.getDept().getdName());
			taskService.setVariable(task.getId(), "applyManager", employee.geteManager());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@Override
	public Integer insertFollow(String taskId, String eName) {
		return activitiDao.insertFollow(taskId, eName);
	}

	@Transactional(readOnly = true)
	@Override
	public Integer checkFollowTask(String taskId) {
		return activitiDao.checkFollowTask(taskId);
	}
	
}
