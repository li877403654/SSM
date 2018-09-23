package com.design.framework.api.bean;

/**
 * API数据对象
 * 
 * @author JohnDeng
 * @date 2018年8月1日上午11:58:04
 */
public class ApiData  extends ApiMap {

	/**
	 * @author JohnDeng
	 * @date 2018年8月1日下午12:43:46
	 */
	private static final long serialVersionUID = 1L;
	
	private Object data;

	public ApiData() {
		
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
