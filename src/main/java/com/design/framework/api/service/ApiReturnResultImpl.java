package com.design.framework.api.service;

import java.util.HashMap;
import java.util.Map;

import com.design.framework.api.bean.ApiData;
import com.design.framework.api.bean.ApiMap;
import com.design.framework.api.bean.ApiPage;
import com.design.framework.api.enums.ApiCodeEnum;

public class ApiReturnResultImpl implements ApiReturnResult {
	
	private Map<String, Object> appendMap = null;

	public void addMap(String key, Object value) {
		if (appendMap == null) {
			appendMap = new HashMap<String, Object>();
		}
		appendMap.put(key, value);
	}

	public ApiData returnSucccssMap() {
		ApiData apiData = new ApiData();
		apiData.setData(appendMap);
		apiData.setCode(ApiCodeEnum.SUCCESS.getCode());
		apiData.setMsg(ApiCodeEnum.SUCCESS.getMessage());
		return apiData;
	}

	@Override
	public ApiMap returnSuccess() {
		ApiMap apiCodeMsg = new ApiMap();
		apiCodeMsg.setCode(ApiCodeEnum.SUCCESS.getCode());
		apiCodeMsg.setMsg(ApiCodeEnum.SUCCESS.getMessage());
		return apiCodeMsg;
	}

	@Override
	public ApiMap returnParamError() {
		ApiMap apiCodeMsg = new ApiMap();
		apiCodeMsg.setCode(ApiCodeEnum.PARAM_ERROR.getCode());
		apiCodeMsg.setMsg(ApiCodeEnum.PARAM_ERROR.getMessage());
		return apiCodeMsg;

	}

	@Override
	public ApiMap returnInputIsempty() {
		ApiMap apiCodeMsg = new ApiMap();
		apiCodeMsg.setCode(ApiCodeEnum.INPUT_IS_NULL.getCode());
		apiCodeMsg.setMsg(ApiCodeEnum.INPUT_IS_NULL.getMessage());
		return apiCodeMsg;
	}

	@Override
	public ApiMap returnCustomCodeMsg(String code, String msg) {
		ApiMap apiCodeMsg = new ApiMap();
		apiCodeMsg.setCode(code);
		apiCodeMsg.setMsg(msg);
		return apiCodeMsg;
	}

	@Override
	public ApiMap returnBusinessError() {
		ApiMap apiCodeMsg = new ApiMap();
		apiCodeMsg.setCode(ApiCodeEnum.BUSINESS_ERROR.getCode());
		apiCodeMsg.setMsg(ApiCodeEnum.BUSINESS_ERROR.getMessage());
		return apiCodeMsg;
	}

	@Override
	public ApiMap returnPage(ApiPage apiPage) {
		ApiData apiData = new ApiData();
		apiData.setCode(ApiCodeEnum.SUCCESS.getCode());
		apiData.setMsg(ApiCodeEnum.SUCCESS.getMessage());
		apiData.setData(apiPage);
		return apiData;
	}

	@Override
	public ApiMap returnTokenError() {
		ApiMap apiCodeMsg = new ApiMap();
		apiCodeMsg.setCode(ApiCodeEnum.TOKEN_ERROR.getCode());
		apiCodeMsg.setMsg(ApiCodeEnum.TOKEN_ERROR.getMessage());
		return apiCodeMsg;
	}

	@Override
	public ApiMap returnDataSuccess(Object object) {
		ApiData apiData = new ApiData();
		apiData.setCode(ApiCodeEnum.SUCCESS.getCode());
		apiData.setMsg(ApiCodeEnum.SUCCESS.getMessage());
		apiData.setData(object);
		return apiData;
	}

	@Override
	public ApiMap returnParamError(String msg) {
		ApiMap apiCodeMsg = new ApiMap();
		apiCodeMsg.setCode(ApiCodeEnum.CUSTOM_PARAM_ERROR.getCode());
		apiCodeMsg.setMsg(msg);
		return apiCodeMsg;
	}

}
