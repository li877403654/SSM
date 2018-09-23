package com.design.framework.tree;

import java.util.List;

/**
 * 树形节点
 * 
 * @author JohnDeng
 * @date 2018年8月14日上午11:36:49
 */
public class TreeNode<ID> {

	/**
	 * id
	 */
	private ID id;
	/**
	 * 父id
	 */
	private ID parentId;
	/**
	 * 节点名称
	 */
	private String name;
	/**
	 * 子集
	 */
	private List<TreeNode<ID>> children;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public ID getParentId() {
		return parentId;
	}

	public void setParentId(ID parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeNode<ID>> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode<ID>> children) {
		this.children = children;
	}

}
