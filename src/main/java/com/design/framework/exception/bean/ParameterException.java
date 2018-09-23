package com.design.framework.exception.bean;

/**
 * 参数缺少异常异常
 */
public class ParameterException extends CustomException {
	
    /**
	 *@author JohnDeng
	 *@time 2018年7月19日下午6:05:57
	 */
	private static final long serialVersionUID = 1L;

	public ParameterException(String message) {
        super(message);
        this.message=message;
    }
}
	