package com.design.framework.exception.bean;
/**
 * 自定义异常
 * @author JohnDeng
 * @datatime 2018年7月5日下午4:32:46
 */
public class CustomException  extends Exception{

	protected  String code;
	
	protected String message;

	/**
	 * @author JohnDeng
	 * @datatime 2018年7月5日下午4:36:20
	 */
	private static final long serialVersionUID = 1L;

	
	public CustomException(String message){
		super(message);
	}
	
	public CustomException(String code,String message){
		super(message);
		this.code=code;
		this.message=message;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
