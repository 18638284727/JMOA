package com.jm.entity;

import java.util.List;

/**
 * 分页实体类
 */
public class PageInfo<T> {

	private Integer pageNum; // 第几页
	private Integer pageSize; // 每页大小
	private Integer total; // 总共多少条
	private Integer pages; // 总共多少页
	private List<Integer> pageNums; // 页码集合
	private List<T> list; // 数据集合

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public List<Integer> getPageNums() {
		return pageNums;
	}

	public void setPageNums(List<Integer> pageNums) {
		this.pageNums = pageNums;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageInfo(Integer pageNum, Integer pageSize, Integer total, Integer pages, List<Integer> pageNums,
			List<T> list) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		this.pages = pages;
		this.pageNums = pageNums;
		this.list = list;
	}

	public PageInfo() {
		super();
	}

	@Override
	public String toString() {
		return "PageInfo [pageNum=" + pageNum + ", pageSize=" + pageSize + ", total=" + total + ", pages=" + pages
				+ ", pageNums=" + pageNums + ", list=" + list + "]";
	}

}
