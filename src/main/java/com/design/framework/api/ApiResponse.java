package com.design.framework.api;

import com.design.framework.api.bean.ApiMap;
import com.design.framework.api.bean.ApiPage;
import com.design.framework.api.service.ApiReturnResultImpl;

/**
 * 返回前端的实体
 * 
 * @author JohnDeng
 * @datatime 2018年7月6日下午6:57:02
 */
public class ApiResponse {

	private static ApiReturnResultImpl apiReturnResult = new ApiReturnResultImpl();

	/**
	 * 返回一个成功map
	 * 
	 * @return
	 */
	public static ApiMap returnSucccssMap() {
		return apiReturnResult.returnSucccssMap();
	}

	/**
	 * 新增属性到data下面
	 * 
	 * @param key
	 * @param value
	 */
	public static void addMap(String key, Object value) {
		apiReturnResult.addMap(key, value);
	}

	/**
	 * 返回成功的状态码和信息
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午2:07:40
	 * @return
	 */
	public static ApiMap returnSuccess() {
		return apiReturnResult.returnSuccess();
	}

	/**
	 * 返回带有数据成功的状态码和信息
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午4:53:34
	 * @return
	 */
	public static ApiMap returnDataSuccess(Object object) {

		return apiReturnResult.returnDataSuccess(object);
	}

	/**
	 * 返回参数异常的状态码和信息
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午2:07:14
	 * @return
	 */
	public static ApiMap returnParamError() {

		return apiReturnResult.returnParamError();
	}

	/**
	 * 返回输入为空的状态码和信息
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午2:06:34
	 * @return
	 */
	public static ApiMap returnInputIsempty() {

		return apiReturnResult.returnInputIsempty();
	}

	/**
	 * 自定义返回状态码和信息
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午2:05:06
	 * @return
	 */
	public static ApiMap returnCustomCodeMsg(String code, String msg) {

		return apiReturnResult.returnCustomCodeMsg(code, msg);
	}

	/**
	 * 返回业务异常状态码和信息
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午2:04:44
	 * @return
	 */
	public static ApiMap returnBusinessError() {

		return apiReturnResult.returnBusinessError();
	}

	/**
	 * 返回分页
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午2:04:22
	 * @param apiPage
	 * @return
	 */
	public static ApiMap returnPage(ApiPage apiPage) {

		return apiReturnResult.returnPage(apiPage);
	}

	/**
	 * 返回token异常
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午4:48:16
	 * @return
	 */
	public static ApiMap returnTokenError() {

		return apiReturnResult.returnTokenError();
	}

	/**
	 * 自定义参数异常信息
	 * 
	 * @author JohnDeng
	 * @date 2018年8月1日下午5:12:06
	 * @param msg
	 * @return
	 */
	public static ApiMap returnParamError(String msg) {

		return apiReturnResult.returnParamError(msg);
	}
}
