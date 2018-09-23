package com.design.framework.base.service;

import java.util.List;
import java.util.Map;

import com.design.framework.bean.Page;

/**
 * 基类服务
 * 
 * @author JohnDeng 2017年11月9日上午10:31:54
 * @param <T>
 */
public interface BaseService<T, ID> {

	/**
	 * 根据ID查找实体
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:27:37
	 * @param id
	 * @return
	 */
	public T findById(ID id);

	/**
	 * 查询所有数据
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:27:55
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 根据实体参数获取实体
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:28:15
	 * @param entity
	 * @return
	 */
	public T get(T entity);

	/**
	 * 根据实体获取列表
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:28:34
	 * @param entity
	 * @return
	 */
	public List<T> getList(T entity);

	/**
	 * 根据分页获取列表
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:28:59
	 * @param page
	 * @return
	 */
	public List<T> getListByPage(Page<T> page);

	/**
	 * 新增
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:29:15
	 * @param entity
	 * @return
	 */
	public int insert(T entity);

	/**
	 * 修改
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:29:27
	 * @param entity
	 * @return
	 */
	public int update(T entity);

	/**
	 * 根据Id删除
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:29:35
	 * @param id
	 * @return
	 */
	public int deleteById(ID id);

	/**
	 * 批量根据ID删除
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:30:08
	 * @param list
	 * @return
	 */
	public int deleteBatchByIds(List<T> list);

	/**
	 * 批量新增
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:30:23
	 * @param list
	 * @return
	 */
	public int insertBatch(List<T> list);

	/**
	 * 批量根据id删除
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:30:32
	 * @param list
	 * @return
	 */
	public int updateBatchByIds(List<T> list);

	/**
	 * 获取列表
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:30:49
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getListMap(Map<String, Object> param);
	/**
	 * 获取列表
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:30:49
	 * @param entity
	 * @return
	 */
	public List<Map<String, Object>> getListMap(T entity);

	/**
	 * 获取分页条数
	 * @author JohnDeng
	 * @datatime 2018年9月3日下午8:31:26
	 * @param page
	 * @return
	 */
	public int getTotalCount(Page<T> page);
}
