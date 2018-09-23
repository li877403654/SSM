package com.design.framework.api.enums;
/**
 * API状态码和返回信息
 * @author JohnDeng
 * 2017年11月9日上午10:33:28
 */
public enum ApiCodeEnum {
	
	SUCCESS("0000", "操作成功"),
	PARAM_ERROR("0001","参数异常"),
	ID_IS_NULL("0002","Id不能为空"),
	INPUT_IS_NULL("0003","输入数据为空"),
	BUSINESS_ERROR("0004","业务异常"),
	ERROR("0005","操作失败"),
	ID_IS_ERROR("0006","Id不存在"),
	CUSTOM_PARAM_ERROR("0007","自定义参数异常信息"), 
	TOKEN_CONNECTION_ERROR("9998","redis连接异常"),
	TOKEN_ERROR("9999","token异常"),
	
	ERROR_400("0400","请求参数有误或者语义有误"),
	ERROR_401("0401","请求体不能为空"),
	ERROR_404("0404","请求路径异常"),
	ERROR_405("0405","请求方法不正确"),
	ERROR_406("0406","请求媒体不接受"),
	ERROR_415("0415","请求资源格式不支持"),
	ERROR_500("0500","服务器异常"),
	
	FILE_IS_NULL("2000","上传文件不存在"),
	FILE_TYPE_ERROR("2001","文件类型错误"),
	FILE_OVER_MAX_SIZE("2002","文件上传过最大文件大小");
	
	
	
	
	
	private final String code;
	private final String message;
	
	private ApiCodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
}
