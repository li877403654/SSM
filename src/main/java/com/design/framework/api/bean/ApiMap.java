package com.design.framework.api.bean;

import java.io.Serializable;

/**
 *  返回给前端看的实体
 * @author JohnDeng
 * @date 2018年8月1日下午5:00:27
 */
public class ApiMap  implements Serializable {

	/**
	 * @author JohnDeng
	 * @date 2018年8月1日下午12:15:34
	 */
	private static final long serialVersionUID = 8252898721470476265L;

	protected String code ;

	protected String msg ;

	public ApiMap(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ApiMap() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
