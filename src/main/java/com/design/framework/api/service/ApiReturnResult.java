package com.design.framework.api.service;

import com.design.framework.api.bean.ApiMap;
import com.design.framework.api.bean.ApiPage;

public interface ApiReturnResult {

	/**
	 * 返回成功的状态码和信息
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午2:07:40
	 * @return
	 */
	ApiMap returnSuccess();

	
	/**
	 * 返回带有数据成功的状态码和信息
	 * @author JohnDeng
	 * @date 2018年8月1日下午4:53:34
	 * @return
	 */
	ApiMap returnDataSuccess(Object object);
	
	/**
	 * 返回参数异常的状态码和信息
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午2:07:14
	 * @return
	 */
	ApiMap returnParamError();
	
	/**
	 * 自定义参数异常信息
	 * @author JohnDeng
	 * @date 2018年8月1日下午5:11:42
	 * @param msg
	 * @return
	 */
	ApiMap returnParamError(String msg);

	/**
	 * 返回输入为空的状态码和信息
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午2:06:34
	 * @return
	 */
	ApiMap returnInputIsempty();

	/**
	 * 自定义返回状态码和信息
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午2:05:06
	 * @return
	 */
	ApiMap returnCustomCodeMsg(String code, String msg);

	/**
	 * 返回业务异常状态码和信息
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午2:04:44
	 * @return
	 */
	ApiMap returnBusinessError();

	/**
	 * 返回分页
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午2:04:22
	 * @param apiPage
	 * @return
	 */
	ApiMap returnPage(ApiPage apiPage);
	
	
	/**
	 *  返回token异常
	 * @author JohnDeng
	 * @date 2018年8月1日下午4:48:16
	 * @return
	 */
	ApiMap returnTokenError();
}
