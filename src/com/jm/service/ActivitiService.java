package com.jm.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;

import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.apache.ibatis.annotations.Param;

import com.jm.entity.ActivitiModel;
import com.jm.entity.ByteArray;
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

/**
 * 模型业务逻辑接口
 */
public interface ActivitiService {

	/**
	 * 创建模型
	 * @param activitiModel 模型实体
	 * @return 模型编号
	 */
	public String create(ActivitiModel activitiModel);
	
	/**
	 * 获取模型列表集合,分页实现
	 * @param pageNo 页码
	 * @param pageSize 每页大小
	 * @return 模型集合
	 */
	public PageInfo<ActivitiModel> modelList(Integer pageNo, Integer pageSize);
	
	/**
	 * 获取模型条数
	 * @return 模型条数
	 */
	public Integer countModel();
	
	/**
	 * 根据模型编号删除模型
	 * @param id 模型编号
	 */
	public void deleteModel(String id);
	
	/**
	 * 部署流程模型
	 * @param modelId 模型编号
	 * @return 是否成功
	 */
	public String deploy(String modelId);
	
	/**
	 * 根据部署编号查询二进制表的记录
	 * @param deploymentId 部署编号
	 * @return ByteArray二进制实体类集合
	 */
	public List<ByteArray> getByteArrayByDeploymentId(String deploymentId);
	
	/**
	 * 根据部署编号修改流程定义的名称为指定模型编号的模型的名称
	 * @param modelId 模型编号
	 * @param deploymentId 部署编号
	 * @return 受影响的行数
	 */
	public Integer updateProcessName(String modelId, String deploymentId);
	
	/**
	 * 获取流程定义集合,分页实现,带条件
	 * @param pageNo 页码
	 * @param pageSize 每页大小
	 * @return 流程定义集合
	 */
	public PageInfo<ProcessModel> processList(Integer pageNo, Integer pageSize, String name);
	
	/**
	 * 获取流程定义条数
	 * @return 流程定义条数
	 */
	public Integer countProcess(String name);
	
	/**
	 * 根据部署编号删除流程定义
	 * @param deploymentId 部署编号
	 */
	public void deleteProcess(String deploymentId);
	
	/**
	 * 查询流程节点,读取act_ge-bytearray表中流程部署时所存的xml文件
	 * @param processDefId 流程定义编号
	 * @return 对应的xml文件信息
	 * @throws XMLStreamException
	 */
	public Iterator<FlowElement> findFlow(String processDefId) throws XMLStreamException;
	
	/**
	 * 启动流程
	 * @param processModel 流程实体
	 * @param varables 存储申请人的信息以及申请信息
	 * @return 
	 * @throws XMLStreamException
	 */
	public String startProcess(ProcessModel processModel, Map<String, Object> varables, String follow) throws XMLStreamException;
	
	/**
	 * 任务信息显示
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<TaskModel> findTask(Integer pageNo, Integer pageSize, String state);
	
	/**
	 * 根据条件查询当前登录人的任务
	 * @param pageNo 页码
	 * @param pageSize 每页条数
	 * @param state 状态 分别表示是未提交的查询还是未处理的查询
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param eName 申请人
	 * @param company 公司
	 * @param dept 部门
	 * @return 
	 */
	public PageInfo<TaskModel> searchTaskByCondition(Integer pageNo, Integer pageSize, String state, String startTime, String endTime, String eName, String company, String dept) throws ParseException;
	
	/**
	 * 根据处理人获取任务条数
	 * @param assignee 处理人
	 * @return 任务条数
	 */
	public Integer countTask(String assignee);
	
	/**
	 * 根据text的值决定对任务id为taskId的任务操作(通过/驳回)
	 * @param taskId 任务Id
	 * @param text 同意/退回
	 * @param opinion 意见
	 * @return
	 * @throws XMLStreamException
	 */
	public String completeTask(String taskId, String text, String opinion) throws XMLStreamException;
	
	/**
	 * 根据text的值决定对任务id为taskId的任务操作(通过/驳回)
	 * @param taskId 任务Id
	 * @param text 同意/退回
	 * @param opinion 意见
	 * @param tableData1 变量
	 * @return
	 * @throws XMLStreamException
	 */
	public String completeTask2(String taskId, String text, String opinion, String tableData1) throws XMLStreamException;
	
	/**
	 * 根据text的值决定对任务id为taskId的任务操作(通过/驳回)
	 * @param taskId 任务Id
	 * @param text 同意/退回
	 * @param opinion 意见
	 * @param varables 变量
	 * @return
	 * @throws XMLStreamException
	 */
	public String completeTask3(String taskId, String text, String opinion, Correction correction) 
			throws XMLStreamException, NoSuchMethodException, SecurityException, IllegalAccessException, 
					IllegalArgumentException, InvocationTargetException;
	
	/**
	 * 根据text的值决定对任务id为taskId的任务操作(大于小于)
	 * @param taskId 任务编号
	 * @param text 同意/退回
	 * @param condition 大于小于条件
	 * @param opinion 意见
	 * @return
	 * @throws XMLStreamException
	 */
	public String completeTaskExpression(String taskId, String text, String condition, String opinion) throws XMLStreamException;
	
	/**
	 * 根据任务Id删除任务
	 * @param taskId 任务Id
	 * @return
	 */
	public String deleteTask(String taskId);
	
	/**
	 * 修改任务
	 * @param taskId 任务Id
	 * @param leave 变量实体
	 * @return
	 */
	public String updateTask(String taskId, Leave leave);
	
	/**
	 * 根据任务ID完成任务
	 * @param taskId 任务ID
	 * @return
	 */
	public String submitTask(String taskId, String follow);
	
