package com.design.framework.base.controller;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.design.framework.api.enums.ApiCodeEnum;
import com.design.framework.bean.Page;
import com.design.framework.bean.Paging;
import com.design.framework.exception.bean.IdExcption;
import com.design.framework.exception.bean.ParameterException;
import com.design.framework.exception.bean.ValidataException;
import com.design.framework.utils.StringUtils;
import com.design.framework.validata.BeanValidation;
import com.design.framework.validata.bean.ValidationResult;

public class ValidateBaseController<T,ID> {

	/**
	 * 检验新增方法参数
	 *
	 * @param json
	 * @param entity
	 * @return
	 * @throws Exception
	 * @author John
	 * @datatime 2017年12月14日下午4:23:46
	 */
	protected void validateAdd(JSONObject json, T entity) throws Exception {
		ValidationResult validation = new ValidationResult();
		if (BeanValidation.validateParamFail(entity, validation)) {
			throw new ValidataException(validation.getMessage());
		}
	}

	/**
	 * 检验删除方法参数
	 *
	 * @param id
	 * @param entity
	 * @return
	 * @throws Exception
	 * @author John
	 * @datatime 2017年12月14日下午4:22:10
	 */
	protected void validateDelete(ID id, T entity) throws Exception {
		if (StringUtils.isEmpty(id)) {
			throw new IdExcption(ApiCodeEnum.ID_IS_NULL.getMessage());
		}
	}

	/**
	 * 检验更新方法参数
	 *
	 * @param json
	 * @param entity
	 * @return
	 * @throws Exception
	 * @author John
	 * @datatime 2017年12月14日下午4:33:09
	 */
	protected void validateUpdate(JSONObject json, T entity) throws Exception {
		ValidationResult validationResult = new ValidationResult();
		if (BeanValidation.validIdFail(entity)) {
			throw new ParameterException(ApiCodeEnum.ID_IS_NULL.getMessage());
		} else if (BeanValidation.validateParamFail(entity, validationResult)) {
			throw new ValidataException(validationResult.getMessage());
		}
	}

	/**
	 * 检验获取单个对象方法参数
	 *
	 * @param id
	 * @param entity
	 * @return
	 * @throws Exception
	 * @author John
	 * @datatime 2017年12月14日下午4:37:50
	 */
	protected void validateGet(ID id, T entity) throws Exception {
		if (StringUtils.isEmpty(id)) {
			throw new IdExcption(ApiCodeEnum.ID_IS_NULL.getMessage());
		}
	}
	
	/**
	 * 检验getListMap入参
	 *
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 * @author John
	 * @datatime 2017年12月22日上午11:03:35
	 */
	protected void validateGetListMap(Map<String, Object> map, String key, String value) throws Exception {
		if (StringUtils.isEmpty(key, value)) {
			throw new ParameterException(ApiCodeEnum.PARAM_ERROR.getMessage());
		}
	}
	
	/**
	 * 检验分页
	 *
	 * @param json
	 * @param paging
	 * @param page
	 * @throws Exception
	 * @author JohnDeng
	 * @time 2018年7月20日下午3:33:12
	 */
	protected void validateGetListByPage(JSONObject json, Paging paging, Page<T> page) throws Exception {
		ValidationResult validation = new ValidationResult();
		if (BeanValidation.validateParamFail(paging, validation)) {
			throw new ValidataException(validation.getMessage());
		}
		if (paging.getCurrPage() > 0) {
			page.setPageNo(paging.getCurrPage());
		} else {
			paging.setCurrPage(1);
			page.setPageNo(paging.getCurrPage());
		}
		if (paging.getPageSize() > 0) {
			page.setPageSize(paging.getPageSize());
		} else {
			paging.setPageSize(1);
			page.setPageSize(paging.getPageSize());
		}
		if (StringUtils.isNotEmpty(paging.getOrderByName())) {
			page.setOrderByName(paging.getOrderByName());
		}
		if (StringUtils.isNotEmpty(paging.getOrderBy())) {
			page.setOrderBy(paging.getOrderBy());
		}
		if (paging.getParams() != null) {
			page.setParams(paging.getParams());
		}
	}
}
