package com.design.framework.exception;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.EmptyResultDataAccessException;
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
import com.design.framework.exception.bean.CustomException;
import com.design.framework.exception.bean.ParameterException;
import com.design.framework.exception.bean.ValidataException;

/**
 * 全局处理异常
 * 
 * @author JohnDeng
 * @datatime 2018年6月15日下午12:04:12
 */
@ControllerAdvice
public class GlobalExceptionHandler {


	/**
	 * 全局异常统称业务异常
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午5:03:10
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ApiMap defaultErrorHandler(Exception e) throws Exception {
		e.printStackTrace();
		return ApiResponse.returnBusinessError();
	}

	/**
	 * 自定义异常
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午5:06:42
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = { CustomException.class })
	public ApiMap customException(final CustomException e) {

		return ApiResponse.returnCustomCodeMsg(e.getCode(), e.getMessage());
	}

	/**
	 * 参数异常
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午5:08:41
	 * @param e
	 * @return
	 */
	@ResponseBody	
	@ExceptionHandler(value = { ParameterException.class })
	public ApiMap parameterException(final ParameterException e) {

		return ApiResponse.returnParamError(e.getMessage());
	}

	/**
	 * ID不存在异常
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = { EmptyResultDataAccessException.class })
	public ApiMap EmptyResultDataAccessException(final EmptyResultDataAccessException e) {

		return ApiResponse.returnParamError(ApiCodeEnum.ID_IS_ERROR.getMessage());
	}
	

	/**
	 * 检验异常
	 * 
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = { ValidataException.class })
	public ApiMap validataException(final ValidataException e) {

		return ApiResponse.returnCustomCodeMsg(ApiCodeEnum.CUSTOM_PARAM_ERROR.getCode(), e.getMessage());
	}

	

}
