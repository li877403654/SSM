package com.design.framework.api.bean;

import java.util.List;

/**
 *  API分页对象
 * @author JohnDeng
 * @date 2018年8月1日下午12:06:41
 */
public class ApiPage {


	/**
	 * 页码
	 */
	protected  int  pageNumber;

	/**
	 * 每页条数
	 */
	protected  int pageSize;

	/**
	 * 统计条数
	 */
	protected  int totalCount;

	/**
	 * 查询数据
	 */
	protected List<?> list;

	
	public ApiPage( int pageNumber, int pageSize, int totalCount, List<?> list) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.list = list;
	}



	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}


	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	

	
}
