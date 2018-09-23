package com.design.framework.tree;

import java.util.List;

/**
 *   树
 * @author JohnDeng
 * @date 2018年8月14日上午11:49:00
 * @param <T>
 */
public interface TreeImpl<T,ID> {

	/**
	 * 获取子列表
	 * 
	 * @param parentId 父id
	 * @param entity  实体
	 * @param list 遍历的数据
	 * @return
	 * @throws Exception
	 * @author John
	 * @datatime 2017年11月23日上午11:20:06
	 */
	public List<T> getChildrenList(ID parentId, T entity, List<T> dateList) throws Exception;

	/**
	 * 获取树
	 * 
	 * @param parentId  父id
	 * @param entity 实体
	 * @param list 
	 * @return
	 * @throws Exception
	 * @author John
	 * @datatime 2017年11月23日上午11:20:24
	 */
	public List<TreeNode<ID>> getTree(ID parentId, T entity, List<T> dateList) throws Exception;

	/**
	 * 实体转换树节点
	 * 
	 * @param entity
	 * @return
	 * @author John
	 * @datatime 2017年11月23日上午11:20:37
	 */
	public TreeNode<ID> entityToTreeNode(TreeNode<ID> node,T entity);
}
