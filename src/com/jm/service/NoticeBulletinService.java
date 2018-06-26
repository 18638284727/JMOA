package com.jm.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jm.entity.NoticeBulletin;

/**
 * 通知公告业务逻辑接口
 */
public interface NoticeBulletinService {

	/**
	 * 获取所有的通知公告
	 * @return 通知公告集合
	 */
	public PageInfo<NoticeBulletin> getAll(Integer pageNo);
	
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
	public Integer delNoticeBulletinById(Integer nbId);
	
	/**
	 * 编辑通知公告
	 * @param noticeBulletin 通知公告实体
	 * @return 受影响的行数
	 */
	public Integer editNoticeBulletinById(NoticeBulletin noticeBulletin);
}
