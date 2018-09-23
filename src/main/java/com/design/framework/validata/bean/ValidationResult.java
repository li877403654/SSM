package com.design.framework.validata.bean;

/**
 * 检验返回的结果
 * @author JohnDeng
 * @date 2018年8月8日下午3:42:18
 */
public class ValidationResult {
	

	private String fieldName;
	
	private String message;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
