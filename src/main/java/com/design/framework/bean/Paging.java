package com.design.framework.bean;

import java.util.Map;

import javax.validation.constraints.NotNull;

/**
 * 分页
 *@author JohnDeng
 *@time 2018年7月20日下午3:18:24
 */
public class Paging {
	
	@NotNull(message="页码不能为空")
	private int currPage ; // 当前页, 默认为第1页
	@NotNull(message="条数不能为空")
	private int pageSize;  // 每页记录数
	@NotNull(message="排序字段不能为空")
	private String orderByName ; // 排序字段
	@NotNull(message="降序升序不能为空")
	private String orderBy ; // 升降序
	private Map<String, Object> params;
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderByName() {
		return orderByName;
	}
	public void setOrderByName(String orderByName) {
		this.orderByName = orderByName;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
}
