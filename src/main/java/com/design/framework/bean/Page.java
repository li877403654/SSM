package com.design.framework.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;

/**
 * 分页
 * @author JohnDeng
 * @datatime 2018年7月6日下午6:56:10
 * @param <T>
 */
@Alias("page")
public class Page<T> {

	private  String ORDER_BY_ASC = "ASC";

	private  String ORDER_BY_DESC = "DESC";

	private  String ORDER_BY_NAME = "id";
	

	private int pageNo = 1; // 当前页, 默认为第1页
	private int pageSize = 10; // 每页记录数
	private int totalRecord = 0; // 总记录数, 默认为-1, 表示需要查询
	private int totalPage = 0; // 总页数, 默认为-1, 表示需要计算
	private String orderByName = ORDER_BY_NAME; // 排序字段
	private String orderBy = ORDER_BY_DESC; // 升降序
	private List<T> results =new ArrayList<T>(); // 当前页记录List形式

	private Map<String, Object> params;// 设置页面传递的查询参数

	public Map<String, Object> getParams() {
		return params;
	}
	
	public  void  put(String  key,Object Value){
		if(params==null){
			params=new HashMap<String, Object>();
		}
		params.put(key, Value);
	}

	public void setParams(Map<String, Object> params) {
		
		this.params = params;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		computeTotalPage();
	}
	protected void computeTotalPage() {
		if (getPageSize() > 0 && getTotalRecord() > -1) {
			this.totalPage =  (getTotalRecord() % getPageSize() == 0 ? getTotalRecord() / getPageSize(): getTotalRecord() / getPageSize() + 1);
		}
		
		this.pageNo=(this.pageNo-1)*this.pageSize;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public String getOrderByName() {
		return orderByName;
	}

	public void setOrderByName(String groupName) {
		if (groupName == null || groupName.equals("")) {
			this.orderByName = ORDER_BY_NAME;
		} else {
			this.orderByName = groupName;
		}
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String groupBy) {
		if (groupBy.toUpperCase().equals(ORDER_BY_ASC)) {
			this.orderBy = groupBy;
		} else if (groupBy.toUpperCase().equals(ORDER_BY_DESC)) {
			this.orderBy = groupBy;
		} else {
			this.orderBy = ORDER_BY_ASC;
		}
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
