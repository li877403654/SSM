package com.design.framework.exception.bean;

/**
 * ID异常
 * @author JohnDeng
 * @date 2018年8月8日下午3:35:33
 */
public class IdExcption  extends CustomException{

	/**
	 *@author JohnDeng
	 *@time 2018年7月20日下午2:30:45
	 */
	private static final long serialVersionUID = 1L;

	public IdExcption(String message) {
		super(message);
		
	}
	
	public IdExcption(String code,String message) {
		super(message);
		this.code=code;
		this.message=message;
	}
	
}
