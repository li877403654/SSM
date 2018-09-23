package com.design.framework.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.design.framework.base.dao.BaseDao;
import com.design.framework.base.service.BaseService;
import com.design.framework.bean.Page;

/**
 * 基类服务实现类
 * @author JohnDeng 
 * 2017年11月9日上午10:32:36
 * @param <T>
 */

@Transactional(readOnly=true,propagation = Propagation.REQUIRED)
public class BaseServiceImpl<T,ID> implements BaseService<T,ID> {

	
	protected BaseDao<T,ID> baseDao;

	public void setBaseDao(BaseDao<T,ID> baseDao) {
		this.baseDao = baseDao;
	}
	

	@Override
	
	public T findById(ID id) {
		
		return baseDao.findById(id);
	}

	
	@Override
	
	public List<T> findAll() {
		
		return baseDao.findAll();
	}

	@Override
	
	public T get(T entity) {
		
		return baseDao.get(entity);
	}

	@Override
	
	public List<T> getList(T entity) {
		
		return baseDao.getList(entity);
	}

	@Override
	
	public List<T> getListByPage(Page<T> page) {
		
		return baseDao.getListByPage(page);
	}

	@Transactional(readOnly=false)
	@Override
	public int insert(T entity) {
		
		return baseDao.insert(entity);
	}

	@Transactional(readOnly=false)
	@Override
	public int update(T entity) {
		
		return baseDao.update(entity);
	}

	@Transactional(readOnly=false)
	@Override
	public int deleteById(ID id) {
		
		return baseDao.delete(id);
	}

	@Transactional(readOnly=false)
	@Override
	public int deleteBatchByIds(List<T> list) {
		
		return baseDao.deleteBatchById(list);
	}

	@Transactional(readOnly=false)
	@Override
	public int insertBatch(List<T> list) {
		
		return baseDao.insertBatch(list);
	}

	@Transactional(readOnly=false)
	@Override
	public int updateBatchByIds(List<T> list) {
		
		return baseDao.updateBatchById(list);
	}

	
	@Override
	public List<Map<String, Object>> getListMap(Map<String, Object> param) {
		
		return baseDao.getListMap(param);
	}

	
	@Override
	public List<Map<String, Object>> getListMap(T entity) {
		
		return baseDao.getListMap(entity);
	}

	
	@Override
	public int getTotalCount(Page<T> page) {
		
		return baseDao.getTotalCount(page);
	}


	
	
}