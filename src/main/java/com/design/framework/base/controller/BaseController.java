package com.design.framework.base.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.design.framework.annotion.SysLog;
import com.design.framework.api.ApiResponse;
import com.design.framework.api.bean.ApiMap;
import com.design.framework.api.bean.ApiPage;
import com.design.framework.base.service.BaseService;
import com.design.framework.bean.Page;
import com.design.framework.bean.Paging;
import com.design.framework.enums.LogTypeEnums;
import com.design.framework.utils.JsonUtils;

/**
 * 基类控制器
 *
 * @param <T>
 * @author JohnDeng 2017年11月9日上午10:32:42
 */
public abstract class BaseController<T, ID> extends ValidateBaseController<T, ID> {
	protected Logger logger = Logger.getLogger(getClass());

	protected BaseService<T, ID> baseService;

	protected Class<T> entityClass;

	protected Class<ID> id;

	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpServletResponse response;

	@Autowired
	protected Validator validator;

	public abstract void initialize();

	@SuppressWarnings("unchecked")
	public BaseController() {
		this.entityClass = null;
		Class<?> c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
			this.id = (Class<ID>) parameterizedType[1];
		}
	}

	public void setBaseService(BaseService<T, ID> baseService) {
		this.baseService = baseService;
	}

	/**
	 * 新增之前操作的方法（可以做业务逻辑）
	 * 
	 * @author JohnDeng
	 * @datatime 2018年9月12日下午8:58:42
	 * @param entity
	 * @param json
	 */
	protected void beforeAdd(T entity, JSONObject json) {

	}

	protected void beforeUpdate(T entity, JSONObject json) {

	}

	protected void afterGetListByPage(Page<T> page) {

	}

	protected void beforeGetListByPage(JSONObject json, T entity) {

	}

	protected void beforeGetList() {

	}

	protected void beforeGetListMap(String key, String value) {

	}

	/**
	 * 新增
	 * 
	 * @author JohnDeng
	 * @datatime 2018年9月12日下午9:00:13
	 * @param entity
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@SysLog(operate = LogTypeEnums.INSERT, description = "新增")
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public ApiMap add(T entity, @RequestBody JSONObject json) throws Exception {
		entity = JsonUtils.toBean(json, entity);
		validateAdd(json, entity);
		beforeAdd(entity, json);
		baseService.insert(entity);
		return ApiResponse.returnSuccess();
	}

	/**
	 * 根据id删除一个对象
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 * @author JohnDeng 2017年11月9日下午3:28:45
	 */
	@SysLog(operate = LogTypeEnums.DELETE, description = "根据id删除一个对象")
	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=UTF-8" })
	public ApiMap delete(T entity, @PathVariable("id") ID id) throws Exception {
		validateDelete(id, entity);
		baseService.deleteById(id);
		return ApiResponse.returnSuccess();
	}

	/**
	 * 修改
	 * 
	 * @author JohnDeng
	 * @datatime 2018年9月12日下午11:15:16
	 * @param entity
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@SysLog(operate = LogTypeEnums.UPDATE, description = "修改")
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public ApiMap update(T entity, @RequestBody JSONObject json) throws Exception {
		entity = JsonUtils.toBean(json, entity);
		validateUpdate(json, entity);
		beforeUpdate(entity, json);
		baseService.update(entity);
		return ApiResponse.returnSuccess();
	}

	/**
	 * 根据ID获取实体
	 * 
	 * @param entity
	 * @param id
	 * @return
	 * @author JohnDeng
	 * @throws Exception
	 */
	@SysLog(operate = LogTypeEnums.SELECT, description = "根据ID获取实体")
	@ResponseBody
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ApiMap get(T entity, @PathVariable("id") ID id) throws Exception {
		validateGet(id, entity);
		entity = baseService.findById(id);
		return ApiResponse.returnDataSuccess(entity);
	}

	/**
	 * 查询全部列表
	 * 
	 * @return
	 * @author JohnDeng
	 * @throws Exception
	 */
	@SysLog(operate = LogTypeEnums.SELECT, description = "查询全部列表")
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public ApiMap getList() throws Exception {
		beforeGetList();
		List<T> entityList = baseService.findAll();
		return ApiResponse.returnDataSuccess(entityList);
	}

	/**
	 * 根据key value 获取listMap
	 * 
	 * @param map
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@SysLog(operate = LogTypeEnums.SELECT, description = "根据key value 获取listMap")
	@ResponseBody
	@RequestMapping(value = "/getListMap/{key}/{value}", method = RequestMethod.GET)
	public ApiMap getListMap(Map<String, Object> map, @PathVariable("key") String key,
			@PathVariable("value") String value) throws Exception {
		beforeGetListMap(key, value);
		map.put(key, value);
		List<Map<String, Object>> listMap = baseService.getListMap(map);
		if (CollectionUtils.isEmpty(listMap)) {
			return ApiResponse.returnDataSuccess(new ArrayList<Map<String, Object>>());
		}
		return ApiResponse.returnDataSuccess(listMap);
	}

	@ResponseBody
	@RequestMapping(value = "/getListByPage", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public ApiMap getListByPage(T entity, @RequestBody JSONObject json, Page<T> page, Paging paging) throws Exception {
		paging = JsonUtils.toBean(json, paging);
		validateGetListByPage(json, paging, page);
		beforeGetListByPage(json, entity);
		page = (Page<T>) baseService.getListByPage(page);
		afterGetListByPage(page);
		return ApiResponse.returnPage(
				new ApiPage(page.getTotalRecord(), paging.getCurrPage(), paging.getPageSize(), page.getResults()));
	}
}
