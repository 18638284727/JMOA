package com.jm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.jm.entity.NoticeBulletin;
import com.jm.service.NoticeBulletinService;

@Controller
@RequestMapping(value = "noticeBulletin")
public class NoticeBulletinController {

	@Lazy
	@Autowired
	private NoticeBulletinService noticeBulletinService;
	
	@ResponseBody
	@RequestMapping(value = "getAll", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageInfo<NoticeBulletin> getAll(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo)
	{
		return noticeBulletinService.getAll(pageNo);
	}
	
	@ResponseBody
	@RequestMapping(value = "getNoticeBulletinByDate", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public List<NoticeBulletin> getNoticeBulletinByDate()
	{
		return noticeBulletinService.getNoticeBulletinByDate();
	}
	
	@ResponseBody
	@RequestMapping(value = "editNoticeBulletinById", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Integer editNoticeBulletinById(NoticeBulletin noticeBulletin)
	{
		return noticeBulletinService.editNoticeBulletinById(noticeBulletin);
	}
	
	@ResponseBody
	@RequestMapping(value = "delNoticeBulletinById", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Integer delNoticeBulletinById(@RequestParam(value = "nbId") Integer nbId)
	{
		return noticeBulletinService.delNoticeBulletinById(nbId);
	}
	
	@ResponseBody
	@RequestMapping(value = "insertNoticeBulletin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Integer insertNoticeBulletin(NoticeBulletin noticeBulletin)
	{
		return noticeBulletinService.insertNoticeBulletin(noticeBulletin);
	}
}
