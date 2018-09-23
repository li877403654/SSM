package com.design.framework.exception.bean;

public class ValidataException extends CustomException {

	/**
	 * @author JohnDeng
	 * @date 2018年8月14日下午4:51:18
	 */
	private static final long serialVersionUID = 1L;

	public ValidataException(String message) {
		  super(message);
		  this.message=message;
	}

}