	/**
	 * 获取所有的流程定义
	 * @return
	 */
	public List<ProcessModel> getProcessModel();
	
	/**
	 * 删除历史任务
	 * @param processInstanceId
	 * @return
	 */
	public String deleteHisTask(String hisTaskId, String message);
	
	public String selectHisExecutionIdByTaskId(String executionId);
	
	public String selectExecutionIdByTaskId(String executionId);
	
	public void deleteVariables(String executionId);
	
	public void deleteTaskInstance(String taskId);
	
	/**
	 * 
	 * @param hisTaskId
	 */
	public void deleteHisVariables(String executionId);
	
	/**
	 * 获取需要高亮的线
	 * @param processDefinitionEntity
	 * @param historicActivityInstances
	 * @return
	 */
	public List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity, List<HistoricActivityInstance> historicActivityInstances);
	
	/**
	 * 高亮显示--执行过程中
	 * @param response
	 * @param processInstanceId
	 * @throws Exception
	 */
	public void hisImage1(HttpServletResponse response, String processInstanceId)throws Exception;
	
	/**
	 * 高亮显示--历史任务
	 * @param response
	 * @param processInstanceId
	 * @throws Exception
	 */
	public void hisImage2(HttpServletResponse response, String processInstanceId)throws Exception;
	
	/**
	 * 获取所有流程定义的名称
	 * @return 名称集合
	 */
	public List<String> getProcdefName();
	
	/**
	 * 修改加班申请流程
	 * @param taskId
	 * @param overTime
	 * @return
	 */
	public String updateTask2(String taskId, OverTime overTime);

	/**
	 * 根据名称查询流程id
	 * @param name
	 * @return
	 */
	public List<String> getProdefIdByName(String name);
	
	/**
	 * 返回历史任务的集合
	 * @param assignee 处理人
	 * @param pageNo 
	 * @param processDefinedId 流程定义的id
	 * @param pageSize
	 * @return
	 */
	public PageInfo<HistoryModel> hisTask(String assignee, Integer pageNo, String processDefinedId, int pageSize);
	
	/**
	 * 返回历史任务的集合
	 * @param assignee 处理人
	 * @param pageNo 
	 * @param processDefinedId 流程定义的id
	 * @param pageSize
	 * @param etime 结束时间
	 * @param stime 开始时间
	 * @return
	 */
	public PageInfo<HistoryModel> hisTaskByCondition(String assignee, Integer pageNo, String processDefinedId, int pageSize, String stime, String etime);
	/**
	 * 修改名片印刷申请流程
	 * @param taskId
	 * @param card 
	 * @return
	 */
	public String updateTask3(String taskId, Card card, Employee employee);
	
	/**
	 * 固定资产借出申请流程
	 * @param taskId
	 * @param assets 
	 * @return
	 */
	public String updateTask4(String taskId, Assets assets, Employee employee);
	
	/**
	 * 修改任务的处理人
	 * @param taskId
	 * @param assignee
	 * @return
	 */
	public String updateAssignee(String taskId, String assignee);
	
	/**
	 * 出差申请修改
	 * @param taskId
	 * @param travle
	 * @return
	 */
	public String updateTask5(String taskId, Travle travle, Employee employee);
	
	/**
	 * 超额补贴申请修改
	 * @param taskId
	 * @param excess
	 * @param employee
	 * @return
	 */
	public String updateTask6(String taskId, Excess excess, Employee employee);
	
	/**
	 * 用车申请修改
	 * @param taskId
	 * @param car
	 * @param employee
	 * @return
	 */
	public String updateTask7(String taskId, Car car, Employee employee);
	
	/**
	 * 招聘申请修改
	 * @param taskId
	 * @param car
	 * @param employee
	 * @return
	 */
	public String updateTask8(String taskId, Recruit recruit, Employee employee);
	
	/**
	 * 礼品申请修改
	 * @param taskId
	 * @param gift
	 * @param employee
	 * @return
	 */
	public String updateTask9(String taskId, Gift gift, Employee employee);
	
	/**
	 * 离职申请修改
	 * @param taskId
	 * @param quit
	 * @param employee
	 * @return
	 */
	public String updateTask10(String taskId, Quit quit, Employee employee);
	
	/**
	 * 用章申请修改
	 * @param taskId
	 * @param chapter
	 * @param employee
	 * @return
	 */
	public String updateTask11(String taskId, Chapter chapter, Employee employee);
	
	/**
	 * 印刷品印刷申请
	 * @param taskId
	 * @param printing
	 * @param employee
	 * @return
	 */
	public String updateTask12(String taskId, Printing printing, Employee employee);
	
	/**
	 * 员工转正申请
	 * @param taskId
	 * @param printing
	 * @param employee
	 * @return
	 */
	public String updateTask13(String taskId, Correction correction, Employee employee);
	
	/**
	 * 修改任务
	 * @param taskId
	 * @param data
	 * @param employee
	 * @return
	 */
	public String updateAllTask(String taskId, Data data, Employee employee);
	
	/**
	 * 完成任务
	 * @param taskId
	 * @param text
	 * @param data
	 * @return
	 */
	public String completeAllTask(String taskId, String text, Data data);
	
	/**
	 * 添加关注
	 * @param taskId 关注任务
	 * @param eName 关注人
	 * @return
	 */
	public Integer insertFollow(String taskId, String eName);
	
	/**
	 * 检查任务是否被关注
	 * @param taskId 任务编号
	 * @return
	 */
	public Integer checkFollowTask(@Param("taskId") String taskId);

	/**
	 * 
	 * @param taskId
	 */
	public void deleteFollow(String taskId);

}
