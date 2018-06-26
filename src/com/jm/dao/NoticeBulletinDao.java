package com.jm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jm.entity.NoticeBulletin;

/**
 * 通知公告接口
 */
public interface NoticeBulletinDao {

	/**
	 * 获取所有的通知公告
	 * @return 通知公告集合
	 */
	public List<NoticeBulletin> getAll();
	
	/**
	 * 根据时间降序返回去前七条
	 * @return 通知公告集合根据时间
	 */
	public List<NoticeBulletin> getNoticeBulletinByDate();
	
	/**
	 * 添加通知公告
	 * @param noticeBulletin 通知公告实体
	 * @return 受影响的行数
	 */
	public Integer insertNoticeBulletin(NoticeBulletin noticeBulletin);
	
	/**
	 * 删除通知公告
	 * @param nId 通知公告编号
	 * @return 受影响的行数
	 */
	public Integer delNoticeBulletinById(@Param("nbId") Integer nbId);
	
	/**
	 * 编辑通知公告
	 * @param noticeBulletin 通知公告实体
	 * @return 受影响的行数
	 */
	public Integer editNoticeBulletinById(NoticeBulletin noticeBulletin);
}
