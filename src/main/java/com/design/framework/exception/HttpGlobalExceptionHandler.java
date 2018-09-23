package com.design.framework.exception;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.design.framework.api.ApiResponse;
import com.design.framework.api.bean.ApiMap;
import com.design.framework.api.enums.ApiCodeEnum;
/**
 * http请求的异常类型
 * @author JohnDeng
 * @dateTime 2018年9月21日 下午8:24:19
 */
@ControllerAdvice
public class HttpGlobalExceptionHandler {
	/**
	 * 语义有误，当前请求无法被服务器理解。除非进行修改，否则客户端不应该重复提交这个请求
	 * 请求参数有误
	 * 
	 * @return
	 * @author JohnDeng
	 * @dateTime 2018年9月21日 下午8:10:50
	 */
	@ResponseBody
	@ExceptionHandler(value = { HttpMessageNotReadableException.class, TypeMismatchException.class,
			MissingServletRequestParameterException.class })
	public ApiMap error400() {
		return ApiResponse.returnCustomCodeMsg(ApiCodeEnum.ERROR_400.getCode(), ApiCodeEnum.ERROR_400.getMessage());
	}

	/**
	 * 请求失败
	 * 
	 * @return
	 * @author JohnDeng
	 * @dateTime 2018年9月21日 下午8:10:50
	 */
	@ResponseBody
	@ExceptionHandler(value = { NoSuchRequestHandlingMethodException.class })
	public ApiMap error404() {
		return ApiResponse.returnCustomCodeMsg(ApiCodeEnum.ERROR_404.getCode(), ApiCodeEnum.ERROR_404.getMessage());
	}

	/**
	 * 请求行中指定的请求方法不能被用于请求相应的资源
	 * 
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	public ApiMap error405() {
		return ApiResponse.returnCustomCodeMsg(ApiCodeEnum.ERROR_405.getCode(), ApiCodeEnum.ERROR_405.getMessage());
	}

	/**
	 * 请求的资源的内容特性无法满足请求头中的条件，因而无法生成响应实体
	 * 
	 * @return
	 * @author JohnDeng
	 * @dateTime 2018年9月21日 下午8:06:52
	 */
	@ResponseBody
	@ExceptionHandler(value = { HttpMediaTypeNotAcceptableException.class })
	public ApiMap error406() {
		return ApiResponse.returnCustomCodeMsg(ApiCodeEnum.ERROR_406.getCode(), ApiCodeEnum.ERROR_406.getMessage());
	}

	/**
	 * 对于当前请求的方法和所请求的资源，请求中提交的实体并不是服务器中所支持的格式，因此请求被拒绝
	 * 
	 * @return
	 * @author JohnDeng
	 * @dateTime 2018年9月21日 下午8:15:19
	 */
	@ResponseBody
	@ExceptionHandler(value = { HttpMediaTypeNotSupportedException.class })
	public ApiMap error415() {
		return ApiResponse.returnCustomCodeMsg(ApiCodeEnum.ERROR_415.getCode(), ApiCodeEnum.ERROR_415.getMessage());
	}

	/**
	 * 服务器无法完成对请求的处理
	 * 
	 * @return
	 * @author JohnDeng
	 * @dateTime 2018年9月21日 下午4:44:03
	 */
	@ResponseBody
	@ExceptionHandler(value = { ConversionNotSupportedException.class })
	public ApiMap error500() {
		return ApiResponse.returnCustomCodeMsg(ApiCodeEnum.ERROR_500.getCode(), ApiCodeEnum.ERROR_500.getMessage());
	}

}
