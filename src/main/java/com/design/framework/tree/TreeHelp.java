package com.design.framework.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.design.framework.utils.MyBeanUtils;

/**
 * 树形接口控制器
 * 
 * @author JohnDeng
 * @date 2018年8月14日上午11:34:33
 * @param <T>
 * @param <ID>
 */
public  class TreeHelp<T, ID> implements TreeImpl<T, ID> {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getChildrenList(ID parentId, T entity, List<T> dateList) throws Exception {
		List<T> nowList = null;
		if (CollectionUtils.isNotEmpty(dateList)) {
			nowList = new ArrayList<T>();
			for (T t : dateList) {
				ID id = (ID) MyBeanUtils.getProperty(t, "parentId");
				if (id.equals(parentId)) {
					nowList.add(t);
				}
			}
		}
		return nowList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TreeNode<ID>> getTree(ID parentId, T entity, List<T> dateList) throws Exception {
		List<T> menuList = getChildrenList(parentId, entity, dateList);
		List<TreeNode<ID>> nodeList = new ArrayList<TreeNode<ID>>();
		if (CollectionUtils.isNotEmpty(menuList)) {
			for (T tt : menuList) {
				ID id = (ID) MyBeanUtils.getProperty(tt, "id");
				TreeNode<ID> node = new TreeNode<ID>();
				node=entityToTreeNode(node,tt);
				node.setChildren(getTree(id, entity, dateList));
				nodeList.add(node);
			}
		}
		return nodeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TreeNode<ID> entityToTreeNode(TreeNode<ID> node,T entity) {
		node.setId((ID) MyBeanUtils.getProperty(entity, "id"));
		node.setName((String) MyBeanUtils.getProperty(entity, "name"));
		node.setParentId((ID) MyBeanUtils.getProperty(entity, "parentId"));
		return node;
	}

}
