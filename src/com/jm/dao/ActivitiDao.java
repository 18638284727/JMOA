package com.jm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jm.entity.ByteArray;
import com.jm.entity.ProcessModel;

/**
 * 模型接口
 */
public interface ActivitiDao {

	/**
	 * 获取模型条数
	 * @return 模型条数
	 */
	public Integer countModel();
	
	/**
	 * 获取流程定义条数
	 * @return 流程定义条数
	 */
	public Integer countProcess(@Param("name") String name);
	
	/**
	 * 根据部署编号查询二进制表的记录
	 * @param deploymentId 部署编号
	 * @return ByteArray二进制实体类集合
	 */
	public List<ByteArray> getByteArrayByDeploymentId(@Param("deploymentId") String deploymentId);
	
	/**
	 * 根据部署编号修改流程定义的名称为指定模型编号的模型的名称
	 * @param modelId 模型编号
	 * @param deploymentId 部署编号
	 * @return 受影响的行数
	 */
	public Integer updateProcessName(@Param("modelId") String modelId, @Param("deploymentId") String deploymentId);
	
	/**
	 * 根据处理人获取任务条数
	 * @param assignee 处理人
	 * @return 任务条数
	 */
	public Integer countTask(@Param("assignee") String assignee);
	
	/**
	 * 获取所有流程定义的名称
	 * @return 名称集合
	 */
	public List<String> getProcdefName();
	
	/**
	 * 获取所有的流程定义
	 * @return
	 */
	public List<ProcessModel> getProcessModel();
	
	/**
	 * 修改任务的处理人
	 * @param taskId 任务ID
	 * @param assignee 处理人名称
	 * @return
	 */
	public Integer updateAssignee(@Param("taskId") String taskId, @Param("assignee") String assignee);
	
	public List<String> getProdefIdByName(@Param("name") String name);
	
	/**
	 * 添加关注
	 * @param taskId 关注任务
	 * @param eName 关注人
	 * @return
	 */
	public Integer insertFollow(@Param("taskId") String taskId, @Param("eName") String eName);
	
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
	public void deleteFollow(@Param("taskId") String taskId);
	
	public String selectHisExecutionIdByTaskId(@Param("hisTaskId") String hisTaskId);
	
	public String selectExecutionIdByTaskId(@Param("hisTaskId") String hisTaskId);
	
	public void deleteVariables(@Param("executionId") String executionId);
	
	public void deleteHisVariables(@Param("executionId") String executionId);
	
	public void deleteTaskInstance(@Param("taskId") String taskId);
}
