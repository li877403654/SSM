package com.design.framework.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.design.framework.api.bean.ApiData;
import com.design.framework.api.enums.ApiCodeEnum;

/**
 * 上传文件拦截器
 * 
 * @author JohnDeng
 * @date 2018年8月8日下午3:36:13
 */
public class FileUploadInterceptor implements HandlerInterceptor {
	protected Logger logger = Logger.getLogger(getClass());
	private long maxSize;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("===================FileUploadInterceptor start ===================");
		if (request != null && ServletFileUpload.isMultipartContent(request)) {
			ServletRequestContext ctx = new ServletRequestContext(request);
			// 获取上传文件尺寸大小
			long requestSize = ctx.contentLength();
			logger.info("=================== file size:" + requestSize + " ===================");
			// 判断是否文件上传
			if (requestSize > maxSize) {
				// 当上传文件大小超过指定大小限制后，

				ApiData apiData = new ApiData();
				apiData.setCode(ApiCodeEnum.FILE_OVER_MAX_SIZE.getCode());
				apiData.setMsg(ApiCodeEnum.FILE_OVER_MAX_SIZE.getMessage());

				PrintWriter out = response.getWriter();
				out.println(JSONObject.toJSON(apiData));
				logger.info("===================FileUploadInterceptor end ===================");
				return false;
			}
		}
		logger.info("===================FileUploadInterceptor end ===================");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

}
