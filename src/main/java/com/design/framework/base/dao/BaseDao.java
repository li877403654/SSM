package com.design.framework.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.design.framework.bean.Page;

/**
 * 基类DAO
 * 
 * @author JohnDeng 2017年11月9日上午10:31:10
 * @param <T>
 */
public interface BaseDao<T, ID> {

	public T findById(@Param("id") ID id);
	
	public List<T> findAll();
	
	public T get(T entity);
	
	public List<T> getList(T entity);
	
	public List<T> getListByPage(Page<T> page);

	public int insert(T entity);

	public int update(T entity);

	public int delete(@Param("id") ID id);

	public int deleteBatchById(List<T> list);
	
	public int insertBatch(List<T> list);

	public int updateBatchById(List<T> list);

	public List<Map<String, Object>> getListMap(Map<String, Object> param);

	public List<Map<String, Object>> getListMap(T entity);

	public int getTotalCount(Page<T> page);

}
